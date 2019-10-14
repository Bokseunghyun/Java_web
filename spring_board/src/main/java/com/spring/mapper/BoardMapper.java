package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardMapper {
	public void insertSelectKey(BoardVO vo);
	//public List<BoardVO> listall();
	public List<BoardVO> listall(Criteria cri);
	public int totalCnt(Criteria cri);
	public BoardVO selectlist(int bno);
	public int update(BoardVO vo);
	public int delete(int bno);
	
	//댓글 수 카운트 수
	public void updateReplyCnt(@Param("bno")int bno,@Param("amount")int amount);
}
