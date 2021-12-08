package chap07;

import java.util.List;

public interface BoardService {// 설계할 때는 목록, 등록, 수정, 삭제 등 모두 들어가야 한다.

	int count(BoardVo vo);//count관련 추가 => 원래는 안해도 되긴 함
	
	List<BoardVo> selectList(BoardVo vo);//Map일 때 매개변수는 String searchType, String searchWord
	int insert(BoardVo vo);//추상메서드이므로, 구현되는 BoardServiceImpl은 반드시 재정의를 해야 한다.
	BoardVo selectOne(int boardno);
	BoardVo2 selectOne2(int boardno);
	int update(BoardVo vo);
	int delete(BoardVo vo);
	
}
