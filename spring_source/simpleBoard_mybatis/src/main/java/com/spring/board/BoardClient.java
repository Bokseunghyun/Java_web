package com.spring.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.domain.BoardVO;
import com.spring.mapper.BoardMapper;
import com.spring.service.BoardService;

public class BoardClient {

	public static void main(String[] args) {
		//스프링 컨테이너 시작
		
		//xml 호출 컨테이너
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//스프링 컨테이너로부터 BoardServiceImpl 객체를 Lookup
		BoardService service = (BoardService)factory.getBean("BoardService");
		
		//기능 호출
		
		//글 등록
		BoardVO vo = new BoardVO();
		vo.setTitle("logback");
		vo.setContent("logback 연습");
		vo.setWriter("홍길동");
		service.insertBoard(vo);
		
		//글 수정
//		BoardVO vo = new BoardVO();
//		vo.setTitle("mybatis 수정");
//		vo.setContent("mybatis 내용 수정");
//		vo.setBno(41);
//		mapper.updateBoard(vo);

		
		//전체 목록 가져오기
//		List<BoardVO> list = mapper.selectAll();
//		for(BoardVO vo : list) {
//			System.out.println(vo);
//		}
//		
//		
		//스프링 컨테이너 종료
		factory.close();
		
		
	}

}
