package com.spring.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	//Connection,close
	public static Connection getConnection() {
		Connection con=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="javaDB";
			String password="12345";
			con=DriverManager.getConnection(url,user,password);
			con.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}	
		return con;
	}//getConnection 종료
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}//rs.close()
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}//con.close()
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}//pstmt.close()
}










