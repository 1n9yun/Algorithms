package ps.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Reference_boj1753_shortestPath {
	static int[] ans;
	static int v, e;
	static int start;
	static ArrayList<Pair>[] adjList;
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		ans = new int[v+1];
		Arrays.fill(ans, -1);
		
		adjList = new ArrayList[v+1];
		for(int i=1;i<adjList.length;i++)adjList[i] = new ArrayList<>(); 
		for(int i=0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Pair(to, cost));
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				if(p1.second > p2.second) return 1;
				else return -1;
			}
		});
				
		pq.add(new Pair(start, 0));
		
//		프림인데 각 정점에 최단 거리 저장해두는 것
		while(!pq.isEmpty()) {
			Pair front = pq.poll();
			
			if(ans[front.first] != -1) continue;
			ans[front.first] = front.second;
			
			for(int i=0;i<adjList[front.first].size();i++) {
				Pair nV = adjList[front.first].get(i);
				if(ans[nV.first] == -1) {
					pq.add(new Pair(nV.first, front.second + nV.second));
				}
			}
		}
		
		for(int i=1;i<ans.length;i++) {
			if(ans[i] == -1) System.out.println("INF");
			else System.out.println(ans[i]);
			
		}
	}
}
