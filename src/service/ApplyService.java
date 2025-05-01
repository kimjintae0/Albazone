package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import domain.Apply;

public class ApplyService {

	// 싱글톤
	private static ApplyService applyService = new ApplyService();
	private ApplyService() {}
	public static ApplyService getInstance() {
		return applyService;
	}
	
	// 리줌, 공고 정보 가져오기
	private ResumeService resumeService = ResumeService.getInstance();
	private GonggoService gonggoService = GonggoService.getInstance();
	
	
	
	// 지원 리스트 생성
	List<Apply> applyList = new ArrayList<>();
	
	 

	public void apply() {
		
	}
	

	
}
