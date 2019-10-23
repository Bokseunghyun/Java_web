package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BookVO;
import com.spring.persistence.BookDAO;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO dao;
	
	@Override
	public int insertBook(BookVO vo) {		
		return dao.insertBook(vo);
	}

	@Override
	public int updateBook(BookVO vo) {		
		return dao.updateBook(vo);
	}

	@Override
	public int deleteBook(String code) {		
		return dao.deleteBook(code);
	}

	@Override
	public ArrayList<BookVO> getBookList() {		
		return dao.getBookList();
	}

	@Override
	public BookVO getBook(String code) {		
		return dao.getBook(code);
	}

	@Override
	public ArrayList<BookVO> getBookSearchList(String criteria, String keyword) {
		return dao.getBookSearchList(criteria, keyword);
	}

}
