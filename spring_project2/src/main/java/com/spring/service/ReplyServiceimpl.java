package com.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageDTO;
import com.spring.domain.ReplyVO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;

@Service("service")
public class ReplyServiceimpl implements ReplyService{
	
	@Inject
	private ReplyMapper mapper;
	@Inject
	private BoardMapper boardMapper;
	
	@Transactional 
	@Override
	public boolean insert_reply(ReplyVO vo) {
		boardMapper.replyCnt(vo.getBno(), 1);
		return mapper.insert_reply(vo)==1?true:false;
	}

	@Override
	public boolean modify_reply(ReplyVO vo) {
		
		return mapper.modify_reply(vo)==1?true:false;
	}

	@Override
	public boolean delete_reply(int rno) {
		ReplyVO vo = mapper.select_reply(rno);
		boardMapper.replyCnt(vo.getBno(), -1);
		return mapper.delete_reply(rno)==1?true:false;
	}

	@Override
	public ReplyVO select_reply(int rno) {

		return mapper.select_reply(rno);
	}

	@Override
	public ReplyPageDTO getList(Criteria cri,int bno) {

		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getList(cri, bno));
	}

}
