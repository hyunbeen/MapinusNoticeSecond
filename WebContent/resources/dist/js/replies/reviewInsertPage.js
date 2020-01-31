$(function(){
	
	//취소 함수
	$('#back').click(function(){
			window.close();
	});
	
	//리플 추가 함수
	$('#replyAddBtn').click(function(){
		
		var r_user = $("#newReplyWriter").val(); //새로 입력한 답글의 작성자
		var r_content = $("#newReplyText").val(); //새로 입력한 답글의 내용
		var n_num = $("#n_num").val();//글의 번호
		var param = {"n_num":n_num,"r_user":r_user,"r_content":r_content}; //데이터를 json형태로 바꿈

		if(newReplyWriter == ""){
			alert("작성자를 입력해 주세요");
		}else if(newReplyText == ""){
			alert("본문을 입력해 주세요");
		}else{
			$('#frm').submit();
			
			$.ajax({
	            url:'../replies/replyClose',
	            type:'post',
	            success:function(data){
	            
	            	opener.parent.location.replace("../sboard/boardReadPage?n_num="+n_num+"&re_page_str=1");
	            	window.close();
	    
	            	
	            }
	        })
		}
		
	});
	
})


