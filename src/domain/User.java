package domain;

public abstract class User {
	// 회원(추상) 개인*사업주(구상)
    //가입, 수정, 탈퇴
    //1) 사업자 : 구현/ ID, Pw, 대표자명, 상호명, 연락처
 //2) 개인회원 : 구현/ ID, PW, 이름, 거주지, 연락처, 이력서
	String id;
	String pw;
	String tel;
	String name;
}
