package domain;

public class Resume  {
	// 제목, 이름, 연락처, 주소, 자기소개
	private String title;
	private String name;
	private String tel;
	private String address;
	private String introduce;
	
	public Resume() {}

	public Resume(String title, String name, String tel, String address, String introduce) {
		super();
		this.title = title;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.introduce = introduce;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Resume [title=" + title + ", name=" + name + ", tel=" + tel + ", address=" + address + ", introduce="
				+ introduce + "]";
	}
		
}

