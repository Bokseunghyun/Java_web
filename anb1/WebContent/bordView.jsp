<%@page import="org.web.borddto.BordDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
 %>
<%

String sessionId="";

if(session.getAttribute("sessionId")==null){	
	out.print("<script>");
	out.print("alert('로그인후에 이용하세요..');");
	out.print("location.href='login.jsp';");	
	out.print("</script>");		
}else{	
	//로그인 후에(세션아이디)
	 sessionId=(String)session.getAttribute("sessionId");
	
}
	BordDto dto=(BordDto)request.getAttribute("dto");//게시글
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 뷰포트 -->

<meta name="viewport" content="width=device-width" initial-scale="1">

<!-- 스타일시트 참조  -->

<link rel="stylesheet" href="css/bootstrap.css">

<meta charset="UTF-8">
<title>게시글 보기</title>
</head>
<body>
	<table border="0" style="margin:0 auto;width: 500px;background:#fff;" >		
			<!-- 게시판 -->
	<div class="container">

		<div class="row">
			<table class="table table-striped"

					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
		<th colspan="3"
			style="background-color: #eeeeee; text-align: center;">글 보기 </th>
					</tr>
			</thead>
			<tbody>
		<form action="bordUpdate.bo" method="POST" name="bordUpdateForm">
		
		<input type="hidden" name ="no" value="<%=dto.getNo()%>">
		
						<tr>
							<td>작성자</td>	
							<td colspan="2" style ="text-align: left;"><%= dto.getUserId()%></td>
							<input type="hidden" name ="userId" value="<%=dto.getUserId()%>">
						</tr>
						<tr>
							<td style="width: 20%;"> 글 제목 </td>

							<td colspan="2" style ="text-align: left;"><input type="text" name="title"value="<%=dto.getTitle()%>" disabled="true"/></td>
						</tr>
						
						<tr id="pw">
						<td>비밀번호</td>	
						<td colspan="2"style ="text-align: left;"><input type="password" name="bordPw" id="bordPw" value="<%=dto.getBordPw()%>" disabled="true"/></td>
						</tr>
						<tr>
							<td>내용</td>	
							<td><textarea class="form-control"  name="content" maxlength="2048" style="height: 350px;" ><%= dto.getContent() %></textarea></td>
						</tr>
			
				<%
					//세션아이디와 게시글 작성 아이가 
					if(!sessionId.equals(dto.getUserId())){
						%>
						<script>
							document.getElementById('pw').style.display="none";
							document.bordUpdateForm.bordPw.value="";
						</script>
						<%
					}else{
						// 세션아이디와 게시글 아이디가 일치 하면
						%>						
						<script>
						
						var bordUpdateForm=document.bordUpdateForm;
						
						bordUpdateForm.bordPw.disabled=false;//disabled 해제
						bordUpdateForm.title.disabled=false;//disabled 해제
						bordUpdateForm.content.disabled=false;//disabled 해제							
							
						function bordUpdate(){
								
							if(bordUpdateForm.no==null || bordUpdateForm.no==""){
								alert("no값 오류..");
								return false;
							}	
							if(bordUpdateForm.userId==null || bordUpdateForm.userId==""){
								alert("userId값 오류..");
								return false;
							}	
							if(bordUpdateForm.bordPw==null || bordUpdateForm.bordPw==""){
								alert("bordPw값 오류..");								
								bordUpdateForm.bordPw.focus();//포커스(포인트)이동
								return false;
							}	
							if(bordUpdateForm.title==null || bordUpdateForm.title==""){
								alert("title값 오류..");								
								bordUpdateForm.title.focus();//포커스(포인트)이동
								return false;
							}	
							if(bordUpdateForm.content==null || bordUpdateForm.content==""){
								alert("content값 오류..");								
								bordUpdateForm.content.focus();//포커스(포인트)이동
								return false;
							}	
							
							
							alert("게시글 수정 완료");
							
							bordUpdateForm.submit();//form의 action에  form데이터를 서버에 전송 해주는 함수
								
						}		
							
						
						function bordDelete(){
							var no=bordUpdateForm.no;
							var userId=bordUpdateForm.userId;
							var bordPw=bordUpdateForm.bordPw;
							
							alert("게시글 삭제");
							
							location.href="bordDelete.bo?no="+no.value+"&userId="+userId.value+"&bordPw="+bordPw.value;
						}						
						</script>
						<%
					}
				%>
			
		</tbody>
		</table>
		</form>
	</div>
				<input class="btn btn-primary pull-right" type="button" Onclick ="location.href='bordListView.bo'"value="게시글 목록">
				<input class="btn btn-primary pull-right" type="button" Onclick ="bordUpdate()"value="게시글 수정">	
				<input class="btn btn-primary pull-right" type="button" Onclick ="bordDelete()"value="게시글 삭제">							
	</div>


</body>
</html>