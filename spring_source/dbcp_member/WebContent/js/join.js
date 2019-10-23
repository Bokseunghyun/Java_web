/**
 * joinForm.jsp의 폼 유효성 검증
 * userid: 아이디는 숫자, 영대소문자를 이용해서 5~12
 * password:비밀번호는 숫자, 영대소문자, 특수문자를 이용해서 8~15
 * confirm_password : password 규칙동일, 입력값 동일여부 확인
 * email : 이메일규칙에 맞는지 확인
 * name : 필수지정, 2~4
 */

$(function(){
	$("#joinform").validate({
		rules:{//규칙지정
			userid:{
				required:true,
				rangelength:[5,12],
				validId:true
			},
			password:{
				required:true,
				rangelength:[8,15],
				validPwd:true
			},
			cofirm_password:{
				required:true,
				rangelength:[8,15],
				equalTo:"#password"
			},
			email:{
				required:true,
				email:true
			},
			name:{
				required:true,
				rangelength:[2,4]
			}
		},
		messages:{//규칙오류메세지
			
		},
		errorPlacement:function(error,element){
			//에러메세지 위치 지정
			$(element).closest("form").find("small[id='"+element.attr("id")+"']").append(error);
		}
	});
	
	
	$.validator.addMethod("validId",function(val){
		var regId =/(?=[A-Za-z])(?=.*\d)[A-Za-z\d]{5,12}/;
		return regId.test(val);
	})
	
	$.validator.addMethod("validPwd",function(val){
		var regPwd =/(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*]).{8,15}/;
		return regPwd.test(val);
	})
})