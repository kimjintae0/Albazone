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
			int no = AlbaUtils.nextInt("1. 회원가입 2. 로그인 7. 종료  ");
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