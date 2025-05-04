package domain;


public class AlbaUser extends User {
	
	// 싱글톤 0504 수정
//	private static  AlbaUser albaUser = new AlbaUser();
//	public static AlbaUser getInstance() {
//		return albaUser;
//	}
	
	//현재 전부 중복되는것들은 유저에 넣어두었고 알바 유저에서는 no
	public AlbaUser() {
		// TODO Auto-generated constructor stub
	}
	public AlbaUser(int userNo, String name, String tel, String id, String pw, String area) {
		super(userNo, name, tel, id, pw, area);
	}
}