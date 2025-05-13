package domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Apply implements Serializable{
	private int gonggoNo;
	private int resumeNo;
	private Date applyDate = new Date(); // 언제
	private int applySitu;// [상태]  1 : 접수    2: 등록
	private int userNo; // 지원한 알바 유저 정보
	
	
	// 게터, 세터
	public int getGonggoNo() {
		return gonggoNo;
	}

	public void setGonggoNo(int gonggoNo) {
		this.gonggoNo = gonggoNo;
	}

	public int getResumeNo() {
		return resumeNo;
	}

	public void setResumeNo(int resumeNo) {
		this.resumeNo = resumeNo;
	}
	
	public Date getApplyDate() {
		return applyDate;
	}
	
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public int getApplySitu() {
		return applySitu;
	}
	
	public void setApplySitu(int applySitu) {
		this.applySitu = applySitu;
	}
	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	
	
	

	// 생성자
	public Apply() {}

	public Apply(int gonggoNo, int resumeNo, int userNo) {
		this.gonggoNo = gonggoNo;
		this.resumeNo = resumeNo;
		this.userNo = userNo;
	}
}