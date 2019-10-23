package org.web.bordCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.borddao.BordDao;
import org.web.borddto.BordDto;

public class BordViewBo implements BordCommend {

	@Override
	public void excuteQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("게시글 조회");
		int no=Integer.parseInt(request.getParameter("no")); //no에 해당하는 게시글만 get
		BordDao dao = BordDao.getInstance();
		BordDto dto=dao.getBord(no);
		String url="";
	
		
		if(dto==null)
		{
			System.out.println("게시글 없음");
			url="/index2.jsp";
		}else
		{	
			System.out.println("게시글 있음");
			request.setAttribute("dto", dto);
			url="/bordView.jsp";
		}
		
		request.setAttribute("url", url);
		
	}

}
