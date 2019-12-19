<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	body{
		margin-top:100px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>
	$(function(){
		var error = "${error}";
		if(error=""){
			alert(error);
		}
	})

</script>
</head>
<body>
<div class="card border-success mb-3 mx-auto" style="max-width: 25rem;">
  <div class="card-header">비밀번호 변경</div>
  <div class="card-body">
  	<form id="changePwd" method="post">
  			<div class="form-group row">
	    		<input type="password" class="form-control" size="50" id="current_pw" name="current_pw" placeholder="현재 비밀번호">
	    		<small id="current_pw" class="text-info"></small>
	    	</div>
    		<div class="form-group row">
			    <input type="password" class="form-control" size="50" id="new_pw" name="new_pw" placeholder="새 비밀번호">  
			    <small id="new_pw" class="text-info"></small> 
			</div>
			<div class="form-group row"> 
			    <input type="password" class="form-control" size="50" id="confirm_pw" name="confirm_pw" placeholder="새 비밀번호 확인">
			    <small id="confirm_pw" class="text-info"></small> 
			</div>
			<div class="form-group row "> 
			    <button type="submit" class="btn btn-primary btn-block" onclick=ChangeOk()>변경</button>
			</div> 
    </form>
    
</div>
</div>
    <script>
    function ChangeOk(){
    	if(new_pw==confirm_pw){
    	alert("비밀번호 변경 완료");
    	location.href="/login";
    	}
    	else{
    		alert("비밀번호가 일치하지 않습니다");
    		
    	}
    }
    </script>
</body>
</html>