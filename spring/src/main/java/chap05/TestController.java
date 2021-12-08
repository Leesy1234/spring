package chap05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller// 매핑이 되려면 Bean에 등록이 되어 있어야 가능하다. 깜빡하고 쓰지 않으면 찾지 못하고 [HTTP 상태 404 - 찾을 수 없음]으로 응답하지 못 한다.
@RequestMapping("/member") // 전체 메서드에 대한 공통 경로가 된다.
public class TestController {//testController라는 이름으로 Bean 생성

	@GetMapping("/test.do") //위에서 @RequestMapping("/member")를 공통으로 적용받으므로, 실제 경로는 localhost:8080/spring/member/test.do가 된다.
	public String test(Model model) {//Model 대신 request를 넣어도 된다.
		model.addAttribute("test", "테스트"); // request에 setAttrubute한 것과 동일하다.
		return "/test"; // view의 경로를 리턴한다. view 경로 = prefix와 suffix 사이의 값, "/WEB-INF/view"와 ".jsp"의 사이에 있는 값
		// 리턴 값은 반드시 나와야 한다.
		// Controller 정리 + Parameter 값을 넣는 법 등
	}
	
	@PostMapping("/test2.do")
	public String test2(Model model) {
		model.addAttribute("test", "테스트"); 
		return "/test";
		// 클라이언트에서 요청이 일어나는 서버에서 작업이 일어남. PostMapping으로 지정했기 떄문에 /test2.do는 무조건 Post방식이어야만 에러가 나지 않는다.
		// 현재는 Get방식이기 때문에 [Request method 'GET' not supported]처럼 에러가 난다.
	}
	
	@RequestMapping("/test3.do")
	public String test3(Model model) {
		model.addAttribute("test", "테스트"); 
		return "/test";
		// RequestMapping은 Get, Post 방식 모두를 사용가능하므로, 정상적으로 창이 뜨는 것을 확인할 수 있다.
	}
	
	//RequestMapping method를 직접 지정
	@RequestMapping(value="/test4.do", method = RequestMethod.POST)//value가 기본값으로, 값이 하나일 때는 작성하지 않아도 자동으로 value값으로 지정된다.
	public String test4(Model model) {
		model.addAttribute("test", "테스트"); 
		return "/test";
		// method를 Post로 지정하면, [HTTP405-허용되지 않는 메소드 : Request method 'GET' not supported]로 에러가 난다. 왜? 저 사이트는 GET방식이므로! 
		// 따라서, method = RequestMethod.GET으로 하면 정상적으로 진행된다.
	}
	
	/*
	 	포워딩/ 리다이렉트
	 	- forward : 하나의 요청! request 공유 가능, 주소 안 바뀜.
	 	- redirect : 두 개의 요청, request 공유 안 됨, 주소 바뀜.
	 */
	
	// redirect : test5.do 로 접속하면 test4.do로 redirect시킴
	@GetMapping("/test5.do")//이 주소로 들어가면 , 아래의 redirect 주소로 변경되어 이동된다.
	public String test5() {
		return "redirect:/test4.do";//실제 client가 접속하는 경로를 작성해야 한다.
	}
	
	// 리턴이 없으면 (void) 매핑된 url과 동일한 경로의 jsp가 포워딩
	@GetMapping("/test6.do")
	public void test6() {
	}
	
	// 서블릿으로 응답
	@GetMapping("/test7.do")
	public void test7(HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");//html이 아닌 text로 출력되는 문제를 해결하기 위해서 setContentType 적용한다.
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('정상적으로 저장되었습니다.');");
		out.println("</script>");
	}
	
	
	/*
	 	url을 mapping시키는 방법
	 	1. GetMapping(Get방식만 가능) 2. PostMapping(Post방식만 가능) 3. RequestMapping
	 	* RequestMapping : 모든 방식 가능(get, post, put, ... 등의 모든 방식을 처리할 수 있는)
	 */
}

// 내가 설정한 web.xml에서 '/'로 설정해서 모든 경로가 dispatcher에 속하게 됨. 
// 전체적인 흐름이 어떻게 연결되는 지를 알아두기!