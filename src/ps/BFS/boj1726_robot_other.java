package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1726_robot_other {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Robot{
		Pair pos;
		int cnt;
		int dir;
		public Robot(Pair pos, int cnt, int dir) {
			super();
			this.pos = pos;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	static int[][] delta = {{0,0},{0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		int[][] map = new int[r+1][c+1];
		boolean[][][] check = new boolean[r+1][c+1][5];
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Robot start = new Robot(new Pair(sc.nextInt(), sc.nextInt()), 0, sc.nextInt());
		Robot goal = new Robot(new Pair(sc.nextInt(), sc.nextInt()), 0, sc.nextInt());
		
		int ans = -1;
		Queue<Robot> q = new LinkedList<>();
		q.add(start);
		check[start.pos.first][start.pos.second][start.dir] = true;
		
		while(!q.isEmpty()) {
			Robot front = q.poll();
			if(front.pos.first == goal.pos.first && front.pos.second == goal.pos.second && front.dir == goal.dir) {
				ans = front.cnt;
				break;
			}
			
//			turn left, right
			for(int turn=0;turn<2;turn++) {
				int nDir = getNextDirection(front.dir, turn);
				if(!check[front.pos.first][front.pos.second][nDir]) {
					check[front.pos.first][front.pos.second][nDir] = true;
					q.add(new Robot(new Pair(front.pos.first, front.pos.second), front.cnt + 1, nDir));
				}
			}
			
			for(int multi=1;multi<=3;multi++) {
				int nRow = front.pos.first + (delta[front.dir][0] * multi);
				int nCol = front.pos.second + (delta[front.dir][1] * multi);
				
				if(1<=nRow && nRow<=r && 1<=nCol && nCol<=c && map[nRow][nCol] == 0 && !check[nRow][nCol][front.dir]) {
					check[nRow][nCol][front.dir] = true;
					q.add(new Robot(new Pair(nRow, nCol), front.cnt + 1, front.dir));
				}else if(!(1<=nRow && nRow<=r && 1<=nCol && nCol<=c) || map[nRow][nCol] != 0) break;
			}
		}
		System.out.println(ans);
	}
	
	static int getNextDirection(int from, int dir) {
		if(dir == 0) {
			if(from % 2 == 1) return from-1 == 0 ? 4 : from-1;
			else return from+1 == 5 ? 1 : from+1;
		}else {
			if(from + 2 > 4) return (from + 2) % 4;
			return from + 2;
		}
	}
}
