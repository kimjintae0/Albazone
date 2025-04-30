package domain;

public class Gonggo {
    //제목, 담당업무 , 근무기간, 근무시간, 시급, 근무지역

    private String title;
    private String role;
    private int wokingPeriod;    //근무기간
    private int workingHours;    //근무시간
    private int wage; //시급
    private String region;     //지역

    //기본 및 모든 필드 사용 생성자
    public Gonggo() {}

    public Gonggo(String title, String role, int wokingPeriod, int workingHours, int wage, String region) {
        super();
        this.title = title;
        this.role = role;
        this.wokingPeriod = wokingPeriod;
        this.workingHours = workingHours;
        this.wage = wage;
        this.region = region;
    }

    //getter, setter
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

    public int getWoking_period() {
        return wokingPeriod;
    }

    public void setWoking_period(int wokingPeriod) {
        this.wokingPeriod = wokingPeriod;
    }

    public int getWorking_hours() {
        return workingHours;
    }

    public void setWorking_hours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Gonggo [title=" + title + ", role=" + role + ", wokingPeriod=" + wokingPeriod + ", workingHours="
                + workingHours + ", wage=" + wage + ", region=" + region + "]";
    }





}