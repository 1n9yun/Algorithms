package hee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1062 {
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);
		
		
		for (int n = 0; n < N; n++) {
			
		}
		
		if (K < 5) {
			System.out.println("0");
			return;
		}
		
	}

}
