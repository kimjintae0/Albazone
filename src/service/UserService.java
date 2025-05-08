package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import domain.AlbaUser;
import domain.Apply;
import domain.BusinessUser;
import domain.User;

import static utils.AlbaUtils.*;

public class UserService {

// 로그인 유저
	private User loginUser;

// 싱글톤
	private static UserService userService = new UserService();

	public static UserService getInstance() {
		return userService;
	}

// 정규식, 중복, 유저리스트 데이터 타입으로 저장 및 불러오기
// 유저 리스트 생성
	List<User> userList = new ArrayList<User>();

// 로그인 유저 정보
	public User getLoginUser() {
		return loginUser;
	}

//	// 파일로 저장하기
//			private void save() {
//				try {
//					File file = new File("data");
//					if(!file.exists()) {
//						file.mkdirs();
//					}
//					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file, "User.ser")));
//					oos.writeObject(userList);
//					oos.close();
//				}
//				catch (Exception e) {
//					System.out.println("파일 접근 권한이 없습니다.");
//					e.printStackTrace();
//				}
//			}
//	// 파일로 저장하기
//			private void save() {
//				try {
//					File file = new File("data");
//					if(!file.exists()) {
//						file.mkdirs();
//					}
//					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file, "apply.ser")));
//					oos.writeObject(applyList);
//					oos.close();
//				}
//				catch (Exception e) {
//					System.out.println("파일 접근 권한이 없습니다.");
//					e.printStackTrace();
//				}
//			}

// 스트림을 이용해야 함(관련 내용 찾아보고 이해해서 하기) - 저장 파일 먼저 추가하고 해야 함

	// 예외 처리까지
//	{ 파일 불러오기
//		ObjectInputStream ois = null;
//		try {
//			ois = new ObjectInputStream(new FileInputStream("data/apply.ser"));
//			applyList = (List<Apply>)ois.readObject();
//			ois.close();
//		}
//		catch(FileNotFoundException e) {
//			System.out.println("Apply : 파일을 불러올 수 없습니다. 임시 데이터셋으로 진행합니다.");
//			applyList.add(new Apply(1, 1, 2));
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// 초기화 블럭
	{
		userList.add(new BusinessUser(1, "새똥이", "010-1111-1111", "1", "1", "서울", "자바사랑", "111-11-11111"));
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
	}

	// 아이디 중복
	public User findById(String id) {
		User user = null; // 초기값 지정
		for (User u : userList) { // 리스트에 유저1, 유저2, 유저3이 있을 때, u = 유저1 로 if문 탐색 후 u = 유저2로 if문 탐색 후 u = 유저3으로 if문
									// 탐색
			if (u.getId().equals(id)) {
				user = u;
			}
		}
		return user;
	}

	// 전화번호 중복
	public User findBytel(String tel) {
		User user = null;
		for (User u : userList) {
			if (u.getTel().equals(tel)) {
				user = u;
			}
		}
		return user;
	}

	// 사업자 번호 중복

	public User findByNum(String comNum) {
		User user = null;
		for (User u : userList) {
			if (u instanceof BusinessUser) {
				if (((BusinessUser) u).getCompanyNumber().equals(comNum)) {
					user = u;
				}
			}
		}
		return user;
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

			String comNum = nextLine("\"-\"(하이픈)을 포함하여 사업자 등록번호를 입력해 주세요."); // 사업자번호 중복체크, 정규식 넣기 000-00-00000
			if (findByNum(comNum) != null) {
				System.out.println("중복된 사업자 번호가 존재합니다.");
				return;
			}
			if (!comNum.matches(comNumCheck)) {
				System.out.println("사업자번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}

			String comName = nextLine("상호명을 입력하세요.");

			String tel = nextLine("\"-\"(하이픈)을 포함하여 전화번호를 입력해주세요.");
			// 중복체크,
			// 정규식 010-0000-0000
			if (!tel.matches(telCheck)) {
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}

			String id = nextLine("아이디를 입력하세요.");
			if (findById(id) != null) {
				System.out.println("중복된 아이디입니다.");
				return;
			}
			if (!id.matches(idCheck)) {
				System.out.println("아이디는 영어와 숫자만 가능합니다. 다시 입력해 주세요");
				return;
			}

			String pw = nextLine("비밀번호를 입력하세요.");
			if (!pw.matches(pwCheck)) {
				System.out.println("비밀번호는 (!_-)특수문자, 영대소문자, 숫자로만 구성되어야합니다.");
				return;
			}
			if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
				System.out.println("비밀번호가 다릅니다.");
				return;
			}

			String area = selectArea();

			// 유저번호, 이름, 연락처, id, pw, 소재지, 상호, 사업자 등록번호

			userList.add(new BusinessUser(num, name, tel, id, pw, area, comName, comNum));

			System.out.println("회원가입이 정상적으로 완료되었습니다.");
			break; // 끝내기

		case 2:

			System.out.println("개인회원 회원가입");

			// 이름, 연락처, 거주지, 아이디, 비밀번호, 이력서

			name = nextLine("이름을 입력하세요.");

			tel = nextLine("\"-\"(하이픈)을 포함하여 전화번호를 입력해주세요.ex) 010-0000-0000");// 중복체크, 정규식 010-0000-0000
			if (!tel.matches(telCheck)) {
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}

			id = nextLine("아이디를 입력하세요.");
			if (findById(id) != null) {// 중복체크
				System.out.println("중복된 아이디가 존재합니다. 다른 아이디로 다시 작성해 주세요.");
				return;
			}
			if (!id.matches(idCheck)) {
				System.out.println("아이디는 영어와 숫자만 가능합니다. 다시 입력해 주세요");
				return;
			}

			pw = nextLine("비밀번호를 입력하세요.");
			if (!pw.matches(pwCheck)) {
				System.out.println("비밀번호는 (!_-)특수문자, 영대소문자, 숫자로만 구성되어야합니다.");
				return;
			}
			if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
				System.out.println("비밀번호가 다릅니다.");
				return;
			}

			area = selectArea();

			System.out.println("회원가입이 정상적으로 완료되었습니다.");
			userList.add(new AlbaUser(num, name, tel, id, pw, area));

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
	// 사업자 회원 정보 조회

	public void lookupOwner() {

	}

	// 개인회원 회원정보 조회

	public void lookupUser() {

	}

	// 회원정보 수정
	public void modify() {
		System.out.println("회원정보 수정");

		// 사업자 , 공고내역(만들어진 메서드 추가하기)
		if (loginUser instanceof BusinessUser) {
			BusinessUser business = (BusinessUser) loginUser;
			String comName = nextLine("수정할 상호명을 입력하세요."); // 상호명 - 사업자

			String name = nextLine("수정할 이름을 입력하세요."); // 이름

			String tel = nextLine("\"-\"(하이픈)을 포함하여 수정할 전화번호를 입력해주세요.ex) 010-0000-0000");// 중복체크, 정규식 010-0000-0000
			if (!tel.matches(telCheck)) {
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}

			String pw = nextLine("수정할 비밀번호를 입력하세요.");
			if (!pw.matches(pwCheck)) {
				System.out.println("비밀번호는 (!_-)특수문자, 영대소문자, 숫자로만 구성되어야합니다.");
				return;
			}
			if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
				System.out.println("비밀번호가 다릅니다.");
				return;
			}

			System.out.println("수정할 거주지 정보");
			String area = selectArea();

			business.setCompanyName(comName);
			loginUser.setName(name);
			loginUser.setTel(tel);
			loginUser.setPw(pw);
			loginUser.setArea(area);
			System.out.println("변경된 회원 정보" + loginUser); // 수정되었는지 확인해보기
			System.out.println("성공적으로 회원 정보 수정이 완료되었습니다.");
		} else if (loginUser instanceof AlbaUser) { // 개인회원 (만들어진 메서드 추가하기(이력서))
			String name = nextLine("수정할 이름을 입력하세요."); // 이름

			String tel = nextLine("\"-\"(하이픈)을 포함하여 수정할 전화번호를 입력해주세요.ex) 010-0000-0000");
			if (!tel.matches(telCheck)) {
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}

			String pw = nextLine("수정할 비밀번호를 입력하세요.");
			if (!pw.matches(pwCheck)) {
				System.out.println("비밀번호는 (!_-)특수문자, 영대소문자, 숫자로만 구성되어야합니다.");
				return;
			}
			if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
				System.out.println("비밀번호가 다릅니다.");
				return;
			}

			System.out.println("수정할 거주지 정보");
			String area = selectArea();
			loginUser.setName(name);
			loginUser.setTel(tel);
			loginUser.setPw(pw);
			loginUser.setArea(area);
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
		System.out.println(loginUser);
		userList.remove(loginUser); // 로그인 유저 삭제
		logOut();// 회원 탈퇴시 로그아웃도 동시에 진행
		System.out.println("탈퇴가 성공적으로 완료되었습니다.");

	}

	// ==================================== 자체 사용 ==============================
	String telCheck = "^01[0-9]{1}-[0-9]{3,4}-[0-9]{4}$";
	String comNumCheck = "^[0-9]{3}-[0-9]{2}-[0-9]{5}$";
	String idCheck = "^[a-zA-Z]{1}[-_0-9a-zA-Z]*$";
	String pwCheck = "^[-!_0-9a-zA-Z]*$";

}
