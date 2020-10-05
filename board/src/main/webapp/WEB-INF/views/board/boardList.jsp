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
<title>게시판목록</title>
<link rel="stylesheet" href="${path}/resources/css/boardList.css">
</head>
<body>
	<div id="wrap">
		<header>
			게시판
			<div id="search">
				<input type="text" id="search_line"> <a href=""> <img
					src="${path}/resources/img/loupe.png">
				</a>
				<c:choose>
					<c:when test="${empty isLogOn}">
						<a href="${path}/memberLogin">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 로그인 </a>
					</c:when>
					<c:otherwise>
						<a href="${path}/memberLogout.do">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 로그아웃 </a>
					</c:otherwise>
				</c:choose>
				<a href="${path}/memberJoin"> &nbsp;&nbsp;&nbsp; 회원가입 </a>
			</div>

		</header>
		<div id="main">
			<table>
				<tbody>
					<tr>
						<th id="articleno">글번호</th>
						<th id="title">제목</th>
						<th id="hit">조회수</th>
						<th id="writer">작성자</th>
						<th id="date">날짜</th>
					</tr>
					<c:forEach var="allFor" items="${all}">
						<tr>
							<td>${allFor.articleno}</td>
							<td><a
								href="${path}/boardDetail.do?articleno=${allFor.articleno}">
									<!--?requestparam으로 보낼 이름={현재 jsp에서 쓰는 이름}  --> <c:if
										test="${allFor.layer gt 0}">
										<c:forEach begin="1" end="${allFor.layer}">
                    RE:
                    </c:forEach>
									</c:if> ${allFor.title}
							</a></td>
							<td>${allFor.hit}</td>
							<td>${allFor.writer}</td>
							<td>${allFor.writedate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<footer>
			<div id="paging">
				<%--현재페이지-1이 0 보다 크면 다음페이지로 넘어가고, 0보다 작으면 alert처리 --%>
				<c:choose>
					<c:when test="${sessionScope.currentPage-1>0}">
						<a href="${path}/boardList?pagenum=${sessionScope.currentPage-1}">
							<img src="${path}/resources/img/btn_pagenav_prev.png" id="arrow">
						</a>
					</c:when>
					<c:otherwise>
						<a onclick="alert('첫번째 페이지 입니다.');"> <img
							src="${path}/resources/img/btn_pagenav_prev.png" id="arrow">
						</a>
					</c:otherwise>
				</c:choose>
				<%--5단위로 페이지 묶기--%>
				<%--시작페이지+4가(ex> 6+4) 마지막 페이지보다 작거나 같으면(10) 6~10까지  --%>
				<c:choose>
					<c:when test="${sessionScope.pagenum >= sessionScope.startPage+4}">
						<c:forEach var="pagingFor" begin="${sessionScope.startPage}"
							end="${sessionScope.startPage+4}">
							<a href="${path}/boardList?pagenum=${pagingFor}">${pagingFor}</a>
						</c:forEach>
					</c:when>
					<%--시작페이지+4가(ex> 7+4) 마지막 페이지보다 크면 11까지만(존재하는 페이지만) 출력  --%>
					<c:otherwise>
						<c:forEach var="pagingFor" begin="${sessionScope.startPage}"
							end="${sessionScope.pagenum}">
							<a href="${path}/boardList?pagenum=${pagingFor}">${pagingFor}</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<%-- ------------------------------------------------------------------- --%>

				<%--현재페이지가 마지막 페이보다 작으면 이동, 같거나 크면 alert  --%>
				<c:choose>
					<c:when test="${sessionScope.currentPage<sessionScope.pagenum}">
						<a href="${path}/boardList?pagenum=${sessionScope.currentPage+1}">
							<img src="${path}/resources/img/btn_pagenav_next.png" id="arrow">
						</a>
					</c:when>
					<c:otherwise>
						<a onclick="alert('마지막 페이지 입니다.');"> <img
							src="${path}/resources/img/btn_pagenav_next.png" id="arrow">
						</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="writebtn">
				<c:choose>
					<c:when test="${isLogOn==false}">
						<a onclick="alert('로그인을 하세요.');"> 글쓰기 </a>
					</c:when>
					<c:otherwise>
						<a href="${path}/boardWrite"> 글쓰기 </a>
					</c:otherwise>
				</c:choose>
			</div>
		</footer>
	</div>
</body>
</html>