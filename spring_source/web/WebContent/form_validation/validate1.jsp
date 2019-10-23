<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class="container" style="margin-top: 50px">
		<form action="" id="myform">
			<div class="form-group">
			    <label for="inputEmail">Email address</label>
			    <input type="email" class="form-control" id="inputEmail" name="inputEmail" 
			    					placeholder="Enter email" required="required">			    
			</div>
			<div class="form-group">		    
			    <button type="submit" class="btn btn-primary mb-2">전송</button>			    
			</div>	
		</form>	
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- validate 플러그인 cdn 경로 -->
<!-- jquery validation plugin cdn 경로-->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<!-- 메세지 한글로 나오게 만들기 -->
<script src="../js/messages_ko.min.js"></script>

<script>
	$("#myform").validate();
</script>



</body>
</html>