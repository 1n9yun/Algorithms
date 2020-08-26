package ps.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class jungol2097_subway {
	static class Item{
		int to, cost;
		String path;
		public Item(int to, int cost, String path) {
			super();
			this.to = to;
			this.cost = cost;
			this.path = path;
		}
		@Override
		public String toString() {
			return "Item [to=" + to + ", cost=" + cost + ", path=" + path + "]";
		}
	}
	static int[][] adjMat;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		adjMat = new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				adjMat[i][j] = sc.nextInt();
			}
		}

		int[] ans = new int[n+1];
		Arrays.fill(ans, -1);
		
		PriorityQueue<Item> pq = new PriorityQueue<Item>((o1, o2) -> {
				if(o1.cost > o2.cost) return 1;
				else return -1;
			});

		
		pq.add(new Item(1, 0, "1"));
		
		while(!pq.isEmpty()) {
			Item front = pq.poll();
			
			if(ans[front.to] != -1) continue;
			ans[front.to] = front.cost;
			
			if(front.to == m) {
				System.out.println(front.cost);
				System.out.println(front.path.toString());
				break;
			
			}
			for(int i=1;i<=n;i++) {
				if(ans[i] == -1 && adjMat[front.to][i] != 0) {
					pq.add(new Item(i, front.cost + adjMat[front.to][i], new String(front.path + " " + i)));
				}
			}
		}
	}
}
