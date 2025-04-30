package domain;


public class AlbaUser extends User {
	 //2) 개인회원 :  거주지, 연락처, 이력서
	String area;
	User user;
	
	public AlbaUser() {
	
	}
	public AlbaUser(User user, String area) {
		this.user = user;
		this.area = area;
	}
	
	
}

