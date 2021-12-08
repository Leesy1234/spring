<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script>
function del() {// function 함수명 = 함수 정의, { 실행문 }, confirm : 물어보는 함수 -> 확인 : 삭제url로 이동시킴
	if (confirm("삭제하시겠습니까?")) {//삭제하기 전에 물어본다. 확인을 누르면 삭제됨
		location.href="delete.do?boardno=${data.boardno}";
	}
}
</script>
</head>
<body>
<h1>게시판 상세</h1>
<table border="1" style="border-collapse:collapse;">
	<tr>
		<th>제목</th>
		<td>${data.title }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${data.content }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${data.writer }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${data.regdate }</td>
	</tr>
	<c:if test="${!empty data.filename }">
	<tr>
		<th>이미지</th>
		<td>
			<img src="/spring/upload/${data.filename }">
		</td>
	</tr>
	</c:if>
	<tr>
		<td colspan="2">
		<input type="button" value="수정" onclick="location.href='edit.do?boardno=${data.boardno}';">
		<input type="button" value="삭제" onclick="del();">
		</td>
	</tr>
</table>
</body>
</html>