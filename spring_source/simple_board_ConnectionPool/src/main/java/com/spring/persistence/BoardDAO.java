package com.spring.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.BoardVO;

@Repository //객체생성 요청  @Component도 가능
public class BoardDAO {
	
	@Autowired
	private DataSource ds;
	
	//insert
	public int insertBoard(BoardVO vo) {
		int result=0;
		String query="";
		query ="insert into spring_board(bno,title,content,writer) values(seq_board.nextVal,?,?,?)";
		try(Connection conn=ds.getConnection(); PreparedStatement pstm=conn.prepareStatement(query);) {
			
		pstm.setString(1, vo.getTitle());
		pstm.setString(2, vo.getContent());
		pstm.setString(3, vo.getWriter());
		result=pstm.executeUpdate();
		
		if(result>0) {
			conn.commit(); //잘됐으면 처리
		}else {
			conn.rollback(); //잘못됐으면 취소
		}
		
		}
		catch (Exception e) {

		
		}
		
		
		return result;
		
	}
	
	//selectAll
	public ArrayList<BoardVO> selectAll(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		ResultSet rs = null;
		String query="";
		query = "select * from spring_board order by bno desc";
		try(Connection conn=ds.getConnection(); PreparedStatement pstm=conn.prepareStatement(query);) {
		 rs=pstm.executeQuery();
		 
		 while(rs.next()) {
			 BoardVO vo = new BoardVO();
			 vo.setBno(rs.getInt(1));
			 vo.setTitle(rs.getString(2));
			 vo.setContent(rs.getString(3));
			 vo.setWriter(rs.getString(4));
			 vo.setReqdate(rs.getDate(5));
			 list.add(vo);
		 }
		} catch (Exception e) {

		}
		
		
		return list;
	}
	//selectOne
	public BoardVO selectOne(int bno) {
		BoardVO vo = null;
		ResultSet rs = null;
		
		String query="select * from spring_board where bno=?";
		try(Connection conn=ds.getConnection(); PreparedStatement pstm=conn.prepareStatement(query);) {
		pstm.setInt(1, bno);
		rs = pstm.executeQuery();
		
		if(rs.next()) {
			vo = new BoardVO();
			vo.setBno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setContent(rs.getString(3));
			vo.setWriter(rs.getString(4));
			vo.setReqdate(rs.getDate(5));
		}
		} catch (Exception e) {

		}
		
		return vo; 
	}
	//update
	
	public int board_update(BoardVO vo) {
		int result = 0;
		
		String query = "update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
		try(Connection conn=ds.getConnection(); PreparedStatement pstm=conn.prepareStatement(query);) {
		pstm.setString(1, vo.getTitle());
		pstm.setString(2, vo.getContent());
		pstm.setInt(3, vo.getBno());
		result = pstm.executeUpdate();
		
		if(result>0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		} catch (Exception e) {

		}
		
		return result;
	}
	
	//delete
	public int delete_board(int bno) {
		BoardVO vo = new BoardVO();
		int result = 0;
		String query = "delete from spring_board where bno=?";
		try(Connection conn=ds.getConnection(); PreparedStatement pstm=conn.prepareStatement(query);) {
			pstm.setInt(1, vo.getBno());
			result = pstm.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {

		
		}
		
		return result;
	}
	
	
	
}
