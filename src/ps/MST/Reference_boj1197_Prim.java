package ps.MST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Reference_boj1197_Prim {
	static class Pair{
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static PriorityQueue<Pair> edges;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		int e = sc.nextInt();
		
		int ans = 0;
		int cnt = 0;
		
//		Prim
		boolean[] check = new boolean[v+1];
		ArrayList<Pair>[] inputs = new ArrayList[v+1];
		
		for(int i=0;i<inputs.length;i++) inputs[i] = new ArrayList<>();
		for(int i=0;i<e;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			inputs[from].add(new Pair(to, cost));
			inputs[to].add(new Pair(from, cost));
		}
		edges = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair i1, Pair i2) {
				return i1.right - i2.right;
			}
		});
		for(int i=0;i<inputs[1].size();i++) {
			Pair temp = inputs[1].get(i);
			edges.add(new Pair(temp.left, temp.right));
		}
		check[1] = true;
		while(!edges.isEmpty()) {
			Pair front = edges.poll();
			
			if(check[front.left]) continue;
			check[front.left] = true;
			ans += front.right;
			cnt++;
			
			if(cnt == v - 1) break;
			for(int i=0;i<inputs[front.left].size();i++) {
				Pair temp = inputs[front.left].get(i);
				if(!check[temp.left]) edges.add(temp);
			}
		}
		System.out.println(ans);
	}
}