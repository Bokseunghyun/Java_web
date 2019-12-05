package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardMapper;

import lombok.Setter;

@Service("boardService")
public class BoardServiceimpl implements BoardService{
	
	@Inject
	private BoardMapper mapper; 
	
	@Override
	public void inesrt_board(BoardVO vo) {
		//게시글 작성
		mapper.insert_board(vo);
	}

	@Override
	public boolean modify_board(BoardVO vo) {

		return mapper.modify_board(vo)==1?true:false;
	}

	@Override
	public boolean delete_board(int bno) {

		return mapper.delete_board(bno)==1?true:false;
	}

	@Override
	public List<BoardVO> listall(Criteria cri) {

		return mapper.listall(cri);
	}

	@Override
	public int totalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public BoardVO selectlist(int bno) {

		return mapper.selectlist(bno);
	}

}
