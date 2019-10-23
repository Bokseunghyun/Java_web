/**
 * jquery validation plugin 이용하여 폼 유효성 검증
 * 사용자가 원하는 메세지와 유효성을 지정할 수 있음
 * 
 * signupForm.html 유효성 검사
 */

$(function(){
	$("#signupForm").validate({
		//검증규칙 작성
		rules:{
			username:{ //필수로 입력되어야 하며, 2~4자리
				required:true,
				minlength:2,
				maxlength:4
			},
			password:{
				required:true,
				rangelength:[8,15],
				validPwd:true
			
			},
			confirm_password:{ //필수입력 8~15자리 이전비밀번호와 동일한값
				required: true,
				rangelength: [8,15],
				equalTo:"#password",
				validPwd:true
			},
			email:{
				required: true,
				email:true
				
			},
			topic:{
				required:"#newsletter:checked",
				minlength:2
			},
			policy:{
				required: true
			}
		},
		//규칙에 따른 메세지 작성
		messages:{
			username:{ //필수로 입력되어야 하며, 2~4자리
				required:"이름은 필수요소 입니다.",
				minlength:"이름은 최소 2자리 이상 이여야 합니다.",
				maxlength:"이름은 최대 4자리 까지 허용 합니다."
			},
			password:{
				required:"비밀번호는 필수요소 입니다.",
				rangelength:"비밀번호는 8~15자리까지 허용합니다.",
				
			},
			confirm_password:{ //필수입력 8~15자리 이전비밀번호와 동일한값
				required: "비밀번호는 필수요소 입니다.",
				rangelength: "비밀번호는 8~15자리까지 허용합니다.",
				equalTo:"입력한 비밀번호가 일치하지 않습니다."
				
			},
			email:{
				required: "이메일은 필수요소 입니다.",
				email:"이메일 형식을 확인해 주세요."
				
			},
			topic:"관심사를 적어도 2개는 표시해주세요",
			policy:{
				required:"정책 동의가 필요합니다."
			}
		}
	});
	
	
	$("#newsletter").click(function(){
		var topics=$("#newsletter_topics");
		if(topics.css("display")=='none'){
			$("newsletter_topics").css("display","inline-block");
			
		}else{
			$("newsletter_topics").css("display","none");
		}
	});
});//function 종료

$.validator.addMethod("validPwd",function(value){
	var regPwd=/(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}/;
	return regPwd.test(value);
},'비밀번호는 숫자와 영문자의 조합으로 8~15자리를 사용해야 합니다.');



