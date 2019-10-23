package com.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.AuthInfo;
import com.spring.domain.LoginVO;
import com.spring.domain.MemberVO;

public interface RegistMapper {
	//중복된 아이디 검사
	public MemberVO selectById(String userid);
	//회원가입
	public int insert_member(MemberVO vo);
	//회원 정보 가져오기(1명)
	public AuthInfo selectByMember(LoginVO vo);
	//비밀번호 변경
	public int changePwd(@Param("userid")String userid,@Param("new_password")String new_password);
	//회원탈퇴
	public int leave_member(String userid);
}
