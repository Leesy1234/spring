package chap04;

import org.springframework.stereotype.Component;

/*
 	XXDao는 데이터 처리를 위한 클래스
 	DAO(= Data Access Object)
 	예) insert, select, update, delete 등을 수행하기 위해서
 */
@Component
public class MemberDao {
	
	public MemberDao() {//MemberDao를 1번만 생성하는 싱글톤이기 때문에, Main 결과에서 한 번만 입력된다.
		System.out.println("MemberDao 생성자");
	}

	// DB에 회원 등록하는 메서드
	public int insert(String name) {
		//DB에 저장
		System.out.println(name+" 저장!!!");
		return 1; // 성공시 1을 리턴하는 메서드
	}
	
}
