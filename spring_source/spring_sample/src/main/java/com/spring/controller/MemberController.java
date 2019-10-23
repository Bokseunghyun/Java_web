package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	//@RequestMapping(value="/login",method= RequestMethod.GET)
	@GetMapping
	public void login(int age) {
		log.info("login 컨트롤러 동작"+age);
		//http://localhost:8083/login => WEB-INF\views\login.jsp
	}
	
	//login.jsp에서 post 넘어오는 값 출력하는 컨트롤러 작성
	@PostMapping("/login")
	public String login1(MemberVO vo) {
		log.info("login 폼 내용");
		log.info("id = "+vo.getUserid());
		log.info("id = "+vo.getUserpw());

		return "login";
	
	}
	
	@RequestMapping("/logout")
	public void logout() {
		log.info("logout 컨트롤러 동작");
	}
}
