//package ps.Bellman_Ford;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Reference_boj11657_time_machine {
//	static int n, m;
//	static class Edge{
//		int from, to, cost;
//
//		public Edge(int from, int to, int cost) {
//			super();
//			this.from = from;
//			this.to = to;
//			this.cost = cost;
//		}
//	}
//	static Edge[] edges;
//	static int[] ans;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		n = sc.nextInt();
//		m = sc.nextInt();
//		ans = new int[n+1];
//		edges = new Edge[m];
//		
//		Arrays.fill(ans, Integer.MAX_VALUE);
//		ans[1] = 0;
//		
//		for(int i=0;i<m;i++) {
//			int from = sc.nextInt();
//			int to = sc.nextInt();
//			int cost = sc.nextInt();
//			
//			edges[i] = new Edge(from, to, cost);
//		}
//		
////		최소 v - 1번 하면 모든 정점을 연결해 볼 수 있으니까 v - 1번 
////		v - 1번 매번 모든 간선 확인
////		계속 갱신
//		for(int i=1;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				if(ans[edges[j].from] != Integer.MAX_VALUE) {
//					ans[edges[j].to] = Math.min(ans[edges[j].to], ans[edges[j].from] + edges[j].cost);
//				}
//			}
//		}
////		v번 째 한 번 더 돌려서 그 결과로 값이 달라지면 음의 사이클이 있다??
//		int[] cycleCheck = ans.clone();
//		for(int i=1;i<=n;i++) {
//			for(int j=0;j<m;j++) {
//				if(cycleCheck[edges[j].from] != Integer.MAX_VALUE) {
//					if(cycleCheck[edges[j].to] > cycleCheck[edges[j].from] + edges[j].cost) {
//						System.out.println(-1);
//						return;
//					}
//				}
//			}
//		}
//		
//		for(int i=2;i<=n;i++) {
//			System.out.println(ans[i] == Integer.MAX_VALUE ? -1 : ans[i]);
//		}
//	}
//}

package ps.Bellman_Ford;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reference_boj11657_time_machine {
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
		
		int[] ans = new int[n+1];
		Arrays.fill(ans, Integer.MAX_VALUE);
//		출발점
		ans[1] = 0;
		
		for(int t=1;t<=n;t++) {
			for(int i=1;i<=n;i++) {
				if(ans[i] == Integer.MAX_VALUE) continue;
				for(int j=0;j<adjList[i].size();j++) {
					Edge e = adjList[i].get(j);
					ans[e.to] = Math.min(ans[e.to], ans[i] + e.cost);
				}
			}
		}
		
//		싸이클 체크
		int[] cycleCheck = ans.clone();
		for(int i=1;i<=n;i++) {
			if(cycleCheck[i] == Integer.MAX_VALUE) continue;
			for(int j=0;j<adjList[i].size();j++) {
				Edge e = adjList[i].get(j);
				if(cycleCheck[e.to] > cycleCheck[i] + e.cost) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		for(int i=2;i<=n;i++) {
			if(ans[i] == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(ans[i]);
		}
	}
}
