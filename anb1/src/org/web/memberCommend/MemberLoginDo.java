package org.web.memberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.web.memberdao.MemberDao;

public class MemberLoginDo implements MemberCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("로그인 ");
			
			
			String userId=request.getParameter("userId");
			String userPw=request.getParameter("userPw");		
			
			MemberDao dao=MemberDao.getInstance();
			
			int rs=dao.login(userId,userPw);//로그인 성공하면 1
			
			

			HttpSession session=request.getSession();//세션 생성
			
			String url="";
			
			if(rs==1) {
				
				session.setAttribute("sessionId", userId);
				session.setMaxInactiveInterval(60*10);
				
				url="/index2.jsp";
				System.out.println("로그인 OK");
				
			}else {
				url="/index1.jsp";
				System.out.println("로그인 NO");
			}
			
			request.setAttribute("url", url);
			
			
			
			
			
			
	}
}
