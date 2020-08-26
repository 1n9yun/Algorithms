package ps.MST;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// Kruskal
public class swea1251_hanaro {
	static int n;
	static double e;
	static class Item{
		int x, y;
		long cost;
		public Item(int x, int y, long cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	static int[][] islands;
//	static boolean[] check;
	static int[] disjoint;
	static int[] rank;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			islands = new int[n][2];
			disjoint = new int[n];
			rank = new int[n];
//			check = new boolean[n];
			for(int i=0;i<n*2;i++) {
				if(i >= n) islands[i-n][1] = sc.nextInt();
				else islands[i][0] = sc.nextInt();
			}
			e = sc.nextDouble();
			
			PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
				@Override
				public int compare(Item i1, Item i2) {
					return i1.cost <= i2.cost ? -1 : 1;
				}
			});
			
			
//			Prim
//			pq.add(new Item(0, 0));
//			
//			double ans = 0;
//			while(!pq.isEmpty()) {
//				Item front = pq.poll();
//				if(check[front.num]) continue;
//				check[front.num] = true;
//				
//				ans += (front.cost * e);
//				
//				for(int i=0;i<n;i++) {
//					if(!check[i]) {
//						long leftDiff = islands[i][0] - islands[front.num][0];
//						long rightDiff = islands[i][1] - islands[front.num][1];
//						pq.add(new Item(i, leftDiff * leftDiff + rightDiff * rightDiff));
//					}
//				}
//			}
			
			for(int i=0;i<n;i++) {
				for(int j=i+1;j<n;j++) {
					long leftDiff = islands[i][0] - islands[j][0];
					long rightDiff = islands[i][1] - islands[j][1];
					pq.add(new Item(i, j, leftDiff * leftDiff + rightDiff * rightDiff));
				}
			}
			
			double ans = 0;
			int cnt = 0;
			
			init();
			while(!pq.isEmpty()) {
				Item front = pq.poll();
				if(union(front.x, front.y)) {
					ans += e * front.cost;
					cnt++;
				}
				if(cnt == n-1) break;
			}
			
			System.out.println("#" + tc + " " + Math.round(ans));
		}
	}
	
	static void init() {
		for(int i=0;i<disjoint.length;i++) {
			disjoint[i] = i;
			rank[i] = 1;
		}
	}
	static int find(int idx) {
		if(idx == disjoint[idx]) return idx;
		
		return disjoint[idx] = find(disjoint[idx]);
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
		disjoint[p1] = p2;
		rank[p2] += rank[p1];
		return true;
	}
}
