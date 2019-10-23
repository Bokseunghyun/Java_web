package org.web.borddao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.web.borddto.BordDto;
import org.web.dbconnect.DBConnect;

public class BordDao {

	
	private static class Singleton {
		private static final BordDao INSTANCE = new BordDao();
	}

	public static BordDao getInstance() {
		return Singleton.INSTANCE;
	}
	

	public int bordWrite(String userId, String bordPw, String title, String content) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "insert into memberBord( userId,bordPw, title,content) "
			+ " values(?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, bordPw);
			pstm.setString(3, title);
			pstm.setString(4, content);
			result = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstm != null)
					pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return result;

	}

	public ArrayList<BordDto> bordList() {
		ArrayList<BordDto> lists = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		conn = DBConnect.getConnection();
		query = "select * from  memberBord order by no asc ";// no내림 차순
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					int no1 = rs.getInt(1);
					String userId = rs.getString(2);
					String bordPw = rs.getString(3);
					String title = rs.getString(4);
					String content = rs.getString(5);
					BordDto dto = new BordDto(no1, userId, bordPw, title, content);
					lists.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return lists;
	}
	public BordDto getBord(int no) {
		BordDto dto=null;
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String query="";
		conn=DBConnect.getConnection();
		query = "SELECT * FROM memberbord WHERE no=?";
		try {
			pstm=conn.prepareStatement(query);
			pstm.setInt(1,no);
			rs=pstm.executeQuery();
			if(rs!=null) {
			while(rs.next()) {
				dto =new BordDto(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5));
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstm!=null) pstm.close();
				if(rs!=null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
			}
		}				
		return dto;
	}
	public BordDto bordDelete(int no)
	{	BordDto dto = null;
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		String query="";
		conn=DBConnect.getConnection();
		query = "delete FROM memberbord WHERE no=?;";
		try {
			pstm=conn.prepareStatement(query);
			pstm.setInt(1,no);
			rs=pstm.executeQuery();
			if(rs.next())
			{
				rs.getInt(1);
			}
		} catch (Exception e) {
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstm!=null) pstm.close();
				if(rs!=null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			}
		
	}
		return dto;
}
	public int bordUpdate(int no,String userId, String bordPw, String title, String content) {
		int rs=0;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		String query="";
		conn = DBConnect.getConnection();
		query = "update memberbord set bordPw=?,title=?,content=? where no=? and userId=?";
		
		try {
			pstm=conn.prepareStatement(query);
			pstm.setString(1, bordPw);
			pstm.setString(2, title);
			pstm.setString(3, content);
			pstm.setInt(4, no);
			pstm.setString(5, userId);
			
			rs=pstm.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstm!=null) pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			}
		}				
		return rs;
	}


	public int bordDelete(int no, String userId, String bordPw) {
		int rs=0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query="";
		conn = DBConnect.getConnection();
		query = "delete from memberbord where no=? and userId=? and bordPw=?";
		
		try {
			pstm=conn.prepareStatement(query);
			pstm.setInt(1, no);
			pstm.setString(2, userId);
			pstm.setString(3, bordPw);
			rs=pstm.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstm!=null) pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			}
		}				
		return rs;
	}	
	
}
