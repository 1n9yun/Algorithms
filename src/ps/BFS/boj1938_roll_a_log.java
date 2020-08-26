package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1938_roll_a_log {
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static class Item{
		Pair pos;
		int state;
		int cnt;
		public Item(Pair pos, int state, int cnt) {
			super();
			this.pos = pos;
			this.state = state;
			this.cnt = cnt;
		}
	}
	static boolean[][][] check;
	static char[][] map;
	static int n;
	static Pair[] log = new Pair[3];
	static Pair[] dest = new Pair[3];
//	dir, udlr, delta
	static int[][][][] delta = {
			{
				{{-1,-1},{-1,0},{-1,1}},
				{{1,-1},{1,0},{1,1}},
				{{0,-2},{0,-1},{0,0}},
				{{0,0},{0,1},{0,2}}
			},
			{
				{{-2,0},{-1,0},{0,0}},
				{{0,0},{1,0},{2,0}},
				{{-1,-1},{0,-1},{1,-1}},
				{{-1,1},{0,1},{1,1}}
			}
	};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new char[n][n];
//		중심점, 가로와 세로
		check = new boolean[n][n][2];
		
		int logSize = 0;
		int destSize = 0;
		for(int i=0;i<n;i++) {
			String input = sc.next();
			for(int j=0;j<n;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'B') {
					log[logSize++] = new Pair(i,j);
					map[i][j] = '0';
				}
				else if(map[i][j] == 'E') {
					dest[destSize++] = new Pair(i,j);
					map[i][j] = '0';
				}
			}
		}
		int sState = 0;
		if(log[0].row == log[1].row) sState = 0;
		else if(log[0].col == log[1].col) sState = 1;
		int dState = 0;
		if(dest[0].row == dest[1].row) dState = 0;
		else if(dest[0].col == dest[1].col) dState = 1;
		
		Queue<Item> q = new LinkedList<>();
		q.add(new Item(log[1], sState, 0));
		check[log[1].row][log[1].col][sState] = true;
		
		while(!q.isEmpty()) {
			Item front = q.poll();
//			System.out.println(front.pos.row + " " + front.pos.col + " " + front.state);
			
			if(front.state == dState && front.pos.row == dest[1].row && front.pos.col == dest[1].col) {
				System.out.println(front.cnt);
				return;
			}
			for(int dir=0;dir<4;dir++) {
				boolean usable = true;
				for(int idx=0;idx<3;idx++) {
					int nRow = front.pos.row + delta[front.state][dir][idx][0];
					int nCol = front.pos.col + delta[front.state][dir][idx][1];
//					System.out.println(front.state + " " + dir + ", " + nRow + " " + nCol);
					if(isOut(nRow, nCol) || map[nRow][nCol] == '1') {
//						System.out.println(front.state + " " + dir + " Fail");
						usable = false;
						break;
					}
				}
				if(usable) {
					int nCenterRow = front.pos.row + delta[front.state][dir][1][0];
					int nCenterCol = front.pos.col + delta[front.state][dir][1][1];
					if(!check[nCenterRow][nCenterCol][front.state]) {
						check[nCenterRow][nCenterCol][front.state] = true;
						q.add(new Item(new Pair(nCenterRow, nCenterCol), front.state, front.cnt + 1));
					}
				}
			}
//			회전
			if(!check[front.pos.row][front.pos.col][Math.abs(front.state - 1)] && isRollable(front.pos)) {
				check[front.pos.row][front.pos.col][Math.abs(front.state - 1)] = true;
				q.add(new Item(new Pair(front.pos.row, front.pos.col), Math.abs(front.state - 1), front.cnt + 1));
			}
		}
		System.out.println(0);
	}
	static boolean isRollable(Pair pos) {
		for(int i=pos.row-1;i<=pos.row+1;i++) {
			for(int j=pos.col-1;j<=pos.col+1;j++) {
				if(isOut(i, j)) return false;
				if(map[i][j] == '1') return false;
			}
		}
		return true;
	}
	static boolean isOut(int row, int col) {
		return !(0<=row && row<n && 0<=col && col<n);
	}
}
