package ps.Dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj1238_party {
	static int n,m,x;
	static int[][] adjMat;
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
		x = sc.nextInt();
		
		adjMat = new int[n+1][n+1];
		
		for(int i=0;i<m;i++) {
			adjMat[sc.nextInt()][sc.nextInt()] = sc.nextInt();
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				if(p1.second > p2.second) return 1;
				else return -1;
			}
		});
		
		int[] fromX = new int[n+1];
		Arrays.fill(fromX, -1);
		fromX[x] = 0;
		
		for(int i=1;i<=n;i++) {
			if(adjMat[x][i] == 0) continue;
			pq.add(new Pair(i, adjMat[x][i]));
		}
		
		while(!pq.isEmpty()) {
			Pair front = pq.poll();
			if(fromX[front.first] != -1) continue;
			fromX[front.first] = front.second;
			
			for(int i=1;i<=n;i++){
				if(adjMat[front.first][i] != 0) {
					pq.add(new Pair(i, front.second + adjMat[front.first][i]));
				}
			}
		}
		pq.clear();
		
		int[] toX = new int[n+1];
		int ans = -1;
		
		for(int s=1;s<=n;s++) {
			Arrays.fill(toX, -1);
			toX[s] = 0;
			for(int i=1;i<=n;i++) {
				if(adjMat[s][i] != 0) pq.add(new Pair(i, adjMat[s][i]));
			}
			
			while(!pq.isEmpty()) {
				Pair front = pq.poll();
				
				if(toX[front.first] != -1) continue;
				toX[front.first] = front.second;
				if(front.first == x) break;
				
				for(int i=1;i<=n;i++) {
					if(adjMat[front.first][i] != 0) {
						pq.add(new Pair(i, front.second + adjMat[front.first][i]));
					}
				}
			}
			ans = Math.max(ans, toX[x] + fromX[s]);
			pq.clear();
		}
		
		System.out.println(ans);
	}
}