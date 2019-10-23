package org.web.borddto;

public class BordDto {

	private int no;
	private String userId;
	private String bordPw;
	private String title;
	private String content;
	//spring 
	//�ڹٺ�.. -> spring
	
	public BordDto() {
		super();
	}
	public BordDto(int no, String userId, String bordPw, String title, String content) {
		super();
		this.no = no;
		this.userId = userId;
		this.bordPw = bordPw;
		this.title = title;
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBordPw() {
		return bordPw;
	}
	public void setBordPw(String bordPw) {
		this.bordPw = bordPw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
