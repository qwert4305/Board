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
<title>회원가입</title>
<link rel="stylesheet" href="${path}/resources/css/memberJoin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">

var idChk = false;
var pwChk = false;
function idcheck(){
 
	var id = $("#id_box").val();
	if(id!=null && id!=""){
	$.ajax({
		url:"/board/idcheck",
		type:"post",
		data:{id:id}, /*{받는이름,보내는이름}*/
		success:function(data) {
			if(data=="사용 가능한 아이디입니다.") {
				$("#result").text(data).css('color','green');
				idChk=true;
			} else {
				$("#result").text(data).css('color', 'red');
				idChk=false;
			}
		}		
	});
} else {
	idChk=false;
	$("#result").empty();
 } //null일시 사용가능한 id입니다. text 지움
	
}

function sendData(){
 if($("#id_box").val()!="" && $("#id_box").val()!=null){
	if(idChk){
		if(pwChk){
		form.submit(); //id,pw모두 true일 때 submit
		} else{
			alert("pw가 올바르지 않습니다.")
		} //pw체크
	} else {
		alert("id가 중복됩니다.");
	} //id체크
 } else {
	 alert("id를 입력해주세요.")
 } //id null체크
 
}
function pwcheck(){
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	var pw = $("#pw_box").val();
	if(reg.test(pw)==false){
		alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
		pwChk=false;
	} else{
		alert('사용가능한 비밀번호입니다.');
		pwChk=true;
	}
	
}


</script>
</head>
<body>
	<header>
		<div id="logo">회원가입</div>
	</header>
	<form action="${path}/memberJoin.do" method="POST" name="form">
		<!--controller의 requestmapping주소  -->
		<section>
			<div id="id">
				<div id="id_subject">
					<span id="id_span"> 아이디</span>
				</div>
				<input type="text" id="id_box" name="id">

				<button id="id_btn" type="button" onclick="idcheck();">중복확인</button>
				<div id="result"></div>

			</div>
			<div id="pw">
				<div id="pw_subject">
					<span id="pw_span"> 비밀번호 </span>
				</div>
				<input type="password" id="pw_box" name="pw">
				<div id="pw_lock_img" onclick="pwcheck();">
				</div>
			</div>
			<div id="name">
				<div id="name_subject">
					<span id="name_span"> 이름</span>
				</div>
				<input type="text" id="name_box" name="name">
			</div>
			<div id="phone">
				<div id="phone_subject">
					<span id="phone_span"> 휴대전화</span>
				</div>
				<div id="phone_1">
					<input type="text" size="15" placeholder=""
						onfocus="this.placeholder=''" onblur="this.placeholder=''"
						class="phone_txt" name="phone1">
				</div>
				<input type="text" id="phone_2" name="phone2"> <input
					type="text" id="phone_3" name="phone3">
			</div>
			<div id="tel">
				<div id="tel_subject">
					<span id="tel_span"> 전화번호</span>
				</div>
				<select id="tel_1" name="tel1">
					<option>02</option>
					<option>031</option>
					<option>032</option>
					<option>033</option>
					<option>041</option>
					<option>042</option>
					<option>043</option>
					<option>044</option>
					<option>051</option>
					<option>052</option>
					<option>053</option>
					<option>054</option>
					<option>055</option>
					<option>061</option>
					<option>062</option>
					<option>063</option>
					<option>064</option>
				</select>
				<div id="tel_img"></div>
				<input type="text" id="tel_2" name="tel2"> <input
					type="text" id="tel_3" name="tel3">
			</div>
			<div id="email">
				<div id="email_subject">
					<span id="email_span">이메일</span>
				</div>
				<input type="text" id="email_1" name="email1"></input> <span
					id="email_1_at">@</span> <select id="email_2" name="email2">
					<option>naver.com</option>
					<option>hanmail.net</option>
					<option>yahoo.co.kr</option>
					<option>hotmail.com</option>
					<option>nate.com</option>
					<option>gmail.com</option>
				</select>
				<div id="email_2_img"></div>
			</div>
			<div style="text-align: center;">
				<input type="button" value="가입하기" id="join_btn" onclick="sendData();" />
			</div>
		</section>
	</form>
	<footer> </footer>
</body>
</html>