<%@page import="javax.tools.DocumentationTool.Location"%>
<%@page import="com.spring.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<%
	//세션 가져오기
	MemberVO vo = (MemberVO)session.getAttribute("loginVO");
	if(vo==null){
		
%>

<form class="form-signin" name="loginform" action="../login.do" method="post">
  <div class="form-label-group">
    <input type="text" id="userid" name="userid" class="form-control" placeholder="id" required autofocus>
    <label for="userid">아이디</label>
  </div>

  <div class="form-label-group">
    <input type="password" id="current_password" name="current_password" class="form-control" placeholder="Password" required>
    <label for="pass">비밀번호</label>
  </div>

  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  <p class="mt-5 mb-3 text-muted text-center">&copy; 2019</p>
</form>

<%@ include file="../layout/footer.jsp" %>

<% //세션에 정보가 없을 때
	}
	else{
%>
	<script>
		var name='<%=vo.getName()%>';
	</script>
	<script src="../js/menu.js"></script>
	<script src="../js/command.js"></script>
<%
		//out.print(vo.getName()+"님 반갑습니다");
		
	}
%>