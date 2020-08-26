package ps.Simulation;

import java.util.ArrayList;
import java.util.Scanner;

public class swea5644_wirelessCharging {
	static class Pair{
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static class BC{
		Pair pos;
		int range, performance;
		
		public BC(Pair pos, int range, int performance) {
			super();
			this.pos = pos;
			this.range = range;
			this.performance = performance;
		}
	}
	static int[][] delta = {{0, 0},{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int m = sc.nextInt();
			int a = sc.nextInt();
			
			int[] aMove = new int[m+1];
			int[] bMove = new int[m+1];
			
			for(int i=1;i<=m;i++) aMove[i] = sc.nextInt();
			for(int i=1;i<=m;i++) bMove[i] = sc.nextInt();
			
			BC[] bcs = new BC[a];
			for(int i=0;i<a;i++) {
				int bcc = sc.nextInt();
				int bcr = sc.nextInt();
				bcs[i] = new BC(new Pair(bcr, bcc), sc.nextInt(), sc.nextInt());
			}
			
			Pair A = new Pair(1, 1);
			Pair B = new Pair(10, 10);
			
			int ans = 0;
			for(int i=0;i<=m;i++) {
//				다음 위치
				A.r += delta[aMove[i]][0];
				A.c += delta[aMove[i]][1];
				B.r += delta[bMove[i]][0];
				B.c += delta[bMove[i]][1];
				
				ArrayList<Integer> aBC = new ArrayList<>();
				ArrayList<Integer> bBC = new ArrayList<>();
				
//				에서 계산
				for(int j=0;j<bcs.length;j++) {
					if(getDist(A, bcs[j].pos) <= bcs[j].range) aBC.add(j);
					if(getDist(B, bcs[j].pos) <= bcs[j].range) bBC.add(j);
				}
				
				int temp = 0;
				
				if(aBC.isEmpty() && bBC.isEmpty()) continue;
				else if(aBC.isEmpty() && !bBC.isEmpty()) {
					for(int bi=0;bi<bBC.size();bi++) {
						temp = Math.max(temp, bcs[bBC.get(bi)].performance);
					}
				}else if(!aBC.isEmpty() && bBC.isEmpty()) {
					for(int ai=0;ai<aBC.size();ai++) {
						temp = Math.max(temp, bcs[aBC.get(ai)].performance);
					}
				}else {
					for(int ai=0;ai<aBC.size();ai++) {
						for(int bi=0;bi<bBC.size();bi++) {
							int aBCi = aBC.get(ai);
							int bBCi = bBC.get(bi);
							
							if(aBCi == bBCi) {
								temp = Math.max(temp, bcs[aBCi].performance);
							}else {
								temp = Math.max(temp, bcs[aBCi].performance + bcs[bBCi].performance);
							}
						}
					}
				}
				ans += temp;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int getDist(Pair p1, Pair p2) {
		return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
	}
}
