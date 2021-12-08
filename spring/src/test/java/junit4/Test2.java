package junit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import chap07.BoardDao;
import chap07.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {chap07.MvcConfig.class})
@WebAppConfiguration
public class Test2 {

	//BoardDao 테스트하려면, 객체가 있어야 한다.
	@Autowired
	BoardDao boardDao;
	
	@Before
	public void before() {
		System.out.println("before");
	}
	
	@Test
	public void boardDaoTest() {
		int cnt = boardDao.count(new BoardVo());
		System.out.println(cnt);
	}
}
