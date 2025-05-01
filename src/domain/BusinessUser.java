package domain;

public class BusinessUser extends User{ 
	//1) 사업자 : 구현/ ID, Pw, 대표자명, 상호명, 연락처
	
   private String BusinessUserName; // 상사업자 이름
   private String companyName; // 상호명
   private String BusinessUserArea; // 주소 
   private String BusinessUserNumber;// 사업자 연락처
   private String BusinessUserId ; //사업자 아이디
   private String BusinessUserPw; // 사업자 비밀번호 -> Business -> business 나중에 소문자 변경
   
   public BusinessUser() {}

   public BusinessUser(String businessUserName, String companyName, String businessUserArea, String businessUserNumber,
		String businessUserId, String businessUserPw) {
		super();
		this.BusinessUserArea  = businessUserName;
		this.companyName = companyName;
		this.BusinessUserArea = businessUserArea;
		this.BusinessUserNumber = businessUserNumber;
		this.BusinessUserId = businessUserId;
		this.BusinessUserPw = businessUserPw;
	}
		
	public String getBusinessUserName() {
		return BusinessUserName;
	}
	
	public void setBusinessUserName(String businessUserName) {
		BusinessUserName = businessUserName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getBusinessUserArea() {
		return BusinessUserArea;
	}
	
	public void setBusinessUserArea(String businessUserArea) {
		BusinessUserArea = businessUserArea;
	}
	
	public String getBusinessUserNumber() {
		return BusinessUserNumber;
	}
	
	public void setBusinessUserNumber(String businessUserNumber) {
		BusinessUserNumber = businessUserNumber;
	}
	
	public String getBusinessUserId() {
		return BusinessUserId;
	}
	
	public void setBusinessUserId(String businessUserId) {
		BusinessUserId = businessUserId;
	}
	
	public String getBusinessUserPw() {
		return BusinessUserPw;
	}
	
	public void setBusinessUserPw(String businessUserPw) {
		BusinessUserPw = businessUserPw;
	}
		   
			
}
