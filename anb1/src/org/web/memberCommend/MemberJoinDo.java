package org.web.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.memberdao.MemberDao;

public class MemberJoinDo implements MemberCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("회원 가입");				
			
			String userId=request.getParameter("userId");
			String userPw=request.getParameter("userPw");
			String userName=request.getParameter("userName");
			int userAge=Integer.parseInt(request.getParameter("userAge"));
			
			//DAO
			MemberDao dao=MemberDao.getInstance();// 싱글톤 호출
			int rs=dao.insert(userId, userPw, userName, userAge);

			String url="";
			
			if(rs==1) {
				System.out.println("회원 가입 성공");
				url="/index1.jsp";				
			}else {
				System.out.println("회원 가입 실패");
				url="/index1.jsp";								
			}
			
			request.setAttribute("url", url);
	}

}
