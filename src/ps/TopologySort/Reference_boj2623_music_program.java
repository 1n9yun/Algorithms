package ps.TopologySort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Reference_boj2623_music_program {
	static int n,m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		ArrayList<Integer>[] adjList = new ArrayList[n+1];
		int[] check = new int[n+1];
		
		for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();
		
		for(int i=0;i<m;i++) {
			int t = sc.nextInt();
			int start = sc.nextInt();
			for(int j=1;j<t;j++){
				int next = sc.nextInt();
				check[next]++;
				adjList[start].add(next);
				start = next;
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		int[] result = new int[n+1];
		for(int i=1;i<=n;i++) {
//			진입 차수가 0
			if(check[i] == 0) q.add(i);
		}
		
		for(int i=1;i<=n;i++) {
			if(q.isEmpty()) {
//				사이클 발생. 위상정렬 불가능
				System.out.println(0);
				return;
			}
			
			int front = q.poll();
			result[i] = front;
			
			for(int j=0;j<adjList[front].size();j++) {
				int next = adjList[front].get(j);
				if(--check[next] == 0) q.add(next);
			}
		}
		
		for(int i=1;i<=n;i++) System.out.println(result[i]);
	}
}
