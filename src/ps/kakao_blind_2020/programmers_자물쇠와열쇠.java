package ps.kakao_blind_2020;

import java.util.Arrays;

public class programmers_자물쇠와열쇠 {
	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		
		System.out.println(solution(key, lock));
	}
	public static boolean solution(int[][] key, int[][] lock) {
		int n = lock.length;
		int m = key.length;
		int[][][] keys = new int[4][m][m];
		keys[0] = key;
		for(int i=1;i<4;i++) {
			keys[i] = rotate(keys[i-1]);
		}
		
		for(int r=0;r<4;r++) {
			for(int ki=-m+1;ki<=m+n-2;ki++) {
				for(int kj=-m+1;kj<=m+n-2;kj++) {
					boolean flag = true;
					out:
					for(int i=0;i<n;i++) {
						for(int j=0;j<n;j++) {
							if(0<=i-ki && i-ki<m && 0<=j-kj && j-kj<m) {
								if(lock[i][j] == keys[r][i-ki][j-kj]) {
									flag = false;
									break out;
								}
							}else if(lock[i][j] == 0){
								flag = false;
								break out;
							}
						}
					}
					if(flag) return true;
				}
			}
		}
		return false;
	}
	
	public static int[][] rotate(int[][] key) {
		int[][] res = new int[key.length][key.length];
		double arrange = ((double)key.length-1) / 2;
		
		for(int i=0;i<key.length;i++) {
			for(int j=0;j<key.length;j++) {
				if(key[i][j] == 1) {
					double nY = i - arrange;
					double nX = j - arrange;
					
					double rY = nX + arrange;
					double rX = -nY + arrange;
					
					res[(int)rY][(int)rX] = 1;
				}
			}
		}
		
		return res;
	}
}
