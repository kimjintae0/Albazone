package domain;

public class Gonggo {
    //제목, 담당업무 , 근무기간, 근무시간, 시급, 근무지역
	private int userNo; // 사업자 유저번호
	private int gonggoNo; //공고번호
    private String title; // 공고 제목
    private String role; // 역할
    private int workHours;  //근무시간
    private int wage = 10030; //시급
    private String workingPeriod; //근무기간
  	public boolean state;	//공고진행상태
    private String comArea; // 사업장 위치
    
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
	public String getWorkingPeriod() {
		return workingPeriod;
	}
	public void setWorkingPeriod(String workingPeriod) {
		this.workingPeriod = workingPeriod;
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
    
	
	
	// 생성자
    public Gonggo() {}

	public Gonggo(int userNo, int gonggoNo, String title, String role, int workHours, int wage, String workingPeriod, boolean state, String comArea) {
		this.userNo = userNo;
		this.title = title;
		this.role = role;
		this.workHours = workHours;
		this.wage = wage;
		this.workingPeriod = workingPeriod;
		this.gonggoNo = gonggoNo;
		this.state = state;
		this.comArea = comArea;
	}

	

	@Override
	public String toString() {
		return "[공고 번호 : " + gonggoNo + ", 공고 제목 : " + title + ", 담당 업무 : " + role + ", 근무시간 : " + workHours + ", 시급 : " + wage
				 + ", 근무기간 : " + workingPeriod
				+ ", 공고번호 : " + gonggoNo + ", 공고진행상태 : " + state + "]";
	}
    
    
}

       