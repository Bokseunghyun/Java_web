<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//post 방식이기 때문에 한글처리 필요
	request.setCharacterEncoding("utf-8");
//Scope1.jsp에서 보낸 값 가져오기
String userId = request.getParameter("userid");
String userName = request.getParameter("username");
out.print("<h3>userid : "+userId+"</h3>");
%>

<h3>userName : <%=userName%></h3>
<h3>age : <%=request.getAttribute("age")%></h3>