package ps.Memoization;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj17071_hidenSeek{
	static class Pair{
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static int[][] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		memo = new int[2][500001];
		for(int[] sub : memo) Arrays.fill(sub, -1);
		
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(n, 0));
		
		while(!q.isEmpty()) {
			Pair front = q.poll();
			int isOdd = front.right % 2;
			if(front.left < 0 || front.left > 500000) continue;
			if(memo[isOdd][front.left] != -1) continue;
			
//			그냥 모든 경우 가게 만들어놓고 체크? 체크가 돼있다면 먼저 온놈일 테니까.
			memo[isOdd][front.left] = front.right;
			
			if(front.left - 1 >= 0)
				q.add(new Pair(front.left - 1, front.right + 1));
			if(front.left + 1 <= 500000)
				q.add(new Pair(front.left + 1, front.right + 1));
			if(front.left * 2 <= 500000)
				q.add(new Pair(front.left * 2, front.right + 1));
		}
		
		int ans = -1;
		int sum, idx = 0;
		while(true) {
			sum = k + getSeqSum(idx);
			if(sum > 500000) break;
			if(memo[idx%2][sum] != -1 && memo[idx%2][sum] <= idx) {
				ans = idx;
				break;
			}
			idx++;
		}
		
		System.out.println(ans);
	}
	static int getSeqSum(int n) {
		return n * (n + 1) / 2;
	}
}