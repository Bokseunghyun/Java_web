/**
 *	로그인한 후 화면 상단에 보여줄 메뉴 부분 
 */

$(function(){
	//register 메뉴 없애기
	$("#2").detach();
	//login 메뉴 없애기
	$("#3").detach();
	//~님 반갑습니다 메뉴 추가
	var $tag="<span class='navbar-text'>"+name+" 님 반갑습니다."
	+"</span>&nbsp;&nbsp;"
	+"<button type='button' id='modify' class='btn btn-success'>"
	+"비밀번호 수정</button>&nbsp;&nbsp;"
	+"<button type='button' id='logout' class='btn btn-primary'>"
	+"로그아웃</button>&nbsp;&nbsp;"
	+"<button type='button' id='leave' class='btn btn-danger'>"
	+"회원탈퇴</button>";
	$("#navbarCollapse").append($tag);
	//비밀번호 수정/로그아웃/회원탈퇴 버튼 추가
})

