package com.spring.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthInfo;
import com.spring.domain.ChangeVO;
import com.spring.domain.LoginVO;
import com.spring.service.RegistMember;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("info") // model객체로 생성한(forward) info값을 세션에 담음  
// 세션 삭제 시(logout) forward 값이 남아있기 때문에 session에 담은 뒤 삭제 처리 해야함
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private RegistMember service;
	//login 처리 컨트롤러 생성
	//localhost:8083/member/login
	@GetMapping("/login")
	public void loginform() {
		log.info("로그인 요청");
	}
	
	@PostMapping("/login")
	public String loginPost(LoginVO vo,RedirectAttributes rttr,Model model) {
		log.info("로그인 요청"+vo);
	
		AuthInfo info = service.select_member(vo);
		
		if(info==null) {
			//userid나 비밀번호가 잘못됐다는 의미
			//로그인 화면으로 돌려보내기
			rttr.addFlashAttribute("error","아이디나 비밀번호를 확인해 주세요");
			return "redirect:login";
		}else {
			//Model 객체에 값담기
			model.addAttribute("info",info);
			//index화면으로 이동
			return "redirect:/";
		}
	}
	
	//로그인 해제 컨트롤러
	@GetMapping("/logout")
	public String logout(SessionStatus sessionStatus) {
		log.info("로그아웃");
		//세션 해제
		sessionStatus.setComplete();
		//index 이동
		return "redirect:/";
	}
	
	
	//http://localhost:8083/member/changePwd
		@GetMapping("/changePwd")
		public void changePwdForm() {
			log.info("비밀번호 변경 폼 보여주기...");
		}
		
		
		//http://localhost:8083/member/changePwd => POST
		@PostMapping("/changePwd")
	
		public String changePwdPost(ChangeVO vo,@SessionAttribute("info")AuthInfo info,SessionStatus sessionstatus,RedirectAttributes rttr){
			log.info("비밀번호 변경하기"+info.getUserid());
			//로그인 상태=> 세션에 정보가 있는 상태(AuthInfo)
			
			//세션에서 아이디 가져오기
			//아이디와 현재비밀번호 넘겨서 존재하는 사용자인지 확인
			//존재하는 사용자라면
			LoginVO loginVO=new LoginVO();
			loginVO.setUserid(info.getUserid());
			loginVO.setCurrent_password(vo.getCurrent_password());
			
			AuthInfo info2=service.select_member(loginVO);
			
			if(info2==null) {
				rttr.addFlashAttribute("error", "현재 비밀번호를 확인해 주세요");			
			}
			
			if(info2!=null) {
				
				if(vo.isNewPasswordToConfirmPassword()) {
					int result = service.changePwd(info.getUserid(), vo.getConfirm_password());
					if(result>0) {
						if(sessionstatus.isComplete()) {
							sessionstatus.setComplete();
						}		
					}
				}
				return "redirect:login";
				
			}else {
				rttr.addFlashAttribute("error", "변경 비밀번호가 같지 않습니다.");
				
			}	
			//new_password와 confirm_password가 같은지 확인
	
			//같다면 비밀번호 수정해주기
			//비밀번호 변경이 완료되면 세션해제
			//로그인 페이지로 이동
			
			//현재비밀번호가 틀린 경우 => changePwd 폼으로 돌려보내고
			//현재 비밀번호를 확인해 주세요 메세지를 띄워주기
			return "redirect:changePwd";
		}
		
		//회원탈퇴
		@GetMapping("/leave")
		public String leave(@SessionAttribute("info")AuthInfo info,SessionStatus sstatus) {
			log.info("회원탈퇴");
			//회원탈퇴가 성공하면 세션 해제 후 index로 이동
			int result=service.leave_member(info.getUserid());
			if(result>0) {
				sstatus.setComplete();
				return "index";
			}
			return "redirect:leave";
		}
}
