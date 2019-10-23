package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardVO;

public interface BoardMapper {

	public int insertBoard(BoardVO vo);
	public List<BoardVO> selectAll();
	public BoardVO selectOne(int bno);
	public int updateBoard(BoardVO vo);
	public int deleteBoard(int bno);
}
