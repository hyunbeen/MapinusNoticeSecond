//댓글에 댓글 달기
$(function(){

	$('#replyGroupBtn').click(function(){

		var r_num = $('#r_num').val();
		var n_num = $('#n_num').val();
		var r_content =$('#r_content').val();  
		var r_user = $('#r_user').val();
		
		if(r_user==""){
			alert("작성자를 입력해 주십시오");
		}else if(r_content==""){
			alert("내용을 입력해 주십시오");
		}else{
			$('#frm').submit();
			$.ajax({
				url: '../replies/replyClose',
				type:'post',
				success:function(data){
					opener.parent.location.replace('../sboard/boardReadPage?n_num='+n_num+'&re_page_str=1');
					window.close();

				}
			})
		}

	});


});

//취소 함수
$('#back').click(function(){
	window.close();
});

