<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta charset="UTF-8">
<title>댓글 입력 페이지</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- Font Awesome Icons -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
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

<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>
<!-- 페이지의 js -->
<script
	src="${pageContext.request.contextPath}/resources/dist/js/replies/reviewInsertPage.js"></script>

<!-- Main content -->
<section class="content">
	<!-- 댓글 등록의 폼 -->
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h2>댓글 작성</h2>
				</div>
				<div class="box-body">
					<form action='../replies/replyInsert' method='post' id='frm'>
						<input type="hidden" id="n_num" name="n_num" value='${n_num}' /> <label
							for="exampleInputEmail1">작성자</label> <input class="form-control"
							type="text" id="newReplyWriter" name="r_user"> <label
							for="exampleInputEmail1">댓글 내용</label>
						<textarea class="form-control" id="newReplyText" name="r_content"></textarea>
					</form>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">

					<button class="btn btn-primary" id="replyAddBtn"
						onClick="replyAddFuc()">저장</button>
					<button class="btn btn-primary" id="back">취소</button>
				</div>
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->


