package ps.Combination;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class bj17136_AttachPaper{
	static int[][] paper;
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		paper = new int[10][10];
		int oneCnt = 0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++){
				paper[i][j] = sc.nextInt();
				if(paper[i][j] == 1) oneCnt++;
			}
		}
		
		boolean[][] check = new boolean[10][10];
		comb(0, 0, check, new int[6], oneCnt);
		
		System.out.println(ans == 987654321 ? -1 : ans);
	}
	static void comb(int row, int col, boolean[][] check, int[] pPapers, int cnt) {
		if(cnt == 0) {
			// pPaper 갯수 ans 갱신
			int tempAns = 0;
			for(int i=1;i<pPapers.length;i++) {
				tempAns += pPapers[i];
			}
			ans = Math.min(ans, tempAns);
			return;
		}
		for(int i=row;i<10;i++) {
			for(int j=col;j<10;j++) {
				if(paper[i][j] == 0 || check[i][j]) continue;
				for(int size=5;size>0;size--) {
					boolean flag = true;
					if(i + size - 1 > 9 || j + size - 1 > 9) continue;
					for(int pRow = i;pRow < i + size;pRow++) {
						for(int pCol = j;pCol <j + size;pCol++) {
							if(paper[pRow][pCol] == 0 || check[pRow][pCol]) {
								flag = false;
								break;
							}
						}
						if(!flag) break;
					}
					
					// 붙일 수 있으면 붙이기
					if(flag) {
						// 붙이면 안돼
						if(pPapers[size] + 1 > 5 || cnt - (size*size) < 0) continue;
						for(int pRow = i;pRow < i + size;pRow++) {
							for(int pCol = j;pCol <j + size;pCol++) {
								check[pRow][pCol] = true;
							}
						}
						pPapers[size]++;
						int paramRow = j + size > 10 ? i + 1 : i;
						int paramCol = j + size > 10 ? 0 : j + size;
						comb(paramRow, paramCol, check, pPapers, cnt - (size*size));
						// 다시 떼버리기
						pPapers[size]--;
						for(int pRow = i;pRow < i + size;pRow++) {
							for(int pCol = j;pCol <j + size;pCol++) {
								check[pRow][pCol] = false;
							}
						}
					}
				}
				// 아무 종이도 못붙였으면 종료해야됨 **
				return;
			}
			col = 0;
		}
		
	}
}