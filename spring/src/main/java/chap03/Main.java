package chap03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);//톰캣이 start될 떄, Bean 등록 후 자동주입
		MemberController con = ctx.getBean("memberController", MemberController.class);
		for (int i=0; i<1000; i++) {
			con.insert("홍길동");
		}
		
	}

}
