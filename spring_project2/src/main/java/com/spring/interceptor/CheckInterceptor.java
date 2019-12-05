package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.domain.Memberinfo;

public class CheckInterceptor extends HandlerInterceptorAdapter {
	
	//컨트롤러 실행 직전에 동작 반환값이 true일 경우 정상실행, false일 경우 정지
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		//세션에 정보가 들어가있는지 확인 후 컨트롤러 실행시키기
		if(session!=null) {
			//MemberController에 있는 info를 가져옴
			Memberinfo info = (Memberinfo)session.getAttribute("info");
			
			return true;
		}else {
			//info가 null일 때 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
		
	}
	
	
	//컨트롤러 -> 뷰로 넘어가기 전에 동작
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
