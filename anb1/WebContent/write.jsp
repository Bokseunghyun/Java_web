<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//게시글 작성.. 로그인이 된 상태에서만 게시글 작성..
	if(session.getAttribute("sessionId")==null){		
		out.print("<script>");
		out.print("alert('로그인 후에 이용하세요');");
		out.print("location.href='index1.jsp';");			
		out.print("</script>");		
	}
%>   
<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 뷰포트 -->

<meta name="viewport" content="width=device-width" initial-scale="1">

<!-- 스타일시트 참조  -->

<link rel="stylesheet" href="css/bootstrap.css">

<title>게시판 웹사이트</title>

</head>

<body>

<%@ include file = "list.jsp" %>

	<!-- 네비게이션  -->

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

			<ul class="nav navbar-nav">


			</ul>


		</div>

	</nav>

	<!-- 게시판 -->

	<div class="container">

		<div class="row">

			<form action="bordWrite.bo" method="POST" name="writeForm">

				<table class="table table-striped"

					style="text-align: center; border: 1px solid #dddddd">

					<thead>

						<tr>

							<th colspan="2"

								style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 </th>

						</tr>

					</thead>

					<tbody>
							
						<tr>
							<input type="hidden" name ="userId" value="<%=session.getAttribute("sessionId")%>"></td></tr>

							<td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50"/></td>

						</tr>
						<tr><td> <input type="password" class="form-control" placeholder="글 비밀번호 "name="bordPw" id="bordPw" maxlength="16"/></td></tr>

						<tr>

							<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height: 350px;"></textarea></td>

						</tr>

					</tbody>

				</table>	

				<input class="btn btn-primary pull-right" type="button" Onclick ="location.href='bordListView.bo'"value="취소">
				<input type="submit" class="btn btn-primary pull-right" value="게시글 등록"/>
			</form>

		</div>

	</div>


	<!-- 애니매이션 담당 JQUERY -->

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

	<!-- 부트스트랩 JS  -->

	<script src="js/bootstrap.js"></script>



</body>

</html>



