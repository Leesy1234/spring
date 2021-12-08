package chap04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration// spring 설정파일 => annotation 필수
@ComponentScan(basePackages = {"chap04"}) // basePackages의 배열값(패키지)를 모두 스캔해서 Bean을 등록시킴 => 따라서 아래의 모든 내용을 전체 주석처리
public class AppContext {
/*
	//Greeter 객체(빈)를 생성해서 컨테이너에 등록 => 컨테이너로 들어가면 Object타입
	// Bean 이름이 greeter인 리턴값이 객체로 등록
	@Bean
	public MemberController memberController() {//@Component, @Autowired를 쓸 수 없을 때(DB, 다른 라이브러리에 있어 사용자가 annotation을 붙일 수 없을 때), 필요하다.
		MemberController con = new MemberController();
//		con.setService(memberService());
		return con;
	}
	
	//MemberDao를 Bean으로 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	//MemberService를 Bean으로 등록
	@Bean
	public MemberService memberService() {
		//MemberDao객체를 주입
		//return new MemberService().setDao(memberDao());//MemberService의 setter메서드를 지우면 에러남
		return new MemberService();
	}
*/	
}
