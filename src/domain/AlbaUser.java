package domain;


public class AlbaUser extends User {
	 //2) 개인회원 :  거주지, 연락처, 이력서
	private String albaName; // 상호명
	private String albaNumber;// 알바 연락처
	private String albaId; //알바 아이디
	private String albaPw; // 알바 비밀번호
	private String albaArea; // 알바 거주지   
	private String resume; //이력서
	
	public AlbaUser() {}
	
	public AlbaUser(String albaName, String albaNumber, String albaId, String albaPw, String albaArea, String resume) {
		super();
		this.albaName = albaName;
		this.albaNumber = albaNumber;
		this.albaId = albaId;
		this.albaPw = albaPw;
		this.albaArea = albaArea;
		this.resume = resume;
	}

	public String getAlbaName() {
		return albaName;
	}

	public void setAlbaName(String albaName) {
		this.albaName = albaName;
	}

	public String getAlbaNumber() {
		return albaNumber;
	}

	public void setAlbaNumber(String albaNumber) {
		this.albaNumber = albaNumber;
	}

	public String getAlbaId() {
		return albaId;
	}

	public void setAlbaId(String albaId) {
		this.albaId = albaId;
	}

	public String getAlbaPw() {
		return albaPw;
	}

	public void setAlbaPw(String albaPw) {
		this.albaPw = albaPw;
	}

	public String getAlbaArea() {
		return albaArea;
	}

	public void setAlbaArea(String albaArea) {
		this.albaArea = albaArea;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}

