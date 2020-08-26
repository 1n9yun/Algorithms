package ps.규칙찾기;

import java.util.Scanner;

public class swea5604_IntervalSum {
	static int[] partialSum;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		partialSum = new int[10];
		for(int i=1;i<10;i++) partialSum[i] = partialSum[i-1] + i;
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			long left = sc.nextLong();
			long right = sc.nextLong();
			
			System.out.println("#" + tc + " " + (getIntervalSum(Long.toString(right)) - getIntervalSum(Long.toString(left == 0 ? 0 : left - 1))));
		}
	}
	
	static long getIntervalSum(String n) {
		long ret = 0;
		
		for(int i=0;i<n.length();i++) {
			long multi = (long) Math.pow(10, n.length() - 1 - i);
			int nowCount = (n.charAt(i) - '0');
			if(nowCount == 0) continue;
			
			ret += partialSum[nowCount - 1] * multi;
			
			for(int j=i+1;j<n.length(); j++) {
				ret += partialSum[9] * multi / 10 * nowCount;
			}
			ret += (nowCount) * (Long.parseLong(n) % multi + 1);
		}
		return ret;
	}
}
