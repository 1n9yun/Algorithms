package ps.MST;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Reference_boj1197_Kruskal {
	static class Item{
		int left, right;
		int cost;
		public Item(int left, int right, int cost) {
			super();
			this.left = left;
			this.right = right;
			this.cost = cost;
		}
	}
	static PriorityQueue<Item> edges;
	static int[] disjoint;
	static int[] rank;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		int e = sc.nextInt();
		
		disjoint = new int[v+1];
		rank = new int[v+1];
		init();
		
		edges = new PriorityQueue<>(new Comparator<Item>() {
			@Override
			public int compare(Item i1, Item i2) {
				return i1.cost - i2.cost;
			}
		});
		
		int ans = 0;
		int cnt = 0;
		
//		Kruskal
		
		for(int i=0;i<e;i++) {
			edges.add(new Item(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		while(!edges.isEmpty()) {
			Item front = edges.poll();
			if(union(front.left, front.right)) {
				ans += front.cost;
				cnt++;
			}
			if(cnt == v-1) break;
		}	
		System.out.println(ans);
	}
	
	static void init() {
		for(int i=1;i<disjoint.length;i++) {
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
