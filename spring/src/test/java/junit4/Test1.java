package junit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test1 {

	/*
	 junit4 테스트 순서(기본적으로 어노테이션을 넣으면 된다. junit4든, junit5든 어노테이션 기반으로 동작)
	 : Before -> Test -> After 순서대로 돌아간다.
	 
	 Before : 초기화 용도
	 After : 끝났을 떄, 자원해제 등에 많이 사용함
	 */
	
	@Before
	public void init() {
		System.out.println("before");
	}
	
	@Test
	public void test() {
		System.out.println("test");
	}
	
	@Test
	public void test2() {
		System.out.println("test2");
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
	
}
