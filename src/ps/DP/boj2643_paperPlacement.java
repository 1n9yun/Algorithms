package ps.DP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class boj2643_paperPlacement {
	static class Pair{
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Pair> points = new ArrayList<>();
		points.add(new Pair(1001,1001));
		int n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			int width = sc.nextInt();
			int height = sc.nextInt();
			if(width < height) points.add(new Pair(height, width));
			else points.add(new Pair(width, height));
		}
		
		points.sort(new Comparator<Pair>(){
			@Override
			public int compare(Pair p1, Pair p2) {
				if(p1.left == p2.left) return p2.right - p1.right;
				else return p2.left - p1.left;
			}
		});
		
		dp = new int[points.size()];
		dp[0] = 0;
		
		int ans = 0;
		for(int i=1;i<points.size();i++) {
			int maxIdx = 0;
			Pair iPoint = points.get(i);
			for(int j=i-1;j>=0; j--) {
				Pair jPoint = points.get(j);
				if(dp[maxIdx] >= dp[j]) continue;
				if((iPoint.left <= jPoint.left && iPoint.right <= jPoint.right) ||
						(iPoint.right <= jPoint.left && iPoint.left <= jPoint.right)) {
					maxIdx = j;
				}
			}
			dp[i] = dp[maxIdx] + 1;
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}
}
