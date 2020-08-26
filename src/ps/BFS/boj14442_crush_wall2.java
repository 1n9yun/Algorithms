package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj14442_crush_wall2 {
	static class Pair{
		int left, right, cnt;
		int crushed;

		public Pair(int left, int right, int cnt, int crushed) {
			super();
			this.left = left;
			this.right = right;
			this.cnt = cnt;
			this.crushed = crushed;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static int n,m,k;
	static char[][] map;
	static boolean[][][] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		map = new char[n+1][m+1];
		check = new boolean[n+1][m+1][k+1];
		for(int i=1;i<=n;i++) {
			char[] input = sc.next().toCharArray();
			for(int j=1;j<=m;j++) {
				map[i][j] = input[j-1];
			}
		}
		
		check[1][1][0] = true;
		int ans = -1;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(1, 1, 1, 0));
		while(!q.isEmpty()) {
			Pair front = q.poll();
			
			if(front.left == n && front.right == m) {
				ans = front.cnt;
				break;
			}
			for(int dir=0;dir<4;dir++) {
				int nRow = front.left + delta[dir][0];
				int nCol = front.right + delta[dir][1];
				
				if(1<=nRow && nRow<=n && 1<=nCol && nCol<=m) {
					if(map[nRow][nCol] == '0' && !check[nRow][nCol][front.crushed]) {
							check[nRow][nCol][front.crushed] = true;
							q.add(new Pair(nRow, nCol, front.cnt + 1, front.crushed));
					}else if(map[nRow][nCol] == '1' && front.crushed + 1 <= k && !check[nRow][nCol][front.crushed+1]) {
						check[nRow][nCol][front.crushed+1] = true;
						q.add(new Pair(nRow, nCol, front.cnt + 1, front.crushed+1));
					}
				}
			}
		}
		System.out.println(ans == 987654321 ? -1 : ans);
	}
}

//이건 빠른 소스  
//import java.io.*;
//import java.util.*;
//
//public class Main {
//	static int N, M, K;
//	static int[][] map;
//	static int[][] visited;
//	static int[] dy = { 0, 0, 1, -1 };
//	static int[] dx = { 1, -1, 0, 0 };
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		K = Integer.parseInt(st.nextToken());
//		map = new int[N][M];
//		visited = new int[N][M];
//		for (int i = 0; i < N; i++) {
//			String data = br.readLine();
//			for (int j = 0; j < M; j++) {
//				map[i][j] = data.charAt(j) - '0';
//				visited[i][j] = Integer.MAX_VALUE;
//			}
//		}
//		System.out.println(bfs());
//	}
//
//	public static int bfs() {
//		Queue<Point> q = new LinkedList<Point>();
//		q.add(new Point(0, 0, 0));
//		visited[0][0] = 0;
//		int cnt = 0;
//		while (!q.isEmpty()) {
//			int size = q.size();
//			cnt++;
//			for (int i = 0; i < size; i++) {
//				Point p = q.poll();
//				if(p.y == N - 1 && p.x == M - 1) {
//					return cnt;
//				}
//				for (int d = 0; d < 4; d++) {
//					int ny = p.y + dy[d];
//					int nx = p.x + dx[d];
//					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
//						continue;
//					int nk = p.wall + map[ny][nx];
//					if (nk > K || visited[ny][nx] <= nk)
//						continue;
//					visited[ny][nx] = nk;
//					q.add(new Point(ny, nx, nk));
//				}
//			}
//		}
//		return -1;
//	}
//
//	public static class Point {
//		int y;
//		int x;
//		int wall;
//
//		public Point(int y, int x, int wall) {
//			super();
//			this.y = y;
//			this.x = x;
//			this.wall = wall;
//		}
//
//	}
//}
