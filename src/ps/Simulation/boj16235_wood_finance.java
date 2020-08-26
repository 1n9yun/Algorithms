package ps.Simulation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj16235_wood_finance {
	static class Tree{
		int row, col, age;

		public Tree(int row, int col, int age) {
			super();
			this.row = row;
			this.col = col;
			this.age = age;
		}
	}
	static int[][] delta = {{-1,0,},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int[][] a = new int[n+1][n+1];
		int[][] food = new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				a[i][j] = sc.nextInt();
				food[i][j] = 5;
			}
		}
		
		Deque<Integer>[][] treeList = new ArrayDeque[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				treeList[i][j] = new ArrayDeque<>();
			}
		}
		Queue<Tree> deadTree = new LinkedList<>();
		
		for(int i=0;i<m;i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int age = sc.nextInt();

			treeList[r][c].add(age);
		}
		
		for(int i=0;i<k;i++) {
//			봄
			for(int r=1;r<=n;r++) {
				for(int c=1;c<=n;c++) {
					int size = treeList[r][c].size();
					
					while(size-- != 0){
						int age = treeList[r][c].poll();
						if(food[r][c] >= age) {
							food[r][c] -= age;
							treeList[r][c].addLast(age + 1);
						}else {
							deadTree.add(new Tree(r, c, age));
						}
					}
				}
			}
//			여름
			while(!deadTree.isEmpty()) {
				Tree tree = deadTree.poll();
				food[tree.row][tree.col] += tree.age/2;
			}
			
//			가을
			for(int r=1;r<=n;r++) {
				for(int c=1;c<=n;c++) {
					for(int age : treeList[r][c]) {
						if(age % 5 == 0) {
							for(int dir=0;dir<8;dir++) {
								int nRow = r + delta[dir][0];
								int nCol = c + delta[dir][1];
								
								if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n)
									treeList[nRow][nCol].addFirst(1);
							}
						}
					}
				}
			}
			
//			겨울
			for(int r=1;r<=n;r++) {
				for(int c=1;c<=n;c++) {
					food[r][c] += a[r][c];
				}
			}
		}
		int ans = 0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				ans += treeList[i][j].size();
			}
		}
		System.out.println(ans);
	}
	
	static void print(int[][] foodMap) {
		for(int i=0;i<foodMap.length;i++) {
			System.out.println(Arrays.toString(foodMap[i]));
		}
		System.out.println();
	}
}
