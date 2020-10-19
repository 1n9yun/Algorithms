package ps.CodingTest.Coupang20201009;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class _04 {
	
	public static void main(String[] args) {
		
		String[][] roads = {{"ULSAN","BUSAN"},{"DAEJEON","ULSAN"},{"DAEJEON","GWANGJU"},{"SEOUL","DAEJEON"},{"SEOUL","ULSAN"},{"DAEJEON","DAEGU"},{"GWANGJU","BUSAN"},{"DAEGU","GWANGJU"},{"DAEGU","BUSAN"},{"ULSAN","DAEGU"},{"GWANGJU","YEOSU"},{"BUSAN","YEOSU"}};
		
		System.out.println(solution("SEOUL", "DAEGU", "YEOSU", roads));
	}
	
	static final int DIV = 10007;
	static int solution(String depar, String hub, String dest, String[][] roads) {
		Map<String, Integer> indexing = new HashMap<>();
		
		int index = 0;
		for(int i=0;i<roads.length;i++) {
			if(!indexing.containsKey(roads[i][0])) {
				indexing.put(roads[i][0], index++);
			}
			if(!indexing.containsKey(roads[i][1])) {
				indexing.put(roads[i][1], index++);
			}
			
			System.out.println(roads[i][0] + ": " + indexing.get(roads[i][0]));
			System.out.println(roads[i][1] + ": " + indexing.get(roads[i][1]));
		}
		
		ArrayList<Integer>[] adjList = new ArrayList[index];
		for(int i=0;i<index;i++) adjList[i] = new ArrayList<>();
		
		int[] come = new int[index];
		for(int i=0;i<roads.length;i++) {
			int from = indexing.get(roads[i][0]);
			int to = indexing.get(roads[i][1]);
			
			adjList[from].add(to);
			come[to]++;
		}
		
		int[][] dp = new int[index][2];
		int start = indexing.get(depar);
		int mid = indexing.get(hub);
		int end = indexing.get(dest);
		
		int[] count = new int[index];
		boolean[] check = new boolean[index];
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=0;i<adjList[start].size();i++) {
			int to = adjList[start].get(i);
			dp[to][0] = 1;
			q.add(to);
			count[to]++;
		}
		System.out.println(Arrays.toString(count));
		while(!q.isEmpty()) {
			int front = q.poll();
			if(come[front] != count[front] || check[front]) {
				System.out.println(front + "-> " + come[front] + "!=" + count[front]);
				continue;
			}else {
				System.out.println(front + "통과-> " + come[front] + "!=" + count[front]);
				check[front] = true;
			}
			System.out.println("from : " + front);
			
			for(int i=0;i<adjList[front].size();i++) {
				int to = adjList[front].get(i);
				count[to]++;
				System.out.print("to (" + to + ") ");
				
				dp[to][0] = (dp[to][0] + dp[front][0]) % DIV;
				if(front == mid) dp[to][1] = (dp[to][1] + dp[front][0]) % DIV;
				else dp[to][1] = (dp[to][1] + dp[front][1]) % DIV;
				
				System.out.println(dp[to][0] + " " + dp[to][1]);
				
				q.add(to);
			}
		}
		
		
		
		return dp[end][1];
	}
}
