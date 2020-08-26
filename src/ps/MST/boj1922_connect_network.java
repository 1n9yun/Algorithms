package ps.MST;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj1922_connect_network {
	static class Edge{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static int n, m;
	static int[] dSet;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		dSet = new int[n+1];
		init();
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				if(e1.cost > e2.cost) return 1;
				else return -1;
			}
		});
		
		for(int i=0;i<m;i++) {
			pq.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		int ans = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge front = pq.poll();
			
			if(union(front.from, front.to)) {
				ans += front.cost;
				cnt++;
			}
			
			if(cnt == n-1) break;
		}
		System.out.println(ans);
	}
	
	static void init() {
		for(int i=1;i<dSet.length;i++) dSet[i] = i;
	}
	
	static int find(int v) {
		if(dSet[v] == v) return v;
		
		return dSet[v] = find(dSet[v]);
	}
	static boolean union(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if(p1 == p2) return false;
		
		dSet[p2] = p1;
		return true;
	}
}
