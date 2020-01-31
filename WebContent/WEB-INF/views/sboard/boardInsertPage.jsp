<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<head>
<meta charset="UTF-8">
<title>게시물 등록 페이지</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- Ionicons -->
<link
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link
	href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css"
	rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link
	href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />


</head>

<!--   에디터 호출에 관련  -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<!-- 페이지의 js -->
<script
	src="${pageContext.request.contextPath}/resources/dist/js/sboard/boardInsertPage.js"></script>


<!-- Main content -->
<body>
	<section class="content">
		<!-- 게시판 등록의 폼 -->
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header">
						<h2>게시판 등록</h2>
					</div>


					<!-- 등록페이지 상단 -->
					<form id="frm" action="boardInsert" method="post">
						<div class="box-body">
							<div class="form-group">
								<label for="exampleInputEmail1">작성자</label> <input type="text"
									name="n_user" id="n_user" class="form-control">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">제목</label> <input type="text"
									name='n_title' id='n_title' class="form-control">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">내용</label>
								<textarea class="form-control" name="n_content" id="n_content"
									rows="3"></textarea>
							</div>
					</form>
					<!-- 파일 업로드 부분 -->
					<div class="form-group" id="uploadForm">
						<label for="exampleInputPassword1">파일업로드</label>
						<form action="fileUpload" id="fileUpload" name="fileUpload"
							method="post" enctype="multipart/form-data">
							<div class="col-md-12 col-xs-12 fileForm" id="delete1">
								</br> </br> <input class="btn btn-default" type="file" name="file1"
									style="float: left;" />
							</div>

						</form>
						</br> </br>
						<div style="margin-top: 5%;">
							<label for="exampleInputPassword1">업로드 목록</label>
							<table class="table table-bordered">
								<thead>
									<tr>
										<td>선택</td>
										<td>파일명</td>
										<td>파일 사이즈</td>
										<td>삭제</td>
									</tr>
								</thead>
								<thead id="uploadList">
								</thead>
							</table>
						</div>
					</div>

				</div>
				<!-- /.box-body -->






				<div class="box-footer">
					<button type="button" id="back" class="btn btn-primary">이전</button>
					<button type="button" id="registerBtn" class="btn btn-primary">등록</button>
				</div>


			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->









</body>

