package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// spring 설정파일 => annotation 필수
public class AppContext {

	//Greeter 객체(빈)를 생성해서 컨테이너에 등록 => 컨테이너로 들어가면 Object타입
	// Bean 이름이 greeter인 리턴값이 객체로 등록
	@Bean
	public MemberController memberController() {//리턴해야 하는 타입인 class 이름으로 지정하고, 메서드 이름은 아무거나 해도 된다.
		MemberController con = new MemberController();
		con.setService(memberService());
		return con;
//		MemberController mc = new MemberController();
//		return mc;
	}
	
	//MemberDao를 Bean으로 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	//MemberService를 Bean으로 등록
	@Bean
	public MemberService memberService() {
//		MemberService service = new MemberService();
//		//MemberDao객체를 주입
//		service.setDao(memberDao());
//		return service;
		return new MemberService().setDao(memberDao());//메서드 체이닝은 . 왼쪽으로 접근하는 것, 중첩되어 있을 때는 안쪽부터 해결
		//MemberService의 setter메서드에서 리턴 타입을 void -> MemberService로 변경하고, return this로 수정
	}
	
}
