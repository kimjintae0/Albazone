package utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;


public class AlbaUtils {
	private static final Scanner scanner = new Scanner(System.in);
	
//	public static void main(String[] args) {
//		int num = nextInt("1. 공고 2. 회원정보 조회 3. 회원정보 수정 4. 회원탈퇴 5. 로그아웃",s -> s > 0 && s <= 5, "1 ~ 5 사이의 번호를 입력해주세요");
//		
//	}
//	================== 스캐너 ==========================
	// base method
		public static <T> T next(String msg, Predicate<T> pred, String errMsg, Function<String, T> parser) {
			T t = null; 
			do {
				System.out.print(msg + " > ");
				try {
					t = parser.apply(scanner.nextLine());
					if(pred == null || pred.test(t)) return t;
					else throw new IllegalArgumentException(errMsg);
				}
				catch (RuntimeException e) {
					System.out.println(errMsg);
				}
			}
			while(true);
		}
		
		public static <T> T next(String msg, Function<String, T> parser) {
			return next(msg, null, null, parser);
		}
		

		public static String nextLine(String msg, Predicate<String> pred, String errMsg) {
			return next(msg, pred, errMsg, s->s);
		}
		
		
//		public static String nextLine(String msg) {
//			return nextLine(msg, null, null);
//		}
		
		
		public static int nextInt(String msg, Predicate<Integer> pred, String errMsg) {
			return next(msg, pred, errMsg, Integer::parseInt);
		}
		
		public static int nextInt(String msg) {
			return Integer.parseInt(nextLine(msg));
		}

		public static long nextLong(String msg) {
			return Long.parseLong(nextLine(msg));
		}
		
		public static String nextLine(String msg) {
			System.out.print(msg);
			return scanner.nextLine();
		}
	
	
	
	
	
	public static boolean nextConfirm(String msg) {
		String s = nextLine(msg + " [y / n]");
		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
	}
	
	
	// ====================== 지역 선택 ===========================
	public static String selectArea() {
		System.out.println("지역을 선택해주세요.");
		// 기타 추가
		int input = nextInt("1.서울 2.인천 3.부산 4.대전 5.대구 6.울산 7.광주\n 8.제주 9.경기 10.경상 11.강원 12.충청 13.전라 14. 기타");
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
		case 14: return "기타";// 추가
		}
		return "지역 미정";
	}
	
	// ========================================= 데이트 타입 포매터 ===========================================
	public static String dateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
		return dateFormat.format(date);
	}
}
