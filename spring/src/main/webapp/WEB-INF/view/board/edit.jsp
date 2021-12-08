<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
</head>
<body>
<h1>게시글 수정</h1>
<form action="update.do" method="post"><!-- controller에서 받고 싶으면 form안의 내용이 있어야 한다.// insert.do는 앞에 /가 없으므로 상대경로!-->
<input type="hidden" name="boardno" value="${data.boardno }"><!-- 화면에 보이지 않도록 boardno 값을 숨겨서 전송해야만 수정페이지에서 boardno를 pk로 사용가능 -->
	<table border="1" style="border-collapse:collapse;">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${data.title }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content">${data.content }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${data.writer }"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="저장"></td>
		</tr>
	</table>
</form>
</body>
</html>