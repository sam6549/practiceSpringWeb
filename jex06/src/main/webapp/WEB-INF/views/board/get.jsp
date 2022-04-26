<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            
                            <div class="form-group">
                           		<label>Bno</label> 
                           		<input class="form-control" name='bno' value='<c:out value="${board.bno}"/>' readonly="readonly">
                           	</div>
                           	<div class="form-group">
                           		<label>Title</label> 
                           		<input class="form-control" name='title' value='<c:out value="${board.title}"/>' readonly="readonly">
                           	</div>
                           	<div class="form-group">
                           		<label>Text Area</label>
                           		<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}"/></textarea>
                           	</div>
                           	<div class="form-group">
                           		<label>Writer</label>
                           		<input class="form-control" name='writer' value='<c:out value="${board.writer}"/>' readonly="readonly">
                           	</div>
                           	<button data-oper="modify" class="btn btn-default" >
                           	<!-- onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'" -->
                           		Modify
                           	</button>
                           	<button data-oper="list" class="btn btn-info" >
                           	<!-- onclick="location.href='/board/list'" -->
                           		List
                           	</button>
                            
                            <form id='operForm' action ="/board/modify" method="get">
                            	<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>' >
                            	<input type='hidden' id='pageNum' name='pageNum' value='<c:out value="${cri.pageNum}"/>' >
                            	<input type='hidden' id='amount' name='amount' value='<c:out value="${cri.amount}"/>' >
                            	<input type='hidden' id='keyword' name='keyword' value='<c:out value="${cri.keyword}"/>' >
                            	<input type='hidden' id='type' name='type' value='<c:out value="${cri.type}"/>' >
                            </form>
                       
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <!-- 첨부파일 영역 -->
            <div class='bigPictureWrapper'>
				<div class='bigPicture'>
				</div>
			</div>
            <div class="row">
            	<div class ="col-lg-12">
            		<div class="panel panel-default">
            			<div class="panel-heading">Files</div>
            			<!-- /.panel-heading -->
            			<div class="panel-body">
            				<div class='uploadResult'>
            					<ul>
            					</ul>
            				</div>
            			</div>
            		</div>
            		<!-- end panel-body -->
            	</div>
            	<!-- end panel-body -->
            </div>
            <!-- /.row -->
            
            <!-- 댓글영역 -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                    	<!-- 
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>Reply
                        </div>
                         -->
                        <!-- New Reply button 추가 -->
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>Reply
                            <button id ='addReplyBtn' class ='btn btn-primary btn-xs pull-right'>New Reply</button>
                        </div>
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul class="chat">
                            	
                            	<!--  start reply -->
                            	<!--
                            	<li class="left clearfix" data-rno='12'>
                            		
                            		<div>
                            			<div class="header">	 
                            				<strong class="primary-font">user00</strong>
                            				<small class="pull-right text-muted">2018-01-01 13:13</small>
                            			</div>
                            			<p>Good job!</p>
                            		</div>
                            		
                            	</li>
                            	-->
                            	<!-- end reply -->
                            	
                            </ul>
                            <!-- ./end ul -->
                        </div>
                        <!-- /.panel .chat-panel -->
                        <div class="panel-footer">
                        	
                        </div>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            
            
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                        </div>
                        <div class="modal-body">
                        	
                        	<div>
                        		<label>Reply</label>
                        		<input class="form-control" name='reply' value='New Reply!!!!'>
                        	</div>
                        	<div>
                        		<label>Replyer</label>
                        		<input class="form-control" name='replyer' value='replyer'>
                        	</div>
                        	<div>
                        		<label>Reply Date</label>
                        		<input class="form-control" name='replyDate' value=''>
                        	</div>
                            
                        </div>
                        <div class="modal-footer">
                            <button id='modalModBtn' type="button" class="btn btn-warning" >Modify</button>
                            <button id='modalRemoveBtn' type="button" class="btn btn-danger" >Remove</button>
                            <button id='modalRegisterBtn' type="button" class="btn btn-primary" >Register</button>
                            <button id='modalCloseBtn' type="button" class="btn btn-default" >Close</button>
                            
                            
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            

<!-- 20220413 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
$(document).ready(function(){
	console.log("/board/getAttachList 시작");
	(function(){
		var bno = '<c:out value= "${board.bno}"/>';
		
		$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
			console.log(arr);
			
			var str ="";
			
			$(arr).each(function(i, attach){
				
				//image type 
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" +attach.uuid+"_"+attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"'";
					str += " data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"'data-type='"+attach.fileType+"'";
					str += " ><div>";
					str += "<img src='/board/display?fileName="+fileCallPath+"'>";
					str += "</div></li>"; 
					
				}else{
					str += "<li data-path='"+attach.uploadPath+"'";
					str += " data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"'data-type='"+attach.fileType+"'";
					str += " ><div>";
					str += "<span> "+attach.fileName+"</span>";
					str += "<img src='/resources/img/attach.png'>";
					str += "</div></li>"; 
				}
			});
			
			$(".uploadResult ul").html(str);
		});//end getjson
	})();//end function
	
	//사진 클릭시 확대
	$(".uploadResult").on("click","li", function(e){
		console.log("view image");
		
		var liObj=$(this);
		
		var path= encodeURIComponent(liObj.data("path")+"/"+liObj.data("uuid")+"_"+liObj.data("filename"));
		
		if(liObj.data("type")){
			showImage(path.replace(new RegExp(/\\/g),"/"));
			
		}else{
			//download
			console.log("path:"+path);
			self.location="/board/download?fileName="+path;
		}
	});
	
	function showImage(fileCallPath){
		
		console.log("경로 :"+fileCallPath);
		
		$(".bigPictureWrapper").css("display","flex").show();
		
		$(".bigPicture").html("<img src='/board/display?fileName="+fileCallPath+"'>").animate({width:'100%', height:'100%'}, 1000);
		
		
		//클릭하면 사라지기
		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
			
			setTimeout(function() {
				$('.bigPictureWrapper').hide();
			}, 1000);
		});
		
	}
	
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action","/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list")
		operForm.submit();
	});
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	//댓글 목록 조회
	function showList(page){
		console.log("show list: " + page);
		replyService.getList(
				{bno:bnoValue,page:page||1},
				function(replyCnt, list){
					console.log("replyCnt: "+replyCnt);
					console.log("list: "+list);
					console.log(list);
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					
					var str="";
					
					if(list == null || list.length == 0){
						return;
					}
					
					for(var i = 0, len = list.length||0 ; i < len ; i++){
						str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
						str +="	<div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
						//replyService.displayTime() 함수로 시간 포맷 변경
						str +="		<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
						str +="		<p>" + list[i].reply + "</p></div></li>";
					}
					
					replyUL.html(str);
					showReplyPage(replyCnt);
		});//end function
	}//end showList
	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	
	//New Reply 버튼을 누르면 댓글 modal open
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		$(".modal").modal("show");
	});
	
	
	//modal의 register 버튼을 누르면 등록이 된다.
	modalRegisterBtn.on("click",function(e){
		
		var reply = {
				reply:modalInputReply.val(),
				replyer:modalInputReplyer.val(),
				bno:bnoValue
		};
		
		replyService.add(reply,function(result){
			alert(result);
			
			modal.find("input").val("");
			modal.modal("hide");
			
			
			//asis
			//showList(1);
			//showList(-1);
			showList(pageNum);
		});
		
	});
	
	//클릭하면 댓글 상세 modal open
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
		
		console.log(".chat"+rno);
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id !='modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
	});
	
	//modal의 modify 버튼을 누르면 등록이 된다.
	modalModBtn.on("click",function(e){
		
		var reply = {
				rno:modal.data("rno"),
				reply:modalInputReply.val()
		};
		
		replyService.update(reply,function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
		
	});
	
	//modal의 삭제 버튼을 누르면 등록이 된다.
	modalRemoveBtn.on("click",function(e){
		
		var rno = modal.data("rno");
		
		replyService.remove(rno,function(result){
			alert(result);
			modal.modal("hide");
			
			showList(pageNum);
		});
		
	});
	
	
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
	//댓글 페이징처리
	function showReplyPage(replyCnt){
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum - 9;
		
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);
		}
		
		if(endNum * 10 < replyCnt){
			next = true;
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if(prev){
			str+= "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
		}
		
		for(var i = startNum ; i <= endNum ; i++){
			var active = pageNum == i?"active":"";
			
			str+="<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		
		if(next){
			str+="<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li>";
		}
		
		str+="</ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
		
	}
	
	replyPageFooter.on("click","li a", function(e){
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum = $(this).attr("href");
		
		console.log("targetPageNum: " + targetPageNum);
	
		pageNum = targetPageNum;
		showList(pageNum);
	});
});

</script>
<!-- 20220422 -->
<!-- 
<script>

$(document).ready(function(){
	
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action","/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list")
		operForm.submit();
	});
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	//댓글 목록 조회
	/* as is
	function showList(page){
		replyService.getList(
				{bno:bnoValue,page:page||1},
				function(list){
					var str="";
					
					if(list == null || list.length == 0){
						replyUL.html("");
						
						return;
					}
					
					for(var i = 0, len = list.length||0 ; i < len ; i++){
						str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
						str +="	<div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
						//replyService.displayTime() 함수로 시간 포맷 변경
						str +="		<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
						str +="		<p>" + list[i].reply + "</p></div></li>";
					}
					
					replyUL.html(str);
		});//end function
	}//end showList
	*/
	function showList(page){
		console.log("show list: " + page);
		replyService.getList(
				{bno:bnoValue,page:page||1},
				function(replyCnt, list){
					console.log("replyCnt: "+replyCnt);
					console.log("list: "+list);
					console.log(list);
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					
					var str="";
					
					if(list == null || list.length == 0){
						return;
					}
					
					for(var i = 0, len = list.length||0 ; i < len ; i++){
						str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
						str +="	<div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
						//replyService.displayTime() 함수로 시간 포맷 변경
						str +="		<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
						str +="		<p>" + list[i].reply + "</p></div></li>";
					}
					
					replyUL.html(str);
					showReplyPage(replyCnt);
		});//end function
	}//end showList
	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	
	//New Reply 버튼을 누르면 댓글 modal open
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		$(".modal").modal("show");
	});
	
	
	//modal의 register 버튼을 누르면 등록이 된다.
	modalRegisterBtn.on("click",function(e){
		
		var reply = {
				reply:modalInputReply.val(),
				replyer:modalInputReplyer.val(),
				bno:bnoValue
		};
		
		replyService.add(reply,function(result){
			alert(result);
			
			modal.find("input").val("");
			modal.modal("hide");
			
			
			//asis
			//showList(1);
			//showList(-1);
			showList(pageNum);
		});
		
	});
	
	//클릭하면 댓글 상세 modal open
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
		
		console.log(".chat"+rno);
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id !='modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
	});
	
	//modal의 modify 버튼을 누르면 등록이 된다.
	modalModBtn.on("click",function(e){
		
		var reply = {
				rno:modal.data("rno"),
				reply:modalInputReply.val()
		};
		
		replyService.update(reply,function(result){
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
		
	});
	
	//modal의 삭제 버튼을 누르면 등록이 된다.
	modalRemoveBtn.on("click",function(e){
		
		var rno = modal.data("rno");
		
		replyService.remove(rno,function(result){
			alert(result);
			modal.modal("hide");
			
			showList(pageNum);
		});
		
	});
	
	
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
	//댓글 페이징처리
	function showReplyPage(replyCnt){
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum - 9;
		
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);
		}
		
		if(endNum * 10 < replyCnt){
			next = true;
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if(prev){
			str+= "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
		}
		
		for(var i = startNum ; i <= endNum ; i++){
			var active = pageNum == i?"active":"";
			
			str+="<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		
		if(next){
			str+="<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li>";
		}
		
		str+="</ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
		
	}
	
	replyPageFooter.on("click","li a", function(e){
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum = $(this).attr("href");
		
		console.log("targetPageNum: " + targetPageNum);
	
		pageNum = targetPageNum;
		showList(pageNum);
	});
	
});

</script>

 -->

<!-- 20220402 reply.js test 
<script>

console.log("=========================");
console.log("JS TEST");

var bnoValue='<c:out value="${board.bno}" />';//board의 bno값을 받아옴


//for replyService add test
//replyService.add(
//	//json으로 입력값 하드코딩
//	{reply:"JS TEST", replyer:"tester", bno:bnoValue}
//	,
//	function(result){
//		alert("RESULT: " + result);
//	}
//);

//replyService.getList(
//		{bno:bnoValue, page:1},
//		function(list){
//			for(var i = 0, len = list.length||0; i <len; i++){
//				console.log(list[i]);
//			}
//		}
//);

//578번 댓글 삭제
//replyService.remove(578, function(count) {
//	console.log("remove............... count");
//	console.log(count);
//	
//	if(count === "success"){
//		alert("REMOVE");
//	}
//	
//}, function(err){
//	alert("ERROR...");
//});

//replyService.update(
//		{
//			rno : 561,
//			bno : bnoValue,
//			reply : "Modified Reply... 완성"
//		}, function(result){
//			alert("수정 완료");
//		}, function(err){
//			alert("ERROR...");
//		}
//);

//replyService.get(561, function(data){
//	console.log(data);
//});

</script>

-->

<!-- 
2022-03-31 임시주석처리 
<script type="text/javascript">

$(document).ready(function(){
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action","/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list")
		operForm.submit();
	});
	
	console.log(replyService);
});

</script>  
 -->
<%@include file="../includes/footer.jsp" %>

