package com.neu.sql.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
 
public class JdbcUtil {
	// 数据源
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}
 
	// 获取数据源
	public static DataSource getDataSource() {
		return dataSource;
	}
 
	// 获取数据库连接对象
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
 
	// 释放资源
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * if (stmt != null) { try { stmt.close(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } if (rs != null) { try {
		 * rs.close(); } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
	}
 
}