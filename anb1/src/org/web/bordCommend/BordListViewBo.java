package org.web.bordCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.borddao.BordDao;
import org.web.borddto.BordDto;

public class BordListViewBo implements BordCommend {

	@Override
	public void excuteQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("게시판리스트 Commend");		
		
		BordDao dao=BordDao.getInstance();
		ArrayList<BordDto> lists=dao.bordList();
				
		
		request.setAttribute("lists", lists);
		request.setAttribute("url", "/bordList.jsp");
		
		
		
	}
	
	
}
