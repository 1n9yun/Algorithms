package ps.Toss_200801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 중복 X 6자  1~45 오름차
public class Toss_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		boolean ans = false;
		
		Set<Integer> duplicated = new HashSet<>();
		
		int passed = 1;
		
		int pre = Integer.parseInt(st.nextToken());
		duplicated.add(pre);
		
		for(int i=1;i<6;i++) {
			if(!st.hasMoreTokens()) break;
			
			int num = Integer.parseInt(st.nextToken());
			
			if(!(1<= num && num <= 45)) break;
			
			if(!duplicated.add(num)) break;
			
			if(pre >= num) break;
			
			passed = i;
		}
		if(passed == 5) ans = true;
		
		System.out.println(ans);
	}
}
