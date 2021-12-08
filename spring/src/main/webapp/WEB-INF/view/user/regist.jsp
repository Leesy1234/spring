<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script><!-- jquery 이용 -->
<script>
	function formCheck(){
		if ($("#id").val().trim() == ''){//'':공백이면, trim():값 없이 스페이스바 누르면 안되므로// $("#id") : 객체 - #:아이디를 의미// $("#id").val() : 아이디의 값을 가져옴
			alert('아이디를 입력하세요');
			$("#id").focus();
			return false;//입력하지 않았을 때 값을 전송하지 않으려면 false로 지정
		}
		// 실제 submit을 하기 전에 아이디 중복체크
		var con = true;//true=계속 실행
		$.ajax({// {}안에 ajax의 5개의 속성, 속성값을 갖는 매개변수
			url:"idcheck.do",
			data:{
				id:$("#id").val()//파라미터를 가져온다. 예) 아이디란에 a를 넣고 입력하면 user/regust.do?id=a라는 파라미터가 들어가게 된다.
			},
			async:false,//증요!!! async가 빠지면 url을 불러오느라 반응이 늦어지기 때문에 중단되지 않고 다음 순서가 일단 진행된다.
			// async:true가 기본값 -> a를 호출하면서도 뒤에서 b를 하고 있는게 보통의 비동기방식(별개의 스레드처럼), 이를 false를 하면 하나의 스레드처럼 순서대로 진행하고 끝날 때까지 기다린다.
			method:"get",
			success:function(r) {//성공하면
				if (r.trim() == '1') {
					alert('아이디가 중복되었습니다. 다른 아이디를 입력하세요.');
					$("#id").val('');
					$("#id").focus();
					//return false;//실행 중에 ajax가 중지된다. 그러나 위에 아이디를 입력하세요는 끝났기 때문에 다음인 비밀번호를 입력하세요로 넘어가진다. 변수con를 하나 만들어서 
					con = false;//true -> false가 되면 중지!
				}
			}
		});
		if (con == false) return false;// 중지된다.
		if ($("#pwd").val().trim() == ''){
			alert('비밀번호를 입력하세요');
			$("#pwd").focus();
			return false;
		}
		if ($("#pwd").val().trim() != $("#pwd2").val()) {
			alert('비밀번호 값을 확인하세요');
			$("#pwd2").focus();
			return false;
		}
		if ($("#name").val().trim() == ''){
			alert('이름을 입력하세요');
			$("#name").focus();
			return false;
		}
		// 학교정보 입력체크, 선택자 추가 안하고 
		$("input[name='school']").each(function(idx) {//input태그의 name속성의 값이 school 선택자의 객체를 반복
			//console.log($("input[name='school']").eq(idx).val());
			if ($("input[name='school']").eq(idx).val() == ''){
				alert(idx+1 + '번째 학교명을 입력해주세요');
				$("input[name='school']").eq(idx).focus();
				con = false;
				return false;
			}
			if ($("input[name='year']").eq(idx).val() == ''){
				alert(idx+1 + '번째 졸업년도를 입력해주세요');
				$("input[name='year']").eq(idx).focus();
				con = false;
				return false;
			}
		});// 한 묶음으로 입력내용을 확인하기 때문에, 1번째 학교명, 1번째 졸업년도/ 2번째 학교명, 2번째 졸업년도 순서로 진행된다.
		if (con == false) return false;
		return true;
	}
	
	function idcheck() {
		if ($("#id").val().trim() == '') {
			alert('아이디를 입력하세요');
		} else {
			$.ajax({
				url:"idcheck.do",//비동기 요청을 할 주소호출 -> 앞에 /가 없으므로 상대경로 spring/user/idcheck.do
				data:{//id라는 이름의 파라미터를 통해 값을 가져오는 것
					id:$("#id").val()
				},
				async:false,//캐시는 아니고, 비동기라는 옵션을 false로 끔. 따라서, 실행의 순서를 동기적으로 바꿈
				method:"get",//전송방식 -> Controller에서 post방식이었으면 post를 쓴다.
				success:function(r) {//성공했을 때, 실행이 되어질 익명함수(여기서는 콜백함수)가 들어간다.
				//console.log(r);
					if (r.trim() == '1') {//양쪽 공백 제거한 값, 값이 1 = DB에 같은 값 존재
						alert('아이디가 중복되었습니다. 다른 아이디를 입력하세요.');
						$("#id").val('');//빈 값''을 주면 입력한 값이 지워지고, 다시 아이디를 입력하면 된다.
						$("#id").focus();
					} else {//값이 0 = DB에 같은 값 없음
						alert('사용가능한 아이디입니다.');
					}
				},
				error:function(res) {//실패하면 실행되는 함수
					console.log(res);
				}
			});
		}
	}
	$(function(){
		$("#id").keyup(function(){//입력할 때마다 반응하도록
			$.ajax({
				url:"idcheck.do",
				data:{
					id:$("#id").val()
				},
				async:false,
				method:"get",
				success:function(r) {
					if (r.trim() == '1') {
						$("#duplicateMsg").html("사용불가");
					} else {
						$("#duplicateMsg").html("사용가능");
					}
				}
			});
		})
	});
</script>
</head>
<body>
<h2>회원 가입</h2>
<form action="regist.do" method="post" onsubmit="return formCheck();"><!-- 가입버튼을 누를 때 혹여나 같은 아이디로 그 사이에 가입했는지 확인 -->
<table border="1">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id" id="id">
			<input type="button" value="중복체크" onclick="idcheck();"><br>
			<span id="duplicateMsg"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd" id="pwd"></td><!-- 객체 : 속성과 속성값으로 이루어짐 -->
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" name="pwd2" id="pwd2"></td>
	</tr><!-- 비밀번호를 확인시키는 이유? 사용자 실수로 오타가 나면 로그인할 때 안되므로, 두 번 입력받도록 해서 확인시킴 -->
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name"></td>
	</tr>
	<tr>
		<td>학교</td>
		<td>
			<table>
				<tr>
					<td>학교명</td>
					<td>졸업년도</td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="가입"></td>
	</tr>
</table>
</form>
</body>
</html>