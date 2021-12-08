<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script><!-- jquery 이용 -->
<script>
	function loginCheck(){
		if($("#id").val().trim() == '') {
			alert('아이디를 입력해주세요');
			$("#id").focus();
			return;//return false; 둘 다 써도 됨. 단순히 중지시키는 역할이므로!
		}
		if($("#pwd").val().trim() == '') {
			alert('비밀번호를 입력해주세요');
			$("#pwd").focus();
			return;
		}
		//return true;
		$("#loginFrm").submit();// #id, .클래스명 둘 다 없으면 태그로 보내야 하므로! => $("form").submit();
	}
</script>
</head>
<body>
<form action="loginProcess.do" method="post" id="loginFrm">
<table border="1">
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name="id" id="id" value="${userVo.id }"><br>
			<input type="checkbox" name="checkId" value="check" <c:if test="${!empty userVo.id }">checked</c:if>> 아이디 저장
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd" id="pwd"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" value="로그인" onclick="loginCheck();"></td>
	</tr>
</table>
</form>
</body>
</html>