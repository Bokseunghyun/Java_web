package com.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.NumVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class AddController {
	//http://localhost:8083/add.jsp
	
//	@RequestMapping("/add")
//	public void add() {
//		log.info("add 호출");
//	}
	
//	@RequestMapping("/add")
//	public void add(HttpServletRequest req) {
//		log.info("add 요청");
//		log.info("num1="+req.getParameter("num1"));
//		log.info("num2="+req.getParameter("num2"));
//		
//	}
//	@RequestMapping("/add")
//	public void add(@RequestParam(value="num1",required=false,defaultValue="0")int num1,@RequestParam(value="num2",required=false,defaultValue="0")int num2) {
//		log.info("add 요청");
//		log.info("num1 = " +num1);
//		log.info("num2 = " +num2);
//		
//	}
	
//	@RequestMapping("/add")
//	public String add(NumVO vo, int page, Model model) {
//		//Model => Request 객체 개념
//		log.info("add 요청");
//		log.info("num1 = " +vo.getNum1());
//		log.info("num2 = " +vo.getNum2());
//		log.info("page = " +page);
//		
//		model.addAttribute("page",page);
//		return "addResult";
//	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("vo")NumVO vo, @ModelAttribute("page")int page, Model model) {
		//Model => Request 객체 개념
		log.info("add 요청");
		log.info("num1 = " +vo.getNum1());
		log.info("num2 = " +vo.getNum2());
		log.info("page = " +page);
		
		model.addAttribute("page",page);
		return "addResult";
	}
}
