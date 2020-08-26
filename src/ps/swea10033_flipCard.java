package ps;

import java.util.Scanner;

public class swea10033_flipCard {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int ans = 0;
			char[] input = sc.next().toCharArray();

//			인덱스 차이만큼 ++
			int bIdx = 0;
			
			while(bIdx < input.length - 1) {
				boolean flag = false;
				for(int i=bIdx+1;i<input.length;i++) {
					if(input[i] == 'W') {
						flag = true;
						ans += (i - bIdx);
						input[bIdx] = 'W';
						input[i] = 'B';
						bIdx++;
						break;
					}
				}
				if(!flag) break;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
