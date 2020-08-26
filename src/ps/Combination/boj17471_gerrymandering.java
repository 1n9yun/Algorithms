package ps.Combination;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj17471_gerrymandering {
	static int n;
	static int[] populations;
	static int[][] adjMat;
	static Queue<Integer> q = new LinkedList<>();
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		populations = new int[n+1];
		adjMat = new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			populations[i] = sc.nextInt();
		}
		
		for(int i=1;i<=n;i++) {
			int count = sc.nextInt();
			for(int j=0;j<count;j++) {
				int to = sc.nextInt();
				adjMat[i][to] = 1;
				adjMat[to][i] = 1;
			}
		}
		
		int[] base = new int [n+1];
		
		for(int i=1;i<=n;i++) base[i] = i;
		for(int i=1;i<=n/2;i++) combination(1, 0, base, new int[i]);
		
		System.out.println(ans == 987654321 ? -1 : ans);
	}
	
	static void combination(int idx, int cnt, int[] base, int[] result) {
		if(cnt == result.length) {
//			solve
			int[] other = getOther(base, result);
			if(isAllConnected(result) && isAllConnected(other)) {
				int resSum = 0, otherSum = 0;
				
				for(int i=0;i<result.length;i++) resSum += populations[result[i]];
				for(int i=0;i<other.length;i++) otherSum += populations[other[i]];
				
				ans = Math.min(ans, Math.abs(resSum - otherSum));
			}
			return;
		}
		
		for(int i=idx;i<base.length;i++) {
			result[cnt] = base[i];
			combination(i+1, cnt+1, base, result);
		}
	}
	
	static int[] getOther(int[] base, int[] result) {
		int[] other = new int[n - result.length];
		int resIdx = 0;
		int otherIdx = 0;
		for(int i=1;i<=n;i++) {
			if(resIdx < result.length && result[resIdx] == base[i]) {
				resIdx++;
			}else {
				other[otherIdx++] = base[i];
			}
		}
		
		return other;
	}
	
	static boolean isAllConnected(int[] target) {
		int cnt = 1;
		boolean[] check = new boolean[target.length];
		check[0] = true;
		q.add(target[0]);
		
		while(!q.isEmpty()) {
			int front = q.poll();
			for(int i=0;i<target.length;i++) {
				if(adjMat[front][target[i]] == 1 && !check[i]) {
					cnt++;
					check[i] = true;
					q.add(target[i]);
				}
			}
		}
		
		if(cnt == target.length) return true;
		else return false;
	}
}
