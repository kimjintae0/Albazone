//package service;
//
//import java.util.ArrayList;
//import java.util.List;
//import static utils.AlbaUtils.*;
//
//import domain.Apply;
//import domain.Resume;
//
//public class ResumeService {
//	// 이력서 리스트 생성
//	List<Resume> resumeList = new ArrayList<>();
//	
//	// 싱글톤
//	private static ResumeService resumeService = new ResumeService();
//	public static ResumeService getInstance() {
//		System.out.println();
//		return resumeService;
//	}
//	
//	// 클래스 연동
//	AlbazoneService albazoneService = AlbazoneService.getInstance();
//	UserService userService = UserService.getInstance();
//	ApplyService applyService = ApplyService.getInstance();
//	GonggoService gonggoService = GonggoService.getInstance();
//	
//	// 초기화 블럭
//	{
//		resumeList.add(new Resume(1, 1, "김진태님의 이력서입니다.", "김진태", "010-1111-1111", "서울", "안녕하세요. 저는 김진태입니다."));
//	}
//	
//	// 이력서 번호 관리
//	int num = resumeList.get(resumeList.size() - 1).getResumeNo() == 0 ? 1 : resumeList.get(resumeList.size() - 1).getResumeNo() + 1; 
//	
//	// 이력서 작성
//	public void resister() {
//		// 알바유저 번호, 이력서 번호, 제목, 알바이름, 알바 연락처, 알바지역, 자기소개
//		String title = nextLine("이력서 제목을 작성해주세요.");
//		String introduce = nextLine("자기소개를 작성해주세요.");
//		resumeList.add(new Resume(userService.getLoginUser().getUserNo(), num, title, userService.getLoginUser().getName(), userService.getLoginUser().getTel(), userService.getLoginUser().getArea(), introduce));
//		System.out.println("이력서 작성 완료");
//	}
//	
//	// 이력서 조회 - 알바
//	public void lookupUser() {
//		for(Resume resume : resumeList) {
//			if(resume.getUserNo() == userService.getLoginUser().getUserNo()) {
//				resume.toString();
//			}
//		}
//	}
//	
//	// 이력서 조회 - 사업자
//	public void lookupOwner() {
//		
//	}
//	
//	// 이력서 수정
//	public void modify() {
//		System.out.println("이력서 수정");
//		Resume input = null;
//		lookupUser();
//		int select = resumeSelect();
//		if (select == 0) {
//			System.out.println("존재하지 않는 이력서입니다.");
//			return;
//		}
//		for(Resume resume : resumeList) {
//			if(resume.getResumeNo() == select) {
//					input = resume;
//			}
//		}
//		String title = nextLine("이력서 제목을 작성해주세요.");
//		String introduce = nextLine("자기소개를 작성해주세요.");
//		input.setTitle(title);
//		input.setIntroduce(introduce);
//	}
//	
//	
//	// 이력서 삭제
//	public void remove() {
//		System.out.println("이력서 삭제");
//		lookupUser();
//		int select = resumeSelect();
//		if (select == 0) {
//			System.out.println("존재하지 않는 이력서입니다.");
//			return;
//		}
//		for(Resume resume : resumeList) {
//			if(resume.getResumeNo() == select) {
//					resumeList.remove(resume);
//			}
//		}
//	}
//	
//	
//	// 이력서 번호 선택
//	public int resumeSelect() {
//		int select = nextInt("이력서 번호를 입력해주세요.");	
//		for(Resume resume : resumeList) {
//			if(resume.getResumeNo() == select) {
//				if(resume.getUserNo() == userService.getLoginUser().getUserNo()) {
//					return select;
//				}
//			}
//		}
//		return 0;
//	}
//}
//
//
//
//
