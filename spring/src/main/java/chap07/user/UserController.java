package chap07.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chap07.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/user/regist.do")
	public String regist() {
		return "user/regist";
	}
	
	@PostMapping("/user/regist.do")
	public String regist(Model model, UserVo vo, HttpServletRequest req) {//vo에는 아이디, 비밀번호, 이름 값이 들어있다.
		//System.out.println("등록 전 userno : "+vo.getUserno());// 가입 전/ 가입 중에는 userno가 없으므로 초기값 0
		log.info("등록 전 userno : "+vo.getUserno());
		boolean r = service.insert(vo, req); // user테이블에 insert 하고 난 후, vo에 set이 되므로!
		//System.out.println("등록 후 userno : "+vo.getUserno());// 가입 후, insert 실행 후에는 userno가 들어가있다.
		log.info("등록 후 userno : "+vo.getUserno());
		
		if (r) {
			model.addAttribute("msg", "정상적으로 가입되었습니다.");
			model.addAttribute("url", "/spring/board/index.do");
		} else {
			model.addAttribute("msg", "가입오류가 발생되었습니다.");
			model.addAttribute("url", "regist.do");
		}
		return "include/result";
	}
	
	@GetMapping("/user/idcheck.do")//간단한 경우 Servlet으로 응답하게 해도 되고, jsp로 응답하게 해도 된다.
	public String idcheck(Model model, @RequestParam String id) {
		int r = service.idcheck(id);
		model.addAttribute("ret", r);//r의 값은 0 또는 1만 나올 수 있다. SELECT COUNT(*) FROM user WHERE id = #{id}의 결과가 나오므로!
		return "include/return";
	}
	
	@RequestMapping("/user/login.do")//RequestMapping 또는 GetMapping 써도 된다.
	public String login(@CookieValue(value="cookieId", required=false) Cookie cookie, UserVo vo) {
		if (cookie != null) {// 이전에 아이디 저장 체크하고 로그인한 경우,
			vo.setId(cookie.getValue());
			//vo.setCheckId("check");
		}
		return "user/login";
	}
	
	@RequestMapping("/user/loginProcess.do")
	public String loginProcess(UserVo vo, HttpSession sess, Model model, HttpServletResponse res) {// model은 메세지 띄울 때 사용
		
		if (service.login(vo, sess)) {//로그인 성공 - if문 안에는 비교연산, 대입연산, 논리연산 true or false만 가능
			//쿠키 객체 생성
			Cookie cookie = new Cookie("cookieId", vo.getId());
			if ("check".equals(vo.getCheckId())) {// 사용자가 아이디 저장 체크
				cookie.setMaxAge(60*60*24*30); // 유효시간 30일 동안 보관 => 쿠키는 삭제 불가, 그래서 삭제한 것처럼 설정하려면 유효시간을 설정한다.
			} else {
				cookie.setMaxAge(0);// 즉시 만료
			}
			res.addCookie(cookie); // response객체에 쿠키 추가 -> 응답에 쿠키가 같이 와야만 사용자가 해당 응답을 PC에 쿠키로 저장할 수 있다.
			return "redirect:/board/index.do";//리다이렉트는 서버에서 보내는 것이므로 contextPath를 빼도 가능
		} else {//로그인 실패
			model.addAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다.");
			model.addAttribute("url", "login.do");
		}
		return "include/result";
	}
	
	@RequestMapping("/user/logout.do")
	public String logout(Model model, HttpSession sess) {
		sess.removeAttribute("loginInfo");
		sess.invalidate();
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("url", "/spring/board/index.do");//클라이언트쪽으로 보내는 경로이므로 contextPath를 포함한 전체주소를 입력
		return "include/result";
	}
	/*
	@RequestMapping("/user/mypage.do")
	public String mypage(Model model, HttpSession sess, BoardVo vo) {//model은 값을 담기 위해, session은 userno를 꺼내려고, boardVo는 커맨드 객체로 검색이나 정렬 이런 내용을 담기 위해 매개변수 선언
		vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
		//안쪽부터 코딩해야 편하다. vo에는 검색서치, 타입이 들어있을 수도 있다.
		
		//꼭 경로는 상관없이 BoardController에서도 작성가능하다.
		int totCount = boardService.count(vo); //총 개수
		int totPage = totCount / 10; //총 페이지 수 
		if (totCount % 10 > 0) totPage++;//총 게시글 수가 10개로 나눴을 때, 나머지가 0보다 크면 총 페이지수를 +1 해야 한다.
		System.out.println("totPage : "+totPage);
		
		int startIdx = (vo.getPage()-1) * 10;
		vo.setStartIdx(startIdx);//vo에 startIdx값이 담겨있어야만 xml에서 사용가능
		
		List<BoardVo> list = boardService.selectList(vo);
		model.addAttribute("list", list);
		model.addAttribute("totPage", totPage);//index.jsp에 가져오려면 request에 저장
		return "user/mypage";
	}
	위 코드를 BoardController에서 함께 작성하는 것으로 방법 변경
	*/
}
