package com.spring.book;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.domain.BookVO;
import com.spring.service.BookService;

public class BookClient {
	public static void main(String[] args) {
		//스프링 컨테이너 시작
		AbstractApplicationContext factory=
				new GenericXmlApplicationContext("applicationContext.xml");
		//스프링 컨테이너로부터 BoardServiceImpl 객체를 Lookup
		BookService service=(BookService)factory.getBean("BookService");
		
//		BookVO vo=new BookVO();
//		vo.setCode("1005");
//		vo.setTitle("스프링 기초");		
//		vo.setWriter("홍길동");
//		vo.setPrice(15000);
//		service.insertBook(vo);
		
//		BookVO vo = new BookVO();
//		vo.setCode("1003");
//		vo.setPrice(20000);
//		service.updateBook(vo);
		
		
		//검색
		System.out.println("검색결과");
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("criteria", "title");
		map.put("keyword", "스프링");
		List<BookVO> list=service.getBookSearchList(map);
		
		for(BookVO vo1:list) {
			System.out.println(vo1);
		}
		
		System.out.println();
		list=service.getBookList();
		
		for(BookVO vo1:list) {
			System.out.println(vo1);
		}
		
		//스프링 컨테이너 종료
		factory.close();		
	}
}
