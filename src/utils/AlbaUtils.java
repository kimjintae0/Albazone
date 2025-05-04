package utils;

import static utils.AlbaUtils.nextInt;

import java.util.Scanner;


public class AlbaUtils {
	private static final Scanner scanner = new Scanner(System.in);
	
	public static int nextInt(String msg) {
		return Integer.parseInt(nextLine(msg));
	}
	
	public static String nextLine(String msg) {
		System.out.print(msg);
		return scanner.nextLine();
	}
	
	public static long nextLong(String msg) {
		return Long.parseLong(nextLine(msg));
	}
	
	public static boolean nextConfirm(String msg) {
		String s = nextLine(msg + " [y / n]");
		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
	}
	
	
	// 지역 선택
	public static String selectArea() {
		System.out.println("지역을 선택해주세요.");
		int input = nextInt("1.서울 2.인천 3.부산 4.대전 5.대구 6.울산 7.광주\n 8.제주 9.경기 10.경상 11.강원 12.충청 13.전라");
		switch(input) {
		case 1: return "서울";  
		case 2: return "인천"; 
		case 3: return "부산"; 
		case 4: return "대전"; 
		case 5: return "대구";
		case 6: return "울산"; 
		case 7: return "광주"; 
		case 8: return "제주"; 
		case 9: return "경기"; 
		case 10: return "경상"; 
		case 11: return "강원"; 
		case 12: return "충청"; 
		case 13: return "전라";
		}
		return "지역 미정";
	}
}
