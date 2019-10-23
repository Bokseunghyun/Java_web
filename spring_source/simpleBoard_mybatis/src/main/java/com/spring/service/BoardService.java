package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;

public interface BoardService {

	int insertBoard(BoardVO vo);
	int updateBoard(BoardVO vo);
	int deleteBoard(int bno);
	BoardVO selectOne(int bno);
	List<BoardVO> selectAll();
}
