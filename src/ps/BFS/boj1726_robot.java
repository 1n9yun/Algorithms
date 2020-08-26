package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1726_robot {
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
//	동 서 남 북
	static int[][] delta = {{0,0},{0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		int[][] map = new int[r+1][c+1];
		int[][][][] check = new int[r+1][c+1][5][4];
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Robot start = new Robot(new Pair(sc.nextInt(), sc.nextInt()), 0, sc.nextInt());
		Robot goal = new Robot(new Pair(sc.nextInt(), sc.nextInt()), 0, sc.nextInt());
		
		int ans = 987654321;
		Queue<Robot> q = new LinkedList<>();
		q.add(start);
		check[start.pos.first][start.pos.second][start.dir][1] = getRequiredCountToTurn(start.dir, 1);
		check[start.pos.first][start.pos.second][start.dir][2] = getRequiredCountToTurn(start.dir, 2);
		check[start.pos.first][start.pos.second][start.dir][3] = getRequiredCountToTurn(start.dir, 3);
		
		while(!q.isEmpty()) {
			Robot front = q.poll();
//			System.out.println(front.pos.first + " " + front.pos.second + ", " + convert(front.dir) + "(" + front.cnt + ")");
			if(front.pos.first == goal.pos.first && front.pos.second == goal.pos.second) {
				ans = Math.min(ans, front.cnt + getRequiredCountToTurn(front.dir, goal.dir));
				continue;
			}
			
			for(int dir=1;dir<=4;dir++) {
				for(int multi=1;multi<=3;multi++) {
					int nRow = front.pos.first + (delta[dir][0] * multi);
					int nCol = front.pos.second + (delta[dir][1] * multi);
					
					int addCount = 1;
					if(dir != front.dir) addCount += getRequiredCountToTurn(front.dir, dir);
					if(1<=nRow && nRow<=r && 1<=nCol && nCol<=c && map[nRow][nCol] == 0 && (check[nRow][nCol][dir][multi] == 0 || check[nRow][nCol][dir][multi] > front.cnt + addCount)) {
						check[nRow][nCol][dir][multi] = front.cnt + addCount;
						q.add(new Robot(new Pair(nRow, nCol), front.cnt + addCount, dir));
					}else if(!(1<=nRow && nRow<=r && 1<=nCol && nCol<=c) || map[nRow][nCol] != 0) break;
				}
			}
		}
		System.out.println(ans);
	}
	
	static int getRequiredCountToTurn(int from, int to) {
		if(from == to) return 0;
		if(from < 3 && to >= 3) return 1;
		else if(from >= 3 && to < 3) return 1;
		else return 2;
	}
	
	static String convert(int from) {
		String[] ret = {"똥", "동", "서", "남", "북"};
		return ret[from];
	}
}
