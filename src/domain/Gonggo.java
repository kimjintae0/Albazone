package domain;

import java.util.Date;

public class Gonggo {
    //제목, 담당업무 , 근무기간, 근무시간, 시급, 근무지역

    private String title;
    private String role;
    private int workHours;    //근무시간
    private int wage; //시급
    private Date startDate;
    private Date endDate;
    private int workingPeriod;	//end date - startdate
    
    public Gonggo() {}

	public Gonggo(String title, String role, int workHours, int wage, Date startDate, Date endDate, int workingPeriod) {
		super();
		this.title = title;
		this.role = role;
		this.workHours = workHours;
		this.wage = wage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workingPeriod = workingPeriod;
	}

	public String getTitle() {
		return title;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getWorkingPeriod() {
		return workingPeriod;
	}

	public void setWorkingPeriod(int workingPeriod) {
		this.workingPeriod = workingPeriod;
	}

	@Override
	public String toString() {
		return "Gonggo [title=" + title + ", role=" + role + ", workHours=" + workHours + ", wage=" + wage
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", workingPeriod=" + workingPeriod + "]";
	}
    
    
}

       