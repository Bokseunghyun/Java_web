package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; //페이지번호
	private int amount; //한페이지당 보여줄 게시물 수
	
	private String type;  //검색조건
	private String keyword;  //검색어
	
	//검색조건을 String 배열로 만들어 리턴
		public String[] getTypeArr() {
			// type => TCW => {'T','C','W'}
			return type==null? new String[]{}:type.split("");
		}
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum,int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
}
