package ps.DFS;

import java.util.Scanner;

public class swea1247_optimized_path {
	static int n;
	static class Pair{
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static Pair[] points;
	static Pair home;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			points = new Pair[n];
			Pair company = new Pair(sc.nextInt(), sc.nextInt());
			home = new Pair(sc.nextInt(), sc.nextInt());
			
			for(int i=0;i<n;i++) points[i] = new Pair(sc.nextInt(), sc.nextInt());
			
//			회사 - 모두 방문 - 집
			int ans = 987654321;
			for(int i=0;i<n;i++) {
				ans = Math.min(ans, dfs(i, 1<<i) + getDist(company, points[i]));
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	} 
	static int getDist(Pair p1, Pair p2) {
		return Math.abs(p1.left - p2.left) + Math.abs(p1.right - p2.right);
	}
	static int dfs(int pos, int check) {
		if(check == (1<<n)-1) return getDist(points[pos], home);
		if(pos >= n) return 987654321;
		
		int ret = 987654321; 
		for(int i=0;i<n;i++) {
			if(i == pos || (check & (1 << i)) != 0) continue;
			ret = Math.min(ret, dfs(i, check | (1 << i)) + getDist(points[pos], points[i])); 
		}
		return ret;
	}
}
