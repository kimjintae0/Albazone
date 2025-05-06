package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.BusinessUser;
import domain.Gonggo;
import domain.User;

import static utils.AlbaUtils.*;

public class GonggoService {
	// 공고 리스트 생성
	List<Gonggo> gonggoList = new ArrayList<>();
	
	//싱글톤 
	private static GonggoService gonggoService = new GonggoService();
	private GonggoService() {}
	public static GonggoService getInstance() {
		return gonggoService;
	}
	
	// 유저서비스, 지원 서비스 호출
	AlbazoneService albazoneService = AlbazoneService.getInstance();
	UserService userService = UserService.getInstance();
	ApplyService applyService = ApplyService.getInstance();

	
	// 초기화 블럭
	{
		// 사업자 유저 번호, 공고 번호, 제목, 역할, 일하는 시간, 시급, 근무 기간, 진행상태, 소재지
		gonggoList.add(new Gonggo(1, 1, "김밥천국 오전 알바(9시 ~ 6시, 1시간 휴식) 구합니다", "서빙", 8, 10030, "2025-05-04" ,"2025-06-04", true, "서울"));
	}
	
	
	public final String dateForm = "(20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
	
	// 공고등록
	void register() {
		// 사업자 유저 번호, 공고 번호, 제목, 역할, 일하는 시간, 시급, 근무 기간, 진행상태, 소재지
		String title = nextLine("공고의 제목을 입력해주세요.");
		String role = nextLine("담당 업무를 입력해주세요.");
		int workHours = nextInt("근무 시간을 숫자로 입력해주세요. (시간 단위로 적어주세요. 소숫점은 미지원)");
		int wage = nextInt("시급을 숫자로 입력해주세요. (2025년 최저시급은 10,030원 입니다.)"); // 최저시급 보다 작을 시 return
			if(wage < 10030) {
				System.out.println("2025년 최저 시급은 10,030원입니다. 다시 입력해주세요. ");
				return;
			}
		String workingStartDate = nextLine("근무 시작일을 입력해주세요 (yyyy-MM-dd)."); // 정규식 만들기 
		boolean workingStartDateCheck = workingStartDate.matches(dateForm);
//		System.out.println(workingStartDateCheck);
		if(Pattern.matches(dateForm, workingStartDate)) {
			System.out.println(workingStartDate);
		}else {
			System.out.println("양식에 맞게 다시 입력하세요. ");
			return;
		}
		String workingEndDate = nextLine("근무 종료일을 입력해주세요(yyyy-MM-dd)."); // 정규식 만들기 
		boolean workingEndDateCheck = workingEndDate.matches(dateForm);
		if(Pattern.matches(dateForm, workingEndDate)){
			System.out.println(workingEndDate);
		}else {
			System.out.println("양식에 맞게 다시 입력해주세요.");
			return;
		}
		String comArea = selectArea();
		
		// 공고번호 관리
		int num = gonggoList.get(gonggoList.size() - 1).getGonggoNo() == 0 ? 1 : gonggoList.get(gonggoList.size() - 1).getGonggoNo() + 1; 
		gonggoList.add(new Gonggo(userService.getLoginUser().getUserNo(), num, title, role, workHours, wage, workingStartDate, workingEndDate, true, comArea));
	}
	

	// 알바가 공고 조회
	void lookupUser() {
		for(Gonggo gonggo : gonggoList) {
			if(userService.getLoginUser().getArea().equals(gonggo.getComArea())) {
				System.out.println(gonggo.toString());
			}
		}
		return;
	}
	
	// 공고 선택 - 알바
	public int gonggoSelectUser() {
		int input = nextInt("공고 번호를 선택해 주세요.");
		for(Gonggo g : gonggoList) {
			if(g.getGonggoNo() == input && g.getComArea() == userService.getLoginUser().getArea()) {
				return input;
			}
		}
		return 0;
	}
	
	// 공고 선택 - 사업자
	public int gonggoSelectOwner() {
		int input = nextInt("지원할 공고를 선택해 주세요.");
		for(Gonggo g : gonggoList) {
			if(g.getGonggoNo() == input) {
				return input;
			}
		}
		return 0;
	}
	
	// 사업자가 자기 공고 조회
	void lookupOwner() {
		int input = nextInt("1. 진행중 2. 마감 3. 종료");
		switch(input) {
		case 1:{
			for(Gonggo g : gonggoList) {
				if(userService.getLoginUser().getUserNo() == g.getUserNo() && g.state == true) {
					System.out.println(g.toString());	
				}
			}
			break;
		}
		case 2:{
			for(Gonggo g : gonggoList) {
				if(userService.getLoginUser().getUserNo() == g.getUserNo() && g.state == false) {
					System.out.println(g.toString());
				}
			}
			break;
		}
		case 3:{
			System.out.println("메뉴로 돌아갑니다.");
			return;
		}
		}
	}
	
	void modify() {
		//공고수정-사업자
		//공고수정할 때 어떤값을 불러와서 수정하지..? 공고번호....?
		//진행중인 공고만 수정가능하도록 진행중(true)인 공고 먼저 출력
		String comNum = nextLine("사업자 등록번호를 입력해주세요.");
//		for(String comNum : userService.userList) {
//			if(userService.getLoginUser().equals(comNum)) {
//				
//				
//			}
//			else {
//				System.out.println("사업자번호를 정확하게 입력해주세요.");
//				return;
//			}
//		}
	}
	
	void remove() {
		//공고삭제-사업자
	}
}
