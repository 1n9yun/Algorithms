package ps.Permutation;

import java.util.Arrays;
import java.util.Scanner;

public class swea2112_protectFilm {
	static int d,w,k;
	static int[][] section;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			d = sc.nextInt();
			w = sc.nextInt();
			k = sc.nextInt();
			ans = 987654321;
					
			section = new int[d][w];
			
			for(int i=0;i<d;i++) {
				for(int j=0;j<w;j++) {
					section[i][j] = sc.nextInt();
				}
			}
			
			perm(0, new int[d]);
				
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void perm(int idx, int[] result) {
		if(idx == result.length) {
			int injection = 0;
			for(int i=0;i<result.length;i++) {
				if(result[i] != -1) injection++;
			}
			if(injection >= ans) return; 
			
			for(int j=0;j<w;j++) {
				int cnt = 0;
				int type = result[0] == -1 ? section[0][j] : result[0];
				boolean flag = false;
				
				for(int i=0;i<d;i++) {
					if(result[i] == -1) {
						if(type == section[i][j]) cnt++;
						else {
							cnt = 1;
							type = section[i][j];
						}
					}else {
						if(type == result[i]) cnt++;
						else {
							cnt = 1;
							type = result[i];
						}
					}
					
					if(cnt == k) {
						flag = true;
						break;
					}
				}
				if(!flag) return;
			}
			
			ans = Math.min(ans, injection);
			return;
		}
		
		for(int i=-1;i<2;i++) {
			result[idx] = i;
			perm(idx + 1, result);
		}
	}
}
