/**
 * reply.js : 댓글과 관련된 자바 스크립트 
 */

var replyService =(function(){
	
	//private method
	function add(reply,callback){
		console.log("add method 실행");
		$.ajax({
			type:'post',
			url:'/replies/new',
			data:JSON.stringify(reply),
			contentType:'application/json;charset=utf-8',
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}//add 종료
	
	function getList(param,callback){
		var bno=param.bno;
		var page=param.page || 1;
		
		$.getJSON("/replies/pages/"+bno+"/"+page,function(data){
			if(data!=null){
				if(callback){
				callback(data.replyCnt,data.list);
				}
			}
		})
	}//getList 종료
	
	function update(reply,callback){
		console.log("update 호출");
		$.ajax({
			type:'put',
			url: '/replies/'+reply.rno,
			data: JSON.stringify(reply),
			contentType : 'application/json;charset=utf-8',
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}//update 종료
	
	function remove(rno,callback,error){
		console.log("delete 호출");
		$.ajax({
			type:'delete',
			url:'/replies/'+rno,
			success:function(result){
				if(callback){
					callback(result);
				}
			},
		error:function(xhr,status,err){
			if(error)
				error(err);
		}
		})
	}//remove 종료
	
	function get(rno,callback,error){
		console.log("get 호출");
		$.getJSON("/replies/"+rno,function(result){
			if(callback)
				callback(result);
		}).fail(function(xhr,status,err){
			if(error)
				error(err);
		})
	}
	
	//댓글 목록 보여줄때 시간/날짜 변환해주는 함수
	function displayTime(timeValue){
		var today = new Date();
		var gap=today.getTime()-timeValue;
		var dateObj = new Date(timeValue);
		var str="";
		if(gap<(1000*60*60*24)){
			var hh=dateObj.getHours();
			var mi=dateObj.getMinutes();
			var ss=dateObj.getSeconds();
			
			return [(hh>9?'':'0')+hh,':',(mi>9?'':'0')+mi,':',(ss>9?'':'0')+ss].join('');
			
		}else{
			var yy=dateObj.getFullYear();
			var mm=dateObj.getMonth()+1;
			var dd=dateObj.getDate();
			
			return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
		}
	}
	
	return {
		add:add,
		getList:getList,
		update:update,
		remove:remove,
		get:get,
		displayTime:displayTime
	}
})();