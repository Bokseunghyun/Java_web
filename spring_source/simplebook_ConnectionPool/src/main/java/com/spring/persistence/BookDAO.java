package com.spring.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.BookVO;

@Repository
public class BookDAO {
	@Autowired
	private DataSource ds;
	
	public int insertBook(BookVO vo) {
		//code(4자리),title,writer,price
		int result=0;
		
		String sql="insert into bookTBL(code,title,writer,price) " + 
				" values(?,?,?,?)";
		try(Connection con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);) {
			
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
		}
		
		return result;
	}
	
	public int updateBook(BookVO vo) {
		//code가 일치하면 가격 수정
		int result=0;
		String sql="update bookTBL set price=? "
				+ " where code=?";
		try(Connection con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);) {
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
		}
		return result;
	}
	
	public int deleteBook(String code) {
		//code가 일치하면 도서 삭제
		int result=0;
		String sql="delete from bookTBL where code=?";
		try(Connection con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);) {
			pstmt.setString(1, code);
			result=pstmt.executeUpdate();
			if(result>0) {
				con.commit();
			}else {
				con.rollback();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public ArrayList<BookVO> getBookList(){
		//도서 전체 목록 가져오기
		ResultSet rs=null;
		String sql="select * from bookTBL order by code desc";
		ArrayList<BookVO> list=new ArrayList<BookVO>();		
		try(Connection con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);) {
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
		}
		
		return list;
	}
	public BookVO getBook(String code){
		//코드가 일치한 도서 목록 가져오기
		BookVO vo=null;
		ResultSet rs=null;
		String sql="select * from bookTBL where code=?";
		try(Connection con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);) {
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
		}
		return vo;
	}
	
	public ArrayList<BookVO> getBookSearchList
			(String criteria,String keyword){
		//criteria(검색조건) => code, title
		// keyword(검색어) => 1001  or 자바
		// 검색조건과 검색어에 맞는 도서목록 가져오기
		ResultSet rs=null;
		ArrayList<BookVO> list=new ArrayList<BookVO>();
		String sql="";
		if(criteria.equals("code")) {
			sql="select * from bookTBL where code = ?";
		}else {				
			sql="select * from bookTBL where title like '%'||?||'%'";
		}
		try(Connection con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);) {
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
		} 
		
		return list;
	}	
}













