package ps;

import java.util.PriorityQueue;
import java.util.Scanner;

public class swea10204_eatingSushi {
	static class Pair{
		long a, b;

		public Pair(long a, long b) {
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			
			PriorityQueue<Pair> qa = new PriorityQueue<Pair>((o1, o2) -> {
//				long diff1 = Math.abs(o1.a - o1.b);
//				long diff2 = Math.abs(o2.a - o2.b);
//				
//				if(diff1 == diff2) {
//					return o1.a < o2.a ? 1 : -1;
//				}else if(diff1 < diff2) return 1;
//				else return -1;
				if(o1.a < o2.b) return 1;
				return -1;
			});
			PriorityQueue<Pair> qb = new PriorityQueue<Pair>((o1, o2) -> {
				long diff1 = Math.abs(o1.a - o1.b);
				long diff2 = Math.abs(o2.a - o2.b);
				
				if(diff1 == diff2) {
					if(o1.b < o2.b) return 1;
					else return -1;
				}else if(diff1 < diff2) return 1;
				else return -1;
			});
			
			for(int i=0;i<n;i++) {
				Pair input = new Pair(sc.nextInt(), sc.nextInt());
				System.out.println("입력 : " + input.a + " " + input.b);
				
				qa.add(new Pair(input.a, input.b));
				qb.add(new Pair(input.a, input.b));
				
				for(Pair p : qa) System.out.println(p.a + " " + p.b);
				System.out.println("-------------------");
				for(Pair p : qb) System.out.println(p.a + " " + p.b);
			}
			
		}
	}
}
