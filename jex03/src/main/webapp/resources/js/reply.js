
console.log("Reply Module..........");
var replyService = (function(){
	console.log("replyService............... add loading complete");
	function add(reply, callback){
		console.log("reply...............");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	
	console.log("replyService............... getList loading complete");
	function getList(param, callback){
		
		console.log("getList............... start");
		var bno = param.bno;
		
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
				function(data){
			if(callback){
				callback(data);
			}
		}).fail(function(xhr, status, err) {
			if(error){
				error();
			}
		});
		console.log("getList............... end");
	}
	
	console.log("replyService............... remove loading complete");
	function remove(rno, callback, error){
		console.log("remove............... start");
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr){
				console.log("deleteResult..............."+deleteResult);
				console.log("status....................."+status);
				console.log("xhr....................... "+xhr);
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				console.log("xhr........................"+xhr);
				console.log("status....................."+status);
				console.log("er........................."+er);
				if(error){
					error(er);
				}
			}
		});
		console.log("remove............... end");
	}
	
	console.log("replyService............... update loading complete");
	function update(reply, callback, error){
		console.log("update............... start");
		console.log("RNO: "+reply.rno);
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		console.log("update............... end");
	}
	
	console.log("replyService............... get loading complete");
	function get(rno, callback, error){
		console.log("get............... start");
		
		$.get("/replies/" + rno + ".json", function(result){
			
			if(callback){
				callback(result);
			}
			
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
		
		console.log("get............... end");
		
	}
	
	
	//시간 display
	console.log("replyService............... displayTime loading complete");
	function displayTime(timeValue){
		console.log("displayTime............... start");
		
		var today = new Date();
		var gap = today.getTime() - timeValue; //시간 차이
		var dateObj = new Date(timeValue);
		var str = "";
		
		//ms(1000) * 초 * 분 * 시 => 1일
		//gap이 1일 미만인 경우
		if(gap < (1000 * 60 * 60 * 24)){
			
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			console.log("displayTime............... 1end");
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss ].join('');
			
		}else{
			//gap이 1일 이상인 경우
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1;  //getMonth() is zero-base
			var dd = dateObj.getDate();
			
			console.log("displayTime............... 2end");
			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd ].join('');
			
		}
		

	}
	
	
	
	return {
		add:add,
		getList:getList,
		remove:remove,
		update:update,
		get:get,
		displayTime:displayTime
	};
})();