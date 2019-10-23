<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String name = request.getParameter("name");

	if(name.equals("홍길동")){
		//out.print(1);
		out.print("true");
	}else{
		//out.print(0);
		//out.print(name);
		out.print("false");
	}

%>