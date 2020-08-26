package ps.BackTracking;

import java.util.Scanner;

public class boj6987_worldcup {
	public static void main(String[] args) {
		Scanner sc = new  Scanner(System.in);
		
		for(int tc=1;tc<=4;tc++) {
			int[][] score = new int[6][3];
			boolean flag = true;
			
			for(int i=0;i<6;i++) {
				score[i][0] = sc.nextInt();
				score[i][1] = sc.nextInt();
				score[i][2] = sc.nextInt();
				
				if(score[i][0] + score[i][1] + score[i][2] != 5) flag = false;
			}
			if(!flag) System.out.print(0 + " ");
			else
				System.out.print((back(0, 1, score) ? 1 : 0) + " ");
		}
	}
	
	static boolean back(int a, int b, int[][] score) {
		if(b == 6) {
			a = a + 1;
			b = a + 1;
		}
		if(a == 5) {
			for(int i=0;i<6;i++) {
				for(int j=0;j<3;j++) {
					if(score[i][j] != 0) return false;
				}
			}
			return true;
		}
		
		for(int j=0;j<3;j++) {
			if(score[a][j] > 0 && score[b][2-j] > 0) {
				score[a][j]--;
				score[b][2-j]--;
				if(back(a, b+1, score)) return true;
				score[a][j]++;
				score[b][2-j]++;
			}
		}
		return false;
	}
}
