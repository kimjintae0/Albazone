package domain;

import java.io.Serializable;

public abstract class User implements Serializable{
	// 공통 부분 묶어두기
	private int userNo; // 회원번호
	private String name; // 이름
	private String tel;// 연락처
	private String id; // 아이디
	private String pw; //  비밀번호
	private String area; //  지역   
	
	
	// 게터, 세터
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	// 생성자
	public User() {
		
	}
	public User(int userNo, String name, String tel, String id, String pw, String area) {
		this.userNo = userNo;
		this.name = name;
		this.tel = tel;
		this.id = id;
		this.pw = pw;
		this.area = area;
	}
	
	
	@Override	// 이쁘게 보일 수 있도록 정리
	public String toString() {
		return "User [회원 이름=" + name + ", 연락처=" + tel + ", 아이디(id)=" + id + ", 거주지(area=)" + area + "]";
	}
}