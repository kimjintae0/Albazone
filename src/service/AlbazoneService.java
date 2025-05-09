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
			int no = nextInt("1. 회원가입 2. 로그인  9. 종료 "); 
			switch (no) {
			case 1: {
				userService.register();
				break;
			}
			case 2: {
				userService.login();

				}
				break;
		
			case 9: { 
				throw new RuntimeException();
			} 

			default: {
				System.out.println("잘못된 입력입니다.");
				break;
			}
			}
		}
		else if(userService.getLoginUser() instanceof AlbaUser) {
			int no = nextInt("1. 알바공고 2. 지원하기 3. 지원내역 4. 지원취소 5. 이력서 관리 6. 회원정보 수정 7.회원 탈퇴 8. 종료(로그아웃)"); 
			switch (no) {
			case 1: {
				gonggoService.lookupUser();
				break;
			}
			case 2: {
				applyService.apply();
				break;
			}
			case 3: {
				applyService.lookupUser();
				break;
			}
			case 4: {
				applyService.remove();
				break;
			}
			case 5: {
				no = nextInt("1. 이력서 등록 2. 이력서 조회 3. 이력서 수정 4. 이력서 삭제 5. 나가기");
				switch (no) {
				case 1:{
					resumeService.resister();
					break;
				}
				case 2:{
					resumeService.lookupUser();;
					break;
				}
				case 3:{
					resumeService.modify();
					break;
				}
				case 4:{
					resumeService.remove();
					break;
				}
				case 5:{
					break;
				}
				}
				break;
			}
			
			case 6: {
				userService.modify(); 
				break;
			}

			case 7: {
				userService.remove();
				break;
			}

			case 8: {
				userService.logOut();
				break; 
			}

			}
		} else if (userService.getLoginUser() instanceof BusinessUser) {
			int no = nextInt("1. 공고 5. 회원정보 수정 6. 회원탈퇴 7. 종료(로그아웃)8. 조회");
			switch (no) {
			case 1:{
				no = nextInt("1. 공고 등록 2. 공고 조회 3. 지원자 확인 4. 공고 수정 5. 공고 삭제 6. 나가기 ");
				switch (no) {
				case 1:{
					gonggoService.register();
					break;
				}
				case 2:{
					gonggoService.lookupOwner();
					break;
				}
				case 3:{
					applyService.lookupUserOwner();
					break;
				}
				case 4:{
					gonggoService.modify(); 
					break;
				}
				case 5:{
					gonggoService.remove();
					break;
				}
				case 6:{
					break;
				}
				}
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
			case 8 :{
				userService.lookupOwner();
			}
			}
		}
	}
}