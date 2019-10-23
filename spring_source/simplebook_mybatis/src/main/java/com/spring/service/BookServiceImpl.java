package com.spring.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BookVO;
import com.spring.mapper.BookMapper;

@Service("BookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;
	
	@Override
	public int insertBook(BookVO vo) {		
		return mapper.insertBook(vo);
	}

	@Override
	public int updateBook(BookVO vo) {		
		return mapper.updateBook(vo);
	}

	@Override
	public int deleteBook(String code) {		
		return mapper.deleteBook(code);
	}

	@Override
	public List<BookVO> getBookList() {		
		return mapper.getBookList();
	}

	@Override
	public BookVO getBook(String code) {		
		return mapper.getBook(code);
	}


	@Override
	public List<BookVO> getBookSearchList(HashMap<String, String> map) {
		return mapper.getBookSearchList(map);
	}

}
