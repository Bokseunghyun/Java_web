package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

public interface ReplyMapper {

	//댓글 작성
	public int insert_reply(ReplyVO vo);
	//댓글 수정
	public int modify_reply(ReplyVO vo);
	//댓글 삭제
	public int delete_reply(int rno);
	
	//댓글 하나 상세보기
	public ReplyVO select_reply(int rno);
	//댓글 목록
	public List<ReplyVO> getList(@Param("cri")Criteria cri,@Param("bno")int bno);
	
	//게시글에 있는 댓글 갯수 카운트
	public int getCountByBno(int bno);
}
