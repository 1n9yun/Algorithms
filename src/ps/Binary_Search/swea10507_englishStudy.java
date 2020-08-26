package ps.Binary_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea10507_englishStudy {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			int[] pSum = new int[1000000 + 1];
			
			int state = 0;
			int idx = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				int day = Integer.parseInt(st.nextToken());
				
				while(idx != day) {
					pSum[idx++] = state;
				}
				state++;
			}
			
			while(idx < pSum.length) {
				pSum[idx++] = state;
			}
			
			int left = 2;
			int right = n + p;
			int mid = 0;
			int ans = -1;
			
			while(true) {
				if(left > right) break;
				
				int max = -1;
				mid = (left + right) / 2;
				
				for(int i=0;i<pSum.length - (mid - 1);i++) {
					max = Math.max(max, pSum[i + (mid - 1)] - (i == 0 ? 0 : pSum[i - 1]));
				}
				
				if(max + p > mid) {
					left = mid + 1;
				}else if(max + p < mid) {
					right = mid - 1;
				}else {
//					딱 들어맞는다고 답이 아니었어 그 중에 제일 큰 놈 찾아야 돼
					ans = mid;
					left = mid + 1;
				}
				
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
