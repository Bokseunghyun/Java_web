package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleVO;

@RestController
public class RestControllerTest {
	
	@GetMapping(value="/hello",produces = "text/plain;charset=utf-8")
	public String sayHello() {
		return "Hello World";
	}
	
	@GetMapping(value="/sendVO",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	public SampleVO sendVO() {
		SampleVO vo=new SampleVO();
		vo.setMno("1234");
		vo.setFirstName("hong");
		vo.setLastName("Dong");
		return vo;
	}
	
	@GetMapping("/getList")
	public List<SampleVO> getList(){
		List<SampleVO> list=new ArrayList<SampleVO>();
		for(int i=0;i<10;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno("1234");
			vo.setFirstName("hong");
			vo.setLastName("Dong");
			list.add(vo);
		}
		return list;
	}
	
	//@PathVariable => URL경로(path)에 변수를 넣을 수 있게 해줌
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat")String cat, @PathVariable("pid")String pid) {
		return new String[] {"category : "+cat+"productid"+pid};
	}
}













