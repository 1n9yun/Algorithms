package ps.Unclassified;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class boj2002_overtake {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		String[] in = new String[n];
		String[] out = new String[n];
		Map<String, Integer> indexing = new HashMap<>();
		
		for(int i=0;i<n;i++) {
			in[i] = sc.next();
			indexing.put(in[i], i);
		}
		for(int i=0;i<n;i++) {
			out[i] = sc.next();
		}
		
		int ans = 0;
		int outIdx = 0;
		for(int i=0;i<n;i++) {
			if(in[i] == null) continue;
			if(outIdx >= n) break;
			
			if(in[i].equals(out[outIdx])) {
				outIdx++;
				continue;
			}
			while(!in[i].equals(out[outIdx]) && outIdx < n) {
				in[indexing.get(out[outIdx])] = null;
				outIdx++;
				ans++;
			}
			outIdx++;
		}
		
		System.out.println(ans);
	}
}
