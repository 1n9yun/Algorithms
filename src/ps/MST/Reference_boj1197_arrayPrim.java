package ps.MST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reference_boj1197_arrayPrim {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static int v,e;
	static ArrayList<Pair>[] adjList;
	static int[] ans;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		v = sc.nextInt();
		e = sc.nextInt();
		
		adjList = new ArrayList[v+1]; 
		ans = new int[v+1];
		check = new boolean[v+1];
		
		for(int i=1;i<=v;i++) {
			adjList[i] = new ArrayList<>();
			ans[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0;i<e;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			adjList[from].add(new Pair(to, cost));
			adjList[to].add(new Pair(from, cost));
		}
		ans[1] = 0;
		for(int i=0;i<v-1;i++) {
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			for(int j=1;j<=v;j++) {
				if(!check[j] && ans[j] < min) {
					minIdx = j;
					min = ans[j];
				}
			}
			check[minIdx] = true;
			
			for(int j=0;j<adjList[minIdx].size();j++) {
				Pair p = adjList[minIdx].get(j);
				
				if(!check[p.first] && ans[p.first] > p.second) {
					ans[p.first] = p.second;
				}
			}
		}
		int res = 0;
		for(int n : ans) res += n;
		System.out.println(res);
	}
}
