package ps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class boj10816_숫자카드2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i=0;i<n;i++) {
			int num = sc.nextInt();
			map.put(num, map.containsKey(num) ? (map.get(num) + 1) : 1);
		}
		
		int m = sc.nextInt();
		for(int i=0;i<m;i++) {
			int num = sc.nextInt();
			System.out.print((map.containsKey(num) ? map.get(num) : 0) + " ");
		}
	}
}
