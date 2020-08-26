package ps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class swea1798_jejuplan {
	static class Pair{
		int pTime, satis;

		public Pair(int pTime, int satis) {
			super();
			this.pTime = pTime;
			this.satis = satis;
		}
	}
	static int n,m;
	static int[][] mTime;
	static char[] nInfo;
	static Pair[] pInfo;
//	static int[][][] dp;
	static Map<Long, Integer>[][][] dp;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			mTime = new int[n][n];
			nInfo = new char[n];
			pInfo = new Pair[n];
			check = new boolean[n];
//			dp = new int[m][541][n];
			dp = new HashMap[m][541][n];
			for(int i=0;i<m;i++) {
				for(int j=0;j<541;j++) {
					for(int k=0;k<n;k++) {
						dp[i][j][k] = new HashMap<>();
					}
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=i+1;j<n;j++) {
					mTime[i][j] = sc.nextInt();
					mTime[j][i] = mTime[i][j];
				}
			}
			
			int start = 0;
			for(int i=0;i<n;i++) {
				nInfo[i] = sc.next().charAt(0);
				if(nInfo[i] == 'P') {
					pInfo[i] = new Pair(sc.nextInt(), sc.nextInt());
				}else if(nInfo[i] == 'A') start = i;
			}
			
			System.out.println(back(0, 0, start, 0));
			
			for(int i=0;i<m;i++) {
				for(int j=0;j<541;j++) {
					for(int k=0;k<n;k++) {
						dp[i][j][k].clear();
					}
				}
			}
		}
	}
	
	static int back(int day, int time, int v, long check) {
//		System.out.println("\n" + Arrays.toString(check));
//		System.out.println(day + " " + time + " " + v + " " + nInfo[v]);
		if(time > 540) {
//			System.out.println("time over");
			return -987654321;
		}
		if(day == m) {
			if(nInfo[v] == 'A') return 0;
			else {
//				System.out.println("not a");
				return -987654321;
			}
		}
//		if(dp[day][time][v] != 0) {
//			System.out.println("already");
//			return dp[day][time][v];
//		}
		if(dp[day][time][v].get(check) != null) return dp[day][time][v].get(check);
//		System.out.println("first");
		
		int res = -978654321;
		for(int i=0;i<n;i++) {
			if(v == i) continue;
			
			if(nInfo[i] == 'P' && (check & (1<<i)) == 0) {
				res = Math.max(res, back(day, time + mTime[v][i] + pInfo[i].pTime, i, check | (1<<i)) + pInfo[i].satis);
			}
			if(nInfo[i] == 'H' && time + mTime[v][i] <= 540) {
				res = Math.max(res, back(day + 1, 0, i, check));
			}
			if(nInfo[i] == 'A' && day == m-1) {
				res = Math.max(res, back(day + 1, time + mTime[v][i], i, check));
			}
		}
		
		dp[day][time][v].put(check, res);
		return res;
	}
}
