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
					AlbazoneService.getInstance().menu();
			}
		}
		catch (Exception e) {
			System.out.println("정상적으로 종료되었습니다.");
			return;
		}
	}
}
