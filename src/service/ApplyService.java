package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import domain.Apply;
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
	private GonggoService gonggoService = GonggoService.getInstance();
	
	
	
	// 지원 리스트 생성
	List<Apply> applyList = new ArrayList<>();
	
	
	// 지원하기
	public void apply() {
		if(nextConfirm("해당 공고에 지원하시겠습니까?"))
		applyList.add(new Apply()); // 로그인 유저의 이력서 중, 유저가 선택한 이력서의 번호. loginUser.findByResume(). 
		System.out.println("공고 지원 완료");
	}
	
	
	// 지원내역 조회
	
	
	
	
	
	
	// 지원 취소
	
	
	
	
	
	
	

	
}
