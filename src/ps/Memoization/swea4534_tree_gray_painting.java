package ps.Memoization;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class swea4534_tree_gray_painting{
	static final int MOD = 1000000007;
	static int n;
	
	static List<Integer>[] adjList;
	static long[][] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			adjList = new ArrayList[n+1];
			for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();
			memo = new long[n+1][2];
			
			for(int i=1;i<n;i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				adjList[from].add(to);
				adjList[to].add(from);
			}
			
			System.out.println("#" + tc + " " + (dfs(1, 0, -1) + dfs(1, 1, -1)) % MOD);
		}
	}
	
//	0 흑 1 백
	static long dfs(int pos, int color, int parent) {
		if(memo[pos][color] != 0) return memo[pos][color];
		
		long ret = 1;
		for(int i=0;i<adjList[pos].size();i++) {
			if(adjList[pos].get(i) == parent) continue;
//			color 흑, ( 백 )
//			경우의 수로 조합이 만들어짐 곱하기.
			if(color == 0) ret *= dfs(adjList[pos].get(i), 1, pos);
//			color 백, ( 흑, 백 )
			else if(color == 1) {
				ret *= (dfs(adjList[pos].get(i), 0, pos) + dfs(adjList[pos].get(i), 1, pos));
			}
			
			ret %= MOD;
		}
		return memo[pos][color] = ret;
	}
}