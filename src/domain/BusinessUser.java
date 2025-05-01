package domain;

public class BusinessUser extends User {
	// 1) 사업자 : 구현/ ID, Pw, 대표자명, 상호명, 연락처

	private String companyName; // 상호명

	public BusinessUser() {
	}

	public BusinessUser(String companyName) {
		super();
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}