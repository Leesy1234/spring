package chap07;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // spring 설정파일
@ComponentScan(basePackages = {"chap07"})// 설정을 반드시 확인하자 -> web.xml파일에서 param-value값을 함께 수정해야 한다.
@EnableWebMvc // spring mvc 활성화
@EnableTransactionManagement // 트랜잭션 기능 활성화
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
	
	// DataSource 객체 등록
	// DB접속정보 설정
	@Bean(destroyMethod="close")
	public BasicDataSource dataSource() {//Bean으로 등록해놓고, 아래의 sqlSessionFactory에 주입하기 위해서 만듦
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/board");//여기서 DB가 달라지면, board를 수정해야 한다.
		ds.setUsername("root");
		ds.setPassword("root1234");
		return ds;
	}
	
	// SqlSessionFactory : 디자인 패턴 중 하나이지만, 객체를 직접 new로 생성하지 못하고, Factory를 만들어서 객체를 생성해야 한다.XXFactory, XXBuilder => 객체를 생성하는 놈
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {// 맨 위의 import를 보면, ibatis를 확인할 수 있다. ibatis->Mybatis로 바뀜.
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());
		
		// sql이 들어있는 xml(=Mapper file) 경로 설정
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml"));
		// classpath : 클래스경로 - local:WEB-INF
		// classpath:/mapper/**/*.xml : classpath의 mapper라는 폴더 하위에 있는 확장자가 xml인 모든 xml
		
		return ssfb.getObject();
		
	}
	
	// SqlSessionTemplate
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {// Dao에서 필요한 것: SqlSessionTemplate
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	// 파일 업로드
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		cmr.setMaxUploadSize(1024*1024*10);// 최대 업로드 사이즈(바이트)
		return cmr;
	}
	
	// 인터셉터
	@Bean
	public LoginInterceptor loginInterceptor() {//Bean으로 등록
		return new LoginInterceptor();
	}
	
	// 인터셉터 설정
	@Override
	public void addInterceptors(InterceptorRegistry reg) {//객체를 만들어 경로로 이동시킴
		//reg.addInterceptor(loginInterceptor()).addPathPatterns("/board/write.do");
		reg.addInterceptor(loginInterceptor())
							.addPathPatterns("/board/write.do")
							.addPathPatterns("/board/insert.do")
							.addPathPatterns("/user/mypage.do");//체이닝으로 연결, interceptor 주입받고 있는 상태
	}
	
	// 트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
}
