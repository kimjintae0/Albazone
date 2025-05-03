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
	
	// 클래스 연동
	AlbazoneService albazoneService = AlbazoneService.getInstance();
	ResumeService resumeService = ResumeService.getInstance();
	
	// 싱글톤
	private static UserService userService = new UserService();
	public static UserService getInstance() {
		return userService;
	}

	// 로그인 유저 정보 
	public User loginUser = null; // 0503 추가
	
	
	//
	// 유저번호 중복 x 식
	int num = userList.get(userList.size() - 1).getUserNo() == 0 ? 1 : userList.get(userList.size()-1).getUserNo() + 1;
	
	// 초기화 블럭
	{
//		userList.add(new BusinessUser(num, "새똥이", "010-1111-1111", "ssa", "1234", "서울", "자바사랑", "111-111-11111"));
	}
	
	// 지역 선택
	public String selectArea() {
		System.out.println("지역을 선택해주세요.");
		int input = nextInt("1.서울 2.인천 3.부산 4.대전 5.대구 6.울산 7.광주 8.제주\n 9.경기 10.경상 11.강원 12.충청 13.전라");
		switch(input) {
		case 1: return "서울";  
		case 2: return "인천"; 
		case 3: return "부산"; 
		case 4: return "대전"; 
		case 5: return "대구";
		case 6: return "울산"; 
		case 7: return "광주"; 
		case 8: return "제주"; 
		case 9: return "경기"; 
		case 10: return "경상"; 
		case 11: return "강원"; 
		case 12: return "충청"; 
		case 13: return "전라";
		}
		return "지역 미정";
	}
	
	
	// 회원가입
	public void register() {
		int choice = nextInt("1. (사업자) 회원가입 2.(개인회원) 회원가입 3. 종료");

		switch (choice) {
		
		case 1:{
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
			userList.add(businessUser); // -> add 추가 
			System.out.println("회원가입이 완료되었습니다.");
			
			// 이번에 기능 한 번에 구현해두기
			break; // 끝내기
		}
		case 2:

			System.out.println("개인회원 회원가입");
//					이름, 연락처, 거주지, 아이디, 비밀번호, 이력서
			AlbaUser albaUser = new AlbaUser();

			albaUser.setName(nextLine("이름을 입력하세요>"));
			
			albaUser.setTel(nextLine("연락처를 입력하세요>"));
			
			albaUser.setArea(nextLine("거주지를 입력하세요>"));
			
			albaUser.setId(nextLine("아이디를 입력하세요>"));
			
			albaUser.setPw(nextLine("비밀번호를 입력하세요>"));
			
			System.out.println("회원가입 구현이 완료되었습니다. 로그인을 진행해주세요!");
			
			userList.add(albaUser); // ->05/03 add 추가 
			
			break;
			
		case 3:

			System.out.println("종료 초기화");
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

			BusinessUser loninBusiness = new BusinessUser();

			String loninBusinessId = nextLine("아이디를 입력하세요>");

			String loninBusinessPw = nextLine("비밀번호를 입력하세요>");
			//05/03 수정중이고 나중에 봐야됨
			
				boolean flag = false;
				for(User u : userList) {
					if(u instanceof BusinessUser && u.getId().equals(loninBusinessId) && u.getPw().equals(loninBusinessPw)) {
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

			AlbaUser loninAlba = new AlbaUser();

			String loginAlbaId = nextLine("아이디를 입력하세요>");

			String loginAlbaPw = nextLine("비밀번호를 입력하세요>");

			break;

		case 3:

			System.out.println("종료 초기화");

			break;

		}
	}

	public Object getLoginUser() { // 물어보기 알바존 서비스에 있음

		return null;
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
