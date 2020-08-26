package ps.Union_Find;

import java.util.Scanner;

public class boj2162_line_group_CCW {
	static class Pair implements Comparable{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Object o) {
			Pair p = (Pair)o;
			if(this.x == p.x) return this.y - p.y;
			else return this.x - p.x;
		}
	}
	static class Line{
		Pair p1, p2;

		public Line(Pair p1, Pair p2) {
			super();
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	static int[] lineSet;
	static int[] rank;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Line[] lines = new Line[n];
		lineSet = new int[n];
		rank = new int[n];
		
		for(int i=0;i<n;i++) {
			lines[i] = new Line(new Pair(sc.nextInt(), sc.nextInt()),
								new Pair(sc.nextInt(), sc.nextInt()));
			lineSet[i] = i;
			rank[i] = 1;
		}
		
		int count = lineSet.length;
		int maxSize = 1;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(i == j) continue;
				if(isInterSect(lines[i], lines[j])) {
					int res = union(i, j);
					if(res != 0) {
						count--;
						maxSize = Math.max(maxSize, res);
					}
				}
			}
		}
		System.out.println(count);
		System.out.println(maxSize);
	}
	
//	(x1, y1, 1)
//	(x2, y2, 1)
//	(x3, y3, 1)의 행렬식
	static int ccw(Pair A, Pair B, Pair C) {
		int res = (B.x-A.x)*(C.y-A.y) - (B.y-A.y)*(C.x-A.x);
		if(res < 0) return -1;
		else if(res > 0) return 1;
		else return 0;
	}
	
	static boolean isInterSect(Line l1, Line l2) {
		int res1_2 = ccw(l1.p1, l1.p2, l2.p1) * ccw(l1.p1, l1.p2, l2.p2);
		int res2_1 = ccw(l2.p1, l2.p2, l1.p1) * ccw(l2.p1, l2.p2, l1.p2);
		
		if(res1_2 == 0 && res2_1 == 0) {
			if(l1.p1.compareTo(l1.p2) > 0) { Pair temp = l1.p1; l1.p1 = l1.p2; l1.p2 = temp; }
			if(l2.p1.compareTo(l2.p2) > 0) { Pair temp = l2.p1; l2.p1 = l2.p2; l2.p2 = temp; }
			
			if(l1.p1.compareTo(l2.p2) <= 0 && l2.p1.compareTo(l1.p2) <= 0) return true;
		}else if(res1_2 <= 0 && res2_1 <= 0) return true;
		
		return false;
	}
	
	static int find(int idx) {
		if(lineSet[idx] == idx) return idx;
		return lineSet[idx] = find(lineSet[idx]);
	}
	
	static int union(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if(p1 == p2) return 0;
		if(rank[p1] < rank[p2]) {
			int temp = p1;
			p1 = p2;
			p2 = temp;
		}
		
		lineSet[p2] = p1;
		return rank[p1] += rank[p2];
	}
}
