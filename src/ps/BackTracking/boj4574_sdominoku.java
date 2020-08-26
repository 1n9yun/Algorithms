package ps.BackTracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class boj4574_sdominoku {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static boolean[][] block;
	static int[][] board;
	static int n;
	static int[][] delta = {{1,0},{0,1}};
	static Set<Integer>[] rows;
	static Set<Integer>[] cols;
	static Set<Integer>[] sub33;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = 1;
		while((n = sc.nextInt()) != 0) {
			block = new boolean[10][10];
			board = new int[9][9];
			rows = new Set[9];
			for(int i=0;i<rows.length;i++) rows[i] = new HashSet<>();
			cols = new Set[9];
			for(int i=0;i<cols.length;i++) cols[i] = new HashSet<>();
			sub33 = new Set[9];
			for(int i=0;i<sub33.length;i++) sub33[i] = new HashSet<>();
			
			for(int i=1;i<=9;i++) {
				for(int j=i+1;j<=9;j++) {
					block[i][j] = true;
					block[j][i] = true;
				}
			}
			
			for(int i=0;i<n;i++) {
				int left = sc.nextInt();
				char[] leftIdx = sc.next().toCharArray();
				int right = sc.nextInt();
				char[] rightIdx = sc.next().toCharArray();
				
				block[left][right] = false;
				block[right][left] = false;
				
				int leftR = leftIdx[0] - 'A';
				int leftC = leftIdx[1] - '1';
				int rightR = rightIdx[0] - 'A';
				int rightC = rightIdx[1] - '1';
				
				board[leftR][leftC] = left;
				rows[leftR].add(left);
				cols[leftC].add(left);
				sub33[whereIsit(leftR, leftC)].add(left);
				
				board[rightR][rightC] = right;
				rows[rightR].add(right);
				cols[rightC].add(right);
				sub33[whereIsit(rightR, rightC)].add(right);
			}
			
			for(int i=1;i<=9;i++) {
				char[] idx = sc.next().toCharArray();
				int r = idx[0]-'A';
				int c = idx[1]-'1';
				
				board[r][c] = i;
				
				rows[r].add(i);
				cols[c].add(i);
				sub33[whereIsit(r,c)].add(i);
			}
			
			solve(0, 0);
			
			System.out.println("Puzzle " + (TC++));
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	static boolean solve(int row, int col) {
		if(col >= 9) {
			row++;
			col = 0;
		}
		for(int i=row;i<9;i++) {
			for(int j=col;j<9;j++) {
				if(board[i][j] != 0) continue;
				for(int dir=0;dir<delta.length;dir++) {
					int nRow = i + delta[dir][0];
					int nCol = j + delta[dir][1];
					
					if(0<=nRow && nRow<9 && 0<=nCol && nCol<9 && board[nRow][nCol] == 0) {
						for(int bi=1;bi<=9;bi++) {
							for(int bj=1;bj<=9;bj++) {
								if(bi == bj || !block[bi][bj]) continue;
								
								int pos1 = whereIsit(i, j);
								int pos2 = whereIsit(nRow, nCol);
								
								if(!rows[i].contains(bi) && !cols[j].contains(bi) &&
										!rows[nRow].contains(bj) && !cols[nCol].contains(bj) &&
										!sub33[pos1].contains(bi) && !sub33[pos2].contains(bj)) {
									rows[i].add(bi);
									rows[nRow].add(bj);
									cols[j].add(bi);
									cols[nCol].add(bj);
									sub33[pos1].add(bi);
									sub33[pos2].add(bj);
									block[bi][bj] = false;
									block[bj][bi] = false;
									board[i][j] = bi;
									board[nRow][nCol] = bj;
									if(solve(i, j+1)) return true;
									rows[i].remove((Integer)bi);
									rows[nRow].remove((Integer)bj);
									cols[j].remove((Integer)bi);
									cols[nCol].remove((Integer)bj);
									sub33[pos1].remove((Integer)bi);
									sub33[pos2].remove((Integer)bj);
									block[bi][bj] = true;
									block[bj][bi] = true;
									board[i][j] = 0;
									board[nRow][nCol] = 0;
								}
							}
						}
					}
				}
				return false;
			}
			col = 0;
		}
		return true;
	}
	static int whereIsit(int row, int col) {
		row = row / 3;
		col = col / 3;
		
		return row * 3 + col;
	}
}
