<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <!-- jQuery --> 
    <script src="/resources/Board/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/Board/vendor/metisMenu/metisMenu.min.js"></script>    
    <!-- Custom Theme JavaScript -->
    <script src="/resources/Board/dist/js/sb-admin-2.js"></script>
  <!-- Bootstrap Core CSS -->
    <link href="/resources/Board/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
   <link href="/resources/Board/vendor/metisMenu/metisMenu.min.css" rel="stylesheet"> 
    <!-- Custom CSS -->
    <link href="/resources/Board/dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="/resources/Board/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">   
<title>Insert title here</title>
</head>
<body>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control"  name="bno" readonly="readonly" value="${vo.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" readonly="readonly"value="${vo.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content" readonly="readonly">${vo.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${vo.writer}">                				
                				</div>  
                			
                				<button type="button" class="btn btn-default">Modify</button>     			
                				<button type="reset" id = "listBtn" class="btn btn-info">List</button>          			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
            
	              
	              
          <!-- 댓글 영역 -->  
            <div class="row">
            	<div class="col-lg-12">
            		<div class="panel panel-default">
            			<div class="panel-heading">
	            			<i class="fa fa-comments fa-fw"></i>      				
	            			Reply
	            			<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right' data-toggle="modal">New Reply</button>
            			</div><!-- ./ end panel-heading  --> 
            			<div class="panel-body">
            				<ul class="chat">
            					<!--  start reply -->
            					<li class="left clearfix" data-rno='12'>
            						<div>
            							<div class="header">
	            							<strong class="primary-font">user00</strong>
	            							<small class="pull-right text-muted">2018-11-06 10:10</small>
            							</div>
            							<p>Good Job!!!</p>
            						</div>            						
            					</li>
            				</ul>            			
            			</div>
            			<!-- ./ end panel-body  --> 
            			
            			<div class="panel-footer"> <!-- 댓글 페이지 영역 -->
            			
            			</div>
            			
            		</div>
            	</div>
            </div>
            
	<!-- 수정 폼을 다시 띄우고, 페이지 나누기 정보를 위한 폼 -->       
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                            	<label>Replyer</label>
                            	<input class="form-control" name="replyer" value="replyer">
                            </div>
                            <div class="form-group">
                            	<label>Reply</label>
                            	<input class="form-control" name="reply" value="New Reply">
                            </div>
                            <div class="form-group">
                            	<label>Reply Date</label>
                            	<input class="form-control" name="replyDate" value="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" id="modalModBtn">Modify</button>
                            <button type="button" class="btn btn-danger" id="modalRemoveBtn">Remove</button>
                            <button type="button" class="btn btn-primary" id="modalRegisterBtn">Register</button>              
                            <button type="button" class="btn btn-success" id="modalCloseBtn">Close</button>                    
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
	
	
	<!-- 게시글수정에 필요한 폼 -->
	<form action="/board/modify" id="operForm">
	
		<input type="hidden" id="bno" name="bno" value="${vo.bno}"/>
		<input type="hidden" id="pageNum" name="pageNum" value="${cri.pageNum}"/>
		<input type="hidden" id="amount" name="amount" value="${cri.amount}"/>
		<input type="hidden" id="type" name="type" value="${cri.type}"/>
		<input type="hidden" id="keyword" name="keyword" value="${cri.keyword}"/>
	
	</form>
	
	<script src="/resources/Board/js/reply.js"></script>
<script>
	
	$(function(){
		
		//현재 글번호 담기
		var bno=${vo.bno};	
		//operForm 담기
		 var form=$("#operForm");
		   
		//현재글의 댓글목록
		var replyList=$('.chat');
		//댓글 페이지의 초기값 1페이지
		var pageNum=1;
		//현재글의 댓글페이지영역
		var replyPagefooter=$('.panel-footer');
		
		//댓글 페이지 나누기
		function showReplyPage(replyCnt){
			//Math.ceil = 소수점이 존재할때 값을 올림  / ex : 3.4 = 4
			//Math.floor = 소수점이 존재할때 소수점값을 버림 / ex : 3.4 = 3
			//Math.round = 소수점이 존재할때 반올림  / ex : 3.5 = 4
			var endPage = Math.ceil(pageNum/10.0)*10; 
			var startPage = endPage-9;
			//시작페이지가 1페이지가 아닐때 뒤로가기에 사용할 변수
			var prev = startPage!=1;
			//다음페이지 이동에 사용할 변수
			var next = false;
			
			//댓글의 끝페이지(10)가 댓글 갯수보다 많거나같을경우 댓글갯수로 끝페이지 계산 
			if(endPage*10>=replyCnt){
				endPage = Math.ceil(replyCnt/10.0);
			}
			
			//댓글갯수가 댓글페이지보다 많을경우 다음페이지로 이동
			if(endPage*10<replyCnt){
				next = true;
			}
			
			//댓글 페이징 처리
			var str="<ul class='pagination pull-right'>";
			
			if(prev){
					str+="<li class='page-item'><a class='page-link' href='";
					str+=(startPage-1)+"'>Previous</a></li>";
			}
				
				for(var i=startPage; i<=endPage; i++){
					var active = pageNum==i?"active":"";
					str+="<li class='page-item"+active+"'>";
					str+="<a class='page-link' href='"+i+"'>"+i+"</a></li>";
			}
				
				if(next){
					str+="<li class='page-item'><a class='page-link' href='";
					str+=(endPage+1)+"'>Next</a></li>";
			}
				str+="</ul></div>";
				//html은 해당 태그안의 내용을 바꿔주는 기능
				//str을 panel-footer안에 넣기 
				replyPagefooter.html(str);
			
		} // showReplyPage 종료
			
			$(replyPagefooter).on("click","li a",function(e){
				//이벤트 중지
				e.preventDefault();
				
				//attr은 해당 태그의 속성을 가져오는기능
				//사용자가 누른 번호 가져오기 
				pageNum=$(this).attr("href");
				showList(pageNum);
			})
			
		
			function showList(page){
				//reply.js에 있는 함수
				//replyService.getList(param,callback)중 param에 bno,page 값 넣기, callback함수
				replyService.getList({bno:bno,page:page||1},
						function (replyCnt,list){
							if(page==-1){
								pageNum=Math.ceil(replyCnt/10.0);
								showList(pageNum);
								return;
							}
							//보여줄 댓글이 없는경우
							if(list ==null || list.lenghth==0){
								replyList.html("");
								return;
							}
							
							//댓글 내용이 있는 경우
							var str="";
							for(var i=0, len=list.length||0;i<len;i++){
								str+="<li class='left claerfix' data-rno='"+list[i].rno+"'>";
								str+="<div><div class='header'><strong class='primary-font'>";
								str+=list[i].replyer+"</strong>";
								str+="<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small>";
								str+="</div><p>"+list[i].reply+"</p></div></li>";
							}
							replyList.html(str);
							
							//페이징 처리 함수 호출
							showReplyPage(replyCnt);
				
				}); //getList 종료
			
		}// showList 종료
	
			//처음으로 보여줄 댓글페이지를 1페이지로 설정
			showList(1);
	
			//모달 처리
   			var modal=$(".modal");
			
   			var modalMoBtn=$("#modalModBtn");
   			var modalRemoveBtn=$("#modalRemoveBtn");
   			var modalRegisterBtn=$("#modalRegisterBtn");
   			
   			$("#modalCloseBtn").click(function(){
   				modal.modal("hide");
   			})
   			
   			$("#addReplyBtn").click(function(){
   			//input 태그 안에 들어잇는 모든 값 초기화
   			modal.find("input").val("");
   			//date가 들어가 있는 div 영역 안보이게 하기
   			modalInputReplyDate.closest("div").hide();
   			//close 버튼만 제외하고 전부 안보이게 만들기
   			modal.find("button[id!='modalCloseBtn']").hide();
   			//등록 버튼만 다시 보이게 만들기
   			modalRegisterBtn.show();
   				 
   			modal.modal("show");
   			})
   			
   			
   			$("#listBtn").click(function(){
		   //operForm 보내기(페이지 정보가 들어있기 때문에 bno를 지우고 리스트로 이동)
		   form.find("input[name='bno']").remove();
		   form.attr("action","/board/list");
		   form.submit();
	   });
	   
	   $(".btn-default").click(function(){
		   form.submit();
	   });
	   
	   
//댓글 처리 부분 시작
			
			//modal클래스에 있는 name=reply를 가진 요소 찾기
   			var modalInputReply = modal.find("input[name='reply']");
   			//modal클래스에 있는 name=replyer를 가진 요소 찾기
   			var modalInputReplyer = modal.find("input[name='replyer']");
   			//modal클래스에 있는 name=replyDate를 가진 요소 찾기
   			var modalInputReplyDate = modal.find("input[name='replyDate']");
	
   		$(modalRegisterBtn).click(function(){
		
			//댓글 등록
			var reply={
				bno : bno,
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val()
			};
		
			replyService.add(reply,
				function(result){
					//modal 창에 쓴 내용 초기화
					modal.find("input").val("");
					modal.modal("hide");
					//댓글 갱신
					showList(-1);
			}); 
		})

		
		//댓글 수정
		$(modalMoBtn).click(function(){
			
			var reply={rno:modal.data("rno"),reply:modalInputReply.val()};
			
		replyService.update(reply,function(result){
			console.log(result);
			modal.modal("hide");
			showList(pageNum);
		}); 
		
		})
		
		
	//댓글 삭제
	$(modalRemoveBtn).click(function(){
		
		var rno = modal.data("rno");
	 replyService.remove(rno,function(result){
		modal.modal("hide");				
		showList(pageNum);
		},function(err){
		alert('에러발생');
		}) 
	})
	
		
	//이벤트 위임 방식:현재 존재하는 요소에 이벤트를 걸고 나중에 변경하는 방식
	
	$(".chat").on("click","li",function(){ //클릭했을 때 li의 내용을 가져옴
		
		var rno=$(this).data("rno"); //data-rno값을 가져옴
		
	//댓글 하나 정보보기
	replyService.get(rno,function(data){
		modalInputReply.val(data.reply);
		modalInputReplyer.val(data.replyer);
		modalInputReplyDate.val(replyService.displayTime(data.replyDate));
		modalInputReplyDate.attr("readonly","true");
		modal.data("rno",data.rno);
		
		modal.find("button[id!='modalCloseBtn']").hide();
		modalMoBtn.show();
		modalRemoveBtn.show();
		modal.modal("show");
	})
	    
	})
   			
})


</script>	

	     
</body>
</html>