package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardAttachMapper;
import com.spring.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardMapper mapper;
	@Inject
	private BoardAttachMapper attachMapper;
	@Override
	public void register(BoardVO vo) throws Exception {
		mapper.insertSelectKey(vo);
		
		//파일 첨부 후 추가
		if(vo.getAttachList()==null || vo.getAttachList().size()<=0) {
			return;
		}
		vo.getAttachList().forEach(attach->{
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
	}

//	@Override
//	public List<BoardVO> listall() {
//		return mapper.listall();
//	}

	@Override
	public BoardVO selectlist(int bno) {

		return mapper.selectlist(bno);
	}

	@Override
	public boolean modify(BoardVO vo) {
		return mapper.update(vo)==1?true:false;
		
	}

	@Override
	public boolean remove(int bno) {
		return mapper.delete(bno)==1?true:false;
	}

	@Override
	public List<BoardVO> listall(Criteria cri) {

		return mapper.listall(cri);
	}


	@Override
	public int gettotalCnt(Criteria cri) {

		return mapper.totalCnt(cri);
	}
	
}
