<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<button type="button" id="mybtn">xml 파일 가져오기</button>
</div>
<div id="result"></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#mybtn").click(function(){
			$.ajax({  
				url : 'member.xml',
				dataType : 'xml', //가져올 데이터의 형태(text,html,xml,json,jsonp,script) 지정
				method : 'get',
				success : function(data){ 
					var output='';
					$(data).find('member').each(function(){ //each는 loop(반복)의 개념
						output+="name : "+$(this).find('name').text()
						+"part : "+$(this).find('part').text()
						+"<br>";
					})
					$("#result").html(output);
				},
				error : function(xhr,status,thrown){
					$("#result").html("<div>"+status
							+"(HTTP - "+xhr.status+")</div>");
				}
			})			
		})
	})
</script>
</body>
</html>