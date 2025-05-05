package service;

import java.util.ArrayList;
import java.util.List;

import domain.AlbaUser;
import domain.BusinessUser;
import domain.User;

import static utils.AlbaUtils.*;

public class UserService {

	// 유저 리스트 생성
	List<User> userList = new ArrayList<User>();

	// 로그인 유저
	private User loginUser;

	// 클래스 연동
	AlbazoneService albazoneService = AlbazoneService.getInstance();
//	ResumeService resumeService = ResumeService.getInstance();

	// 싱글톤
	private static UserService userService = new UserService();

	public static UserService getInstance() {
		return userService;
	}

	// 로그인 유저 정보
	public User getLoginUser() {
		return loginUser;
	}

	// 초기화 블럭
	{
		userList.add(new BusinessUser(1, "새똥이", "010-1111-1111", "1", "1", "서울", "자바사랑", "111-111-11111"));
		userList.add(new AlbaUser(2, "개똥이", "010-2222-2222", "2", "2", "서울"));
	}

	// 유저번호 중복 x 식
	private int num = userList.get(userList.size() - 1).getUserNo() == 0 ? 1
			: userList.get(userList.size() - 1).getUserNo() + 1;

	// 중복체크 - findByNo, ID, comNum, tel

	public User findByNo(int userNo) {
		User user = null;
		for (User s : userList) {
			if (s.getUserNo() == userNo) {
				user = s;
			}
		}
		return user;
	}

	// 회원가입
	public void register() {
		int choice = nextInt("1. (사업자) 회원가입 2.(개인회원) 회원가입 3. 종료");

		switch (choice) {
		case 1:
			System.out.println("사업자 회원가입");
			// 아이디 , 비밀번호, 연락처, 주소, 이름, 상호명

			String name = nextLine("이름을 입력하세요."); // 대표자명
			String comNum = nextLine("사업자 등록번호를 입력해주세요."); // 사업자번호 중복체크, 정규식 넣기 000-00-00000
			String comName = nextLine("상호명을 입력하세요.");
			String tel = nextLine("전화번호를 입력해주세요."); // 중복체크, 정규식 010-0000-0000
			String id = nextLine("아이디를 입력하세요."); // 중복체크
			String pw = nextLine("비밀번호를 입력하세요.");
			if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
				System.out.println("비밀번호가 다릅니다.");
				return;
			}
			String area = selectArea();

			// 유저번호, 이름, 연락처, id, pw, 소재지, 상호, 사업자 등록번호
			User businessUser = new BusinessUser(num, name, tel, id, pw, area, comName, comNum);
			userList.add(businessUser);
			System.out.println("회원가입이 정상적으로 완료되었습니다.");
			break; // 끝내기

		case 2:

			System.out.println("개인회원 회원가입");
//					이름, 연락처, 거주지, 아이디, 비밀번호, 이력서

			String name2 = nextLine("이름을 입력하세요."); // 개인 알바이름
			String tel2 = nextLine("전화번호를 입력해주세요."); // 중복체크, 정규식 010-0000-0000
			String id2 = nextLine("아이디를 입력하세요."); // 중복체크
			String pw2 = nextLine("비밀번호를 입력하세요.");
			if (!pw2.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
				System.out.println("비밀번호가 다릅니다.");
				return;
			}

			String area2 = selectArea();
			User albaUser = new AlbaUser(num, name2, tel2, id2, pw2, area2);
			System.out.println("회원가입이 정상적으로 완료되었습니다.");
			userList.add(albaUser);

		case 3:

			System.out.println("메인 화면으로 돌아갑니다.");
			break;

		}

	}

	// 로그인
	public void login() {
		int choice = nextInt("1. (사업자) 로그인 2.(개인회원) 로그인 3. 종료");
		switch (choice) {

		case 1:

			System.out.println("사업자 로그인");


			String Id = nextLine("아이디를 입력하세요>");

			String Pw = nextLine("비밀번호를 입력하세요>");

			boolean flag = false;
			for (User u : userList) {
				if (u instanceof BusinessUser && u.getId().equals(Id)
						&& u.getPw().equals(Pw)) {
					flag = true;
				
					loginUser = u; // 로그인 유저 u로 지정
					System.out.println("로그인 성공!");
				}
			}
			if (!flag) {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다");
			}
			break;

		case 2:
			System.out.println("개인회원 로그인");

			String Id2 = nextLine("아이디를 입력하세요>");

			String Pw2 = nextLine("비밀번호를 입력하세요>");

			boolean flag2 = false;
			for (User u : userList) {
				if (u instanceof AlbaUser && u.getId().equals(Id2) && u.getPw().equals(Pw2)) {
					flag2 = true;

					loginUser = u; // 로그인 유저 u로 지정
					System.out.println("로그인 성공!");
				} 		
			}
			if (!flag2) {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다");
			}

			break;

		case 3:

			System.out.println("메인 화면으로 돌아갑니다.");
			break;
		}

		
	} 


	// 이력서 등록 관리 // 이거 알바존 서비스 이력서 서비스로 연동시키는거면 필요없는건지 물어보고 삭제 하거나 추가하거나 하기
	public void Resumeservice() {
		
	}

	// 사업자 회원정보 수정 -> 사업자랑 개인 두개 만들기
	public void modify() { // 나중에 디폴트 값 제거 및 수정 무한 생성 
		System.out.println("회원정보 수정");
		
		
	}
	// 개인
	public void modify2() { // 나중에 디폴트 값 제거 및 수정 무한 생성 
//		System.out.println("회원정보 수정");
//		String name2 = nextLine("이름을 입력하세요."); // 개인 알바이름
//		String id2 = nextLine("아이디를 입력하세요."); // 중복체크
//		String tel2 = nextLine("수정할 전화번호를 입력해주세요."); // 중복체크, 정규식 010-0000-0000
//		String pw2 = nextLine("수정할 비밀번호를 입력하세요.");
//		if (!pw2.equals(nextLine("[비밀번호 확인] 수정할 비밀번호를 재입력하세요."))) {
//			System.out.println("비밀번호가 다릅니다.");
//			}
//
//		String area2 = selectArea();
//		User albaUser = new AlbaUser(num, name2, tel2, id2, pw2, area2);
//		System.out.println("수정 완료.");
//		userList.add(albaUser);
//		
//		return; 	
	}

	// 로그아웃
	public void logOut() {
		
		if(loginUser!=null) {
			loginUser=null;
			System.out.println("로그아웃 되었습니다");
		}
		else {
			System.out.println("로그인 정보가 없습니다.");// 메인 화면에서 보일필요 없으면 삭제
		}
	}

	// 회원 탈퇴
	public void remove() {

//		 로그인 안 했을 때는 로그인 문자 보내기
//		if(loginUser==null) {
//			System.out.println("로그인을 먼저 진행해 주세요");
//			return;
//		} // -> 회원 탈퇴를 회원인 상태에서만 볼 수 있게 하면 필요 없을 것 같아서 일단 주석처리 해 두었습니다.

		System.out.println("회원 탈퇴");
		
		if (!nextConfirm("탈퇴하시겠습니까?")) {
			return;
		}
		// 중복으로 한 번 더 물어보기
		if (!nextConfirm("정말로 탈퇴하시겠습니까?")) {
			return;
		}
		userList.remove(loginUser); // 로그인 유저 삭제
		logOut();// 회원 탈퇴시 로그아웃도 동시에 진행
		System.out.println("탈퇴가 성공적으로 완료되었습니다.");

	}

}
