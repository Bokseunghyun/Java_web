package com.spring.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.domain.ChangeVO;
import com.spring.domain.LoginVO;
import com.spring.domain.MemberVO;
import com.spring.domain.Memberinfo;
import com.spring.mapper.MemberMapper;

@Service("mapper")
public class MemberServiceimpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void insert_member(MemberVO vo) {
		mapper.insert_member(vo);
	}

	@Override
	public int delete_member(String userid) {

		return mapper.delete_member(userid);
	}

	@Override
	public Memberinfo select_member(LoginVO vo) {

		return mapper.select_info(vo);
	}

	@Override
	public int changePw(ChangeVO vo) {
		return mapper.changePw1(vo);
	}

	@Override
	public boolean checkId(String userid) {

		return mapper.select_id(userid)==null?true:false;
	}

	@Override
	public MemberVO loginBcrypt(LoginVO vo) {

		return mapper.loginBcrypt(vo);
	}

}
