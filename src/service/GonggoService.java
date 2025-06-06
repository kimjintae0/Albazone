package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import domain.Gonggo;
import utils.AlbaUtils;
import static utils.AlbaUtils.*;
@SuppressWarnings("unchecked")
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

	{
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/gonggo.ser"));
			gonggoList = (List<Gonggo>)ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Gonggo : 파일을 불러올 수 없습니다. 임시 데이터셋으로 진행합니다.");
			gonggoList.add(new Gonggo(1, 1, "김밥천국 오전 알바(9시 ~ 6시, 1시간 휴식) 구합니다", "서빙", 8, 10030, "2025-05-04" ,"2025-06-04", true, "서울", "000-0000-0000"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//====================================== 메서드 ===========================================================================================

	// 공고등록
	void register(){
		// 사업자 유저 번호, 공고 번호, 제목, 역할, 일하는 시간, 시급, 근무 기간, 진행상태, 소재지
		String title = nextLine("공고의 제목을 입력해주세요.");
		String role = nextLine("담당 업무를 입력해주세요.");
		int workHours = nextInt("근무 시간을 숫자로 입력해주세요.", s -> s > 0 && s <= 12,"시간은 1 ~ 12 사이의 정수로 입력해주세요.");
		int wage = nextInt("시급을 숫자로 입력해주세요. (2025년 최저시급은 10,030원 입니다.)", s -> s >= 10030, "시급은 최저시급이상이어야 합니다."); // 최저시급 보다 작을 시 return
		String workingStartDate = nextLine("근무 시작일을 입력해주세요 (yyyy-MM-dd).",s -> s.matches(dateForm),"날짜는 yyyy-MM-dd 형식으로 작성해주세요."); // 정규식 만들기 
		String workingEndDate = nextLine("근무 종료일을 입력해주세요(yyyy-MM-dd).",s -> s.matches(dateForm),"날짜는 yyyy-MM-dd 형식으로 작성해주세요."); // 정규식 만들기 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startdate = format.parse(workingStartDate);
			Date enddate = format.parse(workingEndDate); 
			String today = now.format(formatNow);
			Date todaynow = format.parse(today);
			if(enddate.compareTo(startdate) <  0 || enddate.compareTo(todaynow) < 0) {
				System.out.println("종료일이 시작일보다 빠르거나, 금일보다 빠릅니다.");
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		String comArea = selectArea();
		


		// 공고번호 관리
		int num = gonggoList.size() == 0 ? 1 : gonggoList.get(gonggoList.size() - 1).getGonggoNo() + 1; 
		
		gonggoList.add(new Gonggo(UserService.getInstance().getLoginUser().getUserNo(), num, title, role, workHours, wage, workingStartDate, workingEndDate, true , comArea, UserService.getInstance().getLoginUser().getTel()));
		save();
		System.out.println("공고가 정상적으로 등록 완료되었습니다.");
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
		int input = nextInt("공고 번호를 선택해 주세요.", s -> s > 0 , "공고번호는 1이상의 정수를 입력해주세요.");
		int gonggoNo = 0;
		for(Gonggo g : gonggoList) {
			if(g.getGonggoNo() == input && g.getComArea().equals(UserService.getInstance().getLoginUser().getArea()) && g.state == true) {
				gonggoNo = input;
			}
		}
		return gonggoNo;
	}
	
	// 공고 선택 - 사업자
	public int gonggoSelectOwner() {
		int input = nextInt("공고 번호를 입력해 주세요.", s -> s > 0 , "공고번호는 1이상의 정수를 입력해주세요."); // 사업자는 자신의 유저 번호를 모릅니다. 수정필요
		int gonggoNo = 0;
		for(Gonggo g : gonggoList) {
			if(g.getGonggoNo() == input && g.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				gonggoNo = input;
			}
		}
		return gonggoNo;
	}
	
	// 공고조회 - 사업자 자신이 등록한 공고
	// input을 메서드 밖에서 입력받고 switch문만 안에서
	void lookupOwner(int input) {
		switch(input) {
		case 1:{
			for(Gonggo g : gonggoList) {
				if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == true) {
					System.out.println("<현재 진행중인 공고>");
					System.out.println(g.toString());	
				}
			}
			break;
		}
		case 2:{
			for(Gonggo g : gonggoList) {
				if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo() && g.state == false) {
					System.out.println("<현재 마감된 공고>");

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
	
	//공고수정-사업자
	void modify() {
		System.out.println("공고 수정 기능");
		int input = gonggoSelectOwner();
		if (input == 0) {
			System.out.println("해당공고에 접근이 불가능합니다.");
			return;
		}
		Gonggo g = findGonggoByNo(input);

		int select = AlbaUtils.nextInt("1.공고 제목 2.담당 업무 3.근무 시간 4. 시급 5.근무시작일 6. 근무종료일 7.근무 지역 8. 나가기", s -> s > 0 && s <= 8, "1 ~ 8 사이의 숫자를 입력해주세요.");

		switch(select) {
			case 1 :
				String title = nextLine("공고의 제목을 입력해주세요.");
				System.out.println("공고 제목이 " + "\"" + title + "\"" + "(으)로 변경 완료되었습니다.");
				g.setTitle(title);
				break;
				
			case 2:
				String role = nextLine("담당 업무를 입력해주세요.");
				System.out.println("담당 업무가 " + "\"" + role + "\"" + "(으)로 변경완료되었습니다.");
				g.setRole(role);
				break;
				
			case 3:
				int workHours = nextInt("근무 시간을 숫자로 입력해주세요. (시간 단위로 적어주세요. 소숫점은 미지원)", s -> s > 0 && s <= 12,"시간은 1 ~ 12 사이의 정수로 입력해주세요.");
				System.out.println("근무 시간이 " + "\"" + workHours + "시간\"" + "(으)로 변경되었습니다.");
				g.setWorkHours(workHours);
				ApplyService.getInstance().removeAllOwner(g.getGonggoNo());
				break;
				
			case 4: 
				int wage = nextInt("시급을 숫자로 입력해주세요. (2025년 최저시급은 10,030원 입니다.)", s -> s >= 10030, "시급은 최저시급이상이어야 합니다."); 
				System.out.println("시급이 " + "\"" + wage + "원\"" + "으로 변경되었습니다.");
				g.setWage(wage);
				ApplyService.getInstance().removeAllOwner(g.getGonggoNo());
				break;
				
			case 5 :
				String workingStartDate = nextLine("근무 시작일을 입력해주세요 (yyyy-MM-dd).",s -> s.matches(dateForm),"날짜는 yyyy-MM-dd 형식으로 작성해주세요."); 
				System.out.println("근무 시작일이 " + "\"" + workingStartDate + "\"" + "일로 변경되었습니다.");
				g.setWorkingStartDate(workingStartDate);
				break;
				
			case 6 :
				String workingEndDate = nextLine("근무 종료일을 입력해주세요(yyyy-MM-dd).",s -> s.matches(dateForm),"날짜는 yyyy-MM-dd 형식으로 작성해주세요.");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date startdate = format.parse(g.getWorkingStartDate());
					Date enddate = format.parse(workingEndDate);
					String today = now.format(formatNow);
					Date todaynow = format.parse(today);
					if(enddate.compareTo(startdate) <  0 || enddate.compareTo(todaynow) < 0) {
						System.out.println("종료일이 시작일보다 빠르거나, 금일보다 빠릅니다.");
						return;
					}
				}catch(Exception e) {
					e.printStackTrace();
				}	
				
				
				System.out.println("근무 종료일이 " + "\"" + workingEndDate + "\"" + "일로 변경되었습니다.");
				g.setWorkingEndDate(workingEndDate);
				ApplyService.getInstance().removeAllOwner(g.getGonggoNo());
				break;
				
			case 7 :
				String comArea = selectArea();
				System.out.println("근무 지역이 " + "\"" + comArea + "\"" + " 시로 변경되었습니다.");
				g.setComArea(comArea);
				ApplyService.getInstance().removeAllOwner(g.getGonggoNo());
				break;
		}	
			save();
	}
	
	
	//회원정보 수정시 공고 연락처도 수정
	void gonggoSync() {
		for(Gonggo g : gonggoList) {
			if(UserService.getInstance().getLoginUser().getUserNo() == g.getUserNo()) {
				g.setTel(UserService.getInstance().getLoginUser().getTel());
			}
		}
		save();
	}
	
		
	//공고 마감 - workingEndDate 보다 현재 시간이 지났으면 자동 마감
	void gonggoMagam() {
		String today = now.format(formatNow);
		System.out.println(today);

		for(Gonggo g: gonggoList) {
				Date endDate = (Date) formatNow.parse(g.getWorkingEndDate());
				Date nowDate = (Date) formatNow.parse(today);
				
				if(endDate.compareTo(nowDate) <  0) {
					g.state = false;
					ApplyService.getInstance().removeAllOwner(g.getGonggoNo());
				}			
			}
		save();
		}

	void remove() {
		//공고삭제-사업자
		System.out.println("공고 삭제 기능");
		int input = nextInt("삭제할 공고 번호를 입력해 주세요.",s -> s > 0, "공고번호는 1이상의 정수를 입력해주세요."); 
		Gonggo gonggo = null;
		for(Gonggo g : gonggoList) {
			if(input == g.getGonggoNo() && g.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				gonggo = g;
			}
		}
		if(gonggo == null) {
			System.out.println("해당 공고번호가 존재하지 않습니다.");
			return;
		}
		nextConfirm("해당 공고를 삭제하시겠습니까?");
		gonggoList.remove(gonggo);	
		System.out.println("해당 공고가 삭제되었습니다.");
		ApplyService.getInstance().removeAllOwner(gonggo.getGonggoNo());
		save();
		}


	//공고마감 - 사업자가 직접 마감 
	void stateChange() {
		int input = AlbaUtils.nextInt("마감할 공고 번호를 입력하세요.",s -> s > 0, "공고번호는 1이상의 정수를 입력해주세요."); 
		Gonggo gonggo = null;
		for(Gonggo g : gonggoList) {
			if(input == g.getGonggoNo() && g.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				gonggo = g;
			}
		}
		if(gonggo == null) {
			System.out.println("접근불가능한 공고입니다.");
		}
		if(nextConfirm("공고를 마감하시겠습니까?")) {
			gonggo.state = false;
			System.out.println("공고가 마감되었습니다.");
			ApplyService.getInstance().removeAllOwner(gonggo.getGonggoNo());
		}else 
		{System.out.println("공고 마감이 취소되었습니다.");
		return ;
		}
		save();
	}
	private void save() {
		try {
			File file = new File("data");
			if (!file.exists()) {
				file.mkdirs();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file, "gonggo.ser")));

			oos.writeObject(gonggoList);
			oos.close();
		} catch (Exception e) {
			System.out.println("파일 접근 권한이 없습니다.");
			e.printStackTrace();

		}
	}
	
//	============================== 유틸  ==============================================================
	// 입력 : gonggoNo, 출력 : gonggo
	public Gonggo findGonggoByNo(int gonggoNo) {
		Gonggo gonggo = null;
		for(Gonggo g : gonggoList) {
			if(g.getGonggoNo() == gonggoNo) {
				gonggo = g;
			}
		}
		return gonggo;
	}
	

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
	public List<Gonggo> userFindGonggo(int no){
		List<Gonggo> gonggoUser = new ArrayList<Gonggo>();
		for(Gonggo g : gonggoList) {
			if(no == g.getUserNo()) {
				gonggoUser.add(g);
			}
		}
		return gonggoUser;
	}
	
	
	// 로그인유저가 몇개의 공고를 가지고 있는지 출력하는 메서드
	public int userRemoveCheck() {
		int count = 0;
		for(Gonggo g : gonggoList) {
			if(g.getUserNo() == UserService.getInstance().getLoginUser().getUserNo()) {
				count++;
			}
		}
		return count;
	}
	
	
	
// ============================ 그 외 클래스 내 사용 ====================================================
	
	public final String dateForm = "(20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";	//날짜 정규식
	 DateTimeFormatter formatNow = DateTimeFormatter.ofPattern("yyyy-MM-dd");		//현재날짜패턴
	 LocalDate now = LocalDate.now();
}
