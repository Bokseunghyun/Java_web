<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post">
		<div>
			<label for="num1">첫번째 수</label>
			<input type="text" id="num1" name="num1"/>
		</div>
		
		<div>
			<label for="num2">두번째 수</label>
			<input type="text" id="num2" name="num2"/>
		</div>
		
		<div>
			<input type="hidden" name="page" value="3"/>
		</div>
		
		<div>
			<button type="submit">더하기</button>
		</div>
	
	</form>
</body>
</html>