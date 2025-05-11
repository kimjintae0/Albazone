package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import domain.BusinessUser;
import domain.Gonggo;
import utils.AlbaUtils;

import static utils.AlbaUtils.*;

public class GonggoService {
	// 공고 리스트 생성
	List<Gonggo> gonggoList = new ArrayList<>();
	
	private static GonggoService gonggoService = new GonggoService();
	private GonggoService() {}
	public static GonggoService getInstance() {
		return gonggoService;
	}
	
	// 유저서비스, 지원 서비스 호출
	AlbazoneService albazoneService = AlbazoneService.getInstance();
//	UserService userService = UserService.getInstance();
	ApplyService applyService = ApplyService.getInstance();

	
	// 초기화 블럭
	{
		// 사업자 유저 번호, 공고 번호, 제목, 역할, 일하는 시간, 시급, 근무 기간, 진행상태, 소재지
		gonggoList.add(new Gonggo(1, 1, "김밥천국 오전 알바(9시 ~ 6시, 1시간 휴식) 구합니다", "서빙", 8, 10030, "2025-05-04" ,"2025-06-04", true, "서울", "000-0000-0000"));
	}
	
	//====================================== 메서드 ===========================================================================================

	// 공고등록
	void register(){
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
		if(!Pattern.matches(dateForm, workingStartDate)) {
			System.out.println("양식에 맞게 다시 입력하세요. ");
			return;
		}
		String workingEndDate = nextLine("근무 종료일을 입력해주세요(yyyy-MM-dd)."); // 정규식 만들기 
		boolean workingEndDateCheck = workingEndDate.matches(dateForm);
		if(!Pattern.matches(dateForm, workingEndDate)){
			System.out.println("양식에 맞게 다시 입력해주세요.");
			return;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
		try {
			Date startdate = format.parse(workingStartDate);
			Date enddate = format.parse(workingEndDate);
			
			if(enddate.compareTo(startdate) <  0) {
				System.out.println("종료일이 시작일보다 빠릅니다. 날짜를 다시 입력하세요.");
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		String comArea = selectArea();
		// 공고번호 관리
		int num = gonggoList.get(gonggoList.size() - 1).getGonggoNo() == 0 ? 1 : gonggoList.get(gonggoList.size() - 1).getGonggoNo() + 1; 
		
		gonggoList.add(new Gonggo(UserService.getInstance().getLoginUser().getUserNo(), num, title, role, workHours, wage, workingStartDate, workingEndDate, true, comArea, UserService.getInstance().getLoginUser().getTel()));
	}

	// 공고조회 - 개인유저
	void lookupUser() {
		for(Gonggo gonggo : gonggoList) {
			if(UserService.getInstance().getLoginUser().getArea().equals(gonggo.getComArea())) {
				System.out.println(gonggo.toString());
			}
		}
		return;
	}
	
	// 공고 선택 - 개인유저
	public int gonggoSelectUser() {
		int input = nextInt("공고 번호를 선택해 주세요.");
		for(Gonggo g : gonggoList) {
			if(g.getGonggoNo() == input && g.getComArea() == UserService.getInstance().getLoginUser().getArea() && g.state == true) {
				return input;
			}
		}
		return 0;
	}
	
	// 공고 선택 - 사업자 #수정필요
	public int gonggoSelectOwner() {
		int input = nextInt("유저 번호를 입력해 주세요."); // 사업자는 자신의 유저 번호를 모릅니다. 수정필요
		for(Gonggo g : gonggoList) {
			if(input == UserService.getInstance().getLoginUser().getUserNo()) {
				return input;
			}
		}
		return 0;
	}
	
	// 공고조회 - 사업자 자신이 등록한 공고
	void lookupOwner() {
		int input = nextInt("1. 진행중 2. 마감 3. 종료");
		switch(input) {
		case 1:{
			for(Gonggo g : gonggoList) {
				if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == true) {
					System.out.println(g.toString());	
				}
			}
			break;
		}
		case 2:{
			for(Gonggo g : gonggoList) {
				if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == false) {
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
	
	//공고수정-사업자 #수정필요
	void modify() {
		System.out.println("공고 수정 기능");
		int input = AlbaUtils.nextInt("공고 번호를 입력하세요 : ");
		for(Gonggo g : gonggoList) { // for문 쓰는 이유도 잘 모르겠습니다.
			if(input != UserService.getInstance().getLoginUser().getUserNo()) { // input은 공고번호인데, 왜 유저번호와 비교를 하는지 잘 모르겠습니다.
				System.out.println("공고번호와 사업자의 유저 정보가 일치하지 않습니다.");
				return;
			}else 
			{
			for(int i = 0; i < gonggoList.size(); i++) {
				if(input == gonggoList.get(i).getGonggoNo()) {
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
					if(!Pattern.matches(dateForm, workingStartDate)) {
						System.out.println("양식에 맞게 다시 입력하세요. ");
						return;
					}
					String workingEndDate = nextLine("근무 종료일을 입력해주세요(yyyy-MM-dd)."); // 정규식 만들기 
					boolean workingEndDateCheck = workingEndDate.matches(dateForm);
					if(!Pattern.matches(dateForm, workingEndDate)){
						System.out.println("양식에 맞게 다시 입력해주세요.");
						return;
					}
					
					String comArea = selectArea();
					
					if(!nextConfirm("공고를 수정하시겠습니까?")) {
						System.out.println("공고 수정이 취소되었습니다.");
						return;
					}
					gonggoList.get(i).setTitle(title);
					gonggoList.get(i).setRole(role);
					gonggoList.get(i).setWorkHours(workHours);
					gonggoList.get(i).setWage(wage);
					gonggoList.get(i).setWorkingStartDate(workingStartDate);
					gonggoList.get(i).setWorkingEndDate(workingEndDate);
					gonggoList.get(i).setState(true);
					gonggoList.get(i).setComArea(comArea);
					}
				}
			}
		}
	}
	//회원정보 수정시 공고 연락처도 수정 #수정필요
	void gonggoSync() {
		int input = nextInt("유저 번호를 입력해 주세요."); // 사업자는 자신의 유저번호를 모릅니다.
		for(Gonggo g : gonggoList) {
				if(input == UserService.getInstance().getLoginUser().getUserNo()) {
					if(!UserService.getInstance().getLoginUser().getTel().equals(g.getTel())) {
						g.setTel(UserService.getInstance().getLoginUser().getTel());
					}
				}		
				else {
					System.out.println("유저 번호가 일치하지 않습니다. ");
					return;
				}
		}
	}
	
	//공고 마감 - workingEndDate 보다 현재 시간이 지났으면 자동 마감
	void gonggoMagam() {
		LocalDate now = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = now.format(format);
		System.out.println(today);

		for(Gonggo g: gonggoList) {
				Date endDate = (Date) format.parse(g.getWorkingEndDate());
				Date nowDate = (Date) format.parse(today);
				
				if(endDate.compareTo(nowDate) <  0) {
					g.state = false;
				}			
			}
		}

	void remove() {
		//공고삭제-사업자 # 수정필요
		System.out.println("공고 삭제 기능");
		int input = nextInt("유저 번호를 입력해 주세요."); // 사업자는 자신의 유저번호를 모릅니다.
		for(Gonggo g : gonggoList) {
			if(input != UserService.getInstance().getLoginUser().getUserNo()) {
				System.out.println("유저 번호가 일치하지 않습니다.");
				return;
			}
			else {
				int input2 = nextInt("삭제할 공고의 공고 번호를 입력해 주세요.");
					if(input2 == g.getGonggoNo()) {
						nextConfirm("해당 공고를 삭제하시겠습니까?");
						gonggoList.remove(g);	
						System.out.println("해당 공고가 삭제되었습니다.");
						break;
					}
					else {
						System.out.println("해당 공고번호가 존재하지 않습니다.");
						return;
					}
			}
		}
	}

	//공고마감 - 사업자가 직접 마감 #수정필요 
	void stateChange() {
		int input = AlbaUtils.nextInt("유저 번호를 입력하세요."); // 사업자는 자신의 유저번호를 모릅니다.
		for(Gonggo g : gonggoList) {
			if(input == UserService.getInstance().getLoginUser().getUserNo()) {
				int input2 = nextInt("마감할 공고의 공고 번호를 입력해 주세요.");
				if(g.getGonggoNo() == input2) {
					if(nextConfirm("공고를 마감하시겠습니까?")) {
						System.out.println("공고가 마감되었습니다.");
						g.state = false;
						return;
					}
				}else 
				{System.out.println("공고번호가 일치하지 않습니다");
				return ;
				}
			}else
				System.out.println("유저 번호가 일치하지 않습니다.");
				return;		
		}
	}
	
//	============================== 유틸  ==============================================================
	
	// applyList.gonggoNo() 를 입력받아서 List<Gonggo>를 출력하는 메서드
		
	public List<Gonggo> findGonggoBy(int no){
		List<Gonggo> gonggoes = new ArrayList<>();
		for(Gonggo g : gonggoList) {
			if(no == g.getGonggoNo()) {
				gonggoes.add(g);
			}
		}
		return gonggoes; 
	}
	
	//userNo (지원한 알바 유저 정보) 입력 받아 List<Gonggo> 출력 
//	public List<Gonggo> userFindGonggo(int no){
//		List<Gonggo> gonggoUser = new ArrayList<Gonggo>();
//		for(Gonggo g : gonggoList) {
//			if(no == g.getGonggoNo()) {
//				gonggoUser.add(g);
//			}
//		}
//		return gonggoUser;
//	}
	
// ============================ 그 외 클래스 내 사용 ====================================================
	
	public final String dateForm = "(20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";		
		
}
