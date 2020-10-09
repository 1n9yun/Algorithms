package ps.naver2020_2;

import java.util.LinkedList;
import java.util.Queue;

public class _03 {
	public static void main(String[] args) {
		int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {2, 6}, {3, 7}, {3, 8}, {3, 9}, {4, 10}, {4, 11}, {5, 12}, {5, 13}, {6, 14}, {6, 15}, {6, 16}, {8, 17}, {8, 18}};
		System.out.println(solution(19, edges));
	}
	
	static class Item{
		long infested;
		int count;
		public Item(long infested, int count) {
			super();
			this.infested = infested;
			this.count = count;
		}
	}
	
	static public int solution(int n, int[][] edges) {
		int answer = 987654321;
		
		boolean[][] adjMat = new boolean[n][n];
		for(int i=0;i<edges.length;i++) {
			adjMat[edges[i][0]][edges[i][1]] = true;
		}
		Queue<Item> q = new LinkedList<>();
		q.add(new Item(1<<0, 1));
		
		while(!q.isEmpty()) {
			Item front = q.poll();
			System.out.println("시작" + front.infested);
			
			long infested = 0;
			int count = front.count;
			
			for(int i=0;i<n;i++) {
				if((front.infested & (1<<i)) == (1<<i)) {
					System.out.println(i + "호잉");
					for(int j=0;j<n;j++) {
						if(!adjMat[i][j]) continue;
						infested |= (1<<j);
						count++;
					}
				}
			}
			System.out.println(Integer.toBinaryString((int)infested) + " 다음 감염");
			System.out.println(count);
			if(infested == 0) {
				System.out.println("결과 : " + front.count);
				if(answer > front.count) {
					answer = front.count;
					continue;
				}
			}
			
			for(int i=0;i<n;i++) {
				if((infested & (1<<i)) == (1<<i)) {
					System.out.println(i + "번째 짤라!");
					long slicedInfested = infested ^ (1<<i);
					System.out.println(Integer.toBinaryString((int)slicedInfested) + " 자른 감염");
					q.add(new Item(slicedInfested, count - 1));
				}
			}
		}
		
		return answer;
	}
}
