<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap Core CSS -->
    <link href="/resources/Board/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
   <link href="/resources/Board/vendor/metisMenu/metisMenu.min.css" rel="stylesheet"> 
    <!-- Custom CSS -->
    <link href="/resources/Board/dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="/resources/Board/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Register</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Register Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form">
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content"></textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer">                				
                				</div>  
                				<button type="submit" class="btn btn-default">Submit</button>              			
                				<button type="reset" class="btn btn-default">reset</button>              			
                			</form>
                		</div>
                	</div>
                </div>
            </div>      
            
<script>
$(function(){	
	var formObj=$("form[role='form']"); //글등록 폼 가져오기
	
	$("button[type='submit']").click(function(e){
		e.preventDefault();//Register 폼의 submit 버튼 클릭시 이벤트 막기	
		
		//글등록 버튼 클릭시 사용자가 작성한 내용
		var str="";
		
		//uploadResult ul이 가지고 있는 값 수집하기
		$(".uploadResult ul li").each(function(i,obj){ //i는 배열의 인덱스 또는 key, obj는 해당 인덱스가 가진 값이나 key가 가진 값
			var job=$(obj);
			
			str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+job.data("uuid")+"'>";
			str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+job.data("path")+"'>";
			str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+job.data("fileName")+"'>";
			str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+job.data("type")+"'>";
		});
		
		//글 등록폼 보내기
		formObj.append(str).submit();
		
	})
})
</script>                  

</body>
</html>