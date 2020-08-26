package ps.Unclassified;

import java.util.Scanner;

public class swea10202_stringSync {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			
			String a = sc.next();
			String b = sc.next();
			String c = sc.next();
			
			int ans = 0;
			
			for(int i=0;i<n;i++) {
				char ai = a.charAt(i);
				char bi = b.charAt(i);
				char ci = c.charAt(i);
				
				if(ai == bi && bi == ci) continue;
				else if(ai != bi && bi != ci && ai != ci) {
					ans += 2;
				}else ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
}
