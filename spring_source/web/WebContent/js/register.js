/**
 * register_jequery_validate.html의 유효성 검증
 */

$(function(){
	//submit 버튼이 클릭되면
	$("join").submit(function(e){
		e.preventDefault();
	
	//사용자가 입력한 값 가져오기
	//userid,userpw,confirmpwd,email,mobile
	var userid = $("#userid").val();
	var userpw = $("#userpw").val();
	var confirmpwd = $("#confirmpwd").val();
	var email = $("#email").val();
	var mobile = $("#mobile").val();
	
	//아이디 : 영대소문자, 숫자, 특수문자가 포함된 6~12
	var regId=/(?=[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*]).{6,12}/;
	
	if(!regId.test(userid)){
		alert("아이디는 영대소문자,숫자,특수문자가 사용 포함된 6~12자리 이여야 합니다.");
		$("#userid").focus();
		return false;
	}
	
	//비밀번호 검증
	if(password_check(userpw)){
		$("#userpw").focus();
		return false;
	}
	
	//userpw != confirmpwd 경고창 띄우고 돌아가도록
	if(userpw != confirmpwd){
		alert("비밀번호와 재입력비밀번호가 일치하지 않습니다");
		$("#confirmpwd").focus();
		return false;
	}
	
	//성별
	var n=$("input[name='gender']:checked").length;
	if(n==0){
		alert("성별을 확인해 주세요");
		$("#gender_m").prop("checked",true);
		return false;
	}
	
	
	//이메일 
	var regEmail=/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	if(!regEmail.test(email)){
		alert("이메일 형식이 올바르지 않습니다.");
		$("#email").focus();
		return false;
	}
	
	
	//핸드폰번호 
	var regMobile=/\d{3}\d{4}\d{4}/;
	if(!regMobile.test(mobile)){
		alert("휴대폰번호 형식이 올바르지 않습니다.");
		$("#mobile").focus();
		return false;
	}
	
	
	//취미
	
	
	
	}); //validate 종료
	
	function password_check(pwd){
		//비밀번호 : 영대소문자, 숫자가 포함된 8~15
		var regPwd=/(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}/;
		if(!regPwd.test(pwd)){
			alert("비밀번호는 숫자와 영문자의 조합으로 8~15자리 이여야 합니다.");
			return false;
		}
		
	}
	
})