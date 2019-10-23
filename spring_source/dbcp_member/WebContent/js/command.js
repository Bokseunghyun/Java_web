/**
 * login 후 생성되는 버튼 이벤트 작성 
 */

$(function(){
	$("#modify").click(function(){
		location.href="modifyForm.jsp";
	});
	$("#logout").click(function(){
		location.href="../logout.do";
	});
	$("#leave").click(function(){
		location.href="leaveForm.jsp";
	});
})