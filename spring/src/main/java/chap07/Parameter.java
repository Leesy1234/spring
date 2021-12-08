package chap07;

public class Parameter {
	
	private String searchType;
	private String searchWord;
	private String orderCond;
	private int startIdx;  // limit 시작값
	private int page;  // 사용자가 요청한 페이지
	
	public Parameter() {//생성자 -- 사용자가 페이지를 요청하지 않으면 기본 값 1페이지로 초기화
		page = 1;
	}
	
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getOrderCond() {
		return orderCond;
	}
	public void setOrderCond(String orderCond) {
		this.orderCond = orderCond;
	}
	// BoardVo에서도 사용될 수 있으므로 재사용성을 높이기 위해서는? 상속을 받으면 된다.
		
}