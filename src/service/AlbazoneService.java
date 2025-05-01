package service;

import utils.AlbaUtils;

public class AlbazoneService {
	private UserService userService = UserService.getInstance();
	private static AlbazoneService albazoneService = new AlbazoneService();
	private AlbazoneService() {}
	public static AlbazoneService getInstance() {
		return albazoneService;
	}
		
	public void menu() {
		if(userService.getLoginUser() == null) { 
			int no = AlbaUtils.nextInt("1. 회원가입 2. 로그인 3. 회원 정보 수정 4. 로그아웃  ");
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
					userService.logOut();
				}
				case 4 : 
					userService.modify();
				
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