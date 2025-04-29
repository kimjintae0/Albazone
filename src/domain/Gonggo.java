package domain;

public class Gonggo {
    //제목, 담당업무 , 근무기간, 근무시간, 시급, 근무지역

    private String title;
    private String role;
    private int woking_period;    //근무기간
    private int working_hours;    //근무시간
    private int wage; //시급
    private String region;     //지역

    //기본 및 모든 필드 사용 생성자
    public Gonggo() {}

    public Gonggo(String title, String role, int woking_period, int working_hours, int wage, String region) {
        super();
        this.title = title;
        this.role = role;
        this.woking_period = woking_period;
        this.working_hours = working_hours;
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
        return woking_period;
    }

    public void setWoking_period(int woking_period) {
        this.woking_period = woking_period;
    }

    public int getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
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
        return "Gonggo [title=" + title + ", role=" + role + ", woking_period=" + woking_period + ", working_hours="
                + working_hours + ", wage=" + wage + ", region=" + region + "]";
    }





}