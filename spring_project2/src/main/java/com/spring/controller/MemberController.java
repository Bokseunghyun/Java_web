package com.spring.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.spring.domain.MemberVO;
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

	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	@GetMapping("/login")
	public void getloginform() {
		log.info("로그인 요청");
	}

	@PostMapping("/login")
	public String loginform(LoginVO vo, RedirectAttributes rttr, Model model) throws Exception {
		log.info("로그인 요청" + vo);
		MemberVO login = service.loginBcrypt(vo);

		// 입력한 비밀번호, DB에 등록된 비밀번호가 같은지 확인
		boolean Pwmatches = pwEncoder.matches(vo.getCurrent_pw(), login.getUserpw());
 
		if (vo != null && Pwmatches) {
			log.info("비밀번호 일치");
			// 비밀번호가 일치 할 시 로그인비밀번호에 암호화된 비밀번호를 set
			vo.setCurrent_pw(login.getUserpw());

			// 로그인정보가있는 login를 model객체에 담음
			model.addAttribute("info", login);
			log.info("암호화 된 로그인 정보" + vo);
			log.info("암호화 된 비밀번호 " + vo.getCurrent_pw());
			// index로 이동
			return "redirect:/";
		}
		// 로그인 정보가 없을 시 로그인화면으로 돌려보내기
		else {
			// flash로 일회성 경고 띄우기
			rttr.addFlashAttribute("error", "아이디와 비밀번호를 확인해주세요");
			// login화면으로 돌려보냄
			return "redirect:login";
		}

	}

	@GetMapping("/logout")
	public String logoutform(SessionStatus sts) {
		// 세션 해제
		sts.setComplete();
		return "redirect:/";
	}

	@GetMapping("/changePw")
	public void changePwForm() {
		log.info("비밀번호 변경 폼 보여주기");
	}

	@PostMapping("changePw")
	public String changePw(ChangeVO vo, @SessionAttribute("info") MemberVO info, RedirectAttributes rttr,
			SessionStatus sts, Model model) {
		log.info("비밀번호 변경" + vo);
		boolean Pwmatches = pwEncoder.matches(vo.getCurrent_pw(), info.getUserpw());
		if (info != null && Pwmatches) {
			boolean CheckPw = vo.getNew_pw().equals(vo.getConfirm_pw());
			if(CheckPw) {
				String inputPw = vo.getNew_pw();
				String pw = pwEncoder.encode(inputPw);
				vo.setNew_pw(pw);
			
				int result = service.changePw(vo);
				if(result>0) {
					info.setUserpw(vo.getNew_pw());
					if(sts.isComplete()) { //세션 존재하는지 확인 
						sts.setComplete(); // 세션 해제
					}
				}
			}
				else {
					rttr.addFlashAttribute("error", "변경비밀번호가 일치하지 않습니다.");
					return "redirect:changePw";
				}
			}
			return "redirect:login";
		}

	@GetMapping("/leave")
	public String leave_member(@SessionAttribute("info") LoginVO vo, SessionStatus sts) {
		log.info("회원탈퇴");

		int result = service.delete_member(vo.getUserid());

		// 존재하는 회원정보 일 경우
		if (result > 0) {
			sts.setComplete();
			// 탈퇴 성공 시 index로 이동
			return "redirect:/";
		}

		return "redirect:leave";
	}

}
