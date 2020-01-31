<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.*"%>


<head>
<meta charset="UTF-8">
<title>게시물 검색 페이지</title>
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


<!-- jquery 사용 -->
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

<!-- 검색창 적용 -->
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

<!-- 페이지의 js -->
<script
	src="${pageContext.request.contextPath}/resources/dist/js/sboard/boardSearchPage.js"></script>


<!-- Main content -->
<body style="height: 100%; width: 100%">
	<section class="content">
		<div class="row">
			<!-- left column -->


			<div class="col-md-12">



				<div class="box box-primary">
					<div class="box-header">
						<h2>검색 결과</h2>
						<input type="hidden" id="item" value=${item}> <input
							type="hidden" id="word" value=${word}> <input
							type="hidden" id="notice_param" value="" />
					</div>

					<!-- 검색페이지 상단 -->
					<div class="box-body">
						<table class="table table-bordered">
							<tr bgcolor="#D9F4FA">
								<th width='5%'>번호</th>
								<th width='40%'>제목</th>
								<th width='10%'>작성자</th>
								<th width='20%'>작성날짜</th>
								<th width='20%'>조회수</th>
							</tr>
							<!--게시판 글의 리스트 출력 -->
							<c:forEach items="${searchlist}" var="boardVO" varStatus="status">
								<c:choose>
									<c:when test="${status.index mod 2 == 0 }">
										<tr>
											<td>${boardVO.n_num}</td>
											<td><a class='move'> ${boardVO.n_title} </a> <input
												type="hidden" name="n_num" value="${boardVO.n_num}" /></td>
											<td>${boardVO.n_user}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${boardVO.n_date}" /></td>
											<td>${boardVO.n_count }</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr bgcolor="#D9F4FA">
											<td>${boardVO.n_num}</td>
											<td><a class='move'> ${boardVO.n_title} </a> <input
												type="hidden" name="n_num" value="${boardVO.n_num}" /></td>
											<td>${boardVO.n_user}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${boardVO.n_date}" /></td>
											<td>${boardVO.n_count }</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</table>
					</div>
					<!-- /.box-body -->



				</div>
			</div>
			<!--/.col (left) -->

		</div>

		<!-- 검색 창 -->
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2">
				<div class="input-group">
					<div class="input-group-btn search-panel">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span id="search_concept">검색기준</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#작성자">작성자</a></li>
							<li><a href="#글제목">글제목</a></li>
						</ul>
					</div>
					<input type="hidden" name="search_param" value="all" /> <input
						type="text" class="form-control" id="search_param" name="x"
						placeholder="검색어 입력" /> <span class="input-group-btn">
						<button class="btn btn-default" style="height: 35px;"
							type="button" id='searchBtn' onClick='searchFunc()'>
							<span class="glyphicon glyphicon-search"></span>
						</button>
						<button class="btn btn-primary" id="insertBtn"
							onclick="insertNotice();">글쓰기</button>
					</span>
				</div>
			</div>
		</div>


		<!-- 검색페이지는 검색페이지간의 페이징 -->
		<!-- 게시판 글 페이징 -->
		<div class="row col-xs-6 col-xs-offset-4">
			<c:choose>
				<c:when test="${search_exbtn ne 0 }">
					<div class="col-md-1">
						<button type="button" class="btn btn-default moveBtn"
							value=${search_page_num-search_page_screen}>◀◀</button>
					</div>
				</c:when>
			</c:choose>

			<c:forEach begin='1' end='${search_page_count}' step='1'>
				<c:choose>
					<c:when test="${search_page_start == search_page_num }">
						<div class="col-md-1">
							<button type="button" class="btn btn-primary moveBtn"
								value=${search_page_start }>${search_page_start}</button>
						</div>
					</c:when>

					<c:otherwise>
						<div class="col-md-1">
							<button type="button" class="btn btn-default moveBtn"
								value=${search_page_start}>${search_page_start}</button>
						</div>
					</c:otherwise>
				</c:choose>
				<c:set value="${search_page_start+1}" var="search_page_start" />
			</c:forEach>


			<c:choose>
				<c:when test="${search_nebtn ne 0 }">
					<div class="col-md-1">
						<button type="button" class="btn btn-default moveBtn"
							value=${search_page_num+search_page_screen}>▶▶</button>
					</div>
				</c:when>
			</c:choose>
		</div>
		<!-- 	/.row -->

	</section>

	<!-- /.content -->

</body>





