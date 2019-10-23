package com.spring.persistence;

import static com.spring.persistence.JDBCUtill.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.domain.BoardVO;

@Repository //객체생성 요청  @Component도 가능
public class BoardDAO {
	//insert
	public int insertBoard(BoardVO vo) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query="";
		try {
		conn=getConnection();
		query ="insert into spring_board(bno,title,content,writer) values(seq_board.nextVal,?,?,?)";
		pstm=conn.prepareStatement(query);	
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

		
		}finally {
			close(conn);
			close(pstm);
		}
		
		
		return result;
		
	}
	
	//selectAll
	public ArrayList<BoardVO> selectAll(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query="";
		try {
		 conn = getConnection();
		 query = "select * from spring_board order by bno desc";
		 pstm=conn.prepareStatement(query);
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

		}finally {
			close(rs);
			close(pstm);
			close(conn);
		}
		
		
		
		return list;
	}
	//selectOne
	public BoardVO selectOne(int bno) {
		BoardVO vo = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
		conn = getConnection();
		String query="select * from spring_board where bno=?";
		pstm = conn.prepareStatement(query);
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

		}finally {
			close(rs);
			close(pstm);
			close(conn);
		}
		
		return vo; 
	}
	//update
	
	public int board_update(BoardVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
		conn = getConnection();
		String query = "update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
		pstm = conn.prepareStatement(query);
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

		}finally {
			close(pstm);
			close(conn);
		}
		
		return result;
	}
	
	//delete
	public int delete_board(int bno) {
		BoardVO vo = new BoardVO();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = getConnection();
			String query = "delete from spring_board where bno=?";
			pstm.setInt(1, vo.getBno());
			result = pstm.executeUpdate();
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {

		
		}finally {
			close(pstm);
			close(conn);
		}
		
		return result;
	}
	
	
	
}
