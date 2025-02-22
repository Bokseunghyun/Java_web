package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("게시글 등록 폼 요청 " );
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rttr) throws Exception {
		//register.jsp에서 넘긴 값 가져와서 log로 출력
		log.info("게시글 등록하기"+vo);
		
		//attachList 확인
		if(vo.getAttachList()!=null) {
			vo.getAttachList().forEach(attach->log.info(""+attach));
		}
		
		
		service.register(vo);
		rttr.addFlashAttribute("result",vo.getBno());
		return "redirect:list";
	}
	
	// /board/list => 컨트롤러 작성
	// DB 작업(전체리스트 가져오기)
	// Model에 담고 페이지 이동
	
//	@GetMapping("/list")
//	public void listGet(Model model) {
//		List<BoardVO> list = service.listall();
//		model.addAttribute("list",list);
//	}
	
	@GetMapping("/list")
	public void listGet(Model model,Criteria cri) {
		List<BoardVO> list = service.listall(cri);
		model.addAttribute("list",list);
		model.addAttribute("pageDTO", new PageDTO(service.gettotalCnt(cri), cri));
	
	}
	
	@GetMapping(value= {"/read","/modify"})
	public void read(int bno, Model model, @ModelAttribute("cri")Criteria cri) throws Exception{
		log.info("게시글 보기"+bno);
		log.info(""+cri);
		
		//bno에 해당하는 내용 DB작업 후 Model에 담고 페이지 이동
		BoardVO vo = service.selectlist(bno);
		model.addAttribute("vo",vo);
	}
	@PostMapping("/modify")
	public String modify(BoardVO vo){
		//modify.jsp에서 넘긴 값 가져오기
		//서비스 요청=>title,content 수정
		service.modify(vo);
		return "redirect:list";
		
	}
	
	@PostMapping("/remove")
	public String remove(int bno,Criteria cri,RedirectAttributes rttr) {
		log.info(""+cri);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}		
		rttr.addAttribute("pageNum",cri.getPageNum());  
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:list";
	}
}
