package ps.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2211_network_restore{
	static class Pair{
		int from, to, cost;

		public Pair(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] adjMat = new int[n+1][n+1];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjMat[from][to] = cost;
			adjMat[to][from] = cost;
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>((o1, o2) -> {
			if(o1.cost > o2.cost) return 1;
			else return -1;
		});
		
		System.out.println(n - 1);
		
		boolean[] check = new boolean[n+1];
	
		pq.add(new Pair(1, 1, 0));
		
		while(!pq.isEmpty()) {
			Pair front = pq.poll();
			
			if(check[front.to]) continue;
			
			if(!(front.from == 1 && front.to == 1 && front.cost == 0))
				System.out.println(front.from + " " + front.to);
			check[front.to] = true;
			
			for(int i=1;i<=n;i++) {
				if(!check[i] && adjMat[front.to][i] != 0) {
					pq.add(new Pair(front.to, i, front.cost + adjMat[front.to][i]));
				}
			}
		}
	}
}




// MST로 푸는것이 아님.
//public class boj2211_network_restore {
//	static class Item{
//		int from, to, cost;
//
//		public Item(int from, int to, int cost) {
//			super();
//			this.from = from;
//			this.to = to;
//			this.cost = cost;
//		}
//	}
//	static int[] vSet;
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		int n = Integer.parseInt(st.nextToken());
//		int m = Integer.parseInt(st.nextToken());
//		vSet = new int[n+1];
//		for(int i=1;i<=n;i++) vSet[i] = i;
//		
//		PriorityQueue<Item> pq = new PriorityQueue<Item>((o1, o2) -> {
//			if(o1.cost > o2.cost) return 1;
//			else return -1;
//		});
//		
//		for(int i=0;i<m;i++) {
//			st = new StringTokenizer(br.readLine());
//			
//			int from = Integer.parseInt(st.nextToken());
//			int to = Integer.parseInt(st.nextToken());
//			int cost = Integer.parseInt(st.nextToken());
//			
//			pq.add(new Item(from, to, cost));
//		}
//		
//		int cnt = 0;
//		
//		System.out.println(n - 1);
//		while(!pq.isEmpty()){
//			Item front = pq.poll();
//			
//			if(cnt == n-1) break;
//			if(union(front.from, front.to)) {
//				cnt++;
//				System.out.println(front.from + " " + front.to);
//			}
//		}
//	}
//	
//	static int find(int idx) {
//		if(vSet[idx] == idx) return idx;
//		return vSet[idx] = find(vSet[idx]);
//	}
//	
//	static boolean union(int p1, int p2) {
//		p1 = find(p1);
//		p2 = find(p2);
//		
//		if(p1 == p2) return false;
//		
//		vSet[p1] = p2;
//		return true;
//	}
//}
