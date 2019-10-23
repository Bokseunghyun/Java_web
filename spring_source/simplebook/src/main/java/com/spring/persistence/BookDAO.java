package com.spring.persistence;

import static com.spring.persistence.JDBCUtil.close;
import static com.spring.persistence.JDBCUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.domain.BookVO;

@Repository
public class BookDAO {
	
	public int insertBook(BookVO vo) {
		//code(4자리),title,writer,price
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="insert into bookTBL(code,title,writer,price) " + 
					" values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getCode());			
			pstmt.setString(2, vo.getTitle());			
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getPrice());
			result=pstmt.executeUpdate();
			if(result>0) {
				con.commit();
			}else {
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
	
	public int updateBook(BookVO vo) {
		//code가 일치하면 가격 수정
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="update bookTBL set price=? "
					+ " where code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPrice());
			pstmt.setString(2, vo.getCode());
			result=pstmt.executeUpdate();
			if(result>0) {
				con.commit();
			}else {
				con.rollback();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return result;
	}
	
	public int deleteBook(String code) {
		//code가 일치하면 도서 삭제
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="delete from bookTBL where code=?";
			pstmt=con.prepareStatement(sql);			
			pstmt.setString(1, code);
			result=pstmt.executeUpdate();
			if(result>0) {
				con.commit();
			}else {
				con.rollback();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return result;
	}
	
	public ArrayList<BookVO> getBookList(){
		//도서 전체 목록 가져오기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BookVO> list=new ArrayList<BookVO>();		
		try {
			con=getConnection();
			String sql="select * from bookTBL order by code desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo=new BookVO();
				vo.setCode(rs.getString(1));
				vo.setTitle(rs.getString(2));				
				vo.setWriter(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		
		return list;
	}
	public BookVO getBook(String code){
		//코드가 일치한 도서 목록 가져오기
		BookVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="select * from bookTBL where code=?";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BookVO();
				vo.setCode(rs.getString(1));
				vo.setTitle(rs.getString(2));				
				vo.setWriter(rs.getString(3));
				vo.setPrice(rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return vo;
	}
	
	public ArrayList<BookVO> getBookSearchList
			(String criteria,String keyword){
		//criteria(검색조건) => code, title
		// keyword(검색어) => 1001  or 자바
		// 검색조건과 검색어에 맞는 도서목록 가져오기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BookVO> list=new ArrayList<BookVO>();		
		try {
			con=getConnection();
			if(criteria.equals("code")) {
				String sql="select * from bookTBL where code = ?";
				pstmt=con.prepareStatement(sql);					
			}else {				
				String sql="select * from bookTBL where title like '%'||?||'%'";
				pstmt=con.prepareStatement(sql);
			}
			pstmt.setString(1,keyword);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo=new BookVO();
				vo.setCode(rs.getString(1));
				vo.setTitle(rs.getString(2));				
				vo.setWriter(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		
		return list;
	}	
}













