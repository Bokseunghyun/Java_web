package com.spring.service;

import java.util.ArrayList;

import com.spring.domain.BookVO;

public interface BookService {
	//CRUD
	public int insertBook(BookVO vo);
	public int updateBook(BookVO vo);
	public int deleteBook(String code);
	public ArrayList<BookVO> getBookList();
	public BookVO getBook(String code);
	public ArrayList<BookVO> 
	   getBookSearchList(String criteria, String keyword);
}
