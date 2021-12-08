package chap05;

public class MemberVo {

	private String name;
	private String email;
	private Integer no;
	private String[] hobby;//form에서 input type이 checkbox인 경우, 배열로 처리한다.
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	// 수많은 변수를 각각 전달하는 것 보다는, 그 변수들을 담은 하나의 MemberVo객체를 전달하는 것이 좋다.
	// 잘 못된 값이 입력되지 않게끔 처리하려면 java script처리 필요
}
