package domain;

public abstract class User {
//상속만 받게하고 생성만 받게하기
	// 공통 부분 묶어두기
	private String Name; // 이름
	private String Number;// 연락처
	private String Id; // 아이디
	private String Pw; //  비밀번호
	private String Area; //  사업자 소재지 or 거주지   
	private String resume; //이력서
	
	
	public User () {}

	public User(String name, String number, String id, String pw, String area, String resume) {
		super();
		Name = name;
		Number = number;
		Id = id;
		Pw = pw;
		Area = area;
		this.resume = resume;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPw() {
		return Pw;
	}

	public void setPw(String pw) {
		Pw = pw;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "User [Name=" + Name + ", Number=" + Number + ", Id=" + Id + ", Pw=" + Pw + ", Area=" + Area
				+ ", resume=" + resume + "]";
	}
	
}