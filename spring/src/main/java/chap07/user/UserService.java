package chap07.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {

	boolean insert(UserVo vo, HttpServletRequest req);//school에 있는 데이터를 받기 위해서 req 매개변수로 추가
	int idcheck(String id);
	boolean login(UserVo vo, HttpSession sess);//session 타입 : HttpSession
}
