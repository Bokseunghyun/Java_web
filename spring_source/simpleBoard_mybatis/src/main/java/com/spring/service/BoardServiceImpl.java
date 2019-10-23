package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BoardVO;
import com.spring.mapper.BoardMapper;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Autowired // repository로 객체 생성 하고 autowired로 주입
	private BoardMapper mapper;
	
	@Override
	public int insertBoard(BoardVO vo) {
		
		return mapper.insertBoard(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		
		return mapper.updateBoard(vo);
	}

	@Override
	public int deleteBoard(int bno) {
		return mapper.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public BoardVO selectOne(int bno) {
		return mapper.selectOne(bno);
	}

}
