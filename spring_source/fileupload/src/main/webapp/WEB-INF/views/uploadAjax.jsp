<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/mycss.css"/>
</head>
<body>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" id="" multiple="multiple"/>		
		<button id="uploadBtn">Submit</button>
	</div>
	<div class="uploadResult">
		<ul></ul>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		
		</div>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#uploadBtn").click(function(){
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
				url : 'uploadAjax',
				type : 'post',
				processData : false, //데이터를 서버로 전송할 때 query string 형태로 보내지 않음
				contentType : false, // application/~~~  이기 때문에 false로 지정해서 원하는 형태를 보냄
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
			var str ="";
			var uploadResult=$(".uploadResult ul");
			$(uploadResultArr).each(function(i,obj){
				if(!obj.image){ //이미지 파일 아닌 경우
					var fileCallPath=encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
					
				str+="<li>";
				str+="<a href='/download?fileName="+fileCallPath+"'>";
				str+="<img src='/resources/img/attach.png'>";
				str+=obj.fileName+"</a>";
				str+="<span data-file='"+fileCallPath+"' data-type='file'> x </span>";
				str+="</li>";
				
				}else{ //이미지 파일인 경우
					var fileCallPath=encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
					var oriPath=obj.uploadPath+"/"+obj.uuid+"_"+obj.fileNale;
					priPath=oriPath.replace(new RegExp(/\\/g),"/");	
					str+="<li>";	
					str+="<a href=\"javascript:showImage(\'"+oriPath+"\')\">";
					str+="<img src='/display?fileName="+fileCallPath+"'>";
					str+=obj.fileName+"</a>";
					str+="<span data-file='"+fileCallPath+"' data-type='image'> x </span>";
					str+="</li>";
				}
			});
			uploadResult.append(str);
		}
		
		//x를 누르면 삭제하기
		$(".uploadResult").on("click","span",function(){
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
		
		
		
		
		
		//원본 이미지 클릭하면 닫기
		$(".bigPictureWrapper").on("click",function(){
			$(".bigPicture").animate({width:'0%',height:'0%'},1000);
			setTimeout(function(){
				$(".bigPictureWrapper").hide();
			},1000);
		})
		
		function checkExtension(fileName,fileSize){
			var regex= new RegExp("(.*?)\.(exe|sh|zip|alz)$");
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
	function showImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();

		$(".bigPicture").html(
				"<img src='/display?fileName="+fileCallPath+"'>").animate({
			width : '100%',
			height : '100%'
		}, 1000);
	}
	
</script>
</body>
</html>









