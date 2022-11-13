package com.neu.sql.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;

import com.neu.sql.model.Newre;
import com.neu.sql.utils.JdbcUtil;

public class VariationDao {

	/**
	 * 标记重命名和拆包的类
	 */
	@Test
	public void selectMoveAfterPackageNum(String projectName) {
		Connection connection = null;
		try {
			connection = JdbcUtil.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			connection.setAutoCommit(false);// 开启事务
			String sql = "SELECT COUNT(DISTINCT(evolutionpath)) from " + projectName + "sysinfo";
			int maxevolutionpath = ((Long) queryRunner.query(connection, sql, new ScalarHandler<Long>())).intValue();
			String sql00 = "select * from " + projectName + "newre where changestate = '0';";
			List<Newre> activeNewres = queryRunner.query(connection, sql00, new BeanListHandler<Newre>(Newre.class));

			for (Newre obj : activeNewres) {

				String sql01 = "select count(*) from " + projectName + "variation "
						+ "where evolutionpath=? and packagename=?;";// 计算移动后的类所在包中所有类的数量（邻居节点）
				Object params01[] = { obj.getEvolutionpath(), obj.getPackagename() };
				int moveAfterPackageNum = (queryRunner.query(connection, sql01, new ScalarHandler<Long>(), params01))
						.intValue();
				String sql02 = "select count(*) from " + projectName + "sysinfo a where a.packagename ="
						+ "(select b.packagename  from " + projectName + "variation b where b.evolutionpath=? and"
						+ " b.compareversion=CONCAT(?,'-',?) and b.methodname=(select c.methodname from " + projectName
						+ "variation c " + "where c.evolutionpath=? and c.classname=?)) and evolutionpath =?;";// 计算移动前的类所在包中所有类的数量
				Object params02[] = { obj.getEvolutionpath() - 1, obj.getEvolutionpath() - 1, obj.getEvolutionpath(),
						obj.getEvolutionpath(), obj.getClassname(), obj.getEvolutionpath() - 1 };
				int moveBeforePackageNum = (queryRunner.query(connection, sql02, new ScalarHandler<Long>(), params02))
						.intValue();
				System.out.println(moveBeforePackageNum + " " + moveAfterPackageNum + " " + obj.getClassname() + " "
						+ obj.getEvolutionpath());
				if (moveBeforePackageNum == moveAfterPackageNum) {
					// System.out.println(moveBeforePackageNum+" "+moveAfterPackageNum);

					String sql03 = "update " + projectName + "newre a set a.changestate ='1' ,"
							+ "a.frequency = (select count(distinct b.evolutionpath)+? from " + projectName
							+ "sysinfo b where b.classname" + " =(select c.classname  from " + projectName
							+ "variation c where c.evolutionpath=? and "
							+ "c.compareversion=CONCAT(?,'-',?) and c.methodname=(select c.methodname from "
							+ projectName + "variation c "
							+ "where c.evolutionpath=? and c.classname=?)) and b.evolutionpath<?) ,"
							+ "a.weight = (select count(distinct b.evolutionpath)+? from " + projectName + "sysinfo b "
							+ "where b.classname =(select c.classname  from " + projectName + "variation c "
							+ "where c.evolutionpath=? and c.compareversion=CONCAT(?,'-',?) and "
							+ "c.methodname=(select c.methodname from " + projectName
							+ "variation c where c.evolutionpath=? and c.classname=?)) " + "and b.evolutionpath<?)/"
							+ maxevolutionpath + " where a.classname = ? and a.methodname=?;";// 更新包为重新命名的状态
					Object params03[] = { obj.getFrequency(), obj.getEvolutionpath() - 1, obj.getEvolutionpath() - 1,
							obj.getEvolutionpath(), obj.getEvolutionpath(), obj.getClassname(), obj.getEvolutionpath(),
							obj.getFrequency(), obj.getEvolutionpath() - 1, obj.getEvolutionpath() - 1,
							obj.getEvolutionpath(), obj.getEvolutionpath(), obj.getClassname(), obj.getEvolutionpath(),
							obj.getClassname(), obj.getMethodname() };
					queryRunner.update(connection, sql03, params03);
					System.out.println("rename 4" + " " + obj.getClassname());

				} else if (moveAfterPackageNum < moveBeforePackageNum
						&& moveAfterPackageNum >= moveBeforePackageNum / 2) {

					String sql04 = "update " + projectName + "newre a set a.changestate ='1' ,"
							+ "a.frequency = (select count(distinct b.evolutionpath)+? from " + projectName
							+ "sysinfo b where b.classname" + " =(select c.classname  from " + projectName
							+ "variation c where c.evolutionpath=? and "
							+ "c.compareversion=CONCAT(?,'-',?) and c.methodname=(select c.methodname from "
							+ projectName + "variation c "
							+ "where c.evolutionpath=? and c.classname=?)) and b.evolutionpath<?) ,"
							+ "a.weight = (select count(distinct b.evolutionpath)+? from " + projectName + "sysinfo b "
							+ "where b.classname =(select c.classname  from " + projectName + "variation c "
							+ "where c.evolutionpath=? and c.compareversion=CONCAT(?,'-',?) and "
							+ "c.methodname=(select c.methodname from " + projectName
							+ "variation c where c.evolutionpath=? and c.classname=?)) " + "and b.evolutionpath<?)/"
							+ maxevolutionpath + " where a.classname = ? and a.methodname=?;";// 更新包为重新命名的状态
					Object params04[] = { obj.getFrequency(), obj.getEvolutionpath() - 1, obj.getEvolutionpath() - 1,
							obj.getEvolutionpath(), obj.getEvolutionpath(), obj.getClassname(), obj.getEvolutionpath(),
							obj.getFrequency(), obj.getEvolutionpath() - 1, obj.getEvolutionpath() - 1,
							obj.getEvolutionpath(), obj.getEvolutionpath(), obj.getClassname(), obj.getEvolutionpath(),
							obj.getClassname(), obj.getMethodname() };
					queryRunner.update(connection, sql04, params04);
					System.out.println("split 4" + " " + obj.getClassname());

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void updateMovebefore(String projectName) {
		Connection connection = null;
		try {
			connection = JdbcUtil.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			connection.setAutoCommit(false);// 开启事务
			String sql05 = "UPDATE " + projectName + "newre SET changestate ='3' WHERE weight = 1";
			queryRunner.update(connection, sql05);
			String sql06 = "select * from " + projectName + "newre where changestate = '0';";
			List<Newre> Newres = queryRunner.query(connection, sql06, new BeanListHandler<Newre>(Newre.class));
			for (Newre nr : Newres) {
				String sql07 = "UPDATE " + projectName + "newre a  SET a.movebefore = (SELECT d.classname " + "from "
						+ projectName + "variation d where d.evolutionpath=? and "
						+ "d.compareversion=CONCAT(?,'-',?) and d.methodname=(select c.methodname from " + projectName
						+ "variation c where"
						+ " c.evolutionpath=? and c.classname=?) and d.evolutionpath<?) where a.classname =?";
				Object params07[] = { nr.getEvolutionpath() - 1, nr.getEvolutionpath() - 1,
						nr.getEvolutionpath(), nr.getEvolutionpath(), nr.getClassname(), nr.getEvolutionpath(),
						nr.getClassname() };
				queryRunner.update(connection, sql07, params07);
			}
			String sql07 ="UPDATE " + projectName + "newre SET movebefore =classname WHERE changestate in ('1','3')";
			queryRunner.update(connection, sql07);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}finally {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
