package domain;

import java.util.Date;

public class Apply {
	int gonggoNo;
	int resumeNo;
	Date applyDate = new Date(); // 언제
	int applySitu;// [상태]  1 : 접수    2: 등록
	
	
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
	
	
	
	// 생성자
	public Apply() {}

	public Apply(int gonggoNo, int resumeNo) {
		this.gonggoNo = gonggoNo;
		this.resumeNo = resumeNo;
	}
}