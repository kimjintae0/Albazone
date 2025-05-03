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
			int no = AlbaUtils.nextInt("1. 회원가입 2. 로그인 3. 회원 정보 수정 4.이력서 등록 5. 공고 6. 로그아웃  ");
			switch(no) {
				case 1:{
					userService.register();
					
				}
				case 2:{
					userService.login();
					
					
				}
				case 3 :{
					userService.modify();
					
				}
				case 4 : {
					userService.Resumeservice();		
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