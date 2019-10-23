<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
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
            
            <!-- register 파일 등록 부분 --> 
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">File Attach</div>
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" multiple>
				</div>
				<div class='uploadResult'>
					<ul>
				
					</ul>
				</div>
     			</div><!-- end uploadResult -->
		</div><!-- end panel-default -->
	</div>
</div>      
<script>
$(function(){	
	var formObj=$("form[role='form']"); //글등록 폼 가져오기
	
	$("button[type='submit']").click(function(e){
		e.preventDefault();//Register 폼의 submit 버튼 클릭시 이벤트 막기	
		
		//글등록 버튼 클릭시 사용자가 작성한 내용 + 첨부파일 정보 같이 보내기
		var str="";
		
		//uploadResult ul이 가지고 있는 값 수집하기
		$(".uploadResult ul li").each(function(i,obj){
			var job=$(obj);
			
			str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+job.data("uuid")+"'>";
			str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+job.data("path")+"'>";
			str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+job.data("fileName")+"'>";
			str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+job.data("type")+"'>";
		});
		
		//글 등록폼 보내기
		formObj.append(str).submit();
		
	})
	$("input[type='file']").change(function(){
		console.log("ajax 파일 업로드 호출");
		
		//multipart/form-data 를 ajax 로 쉽게 처리할 수 있는 기능 사용
		var formData=new FormData();
		//첨부 파일 목록 배열로 받기
		var inputFile=$("input[name='uploadFile']");
		var files=inputFile[0].files;
		
		for(var i=0;i<files.length;i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : '/uploadAjax',
			type : 'post',
			processData : false, //데이터를 서버로 전송할 때 query string 형태로 보내지 않음
			contentType : false, // application/~~~  이기 때문에 fasle로 지정해서 원하는 형태를 보냄
			data : formData,
			dataType : 'json',  //서버에서 보내주는 값
			success:function(result){
				console.log(result);
				showUploadedFile(result);
			},
			error:function(request,status,error){
				console.log(status);
			}				
		})//ajax 종료
	})//uploadBtn 종료
	
	//첨부파일 목록 보여주기
	function showUploadedFile(uploadResultArr){
		var str="";
		var uploadResult=$(".uploadResult ul");
		
		$(uploadResultArr).each(function(i,obj){
			if(!obj.fileType){//이미지 파일이 아닌 경우
				var fileCallPath=
					encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
				str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str+="data-fileName='"+obj.fileName+"data-type='"+obj.fileType+"'>";
				str+="<div>";
				str+="<span>"+obj.fileName+"</span> ";
				str+="<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+fileCallPath+"' data-type='file'>";
				str+="<i class='fa fa-times'></i></button><br>";
				str+="<a href='/download?fileName="+fileCallPath+"'>";
				str+="<img src='/resources/img/attach.png'></a>";				
				str+="</div></li>";
			}else{//이미지 파일인 경우
				var fileCallPath=
					encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
				
				var oriPath=obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName;
				// \ 를 /로 변경
				oriPath=oriPath.replace(new RegExp(/\\/g),"/");
				
				str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str+=" data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>"
				str+="<div>";
				str+="<span>"+obj.fileName+"</span> ";				
				str+="<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+fileCallPath+"' data-type='image'>";
				str+="<i class='fa fa-times'></i></button><br>";
				str+="<a href=\"javascript:showImage(\'"+oriPath+"\')\">";
				str+="<img src='/display?fileName="+fileCallPath+"'></a>";
				str+="</div></li>";
			}
		});
		uploadResult.append(str);
	}
	
	//x를 누르면 삭제하기
	$(".uploadResult").on("click","button",function(){
		var targetFile=$(this).data("file");
		var type=$(this).data("type");
		
		var targetLi=$(this).closest("li");
		
		$.ajax({
			url:'deleteFile',
			dataType:'text',
			data:{
				fileName:targetFile,
				type:type
			},
			success:function(result){
				console.log(result);
				targetLi.remove();
			}
		})
	})
	
	function checkExtension(fileName,fileSize){
		var regex=new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize=2485760;
		if(fileSize>maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
})
//썸네일 클릭시 원본 이미지 보여주기
function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture")
	   .html("<img src='/display?fileName="+fileCallPath+"'>")
	   .animate({width:'100%', height:'100%'}, 1000);
}
</script>                  
<%@include file="../includes/footer.jsp" %>       