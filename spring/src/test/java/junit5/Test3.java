package junit5;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import chap07.BoardDao;
import chap07.BoardVo;
import chap07.user.UserVo;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = chap07.MvcConfig.class)
public class Test3 {//Junit을 쓰면 Dao나 Service를 주입받아서, Controller, jsp가 없어도 직접 테스트 가능!

	@Autowired
	BoardDao dao;
	
	@Autowired
	WebApplicationContext ctx;
	MockMvc mockMvc; // 필드로 선언 -> 테스트 할 때마다 먼저 실행되어야 하므로
	
	// 세션객체
	MockHttpSession session;//spring-test(maven repository에 위치) 안에 있는.
	
	@BeforeEach
	void init() {
		// 목업객체(목업? 실제 휴대폰이 아니라 모양만 보라고 만들어 놓는 휴대폰 모형같은 것)
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();//Mvc의 목업객체가 생성됨
		
		// 로그인 세선객체 생성
		UserVo uv = new UserVo();
		uv.setUserno(2);
		session = new MockHttpSession();
		session.setAttribute("loginInfo", uv);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		request.setSession(session);// 톰캣이 없어도 가상으로 request객체와 session객체를 만들어서 test 진행할 예정
	}
	
	// board/index.do 테스트
	@Test
	void boardIndex() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/board/index.do").param("page", "2");//2페이지, Vo에는 int지만 파라미터에서는 모두 문자열
		mockMvc.perform(req);// mockMvc.perform(MockMvcRequestBuilders.get("/board/index.do"));와 동일함, 이렇게 더 많이 쓴다.
	}
	
	@Test
	void boardWrite() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/board/write.do");// 실행만 체크 -> 로그인이 되어 있지 않으면!
		mockMvc.perform(req);
	}
	
	@Test
	void mypage() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/user/mypage.do").session(session);
		mockMvc.perform(req);
	}
	
	@Test
	void boardInsert() throws Exception {
		File f = new File("E:\\kdigital\\web\\img\\logo.png");// 실제 해당 이미지가 있는 경로 + 파일 이름
		FileInputStream fis = new FileInputStream(f);// 파일을 읽어오는 것이므로 FileInputStream사용
		MockMultipartFile file = new MockMultipartFile("file", f.getName(), "multipart/form-data", fis);
		// MockMultipartFile("업로드된 파일명", 업로드한 파일 객체의 실제이름(파라미터 이름), "multipart/form-data", FileInputStream 이름)
		RequestBuilder req = MockMvcRequestBuilders
				.multipart("/board/insert.do")
				.file(file)
				.param("title", "제목테스트")
				.param("content", "내용테스트")
				.param("writer", "작성자테스트")
				.session(session);
		mockMvc.perform(req);
	}
	
	@Test
	void test() {
		int cnt = dao.count(new BoardVo());
		System.out.println(cnt);
		assertTrue(cnt >0);
	}
}
