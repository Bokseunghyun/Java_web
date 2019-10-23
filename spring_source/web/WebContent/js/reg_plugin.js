/**
 * jquery validation plugin 이용하여 폼 유효성 검증
 * 사용자가 원하는 메세지와 유효성을 지정할 수 있음
 * 
 * register_jquery_validate.html 유효성 검사
 */

$(function(){
	$("#join").validate({
	//검증규칙 작성
		rules:{
			username:{
				required: true,
				minlength:2,
				maxlength:4
			},
			userpw:{
				required: true,
				rangelength:[8,15],
				validPwd:true
			},
			confirmpwd:{
				required: true,
				rangelength:[8,15],
				equalTo:"#userpw",
				validPwd:true
			},
			gender:{
				required:true,
			},
			email:{
				required:true,
				email:true
			},
			mobile:{
				required:true,
			},
			hobby:{
				required:true,
			}
			
		},
		messages:{
			username:{ //필수로 입력되어야 하며, 2~4자리
				required:"이름은 필수요소 입니다.",
				minlength:"이름은 최소 2자리 이상 이여야 합니다.",
				maxlength:"이름은 최대 4자리 까지 허용 합니다."
			},
			userpw:{
				required:"비밀번호는 필수요소 입니다.",
				rangelength:"비밀번호는 8~15자리까지 허용합니다.",
				
			},
			confirmpwd:{ //필수입력 8~15자리 이전비밀번호와 동일한값
				required: "비밀번호는 필수요소 입니다.",
				rangelength: "비밀번호는 8~15자리까지 허용합니다.",
				equalTo:"입력한 비밀번호가 일치하지 않습니다."
				
			},
			gender:{
				required:"성별 필수요소 입니다.",
				
			},
			email:{
				required: "이메일은 필수요소 입니다.",
				email:"이메일 형식을 확인해 주세요."
				
			},
			mobile:{
				required: "휴대폰번호는 필수요소 입니다."
			},
			hobby:{
				required:"취미 선택이 필요합니다."
			}
		}
		});

});//function 종료


$.validator.addMethod("validPwd",function(value){
	var regPwd=/(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}/;
	return regPwd.test(value);
},'비밀번호는 숫자와 영문자의 조합으로 8~15자리를 사용해야 합니다.');

