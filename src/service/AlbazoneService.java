package service;

import static utils.AlbaUtils.*;

import domain.AlbaUser;
import domain.BusinessUser;

public class AlbazoneService {
	// 클래스 연동
	private UserService userService = UserService.getInstance();
	private GonggoService gonggoService = GonggoService.getInstance();
	private ApplyService applyService = ApplyService.getInstance();
	private ResumeService resumeService = ResumeService.getInstance();
	
	// 싱글톤
	private static AlbazoneService albazoneService = new AlbazoneService();

	public static AlbazoneService getInstance() {
		return albazoneService;
	}
	
	// 메뉴
	public void menu() {
		if (userService.getLoginUser() == null) {
			int no = nextInt("1. 회원가입 2. 로그인  7. 종료 ");
			switch (no) {
			case 1: {
				userService.register();
				break;
			}
			case 2: {
				userService.login();

				}
				break;
		
			case 7: {
				throw new RuntimeException();
			}

			default: {
				System.out.println("잘못된 입력입니다.");
				break;
			}
			}
		}
		else if(userService.getLoginUser() instanceof AlbaUser) {
			int no = nextInt("1. 알바공고 2. 지원내역 3. 이력서 등록 4. 이력서 관리 5. 회원정보 수정 6.회원 탈퇴 7. 종료(로그아웃)"); // 종료 = 로그아웃(로그인한 회원만 보이게)
			switch (no) {
			case 1: {
				gonggoService.lookupUser();
				break;
			}
			case 2: {
				
				break;
			}
			case 3: {
				resumeService.resister();
				break;
			}
			case 4: {
				
				break;
			}
			
			case 5: {
				userService.modify2();
				break;
			}

			case 6: {
				userService.remove();
				break;
			}

			case 7: {
				userService.logOut();
				break; 
			}

			}
		} else if (userService.getLoginUser() instanceof BusinessUser) {
			int no = nextInt("1. 공고등록 2. 공고관리 5. 회원정보 수정 6.회원탈퇴 7. 종료(로그아웃)");// 종료 = 로그아웃
			switch (no) {
			case 1:{
				gonggoService.register();
				break;
			}
			case 2:{
				
				break;
			}
			case 5: {
				userService.modify();
				break;
			}

			case 6: {
				userService.remove();
				break;
			}
			case 7: {
				userService.logOut();
				break;
			}
			}
		}
	}
}