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
	ResumeService resumeService = ResumeService.getInstance();
	
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
		userList.add(new BusinessUser(1, "새똥이", "010-1111-1111", "ssa", "1234", "서울", "자바사랑", "111-111-11111"));		
	}

	
	// 유저번호 중복 x 식
	private int num = userList.get(userList.size() - 1).getUserNo() == 0 ? 1 : userList.get(userList.size()-1).getUserNo() + 1;
	
	
	
	
	
	// 중복체크 - findByNo, ID, comNum, tel
	
	public User findByNo(int userNo) {
		User user = null;
		for(User s : userList) {
			if(s.getUserNo() == userNo) {
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
			if(!pw.equals(nextLine("[비밀번호 확인] 비밀번호를 재입력하세요."))) {
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
			AlbaUser albaUser = new AlbaUser();

			albaUser.setName(nextLine("이름을 입력하세요>"));
			
			albaUser.setTel(nextLine("연락처를 입력하세요>"));
			
			albaUser.setArea(nextLine("거주지를 입력하세요>"));
			
			albaUser.setId(nextLine("아이디를 입력하세요>"));
			
			albaUser.setPw(nextLine("비밀번호를 입력하세요>"));
			
			System.out.println("회원가입이 정상적으로 완료되었습니다.");
			
			userList.add(albaUser); // ->05/03 add 추가 
			
			break;
			
		case 3:

			System.out.println("메인 화면으로 돌아갑니다.");
			break;

		}
		
	}
	
	
	
	
	
	// 로그인
	public void login() { 
		int choice = nextInt("1. (사업자) 로그인 2.(개인회원) 로그인 3. 종료");
		switch (choice) {
		
		// 로그인 한 다음에 이력서 등록이 필요함
		case 1:

			System.out.println("사업자 로그인");

			BusinessUser loginBusiness = new BusinessUser();

			String loginBusinessId = nextLine("아이디를 입력하세요>");

			String loginBusinessPw = nextLine("비밀번호를 입력하세요>");
			//05/03 수정중이고 나중에 봐야됨
			
				boolean flag = false;
				for(User u : userList) {
					if(u instanceof BusinessUser && u.getId().equals(loginBusinessId) && u.getPw().equals(loginBusinessPw)) {
						flag = true;
						domain.BusinessUser loginBusinessUser = (BusinessUser) u;
						
						System.out.println("로그인 성공!");
						
						Resumeservice();
					}
				}
				if(!flag) {
					System.out.println("아이디 또는 비밀번호가 틀렸습니다");
				}
	// 작성
				break;

		case 2:
			System.out.println("개인회원 로그인");

			AlbaUser loginAlba = new AlbaUser();

			String loginAlbaId = nextLine("아이디를 입력하세요>");

			String loginAlbaPw = nextLine("비밀번호를 입력하세요>");

			break;

		case 3:

			System.out.println("종료 초기화");

			break;

		}
	}


	public void logOut() {
		// TODO Auto-generated method stub

	}

	public void modify() {
		// TODO Auto-generated method stub

	}

	public void Resumeservice() {
		// TODO Auto-generated method stub
		
	}

}
