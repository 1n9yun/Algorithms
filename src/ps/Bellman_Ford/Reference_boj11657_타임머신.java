package ps.Bellman_Ford;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reference_boj11657_타임머신 {
	static class Edge{
		int to, cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
//		인접 리스트 선언
		ArrayList<Edge>[] adjList = new ArrayList[n+1];
		for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();
		
//		인접 리스트 만들기
//		어차피 모든 간선을 검사하므로 간선의 리스트만 있어도 됨.
		for(int i=0;i<m;i++) {
			adjList[sc.nextInt()].add(new Edge(sc.nextInt(), sc.nextInt()));
		}
		
		long[] ans = new long[n+1];
		Arrays.fill(ans, Long.MAX_VALUE);
//		출발점
		ans[1] = 0;
		
		for(int t=1;t<=n;t++) {
			for(int i=1;i<=n;i++) {
				if(ans[i] == Long.MAX_VALUE) continue;
				for(int j=0;j<adjList[i].size();j++) {
					Edge e = adjList[i].get(j);

					if(ans[e.to] > ans[i] + e.cost){
						ans[e.to] = Math.min(ans[e.to], ans[i] + e.cost);
						if(t == n){
							System.out.println(-1);
							return;
						}
					}
				}
			}
		}
		
		for(int i=2;i<=n;i++) {
			if(ans[i] == Long.MAX_VALUE) System.out.println(-1);
			else System.out.println(ans[i]);
		}
	}
}
