<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<h2>마이페이지</h2>
<div>
	<c:if test="${empty loginInfo }"><!-- 저장소에 저장되어 있는 key로 조회 : UserServiceImple에 sess.setAttribute("loginInfo", uv);로 저장됨 -->
	<input type="button" value="로그인" onclick="location.href='/spring/user/login.do';">
	<input type="button" value="회원가입" onclick="location.href='/spring/user/regist.do';">
	</c:if>
	<c:if test="${!empty loginInfo }">
	${loginInfo.name }님, 안녕하세요.<!-- UserVo 객체에 저장된 name이라는 속성을 getName이라는 메서드가 호출되어 이름이 출력된다. -->
	<input type="button" value="로그아웃" onclick="location.href='/spring/user/logout.do';">
	<input type="button" value="마이페이지" onclick="location.href='/spring/user/mypage.do';">
	</c:if>
</div>
<form method="get" action="mypage.do"> <!-- 검색한 결과에 대한 url이 나와야 친구에게 주소를 링크로 공유할 수 있다. -->
<select name="searchType">
	<option value="">전체</option><!-- value에 값이 없으면, searchType 중 맨 위에 있는 '전체'항목이 선택되어 나온다. -->
	<option value="title" <c:if test="${param.searchType == 'title'}">selected</c:if>>제목</option>
	<option value="content" <c:if test="${param.searchType == 'content'}">selected</c:if>>내용</option>
</select>
<input type="text" name="searchWord" value="${param.searchWord}"> <!-- <%=request.getParameter("searchWord")%>을 쓰면 null이 나올 수 있어서 삼항연산자로 null일 때 처리해야한다. 따라서, el 또는 jstl로 해야 null 처리를 따로 해주지 않아도 되기 때문에 편리하다. -->
<input type="submit" value="검색">
</form>
<table border="1">
	<tr>
		<th>번호</th>
		<th>
			<c:choose>
				<c:when test="${param.orderCond == 'title_asc' }">
					<a href="mypage.do?orderCond=title_desc">제목 ↑</a> 
				</c:when>
				<c:when test="${param.orderCond == 'title_desc' }">
					<a href="mypage.do?orderCond=title_asc">제목 ↓</a> 
				</c:when>
				<c:otherwise>
					<a href="mypage.do?orderCond=title_asc">제목</a>					
				</c:otherwise>
			</c:choose>
		</th>
		<th>작성자</th>
		<th>
			<c:choose>
				<c:when test="${param.orderCond == 'regdate_asc' }">
					<a href="mypage.do?orderCond=regdate_desc">작성일 ↑</a> 
				</c:when>
				<c:when test="${param.orderCond == 'regdate_desc' }">
					<a href="mypage.do?orderCond=regdate_asc">작성일 ↓</a> 
				</c:when>
				<c:otherwise>
					<a href="mypage.do?orderCond=regdate_asc">작성일</a>					
				</c:otherwise>
			</c:choose>
		</th>
	</tr>
	<c:forEach var="vo" items="${list }"><!-- var: 변수, items :목록, request.list와 동일하다. -->
	<tr>
		<td>${vo.boardno }</td>
		<td><a href = "/spring/board/detail.do?boardno=${vo.boardno }">${vo.title }</a></td>
		<td>${vo.writer }</td>
		<td>${vo.regdate }</td><!-- vo필드는 private이므로 호출 불가!! 대신 getter메서드를 이용하여 출력해온다. -->
	</tr>
	</c:forEach>
</table>
<c:forEach var="rpage" begin="1" end="${totPage }">
	<a href="mypage.do?page=${rpage }">[${rpage }]</a>
</c:forEach>
<br>
</body>
</html>