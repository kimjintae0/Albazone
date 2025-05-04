package service;

import static utils.AlbaUtils.*;

import domain.AlbaUser;
import domain.BusinessUser;



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
				case 1 :{
					userService.register();
					break;
				}
				case 2 :{
					userService.login();
					break;
				}
				case 3 :{
					userService.modify();
					break;
				}
				case 7 :{
					throw new RuntimeException();
				}
				default :{
					System.out.println("잘못된 입력입니다.");
					break;
				}
			}
		}
	}
	
	public void loginMenu() {
		if(userService.getLoginUser() != null) {
			if(userService.getLoginUser() instanceof AlbaUser) {
				int no = nextInt("1. 알바공고 2. 지원내역 3. 이력서 관리 5. 회원정보 수정 6.회원탈퇴 7. 종료");
				switch(no) {
				
				}
			}
			else if(userService.getLoginUser() instanceof BusinessUser) {
				int no = nextInt("1. 공고관리 5. 회원정보 수정 6.회원탈퇴 7. 종료");
				switch(no) {
				
				}
			}
		}
	}
	
	
	
}