package ps.BackTracking;

import java.util.Scanner;

//10
//1 1 0 1 1 1 1 0 1 1
//0 1 0 0 0 0 1 0 0 0
//1 0 1 0 1 1 0 1 0 1
//1 0 0 0 0 1 0 0 0 0
//1 0 1 1 1 1 0 1 1 1
//1 1 0 1 1 1 1 0 1 1
//0 1 0 0 0 0 1 0 0 0
//1 0 1 0 1 1 0 1 0 1
//1 0 0 0 0 1 0 0 0 0
//1 0 1 1 1 1 0 1 1 1

public class boj1799_bishop{
	static int n;
	static int[][] map;
	static int[] ans;
	static int[][] partialSum;
	static int[][] navigation;
	static int[][] colors;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		colors = new int[n][n];
		ans = new int[2];
		ans[0] = -1; ans[1] = -1;
		int temp = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
				colors[i][j] = temp;
				if(n % 2 != 0 || j != n-1) temp = 1 - temp;
			}
		}
		
		partialSum = new int[n + 1][n + 1];
		navigation = new int[n][n];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				partialSum[i][j] = partialSum[i-1][j] + partialSum[i][j-1] - partialSum[i-1][j-1] + map[i-1][j-1];
			}
		}
//		System.out.println("Start");
		back(0, 0, 0, 0);
//		System.out.println("Start");
		back(0, 0, 0, 1);
		System.out.println(ans[0] + ans[1]);
//		System.out.println(cnt);
	}
	
	static int cnt = 0;
	static void back(int row, int col, int count, int color) {
//		System.out.println(row + " " + col + " " + count + " " + color);
		ans[color] = Math.max(ans[color], count);
//		System.out.println(row + " " + col + " " + count);
//		print(row, col);
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		for(int i=row;i<n;i++) {
			for(int j=col;j<n;j++) {
				if(getPartialSum(i, j, i, n-1) + getPartialSum(i+1,0,n-1,n-1) + count <= ans[color]) return;
//				cnt++;
				
				boolean flag = false;
				if(i != 0) {
//					System.out.println("i is not zero");
					if(j - 1 >= 0 && j + 1 < n) {
//						System.out.println("both of");
						if(0<navigation[i-1][j-1] && navigation[i-1][j-1] <= 2 && navigation[i-1][j+1] >= 2) {
							flag = true;
							navigation[i][j] = 2;
						}else if(0<navigation[i-1][j-1] && navigation[i-1][j-1] <= 2) {
//							System.out.println("Left");
							flag = true;
							navigation[i][j] = 1;
						}else if(navigation[i-1][j+1] >= 2) {
//							System.out.println("Right!");
							flag = true;
							navigation[i][j] = 3;
						}
					}else if(j - 1 >= 0 && 0 < navigation[i-1][j-1] && navigation[i-1][j-1] <= 2) {
//						System.out.println("only Left");
						flag = true;
						navigation[i][j] = 1;
					}else if(j + 1 < n && navigation[i-1][j+1] >= 2) {
//						System.out.println("only right");
						flag = true;
						navigation[i][j] = 3;
					}
				}
				if(!flag && map[i][j] == 1) {
					navigation[i][j] = 2;
					if(colors[i][j] == color) {
//						System.out.println(i + " " + j + " Tok");
						back(i, j+1, count+1, color);
						navigation[i][j] = 0;
					}
				}else if(!flag) navigation[i][j] = 0;
			}
			col = 0;
		}
	}
	static int getPartialSum(int lRow, int lCol, int rRow, int rCol) {
		lRow++; lCol++; rRow++; rCol++;
		return partialSum[rRow][rCol] - partialSum[rRow][lCol-1] - partialSum[lRow-1][rCol] + partialSum[lRow-1][lCol-1];
	}
	static void print(int row, int col) {
		boolean flag = false;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i == row && j == col) flag = true;
				if(flag) System.out.print(0 + " ");
				else System.out.print(navigation[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

//public class boj1799_bishop {
//	static MyStack[] cols;
//	static int n;
//	static int[][] map;
//	static int ans = -1;
//	static class MyStack{
//		int[] get;
//		int top;
//		
//		public MyStack(int length) {
//			top = -1;
//			get = new int[length];
//		}
//		public void add(int data) {
//			get[++top] = data;
//		}
//		public void remove() {
//			top--;
//		}
//		public int size() {
//			return top + 1;
//		}
//	}
//	static int[][] partialSum;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		n = sc.nextInt();
//		map = new int[n][n];
//		cols = new MyStack[n];
//		
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				map[i][j] = sc.nextInt();
//			}
//			cols[i] = new MyStack(10);
//		}
//		
//		partialSum = new int[n + 1][n + 1];
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				partialSum[i][j] = partialSum[i-1][j] + partialSum[i][j-1] - partialSum[i-1][j-1] + map[i-1][j-1];
//			}
//		}
//		back(0, 0, 0);
//		System.out.println(ans);
//		System.out.println(cnt);
//	}
//	static void print() {
//		for(int i=0;i<n;i++) {
//			int colsIdx = 0;
//			for(int j=0;j<n;j++) {
////				if(map[i][j] == 0) {
////					System.out.print(2 + " ");
////					continue;
////				}
//				if(colsIdx < cols[i].size() && cols[i].get[colsIdx] == j) {
//					System.out.print(1 + " ");
//					colsIdx++;
//				}else {
//					System.out.print(0 + " ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//	
//	static int cnt = 0;
//	static void back(int row, int col, int count) {
////		System.out.println(row + " " + col + " " + count);
////		System.out.println(cols[row].toString());
////		print();
////		try {
////			Thread.sleep(10);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		for(int i=row;i<n;i++) {
//			for(int j=col;j<n;j++) {
//				cnt++;
//				if(getPartialSum(i, j, i, n-1) + getPartialSum(i+1,0,n-1,n-1) + count < ans) return;
//				if(map[i][j] == 1 && isPossible(i, j)) {
//					cols[i].add(j);
//					back(i, j+1, count+1);
//					cols[i].remove();
//				}
//			}
//			col = 0;
//		}
//		ans = Math.max(ans, count);
//	}
//	
//	static boolean isPossible(int row, int col) {
//		for(int i=0;i<row;i++) {
//			for(int j=0;j<cols[i].size();j++) {
//				if(Math.abs(i - row) == Math.abs(cols[i].get[j] - col)) return false;
//			}
//		}
//		return true;
//	}
//	
//	static int getPartialSum(int lRow, int lCol, int rRow, int rCol) {
//		lRow++; lCol++; rRow++; rCol++;
//		return partialSum[rRow][rCol] - partialSum[rRow][lCol-1] - partialSum[lRow-1][rCol] + partialSum[lRow-1][lCol-1];
//	}
//}
