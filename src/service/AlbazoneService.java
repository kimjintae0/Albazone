package service;

import static utils.AlbaUtils.*;

public class AlbazoneService {
	// 클래스 연동
	private UserService userService = UserService.getInstance();
	
	// 싱글톤
	private static AlbazoneService albazoneService = new AlbazoneService();
	public static AlbazoneService getInstance() {
		return albazoneService;
	}
	
	// 메뉴
	public void menu() {
		if(userService.getLoginUser() == null) { 
			int no = nextInt("1. 회원가입 2. 로그인 7. 종료");
			switch(no) {
				case 1:{
					userService.register();
					break;
				}
				case 2:{
					userService.login();
					break;
					
				}
				case 3 :{
					userService.modify();
					break;
				}
				case 4 : {
					userService.Resumeservice();
					break;
				}	
//				case 5 : {
//					userService.Resumeservice(); // 공고 내역이나 지원내역 볼 수 있는 칸 -> 공고 내역은 로그인 없이 가능?
//			
//				}
				case 6 :{
					userService.logOut();
					break;
				}	
				case 7:{
					throw new RuntimeException();
				}
				default:{
					System.out.println("잘못된 입력입니다.");
					break;
				}
			}
		}
	}
}