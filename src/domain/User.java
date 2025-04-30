package domain;

public abstract class User {
	// 회원(추상)-> 유저 리스트로 묶일 수 있게
	//개인*사업주(구상)
    //가입, 수정, 탈퇴
    //1) 사업자 : 구현/ ID, Pw, 대표자명, 상호명, 연락처
 //2) 개인회원 : 구현/ ID, PW, 이름, 거주지, 연락처, 이력서
	
	private String id; // 아이디
	private String pw; // 비밀번호
	private String tel; // 연락처
	private String name; // 이름(대표자명 or 개인회원 이름) -> 까지는 공통
	
	public User() {}
	
	public User(String id, String pw, String tel, String name) {
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.name = name;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

