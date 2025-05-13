package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Resume  implements Serializable{
	// 제목, 이름, 연락처, 주소, 자기소개
	private int userNo; // 알바유저 번호
	private int resumeNo; // 이력서 번호
	private String title; // 제목
	private String name;  // 이름
	private String tel;  // 연락처
	private String area; // 사는 지역
	private String introduce; // 소개
	
	// 게터, 세터
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getResumeNo() {
		return resumeNo;
	}
	public void setResumeNo(int resumeNo) {
		this.resumeNo = resumeNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
	// 생성자
	public Resume() {
	
	}
	public Resume(int userNo, int resumeNo, String title, String name, String tel, String area, String introduce) {
		
		this.userNo = userNo;
		this.resumeNo = resumeNo;
		this.title = title;
		this.name = name;
		this.tel = tel;
		this.area = area;
		this.introduce = introduce;
	}
	
	@Override
	public String toString() {
		return "[이력서 번호: " + resumeNo + ",제목: " + title + "]\n"+ 
		           "이름: " + name +
		           "\n전화 번호: " + tel +
		           "\n지역: " + area +
		           "\n자기소개: " + introduce;
		}
}

