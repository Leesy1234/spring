package chap07;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo extends Parameter {//초기화되지 않은 필드값은 기본자료형 0, 참조자료형 null/ Parameter의 상속을 받아서 코드의 중복을 막는다.

	private int boardno;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private String filename; // 파일업로드
	private int userno;
	
	
	// 기존에 있던 getter/setter 메서드를 lombok을 pom.xml 파일에 추가해서,
	// 어노테이션 @Getter, @Setter 기능을 사용하여 자동으로 클래스 파일에 생성하여 정상적으로 기능한다.
}
