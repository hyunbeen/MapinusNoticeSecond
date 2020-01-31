<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<head>
<meta charset="UTF-8">
<title>게시물 상세 페이지</title>
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
	src="${pageContext.request.contextPath}/resources/dist/js/sboard/boardReadPage.js"></script>



<!-- Main content -->
<body style="height: 100%; width: 100%">
	<section class="content">
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				<!-- 상세페이지 폼 -->
				<div class="box box-primary">
					<div class="box-header">
						<h2>상세페이지</h2>
						<input type="hidden" id="n_num" value='${n_num}' />
					</div>

					<!-- 상세정보 표시 -->
					<div class="box-body">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th width='15%' bgcolor="#EEFFFF">작성자</th>
									<td>${boardVO.n_user}</td>
								</tr>
								<tr>
									<th width='15%' bgcolor="#EEFFFF">작성날짜</th>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${boardVO.n_date}" /></td>
								</tr>
								<tr>
									<th width='15%' bgcolor="#EEFFFF">제목</th>
									<td>${boardVO.n_title}</td>
								</tr>
								<tr>
									<th width='15%' height="300px" bgcolor="#EEFFFF">내용</th>
									<td style="overflow-y: scroll;">${boardVO.n_content}</td>
								</tr>
								<tr>
									<th width='15%' height="300px" bgcolor="#EEFFFF">첨부파일</th>
									<td style="overflow-y: scroll;">
										<table class="table table-bordered">
											<thead>
												<tr bgcolor="#EEFFFF">
													<th width='40%'>파일명</th>
													<th width='10%'>파일사이즈</th>
													<th width='10%'>다운로드</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<c:forEach items="${fileList}" var="uploadFile">
														<tr>
															<td>${uploadFile.f_origin}</td>
															<td><fmt:formatNumber value="${uploadFile.f_size}"
																	pattern="#,###"/>byte</td>
															<td>
																<button class="btn btn-default downloadBtn">다운</button>
																<input type="hidden" name="f_origin"
																value='${uploadFile.f_origin}' /> <input type="hidden"
																name="f_server" value='${uploadFile.f_server}' />
															</td>
														</tr>
													</c:forEach>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<!-- 버튼부분 -->
					<div class="box-footer">
						<button class="btn btn-primary" id="insertBtn">댓글달기</button>
						<button class="btn btn-primary" id="modifyBtn">수정</button>
						<button class="btn btn-primary" id="removeBtn">삭제</button>
						<button class="btn btn-primary" id="goListBtn">목록으로</button>
					</div>



				</div>
				<!-- /.box -->
			</div>
			<!--/.col (left) -->

		</div>
		<!-- /.row -->



		<div class="row">
			<div class="col-md-12">


				<!-- 글의 댓글 리스트 -->


				<div class="box box-primary">
					<div class="box-header">
						<h2>댓글리스트</h2>
					</div>

					<table class="table table-bordered">
						<!-- 댓글 리스트 상단 -->
						<thead>
							<tr bgcolor="#EEFFFF">
								<th width='20%'>작성자</th>
								<th width='40%'>내용</th>
								<th width='20%'>작성날짜</th>
								<th width='5%'>수정</th>
								<th width='5%'>삭제</th>
								<th width='5%'>댓글</th>
							</tr>
						</thead>
						<!-- 댓글 리스트 츨략-->
						<tbody id='replyAll'>
							<c:forEach items="${replyList}" var="replyVO" varStatus="status">

								<c:choose>
									<c:when test="${status.index mod 2 == 0 }">
										<tr>
											<td>
												<!-- 댓글의 댓글을 달면 띄어쓰기 표시 --> <c:if
													test="${replyVO.r_order gt 1}">
													<c:set var="endReply" value='${replyVO.r_order}' />
													<c:forEach begin='2' end='${endReply}' step='1'
														varStatus="status">
   																&nbsp
															</c:forEach>
													<font style="font-size: 5">☞Re</font>
												</c:if> &nbsp ${replyVO.r_user} <!-- 상위 부모가 삭제 되면 변수값이 바뀐다 --> <c:choose>
													<c:when test="${replyVO.r_delete eq '1'}">
           												(삭제된 댓글 입니다)
       												</c:when>
												</c:choose>
											</td>

											<td>${replyVO.r_content}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${replyVO.r_date}" /></td>
											<td><button class="btn btn-default reModifyBtn" name="re_modify_btn"
													>수정</button></td>
											<td><button class="btn btn-default reDeleteBtn" name="re_delete_btn"
													>삭제</button></td>
											<td><button class="btn btn-default reAddBtn" name="re_add_btn"
													>댓글달기</button></td>
											<input type="hidden" class="r_num" value='${replyVO.r_num}' />
											<input type="hidden" class="r_num" value='${replyVO.r_user}' />
											<input type="hidden" class="r_num"
												value='${replyVO.r_content}' />
											<input type="hidden" class="r_num" value='${replyVO.r_order}' />

										</tr>
									</c:when>

									<c:otherwise>
										<tr bgcolor="#EEFFFF">
											<td>
												<!-- 댓글의 댓글을 달면 띄어쓰기 표시 --> <c:if
													test="${replyVO.r_order gt 1}">
													<c:set var="endReply" value='${replyVO.r_order}' />
													<c:forEach begin='2' end='${endReply}' step='1'
														varStatus="status">
   																&nbsp
														</c:forEach>
													<font style="font-size: 5">☞Re</font>
												</c:if> &nbsp ${replyVO.r_user} <!-- 상위 부모가 삭제 되면 변수값이 바뀐다 --> <c:choose>
													<c:when test="${replyVO.r_delete eq '1'}">
           												(삭제된 댓글 입니다)
       												</c:when>
												</c:choose>
											</td>
											<td>${replyVO.r_content}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${replyVO.r_date}" /></td>
											<td><button class="btn btn-default reModifyBtn" name="re_modify_btn"
													>수정</button></td>
											<td><button class="btn btn-default reDeleteBtn" name="re_delete_btn"
													>삭제</button></td>
											<td><button class="btn btn-default reAddBtn" name="re_add_btn"
													>댓글달기</button></td>
											<input type="hidden" class="r_num" value='${replyVO.r_num}' />
											<input type="hidden" class="r_num" value='${replyVO.r_user}' />
											<input type="hidden" class="r_num"
												value='${replyVO.r_content}' />
											<input type="hidden" class="r_num" value='${replyVO.r_order}' />

										</tr>
									</c:otherwise>
								</c:choose>



							</c:forEach>
						</tbody>

					</table>

					<!-- 리플의 페이징 -->
					<div class="row col-xs-6 col-xs-offset-4">
						<c:choose>
							<c:when test="${re_exbtn ne 0 }">
								<div class="col-md-1">
									<button type="button" class="btn btn-default"
										onclick="location.href='boardReadPage?n_num=${boardVO.n_num}&re_page_str=${re_page_num-re_page_screen}'">◀◀</button>
								</div>
							</c:when>
						</c:choose>


						<c:forEach begin='1' end='${re_page_count}' step='1'>
							<c:choose>
								<c:when test="${re_page_start == re_page_num }">
									<div class="col-md-1">
										<button type="button" class="btn btn-primary"
											onclick="location.href='boardReadPage?n_num=${boardVO.n_num}&re_page_str=${re_page_start}'">${re_page_start}</button>
									</div>
								</c:when>

								<c:otherwise>
									<div class="col-md-1">
										<button type="button" class="btn btn-default"
											onclick="location.href='boardReadPage?n_num=${boardVO.n_num}&re_page_str=${re_page_start}'">${re_page_start}</button>
									</div>
								</c:otherwise>
							</c:choose>
							<c:set value="${re_page_start+1}" var="re_page_start" />
						</c:forEach>

						<c:choose>
							<c:when test="${re_nebtn ne 0 }">
								<div class="col-md-1">
									<button type="button" class="btn btn-default"
										onclick="location.href='boardReadPage?n_num=${boardVO.n_num}&re_page_str=${re_page_num+re_page_screen}'">▶▶</button>
								</div>
							</c:when>
						</c:choose>
					</div>

				</div>
				<!-- /.col -->






			</div>
			<!-- /.row -->
	</section>
	<!-- /.content -->
</body>









