package ps.Simulation;

import java.util.Scanner;

public class swea4013_magnetic {
	static final int mCnt = 4;
	static final int eCnt = 8;
	static class Magnet{
		int[] edges;
		int top;
		
		public Magnet() {
			edges = new int[eCnt];
			top = 0;
		}
		public void rotate(int dir) {
			if(dir == 1) clockwise();
			else antiwise();
		}
		public void antiwise() {
			top = (top + 1) % eCnt; 
		}
		public void clockwise() {
			top = ((top - 1) + eCnt) % eCnt;
		}
		public int left() {
			return edges[(eCnt + (top - 2)) % eCnt];
		}
		public int right() {
			return edges[(top + 2) % eCnt];
		}
	}
	static int k;
	static Magnet[] magnets;
	static int[][] input;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			k = sc.nextInt();
			input = new int[k][2];
			magnets = new Magnet[mCnt];
			
			for(int i=0;i<mCnt;i++) {
				magnets[i] = new Magnet();
				for(int j=0;j<eCnt;j++) {
					magnets[i].edges[j] = sc.nextInt();
				}
			}
			
			for(int i=0;i<k;i++) {
				input[i][0] = sc.nextInt();
				input[i][1] = sc.nextInt();
			}
			
			for(int i=0;i<k;i++) {
				int n = input[i][0] - 1;
				int left = n-1, right = n+1;
				
				int[] shouldDo = new int[mCnt];
				shouldDo[n] = input[i][1];
				
				while(left >= 0) {
					if(magnets[left + 1].left() != magnets[left].right()) {
						shouldDo[left] = shouldDo[left + 1] * -1;
						left--;
					}else break;
				}
				while(right < mCnt) {
					if(magnets[right - 1].right() != magnets[right].left()) {
						shouldDo[right] = shouldDo[right - 1] * -1;
						right++;
					}else break;
				}
				
				for(int j=0;j<mCnt;j++) {
					if(shouldDo[j] != 0) {
						magnets[j].rotate(shouldDo[j]);
					}
				}
			}
			
			int ans = 0;
			for(int i=0;i<mCnt;i++) {
				ans += magnets[i].edges[magnets[i].top] == 1 ? 1<<i : 0;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
