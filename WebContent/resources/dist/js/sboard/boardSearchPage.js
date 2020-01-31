$(function() {
	//검색 기능
	$('#searchBtn').click(function(){

		var searchItem = $('#notice_param').val(); //검색기준
		var searchWord = $('#search_param').val();//검색어
		location.replace("boardSearchPage?item=" +encodeURI(searchItem) + "&word="
				+encodeURI(searchWord) + "&search_page_str=1");

	});

	//검색 기준 드롭다운 기능
	$('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var param = $(this).attr("href").replace("#", "");
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('#notice_param').val(concept);

	});

	$('.move').click(function(event){
		event.preventDefault();
		var n_num = $(this).parent().find("input[name='n_num']").val();
		location.replace("boardReadPage?n_num="+n_num+"&re_page_str=1");
	});

	//글 등록페이지로 전환	
	$('#insertBtn').click(function(){
		window.open("boardInsertPage", "글 등록창", "width=700,height=800");
	});


	//검색페이지에서 옆페이지로 이동	
	$('.moveBtn').click(function(){
		location.replace('boardSearchPage?item=' + encodeURI($("#item").val())
				+'&word=' + encodeURI($("#word").val())
				+'&search_page_str=' + $(this).val());
	});

});




