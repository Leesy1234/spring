<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
</head>
<body>
<h1>게시글 등록</h1>
<form action="insert.do" method="post" enctype="multipart/form-data"><!-- controller에서 받고 싶으면 form안의 내용이 있어야 한다.// insert.do는 앞에 /가 없으므로 상대경로!-->
<!-- <input type="hidden" name="userno" value="${loginInfo.userno }">  -->
	<table border="1" style="border-collapse:collapse;">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" ></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" ></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="file" name="file" ></td><!-- controller에 작성한 매개변수 이름과 같아야 한다. filename으로 하면 에러 : controller에 커맨드 객체로 BoardVo vo가 있는데 타입이 String -->
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="저장"></td>
		</tr>
	</table>
</form>
</body>
</html>