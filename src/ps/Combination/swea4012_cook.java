package ps.Combination;

import java.util.Arrays;
import java.util.Scanner;

public class swea4012_cook {
	static int n;
	static int[][] synerges;
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			synerges = new int[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					synerges[i][j] = sc.nextInt();
				}
			}
			
			ans = 987654321;
			comb(0, 0, new int[n/2]);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void comb(int idx, int cnt, int[] aSet) {
		if(cnt == aSet.length) {
			int[] bSet = getOther(aSet);
			
			int aSetPoint = getPoint(aSet);
			int bSetPoint = getPoint(bSet);
			
			ans = Math.min(ans, Math.abs(aSetPoint - bSetPoint));
			return;
		}
		
		for(int i=idx;i<n;i++) {
			aSet[cnt] = i;
			comb(i + 1, cnt + 1, aSet);
		}
	}
	
	static int[] getOther(int[] aSet) {
		int[] bSet = new int[aSet.length];
		
		int aSetIdx = 0;
		int bSetIdx = 0;
		for(int i=0;i<n;i++) {
			if(aSetIdx < aSet.length && aSet[aSetIdx] == i) aSetIdx++;
			else {
				bSet[bSetIdx++] = i;
			}
		}
		return bSet;
	}
	
	static int getPoint(int[] set) {
		int setPoint = 0;
		for(int i=0;i<set.length;i++) {
			for(int j=i+1;j<set.length;j++) {
				setPoint += synerges[set[i]][set[j]] + synerges[set[j]][set[i]];
			}
		}
		return setPoint;
	}
}
