package ps.Union_Find;

import java.util.Scanner;

public class boj10775_airport {
	static int[] gateSet;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int g = sc.nextInt();
		int p = sc.nextInt();
		gateSet = new int[g+1];
		init();
		
		int ans = 0;
		for(int i=0;i<p;i++) {
			int input = sc.nextInt();

			int docking = find(input);
			if(docking != 0) {
				union(docking, docking-1);
				ans++;
			}else break;
		}
		System.out.println(ans);
	}
	static void init() {
		for(int i=0;i<gateSet.length;i++) {
			gateSet[i] = i;
		}
	}
	
	static int find(int idx) {
		if(idx == gateSet[idx]) return idx;
		
		return gateSet[idx] = find(gateSet[idx]);
	}
	
//	rank 쓰면 안됨 차례로 저장되어야 함.
	static void union(int idx1, int idx2) {
		int p1 = find(idx1);
		int p2 = find(idx2);
		
		if(p1 != p2) {
			gateSet[p1] = p2;
		}
	}
}