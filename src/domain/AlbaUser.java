package domain;


public class AlbaUser extends User {
	
	
	private String resume; //이력서
	
	public AlbaUser() {}

	public AlbaUser(String resume) {
		super();
		this.resume = resume;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
}