package ps;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class boj2164_카드2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		for(int i=1;i<=n;i++) dq.addLast(i);
		
		while(dq.size() != 1) {
			dq.pollFirst();
			dq.addLast(dq.pollFirst());
		}
		
		System.out.println(dq.poll());
	}
}
