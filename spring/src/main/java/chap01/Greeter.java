package chap01;

public class Greeter {

	private String name;
	
	public String greet() {
		return name + "님 안녕하세요";
	}
	
	public void setName(String name) {//필드에 매개변수에 있는 값을 대입해주는 Setter 메서드 만들기
		this.name = name;
	}
}
