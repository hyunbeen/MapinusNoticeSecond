<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta charset="UTF-8">
<title>댓글의 댓글 입력 페이지</title>
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
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
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
	src="${pageContext.request.contextPath}/resources/dist/js/replies/reviewGroupPage.js"></script>





<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h2>댓글에 댓글달기</h2>

				</div>
				<!-- /.box-header -->




				<!-- 댓글 입력 하기의 폼 -->
				<div class="box-body">
					<form action='../replies/replyGroup' method='post' id='frm'>
						<input type="hidden" id="n_num" name='n_num' value='${n_num}' />
						<input type="hidden" id="r_num" name='r_num' value='${r_num}' />
						<div class="form-group">
							<label for="exampleInputEmail1">작성자</label> <input type="text"
								id="r_user" name='r_user' class="form-control" value="">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">내용</label>
							<textarea class="form-control" id="r_content" name='r_content'
								rows="3"></textarea>
						</div>
					</form>

				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="button" class="btn btn-primary" id="replyGroupBtn">저장</button>
					<button type="button" class="btn btn-primary" id="back">취소</button>
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


