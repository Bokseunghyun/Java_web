package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.bookVO;
import com.spring.mapper.BookMapper;

@Service("bookService")
public class BookServiceImpl implements BookService{
	@Inject
	private BookMapper mapper;
	
	@Override
	public int book_insert(bookVO vo) {
		return mapper.book_insert(vo);
	}

	@Override
	public List<bookVO> getlist() {
		return mapper.getlist();
	}

	@Override
	public int book_delete(String code) {
		return mapper.book_delete(code);
	}


	@Override
	public int book_update(String code, int price) {
		return mapper.book_update(code, price);
	}

	@Override
	public List<bookVO> book_search(String criteria, String keyword) {
		return mapper.book_search(criteria, keyword);
	}

	

}
