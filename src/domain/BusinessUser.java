package domain;

import service.UserService;

public class BusinessUser extends User {
	// 1) 사업자 :상호명, 사업자 번호  이외 공통 User(회원번호, 이름, 연락처, 소재지 , Id, Pw)
	private String companyName; // 상호명
	private String companyNumber; // 사업자 번호

	
	// 게터, 세터
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	
	
	// 생성자
	public BusinessUser() {}
	public BusinessUser(int userNo, String name, String tel, String id, String pw, String area,String companyName, String companyNumber) {
		super(userNo, name, tel, id, pw, area);
		this.companyName = companyName;
		this.companyNumber = companyNumber;
	}
	
	
	@Override
	public String toString() {
		return "BusinessUser [companyName=" + companyName + ", companyNumber=" + companyNumber + "]";
	}
	

	
	
	

	
	

}