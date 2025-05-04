package domain;


public class AlbaUser extends User {
	//공통 User(회원번호, 이름, 연락처, 소재지 , Id, Pw)로 생성해둠
	
	// 싱글톤 0504 수정 -> 필요하다고 하셔서 일단 만들어 뒀습니다!
//	private static  AlbaUser albaUser = new AlbaUser();
//	public static AlbaUser getInstance() {
//		return albaUser;
//	}
	
	public AlbaUser() {
		// TODO Auto-generated constructor stub
	}
	public AlbaUser(int userNo, String name, String tel, String id, String pw, String area) {
		super(userNo, name, tel, id, pw, area);
	}
}