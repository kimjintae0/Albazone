package domain;

import java.util.Date;

public class Apply {
	int gonggoNo;
	int resumeNo;
	Date date; // 언제
	// 상태
	
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

	public Apply() {}
	public Apply(int gonggoNo, int resumeNo) {
		this.gonggoNo = gonggoNo;
		this.resumeNo = resumeNo;
	}

}