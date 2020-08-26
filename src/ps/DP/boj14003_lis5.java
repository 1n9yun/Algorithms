package ps.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class boj14003_lis5 {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
//	Pair 배열로 바꿔보고 버퍼드리더라이터 써봐
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
//		int n = 1000000;
		
		int[] arr = new int[n];
		
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
//			arr[i] = (int) Math.floor(Math.random() * 10000000);
		}
		
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = Integer.MIN_VALUE;
		
		ArrayList<Pair> index = new ArrayList<>();
		
		int lisCnt = 0;
		for(int i=0;i<n;i++) {
			if(arr[i] > dp[lisCnt]) {
				dp[++lisCnt] = arr[i];
				index.add(new Pair(lisCnt, arr[i]));
			}else {
				int left = 1;
				int right = lisCnt;
				
				while(left < right){
					int mid = (left + right) / 2;
					
					if(arr[i] > dp[mid]) {
						left = mid + 1;
					}else if(arr[i] <= dp[mid]) {
						right = mid;
					}
				}
				dp[right] = arr[i];
				index.add(new Pair(right, arr[i]));
			}
		}
		System.out.println(lisCnt);
		
		Stack<Integer> ansSeq = new Stack<>();
		for(int i=index.size()-1;i>=0;i--) {
			Pair p = index.get(i);
			if(p.first == lisCnt) {
				lisCnt--;
				ansSeq.push(p.second);
			}
		}
		
		while(!ansSeq.isEmpty()) {
			System.out.print(ansSeq.pop() + " ");
		}
	}
}
