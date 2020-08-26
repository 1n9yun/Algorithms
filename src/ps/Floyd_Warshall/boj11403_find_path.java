package ps.Floyd_Warshall;

import java.util.Arrays;
import java.util.Scanner;

public class boj11403_find_path {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] adjMat = new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				adjMat[i][j] = sc.nextInt();
			}
		}
		
		
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(adjMat[i][k] != 0 && adjMat[k][j] != 0) {
						adjMat[i][j] = 1;
					}
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(adjMat[i][j] + " ");
			}
			System.out.println();
		}
	}
}
