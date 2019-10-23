<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type='button' class='btn btn-xs pull-right'>Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번 호</th>
                                        <th>제 목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>									
                                </thead>
								<!-- 게시판 리스트 반복문 -->
								<c:forEach var="vo" items="${list}">
									<tr>
									<td>${vo.bno}</td>
									<td><a href="${vo.bno}" class="move">${vo.title}</a><strong> [${vo.replyCnt}]</strong></td>
									<td>${vo.writer}</td>
									<td><fmt:formatDate pattern="yyyy=MM-dd HH:mm" value="${vo.reqdate}"/></td>
									<td><fmt:formatDate pattern="yyyy=MM-dd HH:mm" value="${vo.updatedate}"/></td>
									</tr>
								</c:forEach>
                            </table>
							<div class="row"> <!-- start search -->
                            	<div class="col-md-12">
                            	<div class="col-md-8"><!--search Form-->
                            			<form id="searchForm" action="" method="get">
	                            			<select name="type">
	                            				<option value="">---</option>	
	                            				<option value="T" <c:out value="${pageDTO.cri.type eq T?'selected':''}"/>>제목</option>	
	                            				<option value="C" <c:out value="${pageDTO.cri.type eq C?'selected':''}"/>>내용</option>	
	                            				<option value="W" <c:out value="${pageDTO.cri.type eq W?'selected':''}"/>>작성자</option>	
	                            				<option value="TC" <c:out value="${pageDTO.cri.type eq TC?'selected':''}"/>>제목  or 내용</option>	
	                            				<option value="TW" <c:out value="${pageDTO.cri.type eq TW?'selected':''}"/>>제목  or 작성자</option>                           			
	                            				<option value="TCW" <c:out value="${pageDTO.cri.type eq TCW?'selected':''}"/>>제목  or 내용  or 작성자</option>	
	                            			</select>
	                            			<input type="text" name="keyword" value="${pageDTO.cri.keyword}">	                            			
	                            			<input type="hidden" name="pageNum" value="${pageDTO.cri.pageNum}"/>
	                            			<input type="hidden" name="amount" value="${pageDTO.cri.amount}"/>
	                            			<button class="btn btn-default">Search</button>
                            	   		</form>
                            	   	</div>
                            	  <div class="col-md-8"><!--search Form-->
                            		
                            	   </div>
                            	   <div class="col-md-2 col-md-offset-2">
                            	   	<!--페이지 목록 갯수 지정하는 폼-->
                            	   	<select class="form-control" id="amount">						
										<option value="10" <c:out value="${pageDTO.cri.amount eq 10?'selected':''}"/>>10</option>				
										<option value="20" <c:out value="${pageDTO.cri.amount eq 20?'selected':''}"/>>20</option>
										<option value="30" <c:out value="${pageDTO.cri.amount eq 30?'selected':''}"/>>30</option>
										<option value="40" <c:out value="${pageDTO.cri.amount eq 40?'selected':''}"/>>40</option>										
								  	</select>
								  	<%-- <select class="form-control" id="amount">						
										<option value="10" <c:out value="${cri.amount eq 10?'selected':''}"/>>10</option>				
										<option value="20" <c:out value="${cri.amount eq 20?'selected':''}"/>>20</option>
										<option value="30" <c:out value="${cri.amount eq 30?'selected':''}"/>>30</option>
										<option value="40" <c:out value="${cri.amount eq 40?'selected':''}"/>>40</option>										
								  	</select>	 --%>	
								  </div>
                             	 </div>                             	 
                      		 </div><!-- end search -->
                            <!-- start Pagination -->
                            <div class="text-center">
                            	<ul class="pagination">
                            		<c:if test="${pageDTO.prev}">
                            			<li class="paginate_button previous">
                            			<a href="${pageDTO.startPage-1}">Previous</a>
                            			</li>
                            		</c:if>
                            		<c:forEach var="idx" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
                            			<li class="pagenate_button ${pageDTO.cri.pageNum==idx?'active':''}">
                            			<a href="${idx}">${idx}</a>
                            			</li>
                            		</c:forEach>
                            		<c:if test="${pageDTO.next}">
                            			<li class="paginate_button next">
                            			<a href="${pageDTO.endPage+1}">Next</a>
                            			</li>
                            		</c:if>
                            	</ul>
                            </div>
                            <!-- end Pagination -->   
                            </div>
                            <!-- end panel-body -->
                        </div>
                        <!-- end panel -->
                    </div>                   
                </div>               
            <!-- /.row -->
<!-- 모달 추가 -->
<div class="modal" tabindex="-1" role="dialog" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" >
        <p>처리가 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
<!-- 페이지 링크 처리를 위한 폼 -->
<form action="/board/list" method="get" id="actionForm">
	<input type="hidden" name="pageNum" value="${pageDTO.cri.pageNum}"/>
	<input type="hidden" name="amount" value="${pageDTO.cri.amount}"/>
	<input type="hidden" name="type" value="${pageDTO.cri.type}"/>
	<input type="hidden" name="keyword" value="${pageDTO.cri.keyword}"/>
</form>


<!-- 스크립트 -->
<script>
	//regBtn 클릭시 새글 쓰기 폼 보여주기
	$(function(){
		var result = '${result}';
		checkModal(result);
		//history 값 변경
		//history.pushState : 새로운 경로 삽입 
		history.replaceState({}, null, null);
		
		function checkModal(result){
			if(result==='' || history.state) // ==는 값을 비교 ===는 타입까지 비교 
				return;
			if(parseInt(result)>0){ //parseInt로 result값을 정수타입으로 변환
				$(".modal-body").html("게시글"+parseInt(result)+"번이 등록되었습니다");
			}
			$("#myModal").modal("show");
		}
		
		$("#regBtn").click(function(){
			
		location.href="/board/register";
		})

	//페이지 번호 클릭시 동작하는 부분
	var actionForm=$("#actionForm");
	$(".pagenate_button a").click(function(e){
		//a 태그 속성 중지 = http://localhost:8083/board/1
		e.preventDefault();
		
		//actionForm 안의 속성값 변경하기
		//pageNum값은 사용자가 선택한 pageNum으로 변경
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		//amount 값도 사용자가 선택한 amount로 변경
		actionForm.submit();
	});
	//타이틀 클릭 시 동작하는 부분
	$(".move").click(function(e){
		//a 태그 속성 중지		
		e.preventDefault();
		
		//actionForm을 보낼 때 bno 담아서 같이 보내기
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr('href')+"'>");
		actionForm.attr("action","/board/read");
		actionForm.submit();
		
	});
	
	//amount 값도 사용자가 선택한 amount로 변경
	$(".form-control").change(function(){
		//사용자가 선택한 페이지당 개수 가져오기
		var amount=$(this).val();
		//actionForm에 사용자가 선택한 개수 세팅해주기
		actionForm.find("input[name='amount']").val(amount);
		//폼 보내기
		actionForm.submit();
	})
	
	//searchForm 동작
	$(".btn-default").click(function(){
		var searchForm= $("#searchForm");
		//검색기준 비어있는지 확인 => 비어있는경우 경고창 띄워주고 돌려보내기
		if(!searchForm.find("option:selected").val()){
			alert("검색 종류를 확인해 주세요");
			return false;
		}
		
		//searchForm 에서 검색어가 비어 있는지 확인
		
		if(!searchForm.find("input[name='keyword']").val()){
			alert("존재하지 않는 검색어");
			
			searchForm.find("input[name='keyword']").focus();
			return false;
		}
		//form submit
			searchForm.submit();
		
	})
	
})	
</script>


<%@include file="../includes/footer.jsp" %>       