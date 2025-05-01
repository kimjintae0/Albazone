package service;


import java.util.ArrayList;
import java.util.List;

import domain.AlbaUser;
import domain.BusinessUser;
import domain.User;
import utils.AlbaUtils;

public class UserService {
	AlbazoneService albazoneService = AlbazoneService.getInstance();
	List<User> users = new ArrayList<User>();
	
	private static UserService userService = new UserService();
	
	public static UserService getInstance() {
		// TODO Auto-generated method stub
		return userService;
	}
	
	public void register() {
		int choice = AlbaUtils.nextInt("1. (사업자) 회원가입 2.(개인회원) 회원가입 3. 종료");

		switch (choice) {

		case 1:
			System.out.println("사업자 회원가입");
			// 아이디 , 비밀번호, 연락처, 주소, 이름, 상호명
			BusinessUser businessUser = new BusinessUser();// 스위치에 쓸 사업자 개인 만들기
			businessUser.setBusinessUserName(AlbaUtils.nextLine("이름을 입력하세요>"));
			businessUser.setCompanyName(AlbaUtils.nextLine("상호명을 입력하세요>"));
			businessUser.setBusinessUserArea(AlbaUtils.nextLine("소재지를 입력하세요>"));
			businessUser.setBusinessUserNumber(AlbaUtils.nextLine("연락처를 입력하세요>"+ "10~ 11자리 숫자로 구성되어야 합니다."));
			businessUser.setBusinessUserId(AlbaUtils.nextLine("아이디를 입력하세요>"));
			businessUser.setBusinessUserPw(AlbaUtils.nextLine("비밀 번호를 입력하세요>"));
			
			break; // 끝내기

		case 2:

			System.out.println("개인회원 회원가입");
//					이름, 연락처, 거주지, 아이디, 비밀번호, 이력서
			AlbaUser albaUser = new AlbaUser();

			albaUser.setAlbaName(AlbaUtils.nextLine("이름을 입력하세요>"));
			albaUser.setAlbaNumber(("연락처를 입력하세요>"));
			albaUser.setAlbaArea(("거주지를 입력하세요>"));
			albaUser.setAlbaId(("아이디를 입력하세요>"));
			albaUser.setAlbaPw(("비밀번호를 입력하세요>"));
			albaUser.setResume(("이력서를 작성하세요>"));// 따로 이력서 작성하는 곳으로 연동시켜야함

			break;

		case 3:

			System.out.println("종료 초기화");
			break;

		}
	}

	public void login() { // 내일 건드리기
		int choice = AlbaUtils.nextInt("1. (사업자) 로그인 2.(개인회원) 로그인 3. 종료");
		switch (choice) {

		case 1:

			System.out.println("사업자 로그인");

			BusinessUser loninBusiness = new BusinessUser();

			String loninBusinessId = AlbaUtils.nextLine(AlbaUtils.nextLine("아이디를 입력하세요>"));

			String loninBusinessPw = AlbaUtils.nextLine(AlbaUtils.nextLine("비밀번호를 입력하세요>"));

			break;

		case 2:
			System.out.println("개인회원 로그인");

			AlbaUser loninAlba = new AlbaUser();

			String loginAlbaId = AlbaUtils.nextLine(AlbaUtils.nextLine("아이디를 입력하세요>"));

			String loginAlbaPw = AlbaUtils.nextLine(AlbaUtils.nextLine("비밀번호를 입력하세요>"));

			break;
			
		case 3:

			System.out.println("종료 초기화");
			break;

		}
	}

	public Object getLoginUser() { // 물어보기 알바존 서비스에 있음

//		public void menu() throws Exception{
//			try {
//				if(customerService.getLoginCustomer() == null) { //
//					int no = BankUtils.nextInt("1.회원가입 2.로그인 7.종료");
//		
		return null;
	}


}

