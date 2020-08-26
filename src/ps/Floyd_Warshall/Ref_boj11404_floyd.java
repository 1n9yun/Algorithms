package ps.Floyd_Warshall;

import java.util.Arrays;
import java.util.Scanner;

public class Ref_boj11404_floyd {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] costs = new int[n+1][n+1];
		for(int[] sub : costs) Arrays.fill(sub, Integer.MAX_VALUE);
		
		for(int i=0;i<m;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			if(costs[from][to] > cost)
				costs[from][to] = cost;
		}
		
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				if(k == i) continue;
				for(int j=1;j<=n;j++) {
					if(i == j || j == k) continue;
					if(costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE) {
						costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
					}
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(costs[i][j] == Integer.MAX_VALUE) System.out.print(0 + " ");
				else System.out.print(costs[i][j] + " ");
			}
			System.out.println();
		}
	}
}
