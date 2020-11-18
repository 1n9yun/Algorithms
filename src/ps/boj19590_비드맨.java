package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj19590_비드맨 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minPQ = new PriorityQueue<>(n, (o1, o2) -> {
			if(o1 > o2) return 1;
			return -1;
		});
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(n, (o1, o2) -> {
			if(o1 < o2) return 1;
			return -1;
		});
		
		for(int i=0;i<n;i++) {
			int input = Integer.parseInt(br.readLine());
			
			minPQ.add(input);
			maxPQ.add(input);
		}
		
		while(minPQ.size() > 1) {
			int min = minPQ.poll();
			int max = maxPQ.poll();
			minPQ.remove(max);
			maxPQ.remove(min);
			
			int diff = max - min;
			if(diff != 0) {
				minPQ.add(diff);
				maxPQ.add(diff);
			}
		}
		
		System.out.println(minPQ.size() == 0 ? 0 : minPQ.peek());
		
	}
}