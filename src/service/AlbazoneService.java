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
			int no = nextInt("1. 회원가입 2. 로그인  3. 종료 ",s -> s > 0 && s <= 3, "1 ~ 3 사이의 번호를 입력해주세요");
			switch (no) {
			case 1: {
				userService.register();
				break;
			}
			case 2: {
				userService.login();

			}
				break;

			case 3: {
				throw new RuntimeException();
			}

			default: {
				System.out.println("잘못된 입력입니다.");
				break;
			}
			}
		} else if (userService.getLoginUser() instanceof AlbaUser) {
			int no = nextInt("1. 알바공고 2. 지원하기 3. 지원내역 4. 지원취소 5. 이력서 관리 6. 회원정보 조회 7. 회원정보 수정 8.회원 탈퇴 9. 종료(로그아웃)",s -> s > 0 && s <= 9, "1 ~ 9 사이의 번호를 입력해주세요");
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
				no = nextInt("1. 이력서 등록 2. 이력서 조회 3. 이력서 수정 4. 이력서 삭제 5. 나가기",s -> s > 0 && s <= 5, "1 ~ 5 사이의 번호를 입력해주세요");
				switch (no) {
				case 1: {
					resumeService.resister();
					break;
				}
				case 2: {
					resumeService.lookupUser();
					;
					break;
				}
				case 3: {
					resumeService.modify();
					break;
				}
				case 4: {
					resumeService.remove();
					break;
				}
				case 5: {
					break;
				}
				}
				break;
			}
			case 6 : {
				userService.lookupUser();
				break;
			}

			case 7: {
				userService.modify();
				break;
			}

			case 8: {
				userService.remove();
				break;
			}

			case 9: {
				userService.logOut();
				break;
			}

			}
		} else if (userService.getLoginUser() instanceof BusinessUser) {
			int no = nextInt("1. 공고 2. 회원정보 조회 3. 회원정보 수정 4. 회원탈퇴 5. 로그아웃",s -> s > 0 && s <= 5, "1 ~ 5 사이의 번호를 입력해주세요");
			switch (no) {
			case 1: {
				no = nextInt("1. 공고 등록 2. 공고 조회 3. 지원자 확인 4. 공고 수정 5. 공고 마감 6. 공고 삭제 7. 나가기 ",s -> s > 0 && s <= 7, "1 ~ 7 사이의 번호를 입력해주세요");
				switch (no) {
				case 1: {
					gonggoService.register();
					break;
				}
				case 2: {
					gonggoService.lookupOwner(nextInt("1. 진행중 2. 마감 3. 종료",s -> s > 0 && s <= 3, "1 ~ 3 사이의 번호를 입력해주세요"));
					break;
				}
				case 3: {
					applyService.lookupUserOwner();
					break;
				}
				case 4: {
					gonggoService.modify();
					break;
				}
				case 5: {
					gonggoService.stateChange();
					break;
				}
				case 6: {
					gonggoService.remove();
					break;
				}
				case 7: {
					break;
				}
				}
				break;
			}
			case 2: {
				userService.lookupOwner();

				break;
			}
			case 3: {
				userService.modify();
				break;
			}

			case 4: {
				userService.remove();
				break;
			}
			case 5: {
				userService.logOut();
				break;
			}

			}
		}
	}
}