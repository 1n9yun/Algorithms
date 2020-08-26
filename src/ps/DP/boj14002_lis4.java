package ps.DP;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class boj14002_lis4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		int[] dp = new int[n];
		int[][] trace = new int[n][2];
		
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		
		dp[0] = arr[0];
		trace[0][0] = 0;
		trace[0][1] = arr[0];
		
		int lisLen = 0;
		for(int i=1;i<n;i++) {
			if(arr[i] > dp[lisLen]) {
				dp[++lisLen] = arr[i];
				trace[i][0] = lisLen;
				trace[i][1] = arr[i];
			}else {
				int left = 0;
				int right = lisLen;
				
				while(left < right) {
					int mid = (left + right) / 2;
					
					if(arr[i] > dp[mid]) {
						left = mid + 1;
					}else {
						right = mid;
					}
				}
				
				dp[right] = arr[i];
				trace[i][0] = right;
				trace[i][1] = arr[i];
			}
		}
		
		System.out.println(lisLen + 1);
		
		Stack<Integer> s = new Stack<>();
		
		for(int i=n-1;i>=0;i--) {
			if(trace[i][0] == lisLen) {
				s.push(trace[i][1]);
				lisLen--;
			}
		}
		
		while(!s.isEmpty()) {
			System.out.print(s.pop() + " ");
		}
	}
}
