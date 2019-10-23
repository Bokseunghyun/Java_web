$(function(){
	$("#joinform").validate({
		errorPlacement:function(error,element){ //에러메시지 위치 지정
			/*var errorElement=$(element).closest("form").find("label[for='"+element.attr("id")+"']")
				.append(error); //=> 이렇게 하면 기존 메세지 뒤로 붙여지기 때문에*/
			$(element).closest("form").find("small[id='"+element.attr("id")+"']")
				.append(error);
			//errorElement.text().replace(error);나중에 확인해보기	
		},	
		rules:{
			userid:{
				required:true,
				validId : true,
				remote:{	//$.ajax()			
			        url: "/checkId",
			        type: "post",
			        data: {
			        	userid: function() {			        	 
			        		return $('#userid').val();
			          }						
			       }		        
				}	
			},
			password:{
				required:true,
				validPwd : true,
				validPwdLetter: true
			},
			confirm_password:{
				required:true,
				validPwd : true,
				validPwdLetter: true,
				equalTo: "#password"
			},
			username:{
				required:true,
			},
			gender:{
				required:true,
			},
			email:{
				required:true,
				validEmail:true
			}
		},
		messages:{
			userid:{
				required: "필수 입력 요소입니다.",
				remote : "이 아이디는 사용중입니다."
			},
			password:{
				required:"필수 입력 요소입니다.",				
			},
			confirm_password:{
				required : "필수 입력 요소입니다.",
				equalTo : "이전 비밀번호와 동일하게 입력해주세요"
			},
			email:{
				required : "이메일은 필수입니다."				
			},
			username:"이름은 필수입니다.",
			gender : "성별은 필수입니다."
		}
	});
});
$.validator.addMethod("validId", function(value) {
	var regId=/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,12}$/;
	return regId.test(value);
}, '아이디는 문자,숫자를 사용하여 5~12자리까지 사용가능합니다.');
$.validator.addMethod("validEmail", function(value) {
	var regEmail=/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regEmail.test(value);
}, '이메일 형식이 다릅니다.');
$.validator.addMethod("validPwd", function(value) {
	var regPwd=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/; 
	return regPwd.test(value);
}, '비밀번호는 문자,숫자,특수문자를 사용하여 8~15자리까지 사용가능합니다.');
$.validator.addMethod("validPwdLetter", function(value) {
	var regPwd=/(\w)\1\1\1/; 
	return !regPwd.test(value);
}, '동일한 숫자와 문자를 연속으로 사용할 수 없습니다');