package ps.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2406_stable_network {
	static int[] computerSet;
	static int[] rank;
	static class Edge{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
//		1번 컴퓨터의 간선들을 제외하고 MST??
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		computerSet = new int[n+1];
		rank = new int[n+1];
		for(int i=1;i<=n;i++) {
			computerSet[i] = i;
			rank[i] = 1;
		}
		
		int[][] costs = new int[n+1][n+1];
		int connectedCount = 1;
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(union(from, to)) connectedCount++;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> { return o1.cost - o2.cost; }); 
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i != 1 && i < j) {
					pq.add(new Edge(i, j, cost));
				}
			}
		}
		
		ArrayList<Edge> ansList = new ArrayList<>();
		int ansCost = 0;
		
		while(!pq.isEmpty()) {
			Edge front = pq.poll();
			
			if(connectedCount == n - 1) break;
			
			if(union(front.from, front.to)) {
				connectedCount++;
				ansCost += front.cost;
				ansList.add(front);
			}
		}
		
		System.out.println(ansCost + " " + ansList.size());
		for(Edge e : ansList) System.out.println(e.from + " " + e.to);
	}
	
	static int find(int idx) {
		if(computerSet[idx] == idx) return idx;
		return computerSet[idx] = find(computerSet[idx]);
	}
	
	static boolean union(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if(p1 == p2) return false;
		
		if(rank[p1] < rank[p2]) {
			int temp = p1;
			p1 = p2;
			p2 = temp;
		}
		
		computerSet[p2] = p1;
		rank[p1] += rank[p2];
		
		return true;
	}
}
