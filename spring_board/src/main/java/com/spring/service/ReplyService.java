package com.spring.service;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageDTO;
import com.spring.domain.ReplyVO;

public interface ReplyService {
	public boolean register(ReplyVO vo) throws Exception;
	public ReplyVO read(int rno) throws Exception;
	public ReplyPageDTO getList(Criteria cri,int bno) throws Exception;
	public boolean modify(ReplyVO vo) throws Exception;
	public boolean delete(int rno) throws Exception;

}
