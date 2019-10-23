package com.spring.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spring.domain.MemberVO;
import com.spring.service.RegistMember;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {
	@Inject
	private RegistMember service;
	// localhost:8083/step1 요청시 응답하는 컨트롤러 생성
	// step1() => step1.jsp 보여주는 컨트롤러 작성하기
	
	@GetMapping("/index")
	public void index() {
		log.info("index");
		
	}
	
	
	@GetMapping("/step1")
	public void step1() {
		log.info("약관동의 여부 확인");
		
	}
	
	// localhost:8083/step2 요청시 응답하는 컨트롤러 생성
	@PostMapping("/step2")
	public String step2(@RequestParam(value="agree",defaultValue="false")boolean agree, RedirectAttributes rttr) {
		log.info("약관 동의 확인 "+agree);
		
		if(!agree) {
			//step1으로 되돌려보내기
			//세션에 값을 임시로 담아놓게 됨
			rttr.addFlashAttribute("result",false);
			return "redirect:step1";
		}else {
			//step2.jsp 보여주기
			return "step2";
		}
	}
	
	@PostMapping("/step3")
	//ModelAttribute => MemberVO의 이름을 member로 재지정 jquery사용시 member로 호출가능
	public String step3(@ModelAttribute("member")MemberVO vo) { 
		log.info("회원가입 데이터 가져오기"+vo);
		//password와 confirm_password가 같지않으면 step2로 되돌려보내기
		if(!vo.isPasswordEqualToConfirmPassword()) {
			return "step2";
		}
		return "step3";
	}
	
	// /step2, /step3 를 바로  요청하는 경우에 응답 컨트롤러 
		@GetMapping(value= {"/step2","/step3"})
		public String handleStep2() {
			log.info("/step2 or /step3 get 요청..");
			return "redirect:/step1";
		}
	
	@PostMapping("/checkId")
	@ResponseBody //리턴하는 값이 실제 보내는 값을 의미(JSP 페이지를 찾지 않음)
	public String checkId(String userid) {
		log.info("중복아이디 확인"+userid);
		//return false => 중복아이디라고 인식
		if(service.dupId(userid)) {
			return "true";
		}else {
			return "false";
		}
	}
}
