package ps.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj12015_가장긴증가하는부분수열2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] subSeq = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = 0;
		subSeq[len++] = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<n;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			int left = 0;
			int right = len - 1;
			int target = -1;
			
			while(left <= right) {
				int mid = (left + right) / 2;
				if(num <= subSeq[mid]) {
					target = mid;
					right = mid - 1;
				}else {
					left = mid + 1;
				}
			}
			if(target == -1) {
				subSeq[len++] = num;
			}else {
				subSeq[target] = num;
			}
		}
		System.out.println(len);
	}
}
