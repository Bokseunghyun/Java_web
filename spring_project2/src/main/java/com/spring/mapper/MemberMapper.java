package com.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.LoginVO;
import com.spring.domain.MemberVO;
import com.spring.domain.Memberinfo;

public interface MemberMapper {
	
	//회원가입
	public void insert_member(MemberVO vo);
	
	//회원탈퇴
	public int delete_member(String userid);
	
	//로그인정보 가져오기
	public Memberinfo select_info(LoginVO vo);

	//비밀번호 변경
	public int changePw(@Param("userid")String userid, @Param("new_pw")String new_pw);

	//아이디 중복 검사
	public MemberVO select_id(String userid);
	
	//로그인 암호화
	public MemberVO loginBcrypt(LoginVO vo);
}
