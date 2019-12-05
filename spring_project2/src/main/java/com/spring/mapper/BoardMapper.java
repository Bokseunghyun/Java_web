package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardMapper {
	//게시글 작성
	public void insert_board(BoardVO vo);
	//게시글 수정
	public int modify_board(BoardVO vo);
	//게시글 삭제
	public int delete_board(int bno);
	//게시글 목록
	public List<BoardVO> listall(Criteria cri);
	//게시글 수
	public int totalCnt(Criteria cri);
	//게시글 하나 상세보기
	public BoardVO selectlist(int bno);
	
	//댓글 수 카운트
	public void replyCnt(@Param("bno")int bno,@Param("amount")int amount);
}
