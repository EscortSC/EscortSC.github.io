package com.neu.sql.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
 
public class JdbcUtil {
	// ����Դ
	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}
 
	// ��ȡ����Դ
	public static DataSource getDataSource() {
		return dataSource;
	}
 
	// ��ȡ���ݿ����Ӷ���
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
 
	// �ͷ���Դ
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