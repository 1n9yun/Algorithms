package ps.Toss_200801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//내가 기억할 수 있는 결제수단은 최근 5개의 결제수단이다.
//
//주어진 input은 내가 사용한 순서대로 나열한 것이다. (나열된 값 중 가장 마지막 값이 사용자가 가장 최근에 사용한 결제수단이다)
//
//주어진 input을 기준으로 매 결제수단 사용시 최근 5개 결제수단을 output 하시오.

public class Toss_4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		Deque<String> dq = new ArrayDeque<>();
		
		for(int i=0;i<input.length;i++) {
			
			if(dq.contains(input[i])) {
				dq.remove(input[i]);
				dq.addFirst(input[i]);
			}else {
				if(dq.size() == 5) dq.removeLast();
				dq.addFirst(input[i]);
			}
			
			for(String s : dq) System.out.print(s + " ");
			System.out.println();
			
		}
		
	}
}
