package org.web.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnect {

	public static Connection getConnection() {
		
		Connection conn=null;
		
		DataSource dataSource;
		
		try {
			
			Context context=new InitialContext();
			dataSource=(DataSource)context.lookup("java:comp/env/jdbc/mysqlDB");
			
			conn=dataSource.getConnection();
			
			System.out.println("DBCP¿¬°á OK");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return conn;
	}
}
