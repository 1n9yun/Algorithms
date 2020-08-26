package ps.Unclassified;

import java.util.Scanner;

public class swea4796_ {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			
			int[] heights = new int[n];
			for(int i=0;i<n;i++) heights[i] = sc.nextInt();
			
//			state 0 : down, 1 : up
			boolean uped = false;
			int state = -1;
			int downCount = 0;
			int upCount = 0;
			int ans = 0;
			for(int i=0;i<n-1;i++) {
				if(heights[i] < heights[i+1]) {
					if(uped && state == 0) {
						ans += upCount * downCount;
						upCount = 0;
						downCount = 0;
					}else if(!uped && state == 0) {
						downCount = 0;
					}
					uped = true;
					state = 1;
					upCount++;
				}else if(heights[i] > heights[i+1]) {
					state = 0;
					downCount++;
					if(i == n-2 && uped) ans += upCount * downCount;
				}
//				System.out.println(uped + " " + state + " " + upCount + " " + downCount);
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
