package ps.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj1916_minimumCost {
	static int n,m;
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		ArrayList<Pair>[] adjList = new ArrayList[n+1];
		for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();
		
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair e1, Pair e2) {
				if(e1.second > e2.second) return 1;
				else return -1;
			}
		});
		
		for(int i=0;i<m;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			adjList[from].add(new Pair(to, cost));
		}
		
		int start = sc.nextInt();
		int goal = sc.nextInt();
		
		int[] ans = new int[n+1];
		Arrays.fill(ans, 987654321);
		ans[start] = 0;
		
		for(int i=0;i<adjList[start].size();i++) pq.add(adjList[start].get(i));
		
		while(!pq.isEmpty()) {
			Pair front = pq.poll();
			
			if(ans[front.first] != 987654321) continue;
			ans[front.first] = front.second;
			if(front.first == goal) break;
			
			for(int i=0;i<adjList[front.first].size();i++) {
				Pair next = adjList[front.first].get(i);
				pq.add(new Pair(next.first, front.second + next.second));
			}
		}
		
		System.out.println(ans[goal]);
	}
}
