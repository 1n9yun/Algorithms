package ps.TopologySort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj3665_last_ranking {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int[] ti = new int[n];
			boolean[][] adjMat = new boolean[n+1][n+1];
			int[] counts = new int[n+1];
			for(int i=0;i<n;i++) {
				ti[i] = sc.nextInt();
			}
			for(int i=0;i<n;i++) {
				for(int j=i+1;j<n;j++) {
					adjMat[ti[i]][ti[j]] = true;
					counts[ti[j]]++;
				}
			}
			
			int m = sc.nextInt();
			for(int i=0;i<m;i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				if(adjMat[to][from]) {
					int temp = from;
					from = to;
					to = temp;
				}
				adjMat[from][to] = false;
				adjMat[to][from] = true;
				counts[to]--;
				counts[from]++;
			}
			
			Queue<Integer> q = new LinkedList<>();
			for(int i=1;i<=n;i++) if(counts[i] == 0) q.add(i);
			
			int count = 0;
			int[] ans = new int[n];
			boolean multiple = false;
			while(!q.isEmpty()) {
				if(q.size() > 1) {
					multiple = true;
					break;
				}
				int front = q.poll();
				
				ans[count++] = front;
				for(int i=1;i<=n;i++) {
					if(adjMat[front][i]) {
						if(--counts[i] == 0) {
							q.add(i);
						}
					}
				}
			}
			if(multiple) System.out.print("?");
			else if(count != n) System.out.print("IMPOSSIBLE");
			else {
				for(int i=0;i<count;i++) {
					System.out.print(ans[i] + " ");
				}
			}
			System.out.println();
		}
	}
}
