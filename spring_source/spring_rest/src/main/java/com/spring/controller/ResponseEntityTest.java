package com.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleVO;

@RestController
public class ResponseEntityTest {
	@GetMapping("/check")
	public ResponseEntity<SampleVO> check(double height,double weight){
		SampleVO vo=new SampleVO();
		vo.setMno("123");
		vo.setFirstName(height+"");
		vo.setLastName(weight+"");
		
		ResponseEntity<SampleVO> entity=null;
		
		if(height<150) {
			entity=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
		}else {
			entity=ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return entity;
	}
}






