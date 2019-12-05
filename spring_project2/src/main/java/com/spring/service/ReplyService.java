package com.spring.service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageDTO;
import com.spring.domain.ReplyVO;

public interface ReplyService {
	
	//댓글 작성
	public boolean insert_reply(ReplyVO vo);
	//댓글 수정
	public boolean modify_reply(ReplyVO vo);
	//댓글 삭제
	public boolean delete_reply(int rno);
	//댓글 하나 상세보기
	public ReplyVO select_reply(int rno);
	//댓글 목록
	public ReplyPageDTO getList(Criteria cri, int bno);
	
}
