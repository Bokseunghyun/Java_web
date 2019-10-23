package org.web.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect2 {

	public static Connection getConnection() {
		
		Connection conn=null;
		
		String url="jdbc:mysql://localhost:3306/javaStudy?useSSL=false&serverTimezone=UTC";
		String user="root";
		String password="12345";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//드라이버 로드
			System.out.println("드라이버OK");

			conn=DriverManager.getConnection(url, user, password);//DB연결
			System.out.println("DB연결 OK");			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return conn;
	}
}
