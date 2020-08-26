package ps.Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class swea5658_treasurePassword {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
//			TreeSet이 있었네??
			Map<String, Integer> map = new HashMap<>();
			
			String input = sc.next();
			int rotate = n / 4;
			
			ArrayList<Integer> ans = new ArrayList<>();
			for(int i=0;i<rotate;i++) {
				for(int j=0;j<4;j++) {
					int begin = j*(n/4);
					int end = begin + (n/4);
					
					String subNum = input.substring(begin, end);
					
					if(map.get(subNum) == null) {
						int toDecimal = stod(subNum);
						map.put(subNum, toDecimal);
						ans.add(toDecimal);
					}
				}
				input = input.substring(1, input.length()) + input.charAt(0);
			}
			
			ans.sort((o1, o2) -> {
				if(o1 < o2) return 1;
				else return -1;
			});
			System.out.println("#" + tc + " " + ans.get(k - 1));
		}
	}
	
	static int stod(String s) {
		int res = 0;
		int multi = 1;
		for(int i=s.length() - 1;i>=0;i--) {
			char c = s.charAt(i);
			
			if('A'<=c && c<='F') {
				res += (c - 'A' + 10) * multi;
			}else {
				res += (c - '0') * multi;
			}
			multi *= 16;
		}
		
		return res;
	}
}
