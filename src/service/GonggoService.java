package service;

import java.util.ArrayList;
import java.util.List;

import domain.Gonggo;
import utils.AlbaUtils.*;

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
	UserService userService = UserService.getInstance();
	ApplyService applyService = ApplyService.getInstance();
	
	//공고등록메서드(조건 2개 - 사업주, 개인)
	
	void register() { //사업자
		//로그인 정보로 공고 등록
		
	}
	

	//공고조회메서드
	void lookupUser() {
		//조회 노출 리스트
		//개인기
	}
	
	void apply() {
		//개인 지원하기
		
	}
	
	void lookupOwner() {
		//조회 노출 리스트
		//사업자 - 진행 or 마감 
	}
	
	void modify() {
		//공고수정-사업자
	}
	
	void remove() {
		//공고삭제-사업자
	}
}
