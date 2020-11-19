package ps.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj19590_비드맨 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long sum = 0;
		int max = -1;
		for(int i=0;i<n;i++) {
			int input = Integer.parseInt(br.readLine());
			
			sum += input;
			max = Math.max(max, input);
		}
		
		if(max >= sum - max) System.out.println(max - (sum - max));
		else System.out.println(sum & 1);
		
	}
}