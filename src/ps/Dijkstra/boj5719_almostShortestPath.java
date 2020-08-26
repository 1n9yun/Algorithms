package ps.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj5719_almostShortestPath {
	static class Item{
		int from, to, cost;

		public Item(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static int n, m, start, goal;
	static int[][] adjMat;
	static ArrayList<Integer>[] prevVertex;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		while(!(n == 0 && m == 0)) {
			adjMat = new int[n][n];
			prevVertex = new ArrayList[n];
			for(int i=0;i<n;i++) prevVertex[i] = new ArrayList<>();
			
			start = sc.nextInt();
			goal = sc.nextInt();
			
			for(int i=0;i<m;i++) {
				adjMat[sc.nextInt()][sc.nextInt()] = sc.nextInt();
			}
			
			int[] ans = new int[n];
			Arrays.fill(ans, -1);
			
			PriorityQueue<Item> pq = new PriorityQueue<Item>((o1, o2) -> {
				if(o1.cost > o2.cost) return 1;
				else return -1;
			});
			
			pq.add(new Item(start, start, 0));
			
			while(!pq.isEmpty()){
				Item front = pq.poll();
				if(ans[front.to] == front.cost || ans[front.to] == -1) {
					prevVertex[front.to].add(front.from);
					if(ans[front.to] == front.cost) continue;
					else ans[front.to] = front.cost;
				}else continue;
				
				for(int i=0;i<n;i++) {
					if(adjMat[front.to][i] != 0) {
						pq.add(new Item(front.to, i, front.cost + adjMat[front.to][i]));
					}
				}
			}
			
			deleteShortestPath(goal);
			
			Arrays.fill(ans, -1);
			
			pq.add(new Item(start, start, 0));
			while(!pq.isEmpty()) {
				Item front = pq.poll();
				
				if(ans[front.to] != -1) continue;
				ans[front.to] = front.cost;
				
				for(int i=0;i<n;i++) {
					if(adjMat[front.to][i] != 0) {
						pq.add(new Item(front.to, i, front.cost + adjMat[front.to][i]));
					}
				}
			}
			
			System.out.println(ans[goal]);
			
			n = sc.nextInt();
			m = sc.nextInt();
		}
	}
	
	static void deleteShortestPath(int v) {
		if(v == start) return;
		for(int i=0;i<prevVertex[v].size();i++) {
			int pV = prevVertex[v].get(i);
			adjMat[pV][v] = 0;
			prevVertex[v].remove(i--);
			deleteShortestPath(pV);
		}
	}
}

