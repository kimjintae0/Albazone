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
//	AlbazoneService albazoneService = AlbazoneService.getInstance();
//	ResumeService resumeService = ResumeService.getInstance();
//	ApplyService applyService = ApplyService.getInstance();
//	GonggoService gonggoService = GonggoService.getInstance();

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

	// 중복체크 - findByNo(회원 정보), ID, comNum, tel

	public User findByNo(int userNo) {
		User user = null;
		for (User s : userList) {
			if (s.getUserNo() == userNo) {
				user = s;
			}
		}
		return user;
	} // 따라해보기

	// 아이디 중복 해결 (되기는 하는데 왜 서비스로만 리턴되는지 모르겠습니다ㅏ)
	public UserService findById(String Id) {
		for (User u : userList) {
			if (u.getId().equals(Id)) {
			}
		}
		return userService;
	}

	// 전화번호 중복
	public UserService findBytel(String tel) {
		for (User u : userList) {
			if (u.getTel().equals(tel)) {
			}
		}
		return userService;
	}

	// 회원가입
	public void register() {
		int choice = nextInt("1. (사업자) 회원가입 2.(개인회원) 회원가입 3. 종료");
		// 유저번호 중복 x 식
		int num = userList.get(userList.size() - 1).getUserNo() == 0 ? 1
				: userList.get(userList.size() - 1).getUserNo() + 1;
		switch (choice) {
		case 1:
			System.out.println("사업자 회원가입");
			// 아이디 , 비밀번호, 연락처, 주소, 이름, 상호명

			String name = nextLine("이름을 입력하세요."); // 대표자명
			String comNum = nextLine("사업자 등록번호를 입력해주세요."); // 사업자번호 중복체크, 정규식 넣기 000-00-00000
//			if (!comNum.matches("\"-\"(하이픈)을 포함하여 ^\\d{3}-\\d{2}-\\d{5}$")) {
//				System.out.println("사업자번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
//				return;
//			}

			String comName = nextLine("상호명을 입력하세요.");
			String tel = nextLine("\"-\"(하이픈)을 포함하여 전화번호를 입력해주세요.");
			// 중복체크,
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}
			// 정규식 010-0000-0000
//			if (!tel.matches("^0\\d{2}-\\d{4}-\\d{4}$|^0\\d{2}-\\d{4}-\\d{4}$|^02-\\d{4}-\\d{4}$|^02-\\d{3}-\\d{4}$")) { // 묶기
//				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
//				return;
//			}

			String id = nextLine("아이디를 입력하세요.");
			if (findById(id) != null) {// 중복체크
				System.out.println("중복된 아이디가 존재합니다. 다른 아이디로 다시 작성해 주세요.");
				return;
			}

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

//	이름, 연락처, 거주지, 아이디, 비밀번호, 이력서

			String name2 = nextLine("이름을 입력하세요."); // 개인회원 알바이름
			String tel2 = nextLine("전화번호를 입력해주세요. ex) 000-0000-0000"); // 중복체크, 정규식 010-0000-0000
			if (!tel2.matches("^0\\d{2}-\\d{4}-\\d{4}$")) { // 서울 지역번호나 집번호도 고객한테도 필요한가?
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
			}

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

	// 로그인 // 스위치 없애버리고 사업자 알바 두개 합치기
	public void login() {

		System.out.println("로그인");

		String Id = nextLine("아이디를 입력하세요>");

		String Pw = nextLine("비밀번호를 입력하세요>");

		boolean flag = false;
		for (User u : userList) {
			if (u instanceof BusinessUser && u.getId().equals(Id) && u.getPw().equals(Pw)) {
				flag = true;
				loginUser = u; // 로그인 유저 u로 지정
				System.out.println("사업자 로그인 성공!");
			} else if (u instanceof AlbaUser && u.getId().equals(Id) && u.getPw().equals(Pw)) {
				flag = true;
				loginUser = u; // 로그인 유저 u로 지정
				System.out.println("개인회원 로그인 성공!");
			}
		}
		if (!flag) {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
		}
	}

	// 회원정보 수정
	public void modify() {
		System.out.println("회원정보 수정");

		// 사업자
		BusinessUser business = (BusinessUser) loginUser;
		String comName = nextLine("수정할 상호명을 입력하세요."); // 상호명 - 사업자
		String name = nextLine("수정할 이름을 입력하세요."); // 이름

		String tel = nextLine("수정할 전화번호를 입력해주세요. ex) 000-0000-0000"); // 사업자 전화번호 (휴대폰 번호 010으로 통일하면 사업자 알바 합치기)
		if (!tel.matches("^0\\d{2}-\\d{4}-\\d{4}$|^0\\d{2}-\\d{4}-\\d{4}$|^02-\\d{4}-\\d{4}$|^02-\\d{3}-\\d{4}$")) { // 묶기
			System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
			return;
		}
		String tel2 = nextLine("수정할 전화번호를 입력해주세요. ex) 000-0000-0000"); // 개인회원 전화번호
		if (!tel2.matches("^0\\d{2}-\\d{4}-\\d{4}$")) {
			System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
			return;
		}

		String pw = nextLine("수정할 비밀번호를 입력해 주세요.");
		if (!pw.equals(nextLine("[비밀번호 확인] 수정할 비밀번호를 재입력하세요."))) {
			System.out.println("비밀번호가 다릅니다.");
		}
		System.out.println("수정할 거주지 정보");
		String area = selectArea();

		if (loginUser instanceof BusinessUser) {
			business.setCompanyName(comName);
			business.setName(name);
			business.setTel(tel);
			business.setPw(pw);
			business.setArea(area);
			System.out.println("변경된 회원 정보" + loginUser); // 수정되었는지 확인해보기
			System.out.println("성공적으로 회원 정보 수정이 완료되었습니다.");
		} else {
			System.out.println("회원 정보 수정이 취소되었습니다. 다시 진행해주세요");
			return;
		}

		// 개인회원
		AlbaUser alba = (AlbaUser) loginUser;

		if (loginUser instanceof AlbaUser) {
			alba.setName(name);
			alba.setTel(tel2);
			alba.setPw(pw);
			alba.setArea(area);
			System.out.println("변경된 회원 정보" + loginUser); // 수정되었는지 확인해보기
			System.out.println("성공적으로 회원 정보 수정이 완료되었습니다.");

		}
	}

	// 로그아웃
	public void logOut() {
		if (loginUser != null) {
			loginUser = null;
//			System.out.println("로그아웃 되었는지 확인용" + loginUser); 로그아웃 되었는지 확인용 (나중에 삭제)
			System.out.println("로그아웃 되었습니다");
		}
	}

	// 회원 탈퇴
	public void remove() {
		System.out.println("회원 탈퇴");

		if (!nextConfirm("탈퇴하시겠습니까?")) {
			return;
		}
		// 중복으로 한 번 더 물어보기 (나중에 지원이랑 공고 내역 삭제까지) -> 문장 바꾸기//
		if (!nextConfirm("정말로 탈퇴하시겠습니까?")) {
			return;
		}
		userList.remove(loginUser); // 로그인 유저 삭제
		logOut();// 회원 탈퇴시 로그아웃도 동시에 진행
		System.out.println("탈퇴가 성공적으로 완료되었습니다.");

	}

}
