<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%@include file="../includes/header.jsp" %>
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
                            	<label>Reply</label>
                            	<input class="form-control" name="reply" value="New Reply">
                            </div>
                            <div class="form-group">
                            	<label>Replyer</label>
                            	<input class="form-control" name="replyer" value="replyer">
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
	
	     
   <form action="/board/modify" id="operForm">
   	<input type="hidden" name="bno" value="${vo.bno}"/>
   	<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
   	<input type="hidden" name="amount" value="${cri.amount}"/>
   	<input type="hidden" name="type" value="${cri.type}"/>
   	<input type="hidden" name="keyword" value="${cri.keyword}"/>
   </form>           
   <script src="/resources/js/reply.js"></script>
   <script>
   		$(function(){
   			//현재 글번호 가져오기
   			var bno=${vo.bno};
   			
   			//전체 댓글 목록을 보여줄 화면 영역 가져오기
   			var replyUL=$(".chat");
   			//showList(1);
   			var pageNum=1;
   			var replyPageFooter=$(".panel-footer");
   			
   			//현재 글번호의 페이지 나누기
   			function showReplyPage(replyCnt){
   				var endNum = Math.ceil(pageNum/10.0)*10;
   				var startNum = endNum-9;
   				var prev=startNum !=1;
   				var next=false;
   				
   				if(endNum*10 >= replyCnt){
   					endNum=Math.ceil(replyCnt/10.0);
   				}
   				if(endNum*10<replyCnt){
   					next=true;
   				}
   			
   				var str="<ul class='pagination pull-right'>";
				
   				if(prev){
   					str+="<li class='page-item'><a class='page-link' href='";
   					str+=(startNum-1)+"'>Previous</a></li>";
   				}
   				
   				for(var i=startNum; i<=endNum; i++){
   					var active = pageNum==i?"active":"";
   					str+="<li class='page-item"+active+"'>";
   					str+="<a class='page-link' href='"+i+"'>"+i+"</a></li>";
   				}
   				
   				if(next){
   					str+="<li class='page-item'><a class='page-link' href='";
   					str+=(endNum+1)+"'>Next</a></li>";
   				}
   				str+="</ul></div>";
   				replyPageFooter.html(str);
   			}
   			
   			$(replyPageFooter).on("click","li a",function(e){
   				e.preventDefault();
   				
   				//사용자가 누른 번호 가져오기
   				pageNum=$(this).attr("href");
   				showList(pageNum);
   			})
   			
   			showList(1);
   			
   			//모달 처리
   			var modal=$(".modal");
   			var modalInputReply = modal.find("input[name='reply']");
   			var modalInputReplyer = modal.find("input[name='replyer']");
   			var modalInputReplyDate = modal.find("input[name='replyDate']");
   			
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
   			
   			$(modalRegisterBtn).click(function(){
   				
   			//댓글 등록
				var reply={
   					bno : bno,
   					reply : modalInputReply.val(),
   					replyer : modalInputReplyer.val()
   				};
   			
   				replyService.add(reply,
   					function(result){
   						/* alert("result : " + result); */
   						//modal 창에 쓴 내용 초기화
   						modal.find("input").val("");
   						modal.modal("hide");
   						//댓글 갱신
   						showList(-1);
   				}); 
   			})
   			
			//댓글 리스트
			function showList(page){
   				replyService.getList({bno:bno,page:page||1},
   					function(replyCnt,list){
						console.log("list : "+list.length);
						
						
						if(page==-1){
							pageNum=Math.ceil(replyCnt/10.0);
							showList(pageNum);
							return;
						}
						
						//보여줄 내용이 없는 경우
						if(list == null || list.length==0){
							replyUL.html("");
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
						replyUL.html(str);
						//페이징 처리 함수 호출
						showReplyPage(replyCnt);
   				}); 
   			}//showList end
   			
   			//댓글 수정(/replies/3+put(patch))
   			
   			$(modalMoBtn).click(function(){
   				
   				var reply={rno:modal.data("rno"),reply:modalInputReply.val()};
   				
				replyService.update(reply,function(result){
					//console.log(result);
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
				
			//댓글 하나 가져오기
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
   <script>
   $(function(){
	   var form=$("#operForm");
	   
	   
	   $("#listBtn").click(function(){
		   //operForm 보내기(페이지 정보가 들어있기 때문에)
			form.find("input[name='bno']").remove();
		   form.attr("action","/board/list");
		   form.submit();
	   });
	   
	   $(".btn-default").click(function(){
		   form.submit();
	   });
   })
   
   </script>
<%@include file="../includes/footer.jsp" %>       