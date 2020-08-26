package ps.Brute_Force;

import java.util.Arrays;
import java.util.Scanner;

public class swea4366_jungsiksbank {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			String binary = sc.next();
			String ternary = sc.next();
			
			long bGuess[][] = new long[binary.length()][2];
			long tGuess[][] = new long[ternary.length()][3];
			for(long[] sub : bGuess) Arrays.fill(sub, -1);
			for(long[] sub : tGuess) Arrays.fill(sub, -1);
			
			for(int i=0;i<binary.length();i++) {
				for(int j=0;j<2;j++) {
					if(binary.charAt(i) == ('0' + j)) continue;
					char[] temp = binary.toCharArray();
					temp[i] = (char)('0' + j);
					bGuess[i][j] = Long.parseLong(new String(temp), 2);
				}
			}
			for(int i=0;i<ternary.length();i++) {
				for(int j=0;j<3;j++) {
					if(ternary.charAt(i) == ('0' + j)) continue;
					char[] temp = ternary.toCharArray();
					
					temp[i] = (char)('0' + j);
					tGuess[i][j] = Long.parseLong(new String(temp), 3);
				}
			}
			long ans = 0;
			out:
			for(int bi=0;bi<bGuess.length;bi++) {
				for(int bj=0;bj<2;bj++) {
					if(bGuess[bi][bj] == -1) continue;
					
					for(int ti=0;ti<tGuess.length;ti++) {
						for(int tj=0;tj<3;tj++) {
							if(tGuess[ti][tj] == -1) continue;
							
							if(bGuess[bi][bj] == tGuess[ti][tj]) {
								ans = bGuess[bi][bj];
								break out;
							}
						}
					}
				}		
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
