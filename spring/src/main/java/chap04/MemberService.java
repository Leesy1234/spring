package chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 	XXService 클래스는 로직을 담당하는 클래스로 많이 사용된다.
 	컨트롤러가 특정 기능을 실행하기 위해서 호출
 */
@Component
public class MemberService {
	// 자동주입 => setter 메서드가 필요없어진다.
	// 스프링이 [타입]이 같은 Bean(객체)으로 주입을 시켜준다. 이름은 상관없다.
	@Autowired
	MemberDao dao; //주입받을 필드에 @Autowired를 쓰면 생성자, setter메서드 없어도 되기 때문에 가장 많이 사용된다.
	
	// 주입 방법 (1. 세터메서드 2. 생성자 3. 자동주입(@Autowired)
	// Setter 메서드
//	public MemberService setDao(MemberDao dao) {//리턴값 없으므로 void, 주입받을 타입 MemberDao가 타입으로 오고 변수명은 마음대로, (this.필드 = 변수대입)/ 주입을 해줄 준비만!
//		this.dao = dao;
//		return this;
//	}
	
	/* 회원등록 기능을 수행하는 메서드가 필요하다면?
	   여기서는 XXDao의 어떤 메서드를 호출해야 한다. 비즈니스 로직을 수행한다.
	*/
	public int insert(String name) {//매개변수 name을 받아서 넘겨줌
		int r = dao.insert(name);
		return r;
	}
}
