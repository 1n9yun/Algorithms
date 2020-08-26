package ps.구현;

import java.util.Arrays;
import java.util.Scanner;

public class swea10580_전봇대 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			
			int ans = 0;
			int n = sc.nextInt();
			int[][] lines = new int[n][2];
			
			for(int i=0;i<n;i++) {
				lines[i][0] = sc.nextInt();
				lines[i][1] = sc.nextInt();
			}
			
			Arrays.sort(lines, (o1, o2) -> {
				if(o1[0] > o2[0]) return 1;
				else return -1;
			});
			
			for(int i=1;i<n;i++) {
				for(int j=i-1;j>=0;j--) {
					if(lines[i][1] < lines[j][1]) ans++;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
}
