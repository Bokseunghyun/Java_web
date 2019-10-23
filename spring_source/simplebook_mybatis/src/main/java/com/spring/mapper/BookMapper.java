package com.spring.mapper;

import java.util.HashMap;
import java.util.List;

import com.spring.domain.BookVO;

public interface BookMapper {
	
	public int insertBook(BookVO vo);
	public int updateBook(BookVO vo);
	public int deleteBook(String code);
	public List<BookVO> getBookList();
	public BookVO getBook(String code);
	public List<BookVO> 
	getBookSearchList(HashMap<String, String> map);
}
