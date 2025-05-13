package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Gonggo implements Serializable{
    //제목, 담당업무 , 근무기간, 근무시간, 시급, 근무지역
	private int userNo; // 사업자 유저번호
	private int gonggoNo; //공고번호
    private String title; // 공고 제목
    private String role; // 역할
    private int workHours;  //근무시간
    private int wage = 10030; //시급
    private String workingStartDate; //근무시작일
    private String workingEndDate; //근무종료일
    //근무기간을 yyyy-MM-dd ~ yyyy-MM-dd 의 정규형 사용해서 표현하기 위해 시작/종료일 쪼갬
  	public boolean state;	//공고진행상태
    private String comArea; // 사업장 위치
    private String tel; 
    
    // 게터, 세터
    public String getTitle() {
		return title;
	}
	public int getGonggoNo() {
		return gonggoNo;
	}
	public void setGonggoNo(int gonggoNo) {
		this.gonggoNo = gonggoNo;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getWorkHours() {
		return workHours;
	}
	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public String getWorkingStartDate() {
		return workingStartDate;
	}
	public void setWorkingStartDate(String workingStartDate) {
		this.workingStartDate = workingStartDate;
	}
	public String getWorkingEndDate() {
		return workingEndDate;
	}
	public void setWorkingEndDate(String workingEndDate) {
		this.workingEndDate = workingEndDate;
	}
	
    public String getComArea() {
		return comArea;
	}
	public void setComArea(String comArea) {
		this.comArea = comArea;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	// 생성자
    public Gonggo() {}

	public Gonggo(int userNo, int gonggoNo, String title, String role, int workHours, int wage, String workingStartDate, 
			String workingEndDate, boolean state, String comArea, String tel) {
		this.userNo = userNo;
		this.title = title;
		this.role = role;
		this.workHours = workHours;
		this.wage = wage;
		this.workingStartDate = workingStartDate;
		this.workingEndDate = workingEndDate;		
		this.gonggoNo = gonggoNo;
		this.state = state;
		this.comArea = comArea;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "공고번호 : " + gonggoNo 
				+ "\n제목 : " + title 
				+ "\n업무 : " + role
				+ "\n근무시간 : " + workHours + "시간(일)" 
				+ "\n시급 : " + wage + "원" 
				+ "\n근무기간 :" + (workingStartDate + " ~ " + workingEndDate )
				+ "\n진행상태 : " + (state == true ? "진행중" : "마감" )
				+ "\n근무지역 : " + comArea 
				+ "\n연락처 : " + tel
				+ "\n===================================="
				;
	}

    
    
}

       