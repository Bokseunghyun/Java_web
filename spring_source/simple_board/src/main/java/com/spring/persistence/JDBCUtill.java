package com.spring.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtill {

		public static Connection getConnection() {
			Connection conn = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String user="javaDB";
				String password="12345";
				conn = DriverManager.getConnection(url,user,password);
				conn.setAutoCommit(false);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			
			}
			return conn;
		}
		

		public static void close(ResultSet rs) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void close(Connection conn) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void close(PreparedStatement pstm) {
			try {
				pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
