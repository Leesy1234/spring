package chap07.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	@Transactional
	public boolean insert(UserVo vo, HttpServletRequest req) {
		boolean r = false;
		
		int cnt = dao.insert(vo);
		// school 데이터를 등록
		// UserVo 객체에는 userno가 저장된 상태(dao에 insert가 되고 난 후이므로)
		// 학교정보 배열로 받아오기 => 객체는 무조건 String[] 배열로 가져온다.
/*		String[] school = req.getParameterValues("school");
		String[] year = req.getParameterValues("year");
		SchoolVo svo = new SchoolVo();
		svo.setUserno(vo.getUserno());//svo에 userno를 넣고,
		
		for (int i=0; i<school.length; i++) {// 현재 인덱스 0~2번까지 존재하므로, 3번 반복됨
			svo.setSchool(school[i]);
			svo.setYear(year[i]);
			cnt += dao.insertSchool(svo);
			//if (i== 1) throw new Exception(); 핸들링 처리해야 해서 안됨. try~catch로 묶어도 의미없음
		}
		// 위에서 에러가 나면 r은 그대로 false이므로, r을 for문 밖으로 빼둠
*/
		// 배열필드 사용 UserVo 필드에 배열 변수 school, year 선언
		SchoolVo svo = new SchoolVo();
		svo.setUserno(vo.getUserno());
		
		for (int i=0; i<vo.getSchool().length; i++) {//vo 객체의 getter 메서드 실행 = 배열 + 길이length
			svo.setSchool(vo.getSchool()[i]);// getter 메서드의 리턴값의 인덱스번호로 확인
			svo.setYear(vo.getYear()[i]);
			cnt += dao.insertSchool(svo);
			
		}
		
		// cnt : 배열의 길이 + 1
/*		if (cnt == school.length+1) {
			r = true;
		}
*/
		if (cnt == vo.getSchool().length+1) {
			r = true;
		}
		
		return r;
	}

	@Override
	public int idcheck(String id) {
		return dao.idcheck(id);
	}

	@Override
	public boolean login(UserVo vo, HttpSession sess) {//dao 메서드가 UserVo 타입을 리턴=> 그러면 UserVo 타입에 담아야 한다.
		UserVo uv = dao.login(vo);//vo에는 아이디, 비밀번호가 들어있는 상태! dao는 무조건 sql문 호출
		if (uv != null) {//로그인 성공
			sess.setAttribute("loginInfo", uv);//vo로 하면 안됨! uv는 vo를 가지고 해당되는 회원의 정보가 담긴 객체!(조건적용)
			return true;
		} //else {
		return false;
		//}
	}

}
