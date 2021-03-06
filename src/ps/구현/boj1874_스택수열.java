package ps.구현;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class boj1874_스택수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				
		int n = sc.nextInt();
		Stack<Integer> s = new Stack<>();
		Queue<Character> q = new LinkedList<>();
		
		int idx = 1;
		
		for(int i=0;i<n;i++) {
			int num = sc.nextInt();
			
			while(idx <= num) {
				s.push(idx++);
				q.add('+');
			}
			if(s.peek() != num) {
				System.out.println("NO");
				return;
			}else {
				s.pop();
				q.add('-');
			}
		}
		
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}
}