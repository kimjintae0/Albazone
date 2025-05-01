package main;

import domain.User;
import service.AlbazoneService;
import utils.AlbaUtils;

public class Albazone {
	public static void main(String[] args) {
		
		System.out.println("세상 모든 알바, 알바존");
		try {
			while(true) {
				System.out.println("====================================");
					AlbazoneService.getInstance().menu(); // -> 이거 서버 연결을 여기에다 하는지 알바 서비스에서 연결을 해야 하는지 물어보기
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("정상적으로 종료되었습니다.");
			return;
		}
	}
}
