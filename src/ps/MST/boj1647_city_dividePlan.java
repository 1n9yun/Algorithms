package ps.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class boj1647_city_dividePlan {
	static class Edge{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static int[] dSet;
	static int[] rank;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dSet = new int[n+1];
		rank = new int[n+1];
		init();
		PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				if(e1.cost > e2.cost) return 1;
				else return -1;
			}
		});
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int maxCost = -1;
		int sum = 0;
		int cnt = 0;
		while(!edges.isEmpty()) {
			Edge front = edges.poll();
			
			if(union(front.from, front.to)) {
				if(maxCost < front.cost) maxCost = front.cost;
				
				sum += front.cost;
				cnt++;
			}
			if(cnt == n-1) break;
		}
		System.out.println(sum - maxCost);
	}
	
	static void init() {
		for(int i=0;i<=n;i++) {
			dSet[i] = i;
			rank[i] = 1;
		}
	}
	
	static int find(int idx) {
		if(dSet[idx] == idx) return idx;
		return dSet[idx] = find(dSet[idx]);
	}
	
	static boolean union(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if(p1 == p2) return false;
		if(rank[p1] > rank[p2]) {
			int temp = p1;
			p1 = p2;
			p2 = temp;
		}
		dSet[p1] = p2;
		rank[p2] += rank[p1];
		return true;
	}
}
