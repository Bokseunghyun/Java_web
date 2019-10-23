<%@page import="org.web.borddto.BordDto"%>
<%@page import="org.web.borddao.BordDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	//게시글 작성.. 로그인이 된 상태에서만 게시글 작성..
	/* if(session.getAttribute("sessionId")==null){		
		out.print("<script>");
		out.print("alert('로그인 후에 이용하세요');");
		out.print("location.href='login.jsp';");			
		out.print("</script>");		
	} */
%>   
<%
BordDao dao = new BordDao();
	ArrayList<BordDto> lists = 
	(ArrayList<BordDto>) request.getAttribute("lists");
%>
<!DOCTYPE html>

	<html>

	<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- 뷰포트 -->

	<meta name="viewport" content="width=device-width" initial-scale="1">

	<!-- 스타일시트 참조  -->

	<link rel="stylesheet" href="css/bootstrap.css">

	<title>게시판 목록</title>

	<style type="text/css">

		a, a:hover {

			color: #000000;

			text-decoration: none;

		}

	</style>

	</head>

	<body>
	
	
<%@include file="list.jsp" %>
	

	

		<!-- 네비게이션  -->
<div  style="float:left;width:1900px;">
		<nav class="navbar navbar-default">

			<div class="navbar-header">

				<button type="button" class="navbar-toggle collapsed"

					data-toggle="collapse" data-target="bs-example-navbar-collapse-1"

					aria-expaned="false">

					<span class="icon-bar"></span> <span class="icon-bar"></span> <span

						class="icon-bar"></span>

				</button>


			</div>

			<div class="collapse navbar-collapse"

				id="#bs-example-navbar-collapse-1">

			</div>

		</nav>

		<!-- 게시판 -->

		<div class="container">

			<div class="row">

				<table class="table table-striped"

					style="text-align: center; border: 1px solid #dddddd">

					<thead>

						<tr>

							<th style="background-color: #eeeeee; text-align: center;">번호</th>

							<th style="background-color: #eeeeee; text-align: center;">작성자</th>

							<th style="background-color: #eeeeee; text-align: center;">글 제목</th>

							<th style="background-color: #eeeeee; text-align: center;">글 내용</th>

						</tr>

					</thead>

					<tbody>

						<tr>
						<%
							for (BordDto dto : lists) {
							%>
						<tr>
				<td class="t1"><%=dto.getNo()%></td>
				<td class="t1"><%=dto.getUserId()%></td>
				<td class="t2"><%=dto.getTitle()%></td>
				<td class="t4"><a href="bordView.bo?no=<%=dto.getNo()%>">게시글보기</a></td>
				
			
							</tr>
						<%
							}
						%>						




						</tr>

	

	

					</tbody>

				</table>

			


			</div>

		</div>
	<input class="btn btn-primary pull-right" type="button" Onclick ="location.href='write.jsp'"value="게시글 작성">
	
	</div>

		<!-- 애니매이션 담당 JQUERY -->

		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

		<!-- 부트스트랩 JS  -->

		<script src="js/bootstrap.js"></script>

	

	</body>

	</html>



