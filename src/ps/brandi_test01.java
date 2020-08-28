package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class brandi_test01 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		int min = (1<<31) - 1;
		int minIdx = 0;
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(min > arr[i]) {
				min = arr[i];
				minIdx = i;
			}
		}
		
		int ans = 100001;
		int init = minIdx - (k-1) < 0 ? 0 : minIdx - (k-1);
		for(int i=init;i<minIdx+(k-1);i++) {
			ans = Math.min(ans, getCnt(n, k, i, i+(k-1)));
		}
		
		System.out.println(ans);
	}
	
	static int getCnt(int n, int k, int left, int right) {
		int cnt = 1;
		while(left > 0) {
			left -= k - 1;
			cnt++;
		}
		while(right < n-1) {
			right += k - 1;
			cnt++;
		}
		
		return cnt;
	}
}
