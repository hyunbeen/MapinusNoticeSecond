<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- Ionicons -->
<link
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link
	href="${pageContext.request.contextPath}//resources/dist/css/AdminLTE.min.css"
	rel="stylesheet" type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link
	href="${pageContext.request.contextPath}//resources/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />
</head>

<!-- 에디터 호출 관련 -->
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
	src="${pageContext.request.contextPath}/resources/dist/js/sboard/boardModifyPage.js"></script>
<!-- 페이지의 css -->
<link
	href="${pageContext.request.contextPath}/resources/dist/css/sboard/boardModifyPage.css"
rel="stylesheet">




<body style="height: 100%; width: 100%">
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header">
						<h2>수정페이지</h2>
					</div>

					<!-- 수정 하기의 폼 -->
					<div class="box-body">
						<form method='post' action='boardModify' id='frm'>
							<input type="hidden" id="n_num" name="n_num"
								value="${boardVO.n_num}" />
							<div class="form-group">
								<label for="exampleInputEmail1">작성자</label> <input type="text"
									id="n_user" name="n_user" class="form-control"
									value="${boardVO.n_user}" readonly="readonly">
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">제목</label> <input type="text"
									id='n_title' name="n_title" class="form-control"
									value="${boardVO.n_title}">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">내용</label>
								<textarea class="form-control" id="n_content" name="n_content"
									rows="3">${boardVO.n_content}</textarea>
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
											<td>파일사이즈</td>
											<td>삭제</td>
										</tr>
									</thead>
									<thead id="baseList">
										<c:forEach items="${fileList}" var="fileVO" varStatus="status">
											<tr>
												<td><input type='checkbox' class='checkBase'></input></td>
												<td>${fileVO.f_origin}</td>
												<td>
												<fmt:formatNumber value="${fileVO.f_size}" pattern="#,###" />byte</td>
												</td>
												<td><button type="button" class="btn btn-default deleteFileBase">X</button></td>
												<input type="hidden" name="f_num" value='${fileVO.f_num}'/>
												<input type="hidden" name="f_server" value='${fileVO.f_server}'/>
											</tr>
										</c:forEach>
									</thead>
									<thead id="uploadList">
									</thead>
								</table>
							</div>
						</div>

					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-primary" id="saveBtn">저장</button>
						<button type="button" class="btn btn-primary" id="backBtn">취소</button>
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

