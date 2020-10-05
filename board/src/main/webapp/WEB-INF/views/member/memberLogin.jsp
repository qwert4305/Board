<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" href="${path}/resources/css/memberLogin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	function login() {
		var id = $("#id").val(); /*input type 에서 쓰는 id 이름 #id로 지정해 변수에 담음 */
		var pw = $("#pw").val(); 

		$.ajax({
			url : "/board/memberLogin.do",
			type : "post",
			data : { id : id, pw : pw}, /*input type에서 받은 id 값 dto id로 넘김 */
			success : function(data) {
				if (data == "true") {
					location.href ="/board/boardList";
				} else {
					alert("아이디 또는 비밀번호가 잘못되었습니다. 다시 로그인 하세요.");
					location.href = "/board/memberLogin";
				}
			}

		});
	}
</script>

</head>
<body>
	<div id="wrap">
		<header>
			<div class="log_header">
				<div class="logo">
					<div id="logoimg">
						<a class="login_logo" />
					</div>
					<h1>로그인</h1>
				</div>
			</div>
		</header>
		<!-- </div> -->

		<div class="clear"></div>

		<section>
			<div class="container">
				<div class="tab_content">
					<input type="radio" name="tabmenu" id="tab1" checked>

					<div class="conbox con1">
						<form name="inputbox" method="Post"
							action="${path}/memberLogin.do">
							<input type="text" name="id" id="id" placeholder="아이디"
								onfocus="value='';"><br> <input type="password"
								name="pw" id="pw" placeholder="비밀번호" onfocus="value='';"><br>

							<input type="checkbox" name="select" value="login_keep"
								id="keeplog"> <label for="keeplog"> 로그인 상태
								유지&nbsp; </label> <input type="checkbox" name="select" value="id_keep"
								id="keepid"> <label for="keepid"> 아이디 저장</label> 
								<input type="button" name="login_submit" value="로그인" onclick="login();"><!--로그인 버튼 누르면 login함수 실행 $path contextPath 넣어주려고 함께 보냄   -->

						</form>
						<br> <br>
					</div>

				</div>
				<div class="clear"></div>
				<div class="lnk_header">
					<a href="${path}/memberJoin"><span><b>회원가입</b></span></a>
				</div>

				<div class="clear"></div>

			</div>
		</section>
		<footer> </footer>
	</div>
</body>
</html>