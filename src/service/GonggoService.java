package service;



public class GonggoService {
	
	//싱글톤 
	private static GonggoService gonggoService = new GonggoService();
	private GonggoService() {}
	public static GonggoService getInstance() {
		return gonggoService;
	}
	
	//싱글톤으로 호출하기
	
	

}
