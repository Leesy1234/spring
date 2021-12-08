package chap06;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository// annotation을 붙이면 Bean에 등록이 되는 이유? MvcConfig(설정)에서 @ComponentScan을 작성했으므로!
public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int count(BoardVo vo) {//집계함수는 무조건 하나만 나온다 => selectOne 사용
		return sqlSessionTemplate.selectOne("board.count", vo);
		//count는 BoardService에서 만들 필요가 없다. 왜? 
	}
	
	
	
	public List<BoardVo> selectList(BoardVo vo){//외부에서 값을 받아오려면 매개변수가 있어야 한다.
		return sqlSessionTemplate.selectList("board.selectList", vo);// DB에 데이터가 없는 경우, 요소가 없는 비어있는 ArrayList 배열(!=null)을 가져온다.
	}
	
	public int insert(BoardVo vo) {//board.xml의 parameter의 값을 담고 있는 매개변수를 가져와야 하므로,
		//return sqlSessionTemplate.insert("board.insert", vo);
		int r = -1;//값이 없는 경우를 -1로 표현한다.
		try {
			r = sqlSessionTemplate.insert("board.insert", vo);
		} catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	// 메서드 생성 -> 호출 : sqlSessionTemplate.selectOne("board.selectOne", PK)
	public BoardVo selectOne(int boardno) {//PK값을 받아와야 하므로 int타입 매개변수가 들어온다.
		return sqlSessionTemplate.selectOne("board.selectOne", boardno);
	}
	
	public BoardVo2 selectOne2(int boardno) {
		return sqlSessionTemplate.selectOne("board.selectOne2", boardno);
	}
	
	public int update(BoardVo vo) {
		return sqlSessionTemplate.update("board.update", vo);
	}
	
	public int delete(BoardVo vo) {//값이 하나만 필요해도 그 속성이 boardno이므로, 그 속성과 값이 들어있는 BoardVo vo를 매개변수로 줘도 된다.
		return sqlSessionTemplate.delete("board.delete", vo.getBoardno());
	}
}
