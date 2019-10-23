package member.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCUtill {

		public static Connection getConnection() {
			Connection conn = null;
			
			try {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/Oracle");
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			
			}catch(NamingException e) {
				e.printStackTrace();
			}
			return conn;
		}//getConnection 종료
		

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
		
		public static void commit(Connection conn) {
			try {
				 conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		public static void rollback(Connection conn) {
			try {
				 conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
}

