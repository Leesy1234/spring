package chap01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// spring 설정파일 => annotation 필수
public class AppContext {

	//Greeter 객체(빈)를 생성해서 컨테이너에 등록 => 컨테이너로 들어가면 Object타입
	// Bean 이름이 greeter인 리턴값이 객체로 등록
	@Bean
	public Greeter greeter() {//g는 greeter의 객체로, Bean에 들어감/ 메서드명이 Bean이름
		Greeter g = new Greeter();
		g.setName("홍길동");
		return g;
	}
}
