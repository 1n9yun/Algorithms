package ps.Brute_Force;

import java.util.Scanner;

public class swea2115_honey_gathering {
	static class Range{
		int row, lCol, rCol;

		public Range(int row, int lCol, int rCol) {
			super();
			this.row = row;
			this.lCol = lCol;
			this.rCol = rCol;
		}

		@Override
		public String toString() {
			return "Range [row=" + row + ", lCol=" + lCol + ", rCol=" + rCol + "]";
		}
	}
	static int n,m,c;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			c = sc.nextInt();
			
			map = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int ans = -1;
			for(int fRow=0;fRow<n;fRow++) {
				for(int fCol=0;fCol<n-(m-1);fCol++) {
					int sPoint = fCol + m;
					
					for(int sRow=fRow;sRow<n;sRow++) {
						for(int sCol=sPoint;sCol<n-(m-1);sCol++) {
							if(sCol + m - 1 >= n) break;
							Range bKeeper1 = new Range(fRow, fCol, fCol + m - 1);
							Range bKeeper2 = new Range(sRow, sCol, sCol + m - 1);
							
							int sum = getProfit(bKeeper1.row, bKeeper1.lCol, bKeeper1.rCol, 0, 0) + getProfit(bKeeper2.row, bKeeper2.lCol, bKeeper2.rCol, 0, 0);
							ans = Math.max(ans, sum);
						}
						sPoint = 0;
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	static int getProfit(int row, int lCol, int rCol, int profit, int total) {
		if(total > c) return -1;
		if(lCol == rCol) {
			if(total + map[row][lCol] > c) return profit;
			else return profit + map[row][lCol] * map[row][lCol];
		}
		int ret = -1;
		
		ret = Math.max(ret, getProfit(row, lCol + 1, rCol, profit + map[row][lCol]*map[row][lCol], total + map[row][lCol]));
		ret = Math.max(ret, getProfit(row, lCol + 1, rCol, profit, total));
		
		return ret;
	}
}
