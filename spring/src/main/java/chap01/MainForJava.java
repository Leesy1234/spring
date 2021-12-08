package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForJava {

	public static void main(String[] args) {
		// 설정파일을 읽어와서(= 자바파일 AppContext를 읽어와서) Bean 등록(=객체를 컨테이너에 등록)
		// greeter라는 이름으로 객체(Bean)을 컨테이너에 등록시킴(싱글톤으로 등록)
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		// Bean을 가져오기
		Greeter g = (Greeter)ctx.getBean("greeter");//getBean은 Object타입을 리턴되므로, 타입이 다른 것은 강제형변환(Greeter는 Object의 자식타입)해야 한다.
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		
		System.out.println(g==g2);//true-> spring은 기본적으로 싱글톤으로 객체를 등록한다. 따라서, 같은 주소(위치)를 가리키는 같은 객체(참조자료형)이다.
	}

}
