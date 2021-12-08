package chap05;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // spring 설정파일
@ComponentScan(basePackages = {"chap05"})
@EnableWebMvc // spring mvc 활성화
public class MvcConfig implements WebMvcConfigurer{

	// html, css 등을 처리하기 위한 설정
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer cnf) {
		cnf.enable();
	}
	
	// view 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.jsp("/WEB-INF/view/", ".jsp");
	}
	
	// 비즈니스 로직이 필요없는(디자인요소만 있는 페이지) 경우 = url과 jsp를 매핑(Controller 만들 필요 없이)
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("/test8.do").setViewName("test8");//"/WEB-INF/view/", ".jsp"사이에 있는 값이 ViewName에 들어온다.
	}
}
