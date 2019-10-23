package org.web.memberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.memberdao.MemberDao;

public class MemberIdCheckedDo implements MemberCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("아이디 체크");
		
		String userId=request.getParameter("userId");
		
		MemberDao dao=MemberDao.getInstance();
		
		int rs=dao.idChecked(userId);
	
		PrintWriter out = response.getWriter();
		
		out.write(rs+"");
		out.flush();
		
		
		
		
				
		
		
	}
}
