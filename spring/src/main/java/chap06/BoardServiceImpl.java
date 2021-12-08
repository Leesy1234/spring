package chap06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//Bean으로 등록
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;//이 객체가 있어야 메서드를 호출할 수 있다.
	
	@Override
	public int count(BoardVo vo) {
		return boardDao.count(vo);
	}
	
	@Override
	public List<BoardVo> selectList(BoardVo vo) {//Map 인터페이스를 구현한 놈 중에 HashMap이 있고, 이는 키와 값으로 쌍으로 구성됨 -> 매개변수 String searchType, String searchWord
//		Map map = new HashMap();
//		map.put("searchType", searchType);
//		map.put("searchWord", searchWord);
		return boardDao.selectList(vo);//컨트롤러로 전달하기 위해 호출한다. 메서드를 실행한 후에 리턴값을 리턴한다!! 메서드를 리턴하지 않는다. (map)
	}

	@Override
	public int insert(BoardVo vo) {
		return boardDao.insert(vo);
	}

	@Override
	public BoardVo selectOne(int boardno) {
		return boardDao.selectOne(boardno);
	}
	
	@Override
	public int update(BoardVo vo) {
		return boardDao.update(vo);
	}

	@Override
	public int delete(BoardVo vo) {
		return boardDao.delete(vo);
	}

	@Override//BoardService 필드에 추상메서드를 만들지 않고, @Override를 안 써도 사용가능한데, 굳이 사용하는 이유는? 실수하지 않기 위해서
	public BoardVo2 selectOne2(int boardno) {
		return boardDao.selectOne2(boardno);
	}
	
}
