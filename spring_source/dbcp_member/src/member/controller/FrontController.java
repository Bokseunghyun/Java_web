package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.Action;
import member.action.ActionForward;
import member.action.MemberActionFactory;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//어디에서 요청이 왔는지 확인하기
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String cmd = requestURI.substring(contextPath.length());
		
		//분리된 요청에 의해 Action 생성하기
		MemberActionFactory maf=MemberActionFactory.getInstance();
		Action action = maf.action(cmd);
		
		//생성된 Action에게 일시키기
		ActionForward af=null;
		try {
			af=action.excute(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//일이 끝난 후 페이지 이동
		if(af.isRedirect()) {
			res.sendRedirect(af.getPath());
		}else {
			RequestDispatcher rd= req.getRequestDispatcher(af.getPath());
			rd.forward(req, res);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
