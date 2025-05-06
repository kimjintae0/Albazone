package domain;


public class AlbaUser extends User {
	//공통 User(회원번호, 이름, 연락처, 소재지 , Id, Pw)로 생성해둠
	
	public AlbaUser(int userNo, String name, String tel, String id, String pw, String area) {
		super(userNo, name, tel, id, pw, area);
	}

}