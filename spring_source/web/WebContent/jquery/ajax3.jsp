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
				url : 'school.xml',
				dataType : 'xml', //가져올 데이터의 형태(text,html,xml,json,jsonp,script) 지정
				method : 'get',
				success : function(data){ 
					//$("#result").html(data);
					var title=$(data).find("title").text();
					var time=$(data).find("time").text();
					var teacher=$(data).find("teacher").text();
					
					$("#result").html("title : "+title
							+" time : "+time
							+" teacher : "+teacher);
					
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
<%-- callback(자동호출되는 함수)
	 success : request 가 성공이 된 경우 자동호출
	 error : request 가 실패가 된 경우 자동호출
 --%>















