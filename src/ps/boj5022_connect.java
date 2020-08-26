package ps;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class boj5022_connect {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		Pair red1 = new Pair(sc.nextInt(), sc.nextInt());
		Pair red2 = new Pair(sc.nextInt(), sc.nextInt());
		Pair blue1 = new Pair(sc.nextInt(), sc.nextInt());
		Pair blue2 = new Pair(sc.nextInt(), sc.nextInt());
		
		int ans = 987654321;
		
		Queue<Pair> q = new ArrayDeque<>();
		q.add(red1);
		
		int count = 0;
		int len = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=0;i<qSize;i++) {
				Pair front = q.poll();
				
				if(front.first == red2.first && front.second == red2.second) {
					len = count;
				}
			}
			count++;
		}
	}
}
