package ps.Simulation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

public class swea4050_bigSale {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			
			int[] arr = new int[n];
			
			for(int i=0;i<n;i++) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);
			
			int ans = 0;
			int idx = n;
			int count = 0;
			while(--idx >= 0) {
				if(count == 2) {
					count = 0;
					continue;
				}
				ans += arr[idx];
				count++;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
