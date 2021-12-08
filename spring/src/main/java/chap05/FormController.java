package chap05;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	@GetMapping("/form.do")
	public String form() {
		return "form";
	}
	
	/*
	 	파라미터(여기서는 form 값을 받는)를 받는 방법(총 4가지 => 그 중 3가지만 spring에서 배우고, 1가지는 springboot할 때)
	 	1. HttpServletRequest 객체
	 	2. @RequestParam
	 	3. 커맨드객체 : Parameter가 1~2개가 아니라면 이 방법을 많이 사용한다.
	 		- 파라미터의 이름과 동일한 필드에 자동으로 값을 넣어준다.
	 	4. @PathVariable
	 	/api/list/1 -> 1페이지
	 	/api/list/2 -> 2페이지 응답하는 형태로, 파라미터를 ?형태가 아닌 '/'경로에다가 적어주는 형태
	 */
	
	@PostMapping("/send.do")
	public String send(HttpServletRequest req, @RequestParam("email") String email, @RequestParam(value="tel", required=false) String tel,
						MemberVo vo) {
		// 매개변수 String email 앞에 @RequestParam("email")을 붙여서 사용한다. required값이 true(기본값)인 경우에는 값이 반드시 있어야 하기때문에 에러가 난다.
		
		String name = req.getParameter("name");
		req.setAttribute("msg", name+"님, 안녕하세요");//send.jsp에서 el에 "msg"라는 이름으로 값을 불러오게 한다.
		req.setAttribute("msg2", "이메일 : "+email);
		req.setAttribute("tel", tel);//에러[Required String parameter 'tel' is not present] : tel에 값이 없음
		
//		MemberVo vo = new MemberVo();
//		vo.setName(name);
//		vo.setEmail(email);
//		vo.setNo(no);
//		위와 같은 과정은 변수마다 일일이 코드를 작성해야 하므로, send에 매개변수로 MemberVo vo를 넣어주면 편하다.
//		MemberVo에 있는 Setter메서드를 통해서 값을 호출한다. setter메서드가 없으면 private 접근제한자로 인해서 값을 가져올 수 없다.
		System.out.println(vo.getName()+" "+ vo.getEmail()+ " " +vo.getNo());// 지금은 출력만, 나중에는 Service, Dao로 전달해야 한다.
		if (vo.getHobby() != null) {//getHobby의 초기값은 참조자료형으로 null이기 때문에, getHobby가 null이 되면 null.length-> 에러가 난다. 조건을 넣어줘서 처리한다.
		for (int i=0; i<vo.getHobby().length; i++) {//배열의 값 출력 => 나중에 자격증처럼 몇 개를 추가할 지 모를 때는 자격증관련 창을 별도로 만들어서 insert로 추가할 수 있도록.
			System.out.println(vo.getHobby()[i]);
			}
		
		for (String hobby : vo.getHobby()) {//향상된 for문
			System.out.println(hobby);
			}
		}
		
		// 만약 커맨드 객체 없이 취미값을 vo2의 hobby에 저장하려면?
		MemberVo vo2 = new MemberVo();
		String[] hobbys = req.getParameterValues("hobby");//getParameterValues 자체가 배열을 뜻함.
		vo2.setHobby(hobbys);
//		String[] hobbys = new String[req.getParameterValues("hobby").length];
//		for (int i=0; i<req.getParameterValues("hobby").length; i++) {
//			hobbys[i] = req.getParameterValues("hobby")[i];
//		}
		
		return "send";
	}
	
	/*
	 	View에서 사용하기 위한 값(대부분 출력하기 위한)을 Controller에서 저장하는 방법
	 	- request에 setAttribute(하나의 요청일 때만 가능)
	 	- session에 setAttribute(모든 url에서도 다 써야하는 경우 - 예) 로그인정보!!!)
	 	- model에 addAttribute
	 	- ModelAndView에 add
	 */
	
	// ModelAndView 객체 살펴보기 -> 실제로 이를 통해 코딩을 하는 사람도 있다.
	// 실제로 Model + View 되어 있다.
	@GetMapping("/test9.do")
	public ModelAndView test8() {// ModelAndView 객체를 리턴하도록
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "홍길동");
		mav.setViewName("test9");//viewResolver로 forwarding시키는 작업을 알아서 진행한다.
		return mav;
	}
	
	
}
