package ps;

import java.util.Arrays;
import java.util.Scanner;

public class boj16985_Maaaaaaaaaze {
	static int[][][][] plate = new int[5][4][5][5];
	static int[] plateOrder;
	static int ans = -1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0;i<5;i++) {
			for(int r=0;r<5;r++) {
				for(int c=0;c<5;c++) {
					plate[i][0][r][c] = sc.nextInt();
				}
			}
		}
		
		for(int i=0;i<5;i++) {
			for(int t=1;t<4;t++) {
				for(int r=0;r<5;r++) {
					for(int c=0;c<5;c++) {
						if(plate[i][t-1][r][c] != 0) {
							int nPos = rotateTransform(r*5 + c);
							plate[i][t][nPos / 5][nPos % 5] = 1; 
						}
					}
				}
			}
		}
		
		plateOrder = new int[5];
		platePerm(0, new boolean[5]);
	}
	static void platePerm(int idx, boolean[] check) {
		if(idx == plateOrder.length) {
			rotatePerm(0, new int[5]);
			return ;
		}
		
		for(int i=0;i<plateOrder.length;i++) {
			if(check[i]) continue;
			check[i] = true;
			plateOrder[idx] = i;
			platePerm(idx + 1, check);
			check[i] = false;
		}
	}
	static int count = 0;
	static void rotatePerm(int idx, int[] result) {
		if(idx == result.length) {
			
			
			return;
		}
		
		for(int i=0;i<4;i++) {
			result[idx] = i;
			rotatePerm(idx + 1, result);
		}
	}
	static int rotateTransform(int pos) {
		int r = pos / 5 - 2;
		int c = pos % 5 - 2;
		
		int tr = r * 0 + c * -1;
		int tc = r * 1 + c * 0;
		
		return (tr + 2) * 5 + (tc + 2);
	}
}
