package ps;

import java.util.ArrayList;
import java.util.Scanner;

public class boj1043_lie {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int key = sc.nextInt();
		int[] keys = new int[key];
		
		for(int i=0;i<key;i++) {
			keys[i] = sc.nextInt();
		}
		
		int[][] parties = new int[m][n+1];
		
		for(int i=0;i<m;i++) {
			int cnt = sc.nextInt();
			
			for(int j=0;j<cnt;j++) {
				parties[i][j] = sc.nextInt();
			}
		}
		
		  
	}
}
