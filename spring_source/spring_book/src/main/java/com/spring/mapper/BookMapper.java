package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.bookVO;

public interface BookMapper {
	public int book_insert(bookVO vo);
	public List<bookVO> getlist();
	public int book_delete(String code);
	public int book_update(@Param("code")String code,@Param("price")int price);
	public List<bookVO> book_search(@Param("criteria")String criteria, @Param("keyword")String keyword);
}
