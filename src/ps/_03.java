package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _03 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer>[] idxs = new HashMap[1000001];
		for(int i=0;i<idxs.length;i++) idxs[i] = new HashMap<>();
		int[] arr = new int[n+1];
		int[] res = new int[n+1];
		int[] counts = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			idxs[arr[i]].put(i, ++counts[arr[i]]);
		}
		
		
		int[] firstIdx = new int[1000001];
		for(int tc=0;tc<k;tc++) {
			Arrays.fill(firstIdx, -1);
			
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			int ans = 0;
			
			for(int i=left;i<=right;i++) {
				if(firstIdx[arr[i]] == -1) {
					firstIdx[arr[i]] = idxs[arr[i]].get(i) - 1;
					ans += arr[i];
				}else {
					ans += arr[i] * (idxs[arr[i]].get(i) - firstIdx[arr[i]]); 
				}
			}
			System.out.println(ans);
		}
		
	}
}
