package com.spring.domain;

import lombok.Data;

@Data
public class PageDTO {
	//VO(DB), DTO(화면)
	//list.jsp에서 필요한 정보들을 담는 객체
	private int startPage;
	private int endPage;
	private boolean prev; //뒤로가기
	private boolean next; //앞으로 가기
	
	//화면 하단의 전체 페이지 수
	private int total;
	private Criteria cri;
	
	public PageDTO(int total, Criteria cri) {
		this.total=total;
		this.cri=cri;
		
		//끝페이지 계산
		this.endPage=(int)(Math.ceil(cri.getPageNum()/10.0))*10;
		//시작페이지 계산
		this.startPage=endPage-9;
		
		//페이지가 10페이지가 안될경우 다시 계산해서 끝 페이지 수를 정함 ex) 1~6페이지 까지 보여지게
		int realEnd=(int)(Math.ceil((total/1.0)/cri.getAmount()));
		if(realEnd<this.endPage) {
			this.endPage=realEnd;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage<realEnd;
	}
}
