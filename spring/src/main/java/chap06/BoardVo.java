package chap06;

import java.sql.Timestamp;

public class BoardVo extends Parameter {//초기화되지 않은 필드값은 기본자료형 0, 참조자료형 null/ Parameter의 상속을 받아서 코드의 중복을 막는다.

	private int boardno;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private String filename; // 파일업로드
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
}
