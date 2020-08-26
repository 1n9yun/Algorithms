package ps.Permutation;

import java.util.Arrays;
import java.util.Scanner;

public class swea8275_hamster {
	static int n,x,m;
	static class Triple{
		int l, r, s;

		public Triple(int l, int r, int s) {
			super();
			this.l = l;
			this.r = r;
			this.s = s;
		}
	}
	static Triple[] inputs;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		int[] base = new int[11];
		for(int i=0;i<base.length;i++) base[i] = i;
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			x = sc.nextInt();
			m = sc.nextInt();
			inputs = new Triple[m];
			for(int i=0;i<m;i++) {
				int l = sc.nextInt();
				int r = sc.nextInt();
				int s = sc.nextInt();
				
				inputs[i] = new Triple(l, r, s);
			}
			ans = -1;
			ansArr = null;
			ansMax = -1;
			perm(0, base, new int[n]);
			
			System.out.print("#" + tc + " ");
			if(ansArr == null) System.out.println(-1);
			else {
				for(int i=0;i<ansArr.length;i++) System.out.print(ansArr[i] + " ");
				System.out.println();
			}
		}
	}
	static int ans = -1;
	static int ansMax = -1;
	static int[] ansArr;
	static void perm(int idx, int[] base, int[] result) {
		if(idx == result.length) {
			int[] partialSums = new int[result.length + 1];
			partialSums[1] = result[0];
			for(int i=2;i<partialSums.length;i++) {
				partialSums[i] = partialSums[i-1] + result[i-1];
			}
			for(int i=0;i<inputs.length;i++) {
				if(partialSums[inputs[i].r] - partialSums[inputs[i].l - 1] != inputs[i].s) 
					return;
			}
			if(ansMax < partialSums[partialSums.length-1]) {
				ansMax = partialSums[partialSums.length-1];
				ansArr = result.clone();
			}
			return;
		}
		
		for(int i=0;i<=x;i++) {
			result[idx] = base[i];
			perm(idx + 1, base, result);
		}
	}
}
