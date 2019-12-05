package com.spring.service;

import com.spring.domain.LoginVO;
import com.spring.domain.MemberVO;
import com.spring.domain.Memberinfo;

public interface MemberService {
	//회원가입
	public void insert_member(MemberVO vo);
	//회원탈퇴
	public int delete_member(String userid);
	//아이디 중복체크
	public boolean checkId(String userid);
	//로그인 정보
	public Memberinfo select_member(LoginVO vo);
	//비밀번호 변경
	public int changePw(String userid, String new_pw);
}
