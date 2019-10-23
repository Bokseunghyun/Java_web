<%@page import="org.web.memberDTO.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%	
String sessionId="";

	if(session.getAttribute("sessionId")==null){
		response.sendRedirect("index1.jsp");
	}
	MemberDTO member=(MemberDTO)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
</head>
<body>

<div style="background:#9e9e9e;width:80%;padding:20px 0;margin:0 auto">

	<form action="MemberModifyView.do" method="POST" name="modifyForm" id="frm" 
			style="background:#fff;width:400px;margin:0 auto;line-height:50px;">

		아이디: <%=member.getUserID()%> 
				<input type="hidden" name="userId" value="<%=member.getUserID()%>" ><br>
		비밀번호: <input type="password" name="userPw" id="userPw" value="<%=member.getUserPW()%>"  size="10" maxlength="10"><br>
		이름: <input type="text" name="userName" id="userName" value="<%=member.getUserNAME()%>"><br>
		나이: <input type="text" name="userAge" id="userAge"value="<%=member.getUserAGE()%>"><br>
		<td>
<%
					if(!sessionId.equals(member.getUserID())){
						%>
						<script>
							document.getElementById('id').style.display="none";
							document.modifyForm.userId.value="";
						</script>
							<%
					}else{
						// 세션아이디와 게시글 아이디가 일치 하면
						%>		
						<script>

						var modifyForm=document.modifyForm;
						
						modifyForm.userPw.disabled=false;//disabled 해제
						modifyForm.userName.disabled=false;//disabled 해제							
						modifyForm.userAge.disabled=false;//disabled 해제							
							
			function modifyOk(){
	
			//선택
			var modifyForm=document.modifyForm;//전체 form의 name;
			//userId
		
			if(modifyForm.userId==null || modifyForm.userId==""){
			alert("아이디 입력오류.. 다시 입력해주세요!");			
			modifyForm.userId.focus();			
			return false;//joinOk함수 종료
			}
			if(modifyForm.userPw==null || modifyForm.userPw==""){
			alert("비밀번호 입력오류.. 다시 입력해주세요!");
			modifyForm.userPw.focus();
			return false;//joinOk함수 종료
			}
			//이름
			if(modifyForm.userName==null || modifyForm.userName==""){
			alert("이름 입력오류.. 다시 입력해주세요!");
			modifyForm.userName.focus();
			return false;//joinOk함수 종료
			}
		
			//나이 
			if(modifyForm.userAge==null || modifyForm.userAge==""){
			alert("나이 입력오류.. 다시 입력해주세요!");
			modifyForm.userAge.focus();
			return false;//joinOk함수 종료
			}
		
			alert("회원 수정 실행 ")
			modifyForm.submit();// form의 정보를 서버에 전송...action="JOIN.do"
				
			}
					</script>
					
						<script>
						function memberDelete(){
							var userId=modifyForm.userId;
							var userPw=modifyForm.userPw;
							var userName=modifyForm.userName;
							var userAge=modifyForm.userAge;
							
							alert("게시글 삭제");
							
							location.href="MemberDelete.do?userId="+userId.value+"&userPw="+userPw.value+"&userName="+userName.value+"&userAge="+userAge.value;
							
						}						
							
						</script>
						<%
					}
				
				%>
			</td>
	</form>
</div>	

</body>
</html>