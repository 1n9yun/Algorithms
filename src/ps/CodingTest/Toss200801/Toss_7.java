package ps.CodingTest.Toss200801;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Toss_7 {
	static class Pair{
		int type;
		int value;
		public Pair(int type, int value) {
			this.type = type;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int offset = 1;
		int adr = input[0].charAt(0) - '0';
		
		Queue<Pair> q = new LinkedList<>();
		
		int type = Integer.parseInt(input[adr + offset]);
		while(type == 1) {
			
		}
		
	}
}
