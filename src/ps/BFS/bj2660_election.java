package ps.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
	int left;
	int right;
	public Pair(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}
}

public class bj2660_election {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int left = sc.nextInt();
		int right = sc.nextInt();
		
		boolean[][] edges = new boolean[n+1][n+1];
		while(left != -1 && right != -1) {
			edges[left][right] = true;
			edges[right][left] = true;
			
			left = sc.nextInt();
			right = sc.nextInt();
		}
		
		int[] res = new int[n+1];
		int candiPoint = 987654321;
		for(int i=1;i<=n;i++) {
			Queue<Pair> q = new LinkedList<>();
			boolean[] check = new boolean[n+1];
			q.add(new Pair(i, 0));
			check[i] = true;
			while(!q.isEmpty()) {
				Pair front = q.poll();
				
				for(int j=1; j<=n; j++) {
					if(edges[front.left][j] && !check[j]) {
						check[j] = true;
						q.add(new Pair(j, front.right + 1));
						res[i] = Math.max(res[i], front.right + 1);
					}
				}
			}
			candiPoint = Math.min(candiPoint, res[i]);
		}
		ArrayList<Integer> ans = new ArrayList<>();
		
		for(int i=1;i<=n;i++) {
			if(res[i] == candiPoint) ans.add(i);
		}
		
		System.out.println(candiPoint + " " + ans.size());
		for(int number : ans) System.out.print(number + " ");
	}		
}
