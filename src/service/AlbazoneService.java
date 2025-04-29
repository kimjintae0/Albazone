package service;

import utils.AlbaUtils;

public class AlbazoneService {
	
	private static AlbazoneService albazoneService = new AlbazoneService();
	private AlbazoneService() {}
	public static AlbazoneService getInstance() {
		return albazoneService;
	}
		
	public void menu() {
		if(userService.getLoginUser() == null) {
			int no = AlbaUtils.nextInt("1. 로그인 2. 회원가입");
			switch(no) {
				case 1:{
					userService.register();
					break;
				}
				case 2:{
					userService.login();
					break;
				}
				case 7:{
					throw new Exception();
				}
				default:{
					System.out.println("잘못된 입력입니다.");
					break;
				}
			}
		}
	}
}