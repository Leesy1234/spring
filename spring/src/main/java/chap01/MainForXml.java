package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainForXml {

	public static void main(String[] args) {
		// xml 설정파일을 읽어오기
		// classPath : 클래스가 있는 경로
		// 서버(운영환경)에서는 /WEB-INF/classes (톰캣에서 배포하게 되면 이리로 들어옴) 
		// 로컬(개발환경)에서는 target/classes에 위치 => src는 소스, 만들어지면 컴파일되서 target 폴더 안으로 들어옴
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chap01/beans.xml");
		Greeter g = ctx.getBean("greeter", Greeter.class);
		System.out.println(g.greet());
	}

	//WebApplication 환경에서는 AnnotationConfigApplicationContext와 ClassPathXmlApplicationContext가 해주는 일을 dispatcherServlet이 처리한다.
	
}
