package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {
	//글 등록
	public void inesrt_board(BoardVO vo);
	//글 수정
	public boolean modify_board(BoardVO vo);
	//글 삭제
	public boolean delete_board(int bno);
	//글 목록
	public List<BoardVO> listall(Criteria cri);
	//전체 게시물 수
	public int totalCnt(Criteria cri);
	//게시글 상세보기
	public BoardVO selectlist(int bno);
}
