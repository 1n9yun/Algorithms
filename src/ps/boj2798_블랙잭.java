package ps;

import java.util.Scanner;

public class boj2798_블랙잭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		
		int ans = -1;
		for(int i=0;i<n-2;i++) {
			for(int j=i+1;j<n-1;j++) {
				for(int k=j+1;k<n;k++) {
					int res = arr[i] + arr[j] + arr[k];
					if(res <= m) {
						ans = Math.max(ans, res);
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}

// 걍 싹 다 돌려버리기