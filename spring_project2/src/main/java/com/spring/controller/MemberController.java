package com.spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.ChangeVO;
import com.spring.domain.LoginVO;
import com.spring.domain.Memberinfo;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller

// model객체로 생성한(forward) info값을 세션에 담음					         
// 세션 해제 시(leave,changePw) forward 값이 남아있기 때문에 session에 담아 처리 해야함 
@SessionAttributes("info")		  

@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired 
	private MemberService service;
	
	@GetMapping("/login")
	public void getloginform() {
		log.info("로그인 요청");
	}
	
	@PostMapping("/login")
	public String loginform(LoginVO vo , RedirectAttributes rttr, Model model) {
		log.info("로그인 요청"+vo);
		
		//로그인한 사람의 정보를 info에 memberinfo타입으로(id,name) 담음
		Memberinfo minfo = service.select_member(vo);
		
		//로그인 정보가 없을 시 로그인화면으로 돌려보내기
		if(vo==null) {
			
			//flash로 일회성 경고 띄우기
			rttr.addFlashAttribute("error", "아이디와 비밀번호를 확인해주세요");
			
			//login화면으로 돌려보냄
			return "redirect:login";
		}
		else {
			//info를 model객체에 담음
			model.addAttribute("info", minfo);
			
			//index로 이동
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logoutform(SessionStatus sts) {
		//세션 해제
		sts.setComplete();
		return "redirect:/";
	}
	
	
	@GetMapping("/changePw")
	public void changePwForm() {
		log.info("비밀번호 변경 폼 보여주기");
	}
	
	@PostMapping("changePw")
	public String changePw(ChangeVO vo, @SessionAttribute("info")Memberinfo info,RedirectAttributes rttr,
			SessionStatus sts) {
		
		log.info("비밀번호 변경"+vo);
		//로그인 상태 => 세션에 정보가 있는 상태(Memberinfo)
		//세션에서 아이디 가져오기
		//아이디와 현재비밀번호 넘겨서 존재하는 사용자인지 확인
		LoginVO loginvo = new LoginVO();
		
		loginvo.setUserid(info.getUserid());
		
		loginvo.setCurrent_pw(vo.getCurrent_pw());
		
		Memberinfo info2 = service.select_member(loginvo);
		//존재하는 사용자인지 확인
		
		if(info2==null) {
			rttr.addFlashAttribute("error", "비밀번호를 확인해주세요");
		}
		
		if(info2!=null) {
			if(!vo.currentpwequalconfirmpw()) {
				int result = service.changePw(info2.getUserid(), vo.getNew_pw());
				
				if(result>0) {
					if(sts.isComplete()) { //세션 존재하는지 확인
						sts.setComplete(); //세션 해제
					}
				}
			}
			return "redirect:login";
		}
		else {
			rttr.addFlashAttribute("error", "변경비밀번호가 일치하지 않습니다.");
		}
		
		
		return "redirect:changePw";
	}
	
	@GetMapping("/leave")
	public String leave_member(@SessionAttribute("info")Memberinfo info, SessionStatus sts) {
		log.info("회원탈퇴");
		
		int result = service.delete_member(info.getUserid());
		
		//존재하는 회원정보 일 경우
		if(result>0) {
			sts.setComplete();
			//탈퇴 성공 시 index로 이동
			return "redirect:/";
		}
		
		return "redirect:leave";
	}
		
}
