package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.bookVO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	@Inject
	private BookService service;
	
	
	@GetMapping(value= {"/insert","/delete","/modify"})
	public String handleGet() {
		log.info("insert,delete,modify get 호출");
		
		return "redirect:/";
	}
	
	@PostMapping("/insert")
	public String insert_book(bookVO vo,RedirectAttributes rttr) {
		log.info("insert"+vo);
		try {
			int result = service.book_insert(vo);
			if(result>0) {
				//도서 입력 성공시 전체 리스트로 이동
				return"redirect:select";
			}else{
				return "redirect:/";
			}
		} catch (Exception e) { //무결성 위반일때, 코드 길이
			rttr.addFlashAttribute("tab","insert");
			return "redirect:/";
		}
	}
	
	@GetMapping("/select")
	public String book_selectAll(Model model) {
		
		List<bookVO> list = service.getlist();
		
		//model => request 객체 
		model.addAttribute("list",list);
		return "book_selectAll";
	}
	
	//삭제요청 컨트롤러 
	//삭제가 성공한 후 전체 리스트 보여주기
	@PostMapping("/delete")
	public String delete_book(String code,RedirectAttributes rttr) {
		
		log.info("삭제코드"+code);
		int result = service.book_delete(code);
		if(result>0) {
			
			return"redirect:select";
		}else {
			rttr.addFlashAttribute("tab","delete");
			return "redirect:/";
		}
		
	}
	
	//도서정보 수정 컨트롤러(코드가 일치하는 경우 가격 수정)
	//수정 성공시 전체리스트 보여주기
	@PostMapping("/modify")
	public String update_book(String code,int price,RedirectAttributes rttr) {
		log.info("수정코드"+code+"수정 가격"+price);
		int result = service.book_update(code, price);
		if(result>0) {
			
			return "redirect:select";
		}else {
			rttr.addFlashAttribute("tab","modify");
			return "redirect:/";
		}
	}
	
	@PostMapping("/search")
	public String book_search(String criteria,String keyword,Model model,RedirectAttributes rttr) {
		List<bookVO> list = service.book_search(criteria, keyword);
		model.addAttribute("list",list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			return "book_searchAll";
		}else {
			rttr.addFlashAttribute("tab", "search");
			return "redirect:/";
		}		
	}
	
	@GetMapping("/search")
	public String search(RedirectAttributes rttr) {
		log.info("search Form 호출");
		rttr.addFlashAttribute("tab","search");
		return "redirect:/";
	}
}
