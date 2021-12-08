package chap07;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {//추상메서드가 없음
	/*
	 * Interceptor란, 요청을 하러 가든, 컨트롤러에서 끝났든, 다시 응답을 하든 중간에 가로챈다.
	 * 재정의를 통한 로그인체크 메서드 생성
	 * preHandle : 컨트롤러 실행 전
	 * postHandle : 컨트롤러 실행 후
	 * afterCompletion : 뷰까지 실행된 후
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		/*
		 * Session 객체를 생성해서 -> 로그인세션이 존재하는지 여부 체크
		 * 존재여부에 따라서 처리
		 */
		HttpSession sess = req.getSession();
		if (sess.getAttribute("loginInfo") == null) {//로그인이 안 된 상태
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용가능합니다.')");
			out.println("location.href='/spring/user/login.do';");
			out.println("</script>");
			return false;
		} else { //로그인된 상태
			return true; // 인터셉터 하기 전 가던 길로 계속 진행
		}
		
	}

}
