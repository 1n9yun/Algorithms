package ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj3025_throw_stone {
	static int R, C;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R+1][C+1];
		
		for(int i=1;i<=R;i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=1;j<=C;j++) {
				map[i][j] = input[j-1];
			}
		}

		int[][] sets = new int[C+1][R+1];
		
		for(int j=1;j<=C;j++) {
			int bottom = R;
			for(int i=R;i>=1;i--) {
				if(map[i][j] == 'X') bottom = i-1;
				sets[j][i] = bottom;
			}
		}
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			int col = Integer.parseInt(br.readLine());
			
			int bottom = find(sets[col], 1);
			
			while(true) {
				if(bottom == R || map[bottom + 1][col] == 'X') {
					map[bottom][col] = 'O';
					sets[col][bottom]--;
					sets[col][bottom-1] = sets[col][bottom];
					break;
				}else if(map[bottom + 1][col] == 'O') {
					if(col > 1 && map[bottom + 1][col - 1] == '.' && map[bottom][col - 1] == '.') {
						col--;
						bottom = find(sets[col], bottom + 1);
					}else if(col < C && map[bottom + 1][col + 1] == '.' && map[bottom][col + 1] == '.') {
						col++;
						bottom = find(sets[col], bottom + 1);
					}else {
						map[bottom][col] = 'O';
						sets[col][bottom]--;
						sets[col][bottom-1] = sets[col][bottom];
						break;
					}
				}
			}
		}
		
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	
	static int find(int[] set, int row) {
		if(set[row] == row) {
			return row;
		}
		
		return set[row] = find(set, set[row]);
	}
}