package com.spring.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageDTO;
import com.spring.domain.ReplyVO;
import com.spring.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Inject
	private ReplyService service;
	
	//json타입으로 데이터 처리 요청(consumes) text타입으로 최종결과 처리
	@PostMapping(value="/new",consumes="application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> insert_reply(@RequestBody ReplyVO vo){ // json형태를 ReplyVO타입으로 변경 후 처리
		log.info("댓글 작성 요청");
		
		return service.insert_reply(vo)? new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyVO> getReply(@PathVariable("rno")int rno){
		log.info("댓글 하나 가져오기");
		return new ResponseEntity<ReplyVO>(service.select_reply(rno),HttpStatus.OK);
	}
	
	//json형태로 결과 처리
	@GetMapping(value = "/pages/{bno}/{page}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyPageDTO> getReplyList(@PathVariable("bno")int bno,
			@PathVariable("page")int page){
		log.info("댓글 전체 가져오기");
		
		Criteria cri = new Criteria(page,10); //page =>현재 페이지 / 10 => 보여줄 게시물 수
		return new ResponseEntity<ReplyPageDTO>(service.getList(cri, bno),HttpStatus.OK);
	}

	@PutMapping(value = "/{rno}")
	public ResponseEntity<String> modify_reply(@PathVariable("rno")int rno,@RequestBody ReplyVO vo){
		log.info("댓글 수정"+vo);
		vo.setRno(rno);
		return service.modify_reply(vo)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/{rno}")
	public ResponseEntity<String> delete_reply(@PathVariable("rno")int rno){
		log.info("댓글 삭제");
	
		return service.delete_reply(rno)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

}
