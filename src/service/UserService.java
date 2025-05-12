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

// 초기화 블럭
	{ // 직렬화하고 사용(유저 도메인)

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/user.ser")); // 보조 입력 스트림 , 입력
			userList = (List<User>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("User : 파일을 불러올 수 없습니다. 임시 데이터셋으로 진행합니다.");
			userList.add(new BusinessUser(1, "새똥이", "010-1111-1111", "1", "1", "서울", "자바사랑", "111-11-11111"));
			userList.add(new AlbaUser(2, "개똥이", "010-2222-2222", "2", "2", "서울"));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

// 중복체크 - findByNo(회원 정보), ID(아이디), tel(연락처), comNum(사업자 번호)

// 회원 정보 중복
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
		for (User u : userList) {

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

// 회원가입 // - > 다른 숫자 번호일시 처음으로 돌아가게 만들기
	public void register() {
		int choice = nextInt("1. (사업자) 회원가입 2.(개인회원) 회원가입 3. 종료");
// 유저번호 중복 x 식
		int num = userList.get(userList.size() - 1).getUserNo() == 0 ? 1
				: userList.get(userList.size() - 1).getUserNo() + 1;
		switch (choice) {
		case 1:
			System.out.println("사업자 회원가입");
// 아이디 , 비밀번호, 연락처, 주소, 이름, 상호명

			String name = nextLine("사업자 본인 이름을 입력하세요. 한글로 최대 5글자까지 작성 가능합니다."); // 대표자명
			if (!name.matches(nameCheck)) { // 한글 5자 제한 정규식 추가
				System.out.println("이름 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			String comNum = nextLine("\"-\"(하이픈)을 포함하여 사업자 등록번호를 입력해 주세요.ex) 000-00-00000"); // 사업자번호 중복체크, 정규식 넣기
// 000-00-00000
			if (findByNum(comNum) != null) {
				System.out.println("중복된 사업자 번호가 존재합니다.");
				return;
			}
			if (!comNum.matches(comNumCheck)) {
				System.out.println("사업자번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}

			String comName = nextLine("상호명을 입력하세요.");// 공백이면 넘어가서 못 넘어가게 수정
			if (comName.isEmpty()) {
				System.out.println("공백은 입력불가 합니다. 다시 입력해 주세요.");
				return;
			}

			String tel = nextLine("\"-\"(하이픈)을 포함하여 전화번호를 입력해주세요.ex) 010-0000-0000");

// 형식, 중복체크
			if (!tel.matches(telCheck)) {
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}
			String id = nextLine("아이디를 입력하세요.(첫 글자는 영문자로 시작하고, 영문자 또는 숫자로만 구성되어야 합니다.");
// 중복 체크
			if (findById(id) != null) {
				System.out.println("중복된 아이디입니다.");
				return;
			}
			if (!id.matches(idCheck)) {
				System.out.println("아이디는 영어와 숫자만 가능합니다. 다시 입력해 주세요");
				return;
			}
// 체크
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

// 회원 번호, 이름, 연락처, id, pw, 소재지, 상호, 사업자 등록번호

			userList.add(new BusinessUser(num, name, tel, id, pw, area, comName, comNum));
			save(); // save
			System.out.println("회원가입이 정상적으로 완료되었습니다.");
			break; // 끝내기

		case 2:

			System.out.println("개인회원 회원가입");

// 이름, 연락처, 거주지, 아이디, 비밀번호, 이력서

			name = nextLine("이름을 입력하세요.(한글 5글자 제한)");
			if (!name.matches(nameCheck)) { // 한글 5자 제한 정규식 추가
				System.out.println("이름 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			tel = nextLine("\"-\"(하이픈)을 포함하여 전화번호를 입력해주세요.ex) 010-0000-0000");// 중복체크, 정규식 010-0000-0000
			if (!tel.matches(telCheck)) {
				System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
				return;
			}
			if (findBytel(tel) != null) {
				System.out.println("중복된 전화번호가 존재합니다.");
				return;
			}

			id = nextLine("아이디를 입력하세요.(첫 글자는 영문자로 시작하고, 영문자 또는 숫자로만 구성되어야 합니다.");
// 중복체크
			if (findById(id) != null) {
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
			save();

		case 3:

			System.out.println("메인 화면으로 돌아갑니다.");
			break;

			default : 
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
		}
	}

// 로그인 //
	public void login() {

		System.out.println("로그인");

		String Id = nextLine("아이디를 입력하세요>");

		String Pw = nextLine("비밀번호를 입력하세요>");

		boolean flag = false;
		for (User u : userList) {
			if (u instanceof BusinessUser && u.getId().equals(Id) && u.getPw().equals(Pw)) {
				flag = true;
				loginUser = u; // 로그인 유저 u로 지정
				System.out.println("사업자회원 로그인 성공!");
				break;
			} else if (u instanceof AlbaUser && u.getId().equals(Id) && u.getPw().equals(Pw)) {
				flag = true;
				loginUser = u;
				System.out.println("개인회원 로그인 성공!");

			}
		}
		if (!flag) {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
		}
	}
// 사업자 회원 정보 조회 //

	public void lookupOwner() {

		BusinessUser business = (BusinessUser) loginUser;// 사업자 변환 변수

		System.out.println("회원 정보 조회");
		System.out.println("============================");
		System.out.println("아이디 : " + loginUser.getId());
		System.out.println("비밀번호 나중에 삭제 확인용 : " + loginUser.getPw());
		System.out.println("이름 : " + loginUser.getName());
		System.out.println("연락처 : " + loginUser.getTel());
		System.out.println("사업자 번호 : " + business.getCompanyNumber());
		System.out.println("상호명 : " + business.getCompanyName());
		System.out.println("지역 : " + loginUser.getArea());

	}

// 개인회원 회원정보 조회//

	public void lookupUser() {

		System.out.println("회원 정보 조회");
		System.out.println("============================");
		System.out.println("아이디 : " + loginUser.getId());
		System.out.println("이름 : " + loginUser.getName());
		System.out.println("연락처 : " + loginUser.getTel());
		System.out.println("비밀번호 나중에 삭제 확인용 : " + loginUser.getPw());
		System.out.println("지역 : " + loginUser.getArea());

	}

// 회원정보 수정 //

	public void modify() {

		// 사업자
		if (getLoginUser() instanceof BusinessUser) {
			System.out.println("회원정보 수정");
			int choice = nextInt("1. 상호명 2.이름 3.연락처 4. 비밀번호 5. 거주지 정보 6. 나가기");

			switch (choice) {
			case 1: {
				BusinessUser business = (BusinessUser) loginUser;// 사업자 변환 변수

				String comName = nextLine("수정할 상호명을 입력하세요."); // 상호명 - 사업자
				// 공백 불가 및 중복 허용 불가
				if (comName.isEmpty() || business.getCompanyName().equals(comName)) {
					System.out.println("공백이나 동일 상호명 입력할 수 없습니다. 다시 입력해 주세요.");
					break;
				}
				System.out.println("상호명이 " + comName + " (으)로 수정되었습니다.");
				business.setCompanyName(comName);
				break;
			}
			case 2: {

				String name = nextLine("수정할 이름을 입력하세요."); // 기존 로그인 유저 이름과 중복 안되게 (!name.trim().matches(nameCheck)) 추가
				if ((loginUser.getName().equals(name)) || (!name.matches(nameCheck))) { // 한글 5자 제한 정규식 추가
					System.out.println("수정된 이름이 동일하거나 형식이 올바르지 않습니다. 다시 입력해 주세요");
					break;
				}
				System.out.println("회원 이름 정보가 " + name + " 님으로 수정되었습니다.");
				loginUser.setName(name);
				break;
			}
			case 3: {
				String tel = nextLine("\"-\"(하이픈)을 포함하여 수정할 전화번호를 입력해주세요.ex) 010-0000-0000");
				// 중복체크, 정규식 010-0000-0000
				if (!tel.matches(telCheck)) {
					System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
					break;
				}
				if (findBytel(tel) != null) {
					System.out.println("중복된 전화번호가 존재합니다.");
					break;
				}
				loginUser.setTel(tel);
				System.out.println("전화번호 정보가 " + tel + " (으)로 수정되었습니다.");
				break;
			}

			case 4: {
				String pw = nextLine("수정할 비밀번호를 입력하세요.");
				// 중복 체크 ,정규식, 재입력
				if (loginUser.getPw().equals(pw)) {
					System.out.println("동일한 비밀번호는 입력할 수 없습니다. 다시 입력해 주세요");
					break;
				}
				if (!pw.matches(pwCheck)) {
					System.out.println("비밀번호는 (!_-)특수문자, 영대소문자, 숫자로만 구성되어야합니다.");
					break;
				}
				if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
					System.out.println("비밀번호가 다릅니다.");
					break;
				}
				loginUser.setPw(pw);
				System.out.println("비밀번호가 성공적으로 수정되었습니다.");
				break;
			}

			case 5: {

				System.out.println("수정할 거주지 정보");
				String area = selectArea();

				if (loginUser.getArea().equals(area)) {
					System.out.println("동일한 거주지 정보는 입력할 수 없습니다. 다시 입력해 주세요.");
					break;
				}
				System.out.println("거주지 정보가 " + area + " (으)로 수정되었습니다.");
				loginUser.setArea(area);
				break;
			}

			case 6: {
				System.out.println("처음으로 돌아갑니다.");
				break;
			}
			default:
				System.out.println("잘못된 번호 입력입니다. 숫자 1 ~ 6 중에 해당되는 번호를 다시 입력해 주세요");
				break;
			}
			// 개인회원
		} else if (getLoginUser() instanceof AlbaUser) {

			System.out.println("회원정보 수정");

			int choice = nextInt("1. 이름 2.연락처 3. 비밀번호 4. 거주지 정보 5. 나가기");

			switch (choice) {
			case 1: {
				String name = nextLine("수정할 이름을 입력하세요."); // 기존 로그인 유저 이름과 중복 안되게 (!name.trim().matches(nameCheck)) 추가
				if ((loginUser.getName().equals(name)) || (!name.trim().matches(nameCheck))) { // 한글 5자 제한 정규식 추가
					System.out.println("수정된 이름이 동일하거나 형식이 올바르지 않습니다. 다시 입력해 주세요");
					break;
				}
				loginUser.setName(name);
				System.out.println("회원 이름 정보가 " + name + " 님으로 수정되었습니다.");
				break;
			}

			case 2: {
				String tel = nextLine("\"-\"(하이픈)을 포함하여 수정할 전화번호를 입력해주세요.ex) 010-0000-0000");// 중복체크, 정규식 010-0000-0000
				if (!tel.matches(telCheck)) {
					System.out.println("전화번호 형식이 올바르지 않습니다. 다시 입력해 주세요");
					break;
				}
				if (findBytel(tel) != null) {
					System.out.println("중복된 전화번호가 존재합니다.");
					break;
				}
				System.out.println("전화정보 정보가 " + tel + " (으)로 수정되었습니다.");
				loginUser.setTel(tel);
				break;
			}

			case 3: {

				String pw = nextLine("수정할 비밀번호를 입력하세요.");

				if (loginUser.getPw().equals(pw)) {
					System.out.println("동일한 비밀번호는 입력할 수 없습니다. 다시 입력해 주세요");
					break;
				}
				if (!pw.matches(pwCheck)) {
					System.out.println("비밀번호는 (!_-)특수문자, 영대소문자, 숫자로만 구성되어야합니다.");
					break;
				}
				if (!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
					System.out.println("비밀번호가 다릅니다.");
					break;
				}
				System.out.println("비밀번호가 성공적으로 수정되었습니다.");
				loginUser.setPw(pw);
				break;
			}

			case 4: {
				System.out.println("수정할 거주지 정보");
				String area = selectArea();
				if (loginUser.getArea().equals(area)) {
					System.out.println("동일한 거주지 정보는 입력할 수 없습니다. 다시 입력해 주세요.");
					break;
				}
				System.out.println("거주지 정보가 " + area + " (으)로 수정되었습니다.");
				loginUser.setArea(area);
				break;
			}
			case 5: {
				System.out.println("처음으로 돌아갑니다.");
				break;
			}
			default:
				System.out.println("잘못된 번호 입력입니다. 숫자 1 ~ 5 중에 해당되는 번호를 다시 입력해 주세요");
				break;
			}
		}
		save(); // 수정 정보 저장
	}

	// 로그아웃 //
	public void logOut() {
		if (loginUser != null) {
			loginUser = null;
			System.out.println("로그아웃 되었습니다");
		}
	}

	// 회원 탈퇴 // 개인 ,사업자 나누기
	public void remove() {
		System.out.println("회원 탈퇴");
		
		// 공통 문구
		
		String pw = nextLine("비밀번호를 입력하세요.");
		// 탈퇴 하기 전에 비밀번호 입력
		if (!loginUser.getPw().equals(pw)) {
			System.out.println("비밀 번호가 일치 하지 않습니다.");
			return;
		}
		// 사업자 탈퇴
		if(loginUser instanceof BusinessUser) {
			
			
			if (!nextConfirm("탈퇴하시겠습니까?")) {
				return;
			}
			if (!nextConfirm("회원 정보가 전부 삭제됩니다. 탈퇴하시겠습니까?")) {
				return;
			}
//		GonggoService.getInstance().gonggoList.removeAll(GonggoService.getInstance().userFindGonggo(loginUser.getUserNo())); // 사업자 - 공고 내역 삭제
//			ApplyService.getInstance().applyList.removeAll(ApplyService.getInstance().findApplysByGonggo(loginUser.getUserNo())); // 사업자 - 공고 지원 삭제
		}
		
		// 개인회원
		else if (loginUser instanceof AlbaUser) {
			
			if (!nextConfirm("탈퇴하시겠습니까?")) {
							return;
			}
			if (!nextConfirm("회원 정보가 전부 삭제됩니다. 탈퇴하시겠습니까?")) {
							return;
		}
	
			// 확인 완료
			ResumeService.getInstance().resumeList.removeAll(ResumeService.getInstance().findResumeBy(loginUser.getUserNo())); // 개인 회원 - 이력서 내역 삭제
			// 확인 필요
//			ApplyService.getInstance().applyList.removeAll(ApplyService.getInstance().findApplysByResume(loginUser.getUserNo()));// 개인회원 - 지원 내역 삭제

		}
		// 공통
		userList.remove(loginUser); // 로그인 정보 유저 삭제
		logOut();// 회원 탈퇴시 로그아웃도 동시에 진행
		System.out.println("탈퇴가 성공적으로 완료되었습니다.");
		save(); // 탈퇴한 것 까지 저장
	}

	// 파일 저장 //
	private void save() {
		try {
			File file = new File("data");
			if (!file.exists()) {
				file.mkdirs();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file, "user.ser")));

			oos.writeObject(userList);
			oos.close();
		} catch (Exception e) {
			System.out.println("파일 접근 권한이 없습니다.");
			e.printStackTrace();

		}
	}

// ==================================== 자체 사용 ==============================
	String telCheck = "^01[0-9]{1}-[0-9]{3,4}-[0-9]{4}$";
	String comNumCheck = "^[0-9]{3}-[0-9]{2}-[0-9]{5}$";
	String idCheck = "^[a-zA-Z]{1}[-_0-9a-zA-Z]*$";
	String pwCheck = "^[-!_0-9a-zA-Z]*$";
	String nameCheck = "^[가-힣]{1,5}$"; // 5글자 제한
}