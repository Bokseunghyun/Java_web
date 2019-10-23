package org.web.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.memberdao.MemberDao;

public class MemberModifyOkDo implements MemberCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("정보 수정");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		MemberDao dao = MemberDao.getInstance();
		int result=dao.MemberUpdate(userId,userPw,userName,userAge);
		String url="";
		
		System.out.println(result);
		if(result==1) {
			request.setAttribute("url", result);
		}
		else {
			request.setAttribute("url", result);
		}
		
		request.setAttribute("url", "/memberView.jsp");
		
	}		
	}


