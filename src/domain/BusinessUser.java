package domain;

public class BusinessUser extends User {
	// 1) 사업자 : 구현/ ID, Pw, 대표자명, 상호명, 연락처, 사업자 번호

	private String companyName; // 상호명
	private String number; // t사업자 번호
	

	public BusinessUser() {}

	
	public BusinessUser(String companyName, String number) {
		super();
		this.companyName = companyName;
		this.number = number;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "BusinessUser [companyName=" + companyName + ", number=" + number + "]";
	}

	
	

}