package ps.TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1766_problemSet {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] counts = new int[n+1];
		
		ArrayList<Integer>[] adjList = new ArrayList[n+1];
		for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			counts[to]++;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				if(n1 > n2) return 1;
				else return -1;
			}
		});
		for(int i=1;i<=n;i++) {
			if(counts[i] == 0) pq.add(i);
		}
		
		while(!pq.isEmpty()) {
			int front = pq.poll();
			
			System.out.print(front + " ");
			
			for(int i=0;i<adjList[front].size();i++) {
				int next = adjList[front].get(i);
				if(--counts[next] == 0) pq.add(next);
			}
		}
	}
}
