package ps.CodingTest.KakaoBlind2021;

import java.util.Arrays;

public class _04 {
//  1 ~ n
//	무방향
	public static void main(String[] args) {
		int[][][] fares = {
				{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}},
				{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
				{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}
				};
		System.out.println(solution(7,3,4,1, fares[1]));
	}
	static final int MAX = Integer.MAX_VALUE;
    static public int solution(int n, int s, int a, int b, int[][] fares) {
    	int[][] adjMat = new int[n+1][n+1];
    	for(int[] sub : adjMat) Arrays.fill(sub, MAX);
    	
    	for(int i=0;i<fares.length;i++) {
    		adjMat[fares[i][0]][fares[i][1]] = fares[i][2];
    		adjMat[fares[i][1]][fares[i][0]] = fares[i][2];
    	}
    	
    	for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				if(k == i) continue;
				for(int j=1;j<=n;j++) {
					if(i == j || j == k) continue;
					if(adjMat[i][k] != MAX && adjMat[k][j] != MAX) {
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
					}
				}
			}
		}
    	
//    	따로가기
    	int answer = adjMat[s][a] + adjMat[s][b];
    	for(int k=1;k<=n;k++) {
//    		갈 수 없는 중간점 패스
    		if(k == s || adjMat[s][k] == MAX) continue;
    		int temp = adjMat[s][k];
    		temp += (k == a ? 0 : adjMat[k][a]) + (k == b ? 0 : adjMat[k][b]);
    		
    		answer = Math.min(answer, temp);
    	}
    	return answer;
    }
}
