package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.domain.AuthInfo;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//http:localhost:8083/member/changePwd 
		//=> 세션에 정보가 있는지 확인한 후 컨트롤러 동작시키기 
		HttpSession session = request.getSession(false);
		if(session!=null) {
			//membercontroller에 있는 info를찾아서 가져옴
			AuthInfo info = (AuthInfo)session.getAttribute("info");
			if(info!=null) {
				return true;
			}
		}
		//info가 null일 때 login페이지로 이동시킴
		response.sendRedirect(request.getContextPath()+"/member/login");
		return false;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}
}
