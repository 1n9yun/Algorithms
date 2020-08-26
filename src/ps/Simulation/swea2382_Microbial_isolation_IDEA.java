package ps.Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class swea2382_Microbial_isolation_IDEA {
	static class Cluster{
		int row, col, count, dir;
//		위치별로 묶는 아이디어
		int pos;
		public Cluster(int row, int col, int count, int dir, int pos) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
			this.dir = dir;
			this.pos = pos;
		}
	}
	static ArrayList<Cluster> clusters;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			
			clusters = new ArrayList<>();
			for(int i=0;i<k;i++) {
				int row = sc.nextInt();
				int col = sc.nextInt();
				clusters.add(new Cluster(row, col, sc.nextInt(), sc.nextInt()-1, row * n + col));
			}
			
			for(int time=0;time<m;time++) {
				for(int i=0;i<clusters.size();i++) {
					Cluster c = clusters.get(i);
//					방향 별 진행
					int nRow = c.row + delta[c.dir][0];
					int nCol = c.col + delta[c.dir][1];
					
					if(nRow == 0 || nRow == n-1 || nCol == 0 || nCol == n-1) {
						if(c.dir < 2) c.dir = 1 - c.dir;
						else if(c.dir < 4) c.dir = 5 - c.dir;
						c.count /= 2;
						if(c.count == 0) {
							clusters.remove(i--);
							continue;
						}
					}
					c.row = nRow;
					c.col = nCol;
					c.pos = nRow * n + nCol;
				}
				
				Collections.sort(clusters, new Comparator<Cluster>() {
					@Override
					public int compare(Cluster c1, Cluster c2) {
//						같은 위치에 있는 cluster 끼리 묶기 위해서
						if(c1.pos == c2.pos)
							return c1.count - c2.count;
						else return c2.pos - c1.pos;
					}
				});
				
				for(int i=0;i<clusters.size()-1;i++) {
					Cluster c = clusters.get(i);
					Cluster cNext = clusters.get(i+1);
					
					if(c.pos == cNext.pos) {
						c.dir = cNext.dir;
						c.count += cNext.count;
						clusters.remove(i+1);
						i--;
					}
				}
			}
			
			int ans = 0;
			for(Cluster c : clusters) ans += c.count;
			System.out.println("#" + tc + " " + ans);
		}
	}
}
