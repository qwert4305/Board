<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글보기</title>
<link rel="stylesheet" href="${path}/resources/css/boardDetail.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function update() {
		form.action = "${path}/boardUpdate?articleno=${detailAll.articleno}"
		form.method = "post"
		form.submit();
	}

	function Delete() {
		form.action = "${path}/boardDelete.do?articleno=${detailAll.articleno}"
		form.method = "post"
		form.submit();
	}

	function reply() {
		form.action = "${path}/boardReply?parentno=${detailAll.articleno}"
		form.method = "post"
		form.submit();
	}

	function list() {
		form.action = "${path}/boardList"
		form.method = "post"
		form.submit();
	}
</script>
</head>
<body>
	<form name="form">
		<!-- enctype 파일이나 이미지를 서버로 전송할 때 주로 사용 -->
		<div id="wrap">
			<header> 글 보기 </header>
			<div id="main">
				<div id="title">
					<div id="title_name">제목</div>
					<div id="title_text">${detailAll.title}
						<div id="dateinfo">${detailAll.writedate}</div>
						<input type="hidden" value="${detailAll.writedate}"
							name="writedate">
					</div>
					<div id="content">
						<div id="content_text">${detailAll.content}</div>
					</div>
					<div id="file">
						<div id="file_name">첨부파일</div>
						<div id="upload">
							<c:if test="${not empty detailAll.imagename}">
								<img height="300px"
									src="${path}/imageView?articleno=${detailAll.articleno}&file=${detailAll.imagename}">
							</c:if>
						</div>
						<div id="preview">
							<div id="preview_name">첨부파일 다운로드</div>
							<c:if test="${not empty detailAll.imagename}">
								<span id="imgname"> <a
									href="${path}/download.do?articleno=${detailAll.articleno}&file=${detailAll.imagename}">
										${detailAll.imagename} </a>
								</span>
							</c:if>
						</div>
					</div>
					<footer>
						<c:if test="${sessionScope.isLogOn==true }">
							<div id="delete_btn">
								<a href="#" id="" Onclick="Delete();">삭제</a>
							</div>
						</c:if>
						<c:if test="${sessionScope.isLogOn==true }">
							<div id="update_btn">
								<c:choose>
									<c:when
										test="${detailAll.writer eq sessionScope.memberInfo.id}">
										<a href="#" id="" Onclick="update();">수정</a>
									</c:when>
									<c:otherwise>
										<a href="#" id="" Onclick="alert('수정 권한이 없습니다.')">수정</a>
									</c:otherwise>
								</c:choose>

							</div>
						</c:if>
						<c:if test="${sessionScope.isLogOn==true }">
							<div id="reply_btn">
								<a href="#" id="" Onclick="reply();">답글</a>

							</div>
						</c:if>
						<div id="list_btn">
							<a href="#" id="" Onclick="list();">글목록</a>
						</div>
					</footer>
				</div>
			</div>
		</div>
	</form>
</body>
</html>