
var index = 2;

//입력 이벤트
$(function(){

	//글의 목록으로 이동
	$("#back").click(function(){
		opener.parent.location.replace("boardListPage?page_str=1");
		window.close();
	});

	$("#registerBtn").click(function(){

		//입력할 글의 데이터
		var title = $('#n_title').val();
		var content = $('#n_content').summernote('code');
		var user = $('#n_user').val();
		var param = {"n_title":title,"n_content":content,"n_user":user};
		//파일을 저장할 변수
		var files;

		if(title==''){
			alert("제목을 입력해주세요");
		}else if(content==''){
			alert("내용을 입력해주세요");
		}else if(user==''){
			alert("작성자를 입력해주세요");
		}else{

			$.ajax({
				url:'boardFindNum',
				type:'post',
				success:function(data){

					var n_num = data;

					//파일을 저장할 폼데이터
					var formData = new FormData();
					//파일에 저장하게 될 글의 번호
					formData.append("n_num",n_num);
					//check한 파일리스트 저장
					$(".check").each(function(){

						if($(this).is(":checked")==true){

							var checkIndex = $(this).val();

							files = $("#delete"+checkIndex).find("input[name=file1]")[0].files[0];

							//check한 것중에 파일이 선택 된 경우
							if(files != undefined){

								formData.append("file1",files);
								//전송할 데이터에 추가한다
							}


						}
					});
					//upload할 파일을 전송한다
					$.ajax({ 
						url: '../file/fileUpload', 
						data: formData, 
						processData: false, 
						contentType: false, 
						type: 'POST', 
						success: function(data){ 

							$('#frm').submit();
							$.ajax({ 
								url:'../sboard/boardClose',
								type:'POST',
								success:function(data){

									opener.parent.location.replace('../sboard/boardListPage?page_str=1');
									window.close(); 
								}
							});

						} 
					});

				}
			});

		}

	});



	//제거 버튼을 눌럿을 경우
	$(document).on("click",".deleteFile",function(event){
		var deleteIndex = 'delete'+$(this).val();
		$(this).parent().parent().remove();
		$('#'+deleteIndex).remove();
		$(".fileForm").each(function(){
			$(this).css("display","none");
		});
		$("#fileUpload .fileForm:last-child").css("display","inline");
	});

	//제거 버튼을 눌럿을 경우
	$(document).on("change","input[name='file1']",function(event){
		var regexp = /\B(?=(\d{3})+(?!\d))/g;
		var fileName = $(this)[0].files[0].name;
		var fileSize = $(this)[0].files[0].size;
		index--;
		var tdCheck = "<td>"+"<input type='checkbox' class='check' value="+index+">"+"</input>"+"</td>";
		var tdName = "<td>"+fileName+"</td>";
		var tdSize = "<td>"+fileSize.toString().replace(regexp, ',')+'byte'+"</td>";
		var tdCancel = "<td>"+'<button type="button" class="btn btn-default deleteFile" value='+index+'>X</button>'+"</td>";
		index++;
		var trUpload = "<tr>"+tdCheck+tdName+tdSize+tdCancel+"</tr>";
		$('#uploadList').append(trUpload);
		//파일을 더 추가할 경우
		var file = '<input class="btn btn-default" type="file" name="file1" style="float: left;"/>';
		//만든 폼을 덧붙인다
		var data = '<div class="col-md-12 col-xs-12 fileForm"'+' id="delete'+index+'">'+file+'</div>';
		$("#fileUpload").append(data);
		$(".fileForm").each(function(){
			$(this).css("display","none");
		});
		$("#fileUpload .fileForm:last-child").css("display","inline");
		index++;
	});

	//에디터 호출 관련 함수 
	$('#n_content').summernote({
		height: 300,                 // 에디터 높이
		minHeight: null,             //에디터 최고높이
		maxHeight: null,             // 에디터 최저높이
		focus: true                  // 높이 수정가능
	});

});




