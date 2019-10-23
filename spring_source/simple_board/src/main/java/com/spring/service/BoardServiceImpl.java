package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardVO;
import com.spring.persistence.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired // repository로 객체 생성 하고 autowired로 주입
	private BoardDAO dao;
	
	@Override
	public int insertBoard(BoardVO vo) {
		
		return dao.insertBoard(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		
		return dao.board_update(vo);
	}

	@Override
	public int deleteBoard(int bno) {
		return dao.delete_board(bno);
	}

	@Override
	public BoardVO getBoard(int bno) {
		return dao.selectOne(bno);
	}

	@Override
	public ArrayList<BoardVO> getBoardList() {
		return dao.selectAll();
	}

}
