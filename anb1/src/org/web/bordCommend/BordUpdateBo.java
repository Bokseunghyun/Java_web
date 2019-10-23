package org.web.bordCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.borddao.BordDao;
import org.web.borddto.BordDto;


public class BordUpdateBo implements BordCommend{

	@Override
	public void excuteQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 수정");
		int no=Integer.parseInt(request.getParameter("no"));
		String userId = request.getParameter("userId");
		String bordPw = request.getParameter("bordPw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BordDao dao = BordDao.getInstance();
		
		int result=dao.bordUpdate(no,userId,bordPw,title,content);
		
		String url="";
		
		if(result==1) {
			System.out.println("글 수정 성공");
		}
		else {
			System.out.println("글 수정 실패");
		}
		
		request.setAttribute("url", "/bordListView.bo");
		
			
	}

}
