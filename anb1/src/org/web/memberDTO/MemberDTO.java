package org.web.memberDTO;

public class MemberDTO {
	
	private String userID;
	private String userPW;
	private String userNAME;
	private int userAGE;
	
	public MemberDTO(String userID, String userPW, String userNAME, int userAGE) {
		super();
		this.userID = userID;
		this.userPW = userPW;
		this.userNAME = userNAME;
		this.userAGE = userAGE;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserNAME() {
		return userNAME;
	}

	public void setUserNAME(String userNAME) {
		this.userNAME = userNAME;
	}

	public int getUserAGE() {
		return userAGE;
	}

	public void setUserAGE(int userAGE) {
		this.userAGE = userAGE;
	}
	
	
	
}
