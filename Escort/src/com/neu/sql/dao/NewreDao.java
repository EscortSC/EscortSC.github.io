package com.neu.sql.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neu.sql.utils.JdbcUtil;

public class NewreDao {
	

	public void saveNewre(String projectName) {
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql0 = "SELECT COUNT(DISTINCT(evolutionpath)) from "+ projectName +"sysinfo";
		
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);// ¿ªÆôÊÂÎñ
			int maxevolutionpath = ((Long) runner.query(con, sql0, new ScalarHandler<Long>())).intValue();
			String sql1 = "insert into " + projectName
					+ "newre select versionnumber,classname,null,methodname,packagename,null,null,null"
					+ ",majornum,null,null from " + projectName + "sysinfo where evolutionpath = (SELECT MAX(evolutionpath)"
					+ " FROM " + projectName + "sysinfo)";
			runner.update(con,sql1);
			System.out.println(maxevolutionpath);
			String sql2 = "update " + projectName + "newre a set a.changestate =  ifnull((select b.changestate from "
					+ projectName + "variation b "
					+ "where a.classname = b.classname and b.evolutionpath = (select max(c.evolutionpath) from "
					+ projectName + "variation " + "c where a.classname = c.classname)),3), a.frequency = ifnull((select "
					+ maxevolutionpath + "-b.evolutionpath+1 from " + "" + projectName
					+ "variation b where a.classname = b.classname and b.evolutionpath = (select max(c.evolutionpath)"
					+ " from " + projectName + "variation c where a.classname = c.classname) )," + maxevolutionpath + "), "
					+ "a.weight = ifnull((select (" + maxevolutionpath + "-b.evolutionpath+1) from " + projectName
					+ "variation b where a.classname = b.classname and "
					+ "b.evolutionpath = (select max(c.evolutionpath) from " + projectName
					+ "variation c where a.classname = c.classname))," + maxevolutionpath + ")/" + maxevolutionpath + ", "
					+ "a. changeversion =ifnull((select b.versionnumber from " + projectName
					+ "variation b where a.classname = b.classname "
					+ "and b.evolutionpath = (select max(c.evolutionpath) from " + projectName
					+ "variation c where a.classname = c.classname)),'stable'), a.evolutionpath =  "
					+ "ifnull((select max(c.evolutionpath) from " + projectName + "variation c where a.classname = c.classname),0) " + "where a.changestate is null ;";
			runner.update(con,sql2);
			System.out.println("sql2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
