package com.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.AuthInfo;
import com.spring.domain.LoginVO;
import com.spring.domain.MemberVO;
import com.spring.mapper.RegistMapper;

@Service("regist")
public class RegistMemberImpl implements RegistMember {

	@Inject
	private RegistMapper regist;
	
	@Override
	public int insert_member(MemberVO vo) {		
		return regist.insert_member(vo);
	}

	@Override
	public boolean dupId(String userid) {
		return regist.selectById(userid)==null?true:false;
	}

	@Override
	public AuthInfo select_member(LoginVO vo) {

		return regist.selectByMember(vo);
	}
	
	@Override
	public int changePwd(String userid, String new_password) {
		return regist.changePwd(userid, new_password);
	}

	@Override
	public int leave_member(String userid) {
		return regist.leave_member(userid);
	}

}
