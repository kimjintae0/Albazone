package service;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import domain.Apply;
import domain.Gonggo;
import domain.Resume;

import static utils.AlbaUtils.*;
public class ApplyService {

	// 싱글톤
	private static ApplyService applyService = new ApplyService();
	private ApplyService() {}
	public static ApplyService getInstance() {
		return applyService;
	}
	
	// 리줌, 공고 정보 가져오기
	private UserService userService = UserService.getInstance();
	private ResumeService resumeService = ResumeService.getInstance();

	
	
	// 지원 리스트 생성
	List<Apply> applyList = new ArrayList<>();
	
	
	
	// 지원하기 - 알바
	public void apply() {
		int gonggoNo = GonggoService.getInstance().gonggoSelectUser();
		if(gonggoNo == 0) {
			System.out.println("없는 공고입니다.");
			return;
		}
		resumeService.lookupUser();
		int resumeNo = resumeService.resumeSelect();
		if (resumeNo == 0) {
			System.out.println("존재하지 않는 이력서입니다.");
			return;
		}
		if(!nextConfirm("해당 공고에 지원하시겠습니까?")) {
			System.out.println("공고 지원 취소");
			return;
		}
		applyList.add(new Apply(gonggoNo, resumeNo)); // 로그인 유저의 이력서 중, 유저가 선택한 이력서의 번호 
		System.out.println("공고 지원 완료");
	}
	
	// 데이트 타입 포매터
	SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
	
	// 지원내역 조회 - 알바
	public void lookupUser() {
		for(Resume r : resumeService.resumeList) {
			if(r.getUserNo() == userService.getLoginUser().getUserNo()) {
				for(Apply a : applyList) {
					if(r.getResumeNo() == a.getResumeNo()) {
						for(Gonggo g : GonggoService.getInstance().gonggoList) {
							if(a.getGonggoNo() == g.getGonggoNo()) {
								System.out.println("지원 시간 : " + dateFormat.format(a.getApplyDate()));
								System.out.println("지원 상태 : " + (a.getApplySitu() == 0 ? "접수" : "읽음"));
								System.out.println(g.toString());
							}
						}
					}
				}
			}
		}
	}
	
	
	// 지원 취소
	public void remove() {
		lookupUser();
		Apply removeApply = null;
		int input = nextInt("지원을 취소하실 공고의 번호를 입력해주세요.");
		for(Apply a : applyList) {
			if(a.getGonggoNo() == input) {
				for(Resume r : resumeService.resumeList) {
					if(r.getUserNo() == userService.getLoginUser().getUserNo()) {
						if(a.getResumeNo() == r.getResumeNo()) {
							removeApply = a;
						}
					}
				}
			}
		}
		applyList.remove(removeApply);
		System.out.println("지원이 취소되었습니다.(중복 지원시, 오래된 항목이 먼저 삭제됩니다.)");
	}
	
	// 내 공고에 지원한 내역 조회 - 사업자
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
