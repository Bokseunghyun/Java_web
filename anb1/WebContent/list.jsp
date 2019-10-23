<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Floating Aside HTML Buttons - v2</title>
  
<link href="https://fonts.googleapis.com/css?family=Montserrat:800&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" >

  
  
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>

  <div class="float-container">
  
  <a href="index2.jsp" class="icon two">HOME</a>
  <%
		if(session.getAttribute("sessionId")==null){
	%>
	<a href="index1.jsp" class="icon one">로그인</a>
	<%
		}else{
			%>
			
	<a href="my.jsp" class="icon one">내 정보</a>
	<a href="selectshop.jsp" class="icon one">물품 보기</a>
	<a href="bordList.jsp" class="icon one">추천 상품</a>
	<a href="LOGOUT.do" class="icon three" style="background:#f00;color:#fff">로그아웃</a>
	
			
			<%			
		}
	%>

	


</div>




</body>

</html>
