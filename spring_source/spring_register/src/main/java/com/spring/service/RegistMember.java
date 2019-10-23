package com.spring.service;

import com.spring.domain.AuthInfo;
import com.spring.domain.LoginVO;
import com.spring.domain.MemberVO;

public interface RegistMember {
	public int insert_member(MemberVO vo);
	public boolean dupId(String userid);
	public AuthInfo select_member(LoginVO vo);
	public int changePwd(String userid,String new_password);
	public int leave_member(String userid);
}
