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
		log.info("게시글 등록 폼부르기");
	}
	
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) throws Exception{
		log.info("게시글 등록"+vo);
		
		service.inesrt_board(vo);
		rttr.addFlashAttribute("result",vo.getBno());
		
		return "redirect:list";
	}

	
	@GetMapping("/list")
	public void board_list(Model model,Criteria cri) {
		List<BoardVO> list = service.listall(cri);
		model.addAttribute("list",list);
		
		model.addAttribute("pageDTO", new PageDTO(service.totalCnt(cri), cri));
	}
	
	//read또는 modify를 요청시 작동 (다중매핑)
	@GetMapping(value = {"/read","/modify"})
	public void board_read(int bno, @ModelAttribute("cri")Criteria cri,Model model) {
		log.info("게시글 보기"+bno);
		log.info(""+cri);
		//bno에 해당하는 게시글 DB작업 후 Model에 담아 페이지이동
		BoardVO vo = service.selectlist(bno);
		model.addAttribute("vo",vo);
	}
	
	@PostMapping("/modify")
	public String board_modify(BoardVO vo) {
		//modify.jsp에서 넘긴 값 가져오기
		//서비스 요청=>title,content 수정 후 list로 이동
		service.modify_board(vo);
		return "redirect:list";
	}
	
	@PostMapping("/delete")
	public String board_delete(int bno, Criteria cri,RedirectAttributes rttr) {
		log.info(""+cri);
		
		if(service.delete_board(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:list";
	}
	
	
}
