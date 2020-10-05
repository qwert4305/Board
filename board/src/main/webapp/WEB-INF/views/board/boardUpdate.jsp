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
<title>글수정</title>
<link rel="stylesheet" href="${path}/resources/css/boardWrite.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	function readURL(input) { /*this를 input으로 받음*/
		if (input.files && input.files[0]) { /*files의 첫번째 파일이 있으면*/
			var reader = new FileReader(); /*파일을 읽어오는 객체 생성*/
			reader.onload = function(e) { /*onchange실행시 onload바로 실행됨 e는 첫번째 파일의 input정보*/
				$("#previewimg").css("display", "block");
				$("#previewimg").attr("src", e.target.result); /*첫번째의 이미지의 경로를 previewimg의 src에 넣어준다*/
			}
			reader.readAsDataURL(input.files[0]); /*데이터를 읽어와 이미지 경로인 url로 만듬*/
		}
	}
</script>
</head>
<body>
	<form action="${path}/boardUpdate.do" method="post"
		enctype="multipart/form-data">
		<!-- enctype 파일이나 이미지를 서버로 전송할 때 주로 사용 -->
		<div id="wrap">
			<header> 글 수정 </header>
			<div id="main">
				<div id="title">
					<div id="title_name">제목</div>
					<input type="text" name="title" id="title_text">
					<div id="dateinfo">${writedate}</div>
				</div>
				<div id="content">
					<textarea name="content" id="content_text"></textarea>
				</div>
				<div id="file">
					<div id="file_name">첨부파일</div>
					<div id="preview">
						<img id="previewimg" height="250px" src="#">
					</div>
					<div id="upload_btn">
						<label for="upload">파일 선택</label> <input type="file" id="upload"
							value="이미지업로드" name="file" style="display: none;"
							onchange="readURL(this);">
						<!-- 버튼을 누르면 #upload의 모든 내용이 this로 들어감 -->
					</div>
				</div>
			</div>
			<footer>

				<input type="hidden" value="${sessionScope.memberInfo.id}"
					name="writer"> <input type="hidden" value="${articleno}"
					name="articleno">
				<!-- name 은 dto 필드명 -->
				<!-- hidden은  -->
				<input type="submit" id="gotoList" value="수정완료">
			</footer>
		</div>
	</form>
</body>
</html>