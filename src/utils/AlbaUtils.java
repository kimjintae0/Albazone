package utils;

import java.util.Scanner;

public class AlbaUtils {
	private static final Scanner scanner = new Scanner(System.in);
	
	public static int nextInt(String msg) {
		System.out.print(msg);
		return scanner.nextInt();
	}
	
	public static String nextLine(String msg) {
		System.out.print(msg);
		return scanner.nextLine();
	} 
	
	public static Long nextLong(String msg) {
		System.out.print(msg);
		return scanner.nextLong();
	}
	
	public static boolean nextConfirm(String msg) {
		String s = nextLine(msg);
		return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
	}
	
}
