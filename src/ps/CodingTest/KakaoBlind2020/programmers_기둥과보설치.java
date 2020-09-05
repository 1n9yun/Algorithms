package ps.CodingTest.KakaoBlind2020;

public class programmers_기둥과보설치 {
	public int[][] solution(int n, int[][] build_frame){
		int[][] map = new int[5][1001];
		int offset = 987654321;
		
		for(int i=0;i<n;i++) {
			offset = Math.min(offset, build_frame[i][0]);
		}
		
		for(int i=0;i<5;i++) {
			map[i][0] |= 1<<0;
		}
		
		for(int i=0;i<n;i++) {
			int x = build_frame[i][0] - offset;
			int y = build_frame[i][1];
//			기둥이면
			if(build_frame[i][2] == 0) {
//				추가
				if(build_frame[i][3] == 0) {
					if(map[x][y] != 0) {
						map[x][y] |= (1<<0);
						map[x][y+1] |= (1<<0);
					}
				}
//				삭제
				else {
					
				}
			}
//			보 면
			else if(build_frame[i][2] == 1) {
//				추가
				if(build_frame[i][3] == 0) {
					
				}
//				삭제
				else {
					
				}
			}
		}
	}
}
