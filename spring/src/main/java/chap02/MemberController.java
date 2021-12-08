package chap02;

/*
 	컨트롤러(Controller)가 하는 역할? 클라이어트의 요청을 받아서, 해당하는 로직을 처리(=Service), 화면(=View)을 응답하게 하는 역할을 한다.
 */
public class MemberController {//설계도만 주입받을 수 있도록 해둠

	MemberService service;
	
	// MemberService 객체를 주입받음
	public void setService(MemberService service) {
		this.service = service;
	}
	
	// 실제 등록form에서 저장버튼을 클릭하면 전송되는 위치
	public String insert(String name) {
		//MemberService service = new MemberService();//를 필드로 선언하고, 객체 주입
		if (service.insert(name) > 0) {
			//등록 성공 시 처리
			
		} else {
			//등록 실패 시 처리
			
		}
		return "응답";
	}
}
