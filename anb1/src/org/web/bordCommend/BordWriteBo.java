package org.web.bordCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.borddao.BordDao;

public class BordWriteBo implements BordCommend {

	@Override
	public void excuteQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(" Commend");
		
		String userId = request.getParameter("userId");
		String bordPw = request.getParameter("bordPw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BordDao dao=BordDao.getInstance();
		
		int result=dao.bordWrite(userId, bordPw, title, content);
		
		String url="";
		
		if(result==1) {
			url="/bordListView.bo";  // �Խñ� �ۼ� ����..
		}else {
			url="/bordWrite.jsp";	 // �Խñ� �ۼ�����..		
		}
		
		request.setAttribute("url", url);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
