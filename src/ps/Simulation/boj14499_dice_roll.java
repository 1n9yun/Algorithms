package ps.Simulation;

import java.util.Scanner;

public class boj14499_dice_roll {
	static int n,m;
	static int[][] map;
	static int[][] delta = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
	static int[][] dice = new int[4][3];
	static int[] downSide = {3, 1};
	static int[] upSide = {1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		int[] pos = {sc.nextInt(), sc.nextInt()};
		int[] commands = new int[sc.nextInt()];
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0;i<commands.length;i++) {
			commands[i] = sc.nextInt();
		}
		
		for(int i=0;i<commands.length;i++) {
			int nRow = pos[0] + delta[commands[i]][0];
			int nCol = pos[1] + delta[commands[i]][1];
			if(0<=nRow && nRow<n && 0<=nCol && nCol<m) {
				pos[0] = nRow;
				pos[1] = nCol;
				
				diceRoll(commands[i]);
				
				if(map[nRow][nCol] == 0) {
					map[nRow][nCol] = dice[downSide[0]][downSide[1]];
				}else {
					dice[downSide[0]][downSide[1]] = map[nRow][nCol];
					map[nRow][nCol] = 0;
				}
				System.out.println(dice[upSide[0]][upSide[1]]);
			}
		}
	}
	
	static void print() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(dice[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void diceRoll(int dir) {
		if(dir == 1 || dir == 2) {
			int weight = dir == 1 ? 1 : -1;
			int idx = dir == 1 ? 0 : 2;
			int save = dice[1][idx];
			idx += weight;
			
			while(idx >= 0 && idx <= 2) {
				int temp = dice[1][idx];
				dice[1][idx] = save;
				save = temp;
				idx += weight;
			}
			idx = 1;
			int temp = dice[3][idx];
			dice[3][idx] = save;
			save = temp;
			dice[1][idx + (weight * -1)] = save;
		}else {
			int weight = dir == 3 ? -1 : 1;
			int idx = dir == 3 ? 3 : 0;
			int save = dice[idx][1];
			idx += weight;
			
			while(idx >= 0 && idx <= 3) {
				int temp = dice[idx][1];
				dice[idx][1] = save;
				save = temp;
				idx += weight;
			}
			dice[idx == 4 ? 0 : 3][1] = save;
		}
	}
}
