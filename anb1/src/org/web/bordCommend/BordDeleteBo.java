package org.web.bordCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.borddao.BordDao;
import org.web.borddto.BordDto;

public class BordDeleteBo implements BordCommend{

	@Override
	public void excuteQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 삭제");
		BordDao dao = BordDao.getInstance();
		 int no=Integer.parseInt(request.getParameter("no"));
		String userId = request.getParameter("userId");
		String bordPw = request.getParameter("bordPw");
		int result = dao.bordDelete(no,userId,bordPw);
		String url="";
		System.out.println(result);
	if(result==1) {
		System.out.println("글 삭제 성공");
	}
	else {
		System.out.println("글 삭제 실패");
	}
	
	request.setAttribute("url", "/bordListView.bo");
	}
}
