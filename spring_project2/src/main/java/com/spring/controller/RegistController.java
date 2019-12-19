package com.spring.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.MemberVO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegistController {
	

	@Inject
	private MemberService service;
	
	@Inject
	private BCryptPasswordEncoder pwEncoder;
	
	@GetMapping("/index")
	public void index() {
		log.info("index호출");
	}
	
	@GetMapping("/step1")
	public void step1() {
		log.info("약관동의여부 확인");
	}
	
	//약관동의 시 true default 값 false
	@PostMapping("/step2")
	public String step2_agree(@RequestParam(value="agree",defaultValue = "false")boolean agree,
			RedirectAttributes rttr) {
		log.info("약관 동의 확인"+agree);
		
		// 약관에 동의하지 않았을 때 step1로 돌려보내기
		if(!agree) {
			//세션을 임시로 담아둠
			rttr.addFlashAttribute("result",false);
			return "redirect:step1";
		}
		else {
			//step2.jsp 보여주기
			return "step2";
		}
	}
	
	@PostMapping("/index")
	//view에서 MemberVO를 member로 호출가능
	public String insert_member(@ModelAttribute("member")MemberVO vo) {
		log.info("회원 가입 정보 확인"+vo);
		//비밀번호와 비밀번호 재입력이 같지 않을 경우 step2로 되돌려보냄
		if(!vo.userpwequalconfirm_pw()) {
			return"/step2";
		}
		else {
			//비밀번호 암호화
			String inputPw = vo.getUserpw();
			String pw = pwEncoder.encode(inputPw);			
			vo.setUserpw(pw);
		
			service.insert_member(vo);
		return "/index";
		}
	}
	
	@PostMapping("/checkId")
	//아이디 중복확인
	@ResponseBody //jsp페이지를 참고하지않고 자바객체를 body내용으로 매핑
	public String checkId(String userid) {
		log.info("아이디중복확인"+userid);
		
		//true :중복아이디가 아님 false : 중복아이디
		if(service.checkId(userid)) {
			return "true";
		}
		else {
			return "false";
		}
		
	}
}
