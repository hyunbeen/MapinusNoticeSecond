//리뷰 수정하기
$(function(){
	$("#replyModifyBtn").click(function(){

		var r_content = $('#r_content').val();
		var r_user = $('#r_user').val();
		var n_num =  $('#n_num').val();
		var r_num =  $('#r_num').val();
	
		$("#frm").submit();

		$.ajax({
			url: '../replies/replyClose',
			type:'post',
			success:function(data){
				alert("댓글 수정이 완료 되었습니다");
				opener.parent.location.replace("../sboard/boardReadPage?n_num="+n_num+"&re_page_str=1");
				window.close();
			}
		})

	});

	$('#back').click(function(){
		window.close();
	});

});