$(function(){
	$("#insertBtn").click(function(){
		//리뷰 등록 페이지로 전환 
		window.open("../replies/replyInsertPage?n_num="+$("#n_num").val(),"리뷰등록페이지","width=500,height=500");
		
	});
	
	$("#modifyBtn").click(function(){
		//글 수정 페이지로 전환
		window.open("boardModifyPage?n_num="+$("#n_num").val(),"리뷰 입력창","width=700,height=800");
 
	});
	$("#removeBtn").click(function(){
		//글의 번호 가져오기
		var deleteNum = $("#n_num").val();
		var param = {"n_num":deleteNum};
		//삭제기능으로 이동
		 $.ajax({
	            url:'boardDelete',
	            data:param,
	            type:'post',
	            success:function(data){
	            	alert("삭제가 완료 되었습니다");
	        		location.replace("boardListPage?page_str=1");
	                
	            }
	        })
		
	});
	//글 목록 페이지로 이동
	$("#goListBtn").click(function(){
		location.replace("boardListPage?page_str=1");
	});
	
	//다운로드
	$(".downloadBtn").each(function(){
		$(this).click(function(){
			var f_origin = $(this).parent().find("input[name='f_origin']").val(); //원래 파일명
			var f_server = $(this).parent().find("input[name='f_server']").val(); //서버 저장명
		    location.replace("../file/fileDownload?f_origin="+f_origin+"&f_server="+f_server);
	
		});
	}); 
	
	//리뷰 수정하기
	$(".reModifyBtn").click(function(){
		var r_num = $(this).parent().parent().find('input').eq(0).val();
		var r_user = $(this).parent().parent().find('input').eq(1).val();
		var r_content =  $(this).parent().parent().find('input').eq(2).val();
		var n_num = $("#n_num").val();
		window.open("../replies/replyModifyPage?n_num="+n_num+"&r_num="+r_num+"&r_user="+r_user+"&r_content="+r_content,"리뷰 입력창","width=400,height=350");
	});
	
	//리뷰 삭제하기
	$(".reDeleteBtn").click(function(){
		var r_num = $(this).parent().parent().find('input').eq(0).val();
		var n_num = $("#n_num").val();
	//리뷰 삭제기능으로 이동
		 $.ajax({
	            url:'../replies/replyDelete?r_num='+r_num,
	            type:'post',
	            success:function(data){
	            	alert("삭제가 완료 되었습니다");
	        		location.replace("boardReadPage?n_num="+n_num+"&re_page_str=1");
	                
	            }
	        })
	});
	
	//리뷰의 리뷰 추가하기
	$(".reAddBtn").click(function(){
		var r_order = $(this).parent().parent().find('input').eq(3).val();
		var r_num = $(this).parent().parent().find('input').eq(0).val();
		var n_num = $("#n_num").val();
		if(r_order<8){
			window.open("../replies/replyGroupPage?n_num="+n_num+"&r_num="+r_num,"리뷰 입력창","width=400,height=350");
		}else{
			alert('더이상 리플의 리플을 달을수 없습니다');
		}
		
	});
})
	