package chap06;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import chap05.MemberVo;

/*
 	RestAPI Controller
 */

@RestController
public class ApiController {
	
	@Autowired
	BoardService service;
	/*
	 	파라미터를 받는 4번째 방법
	 	4. @PathVariable : API개발할 때 사용하기도 함, 일반적인 어플리케이션에서는 잘 사용하지 않는다.
	 	/api/list/1/자바 -> page=1, searchWord=자바
	 	/api/list/2/파이썬 -> page=2, searchWord=파이썬 응답하는 형태로, 파라미터를 ?형태가 아닌 '/'경로에다가 적어주는 형태
	 */
	@GetMapping("/api/list/{page}/{searchWord}")// 경로처럼 생겼지만 {page}, {searchWord} 안에는 파라미터값이 들어간다.
	public List<BoardVo> list(@PathVariable int page, @PathVariable String searchWord){//안드로이드, 모바일 개발 관련해서 API 사용한다.
		System.out.println("page : "+page);
		System.out.println("searchWord : "+searchWord);
		List<BoardVo> list = service.selectList("","");
		return list;//[{"name":"홍길동","email":"hong@gmail.com","no":1,"hobby":null},{"name":"홍길동","email":"hong@gmail.com","no":1,"hobby":null}] 출력
		// JSON은 Java Script로 출력되므로 JAVA와는 반대로 [] 배열, {} 객체를 뜻한다. "속성명":"속성값"
		// ArrayList든, HashMap이든 데이터를 다루는 것이 가장 중요한 핵심 : 어떤 식으로 데이터가 저장이 되는지? 어떤 식으로 나중에 쓰이게 될 지, 어떻게 분석해야 할 지 ... 등 모두 감안해서 연계!
	}
}
