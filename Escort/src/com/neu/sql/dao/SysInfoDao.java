package com.neu.sql.dao;

import com.neu.sql.model.SysInfo;
import com.neu.sql.utils.JdbcUtil;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class SysInfoDao {

	private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	public void saveSysInfo(SysInfo sysInfo,String projectName) {
		try {
			runner.update("replace into "+projectName+"sysinfo (versionnumber,classname,methodname,packagename,"
					+ "evolutionpath,majornum,projectname) VALUES (?,?,?,?,?,?,?)",sysInfo.getVersionnnumber(),
					sysInfo.getClassname(),sysInfo.getMethodname(),sysInfo.getPackagename(),sysInfo.getEvolutionpath(),
					sysInfo.getMajornum(),sysInfo.getProjectname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
