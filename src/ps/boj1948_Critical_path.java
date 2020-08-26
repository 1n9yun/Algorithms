package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1948_Critical_path {
	static int n,m;
	static int start, goal;
	static ArrayList<Pair>[] adjList;
	static int[] counts;
	static int[] maxDists;
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		counts = new int[n+1];
		maxDists = new int[n+1];
		adjList = new ArrayList[n+1];
		for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();
		for(int i=0;i<m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			counts[to]++;
			adjList[from].add(new Pair(to, cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());

		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(start, 0));
		
		while(!q.isEmpty()) {
			Pair front = q.poll();
			
			for(int i=0;i<adjList[front.first].size();i++) {
				Pair next = adjList[front.first].get(i);
				
				maxDists[next.first] = Math.max(maxDists[next.first], maxDists[front.first] + next.second);
				System.out.println(Arrays.toString(maxDists));
				if(--counts[next.first] == 0) 
					q.add(new Pair(next.first, front.second + next.second));
			}
		}
		
		
	}
}
