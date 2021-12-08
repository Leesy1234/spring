<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<h1>게시판 상세</h1>
<table border="1" style="border-collapse:collapse;">
	<tr>
		<th>제목</th>
		<td>${data.subject }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${data.article }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${data.author }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${data.regdate }</td>
	</tr>
	<tr>
		<td colspan="2">
		</td>
	</tr>
</table>
</body>
</html>