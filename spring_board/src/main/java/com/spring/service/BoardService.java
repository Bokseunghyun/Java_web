package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {
	//글 등록
	public void register(BoardVO vo) throws Exception;
	//글 목록
	//public List<BoardVO> listall();
	public List<BoardVO> listall(Criteria cri);
	//전체 게시물 수
	public int gettotalCnt(Criteria cri);
	//글 상세 조회
	public BoardVO selectlist(int bno);
	//글 수정
	public boolean modify(BoardVO vo);
	//글 삭제
	public boolean remove(int bno);
}
