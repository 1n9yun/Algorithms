package ps.Bipartite_Graph;

import java.util.HashMap;
import java.util.Scanner;

public class swea7988_cvil_war {
	static HashMap<String,Integer> hm;
	static boolean[][] adjMat;
	static class Pair{
		String from;
		String to;
		public Pair(String from, String to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	static int[] state;
	static int vSize;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			hm = new HashMap<>();
			
			int k = sc.nextInt();
			Pair[] inputs = new Pair[k];
			vSize = 0;
			for(int i=0;i<k;i++) {
				String from = sc.next();
				String to = sc.next();
				
				if(!hm.containsKey(from)) hm.put(from, vSize++);
				if(!hm.containsKey(to)) hm.put(to, vSize++);
				
				inputs[i] = new Pair(from, to);
			}
			state = new int[vSize];
			adjMat = new boolean[vSize][vSize];
			for(int i=0;i<k;i++) {
				int from = hm.get(inputs[i].from);
				int to = hm.get(inputs[i].to);
				
				adjMat[from][to] = true;
				adjMat[to][from] = true;
			}
			
			for(int i=0;i<vSize;i++) {
				if(state[i] == 0) {
					state[i] = 1;
					dfs(i, 1);
				}
			}
			
			System.out.println("#" + tc + " " + (isBipartite() ? "Yes" : "No"));
		}
	}
	
	static void dfs(int v, int color) {
		for(int i=0;i<vSize;i++) {
			if(adjMat[v][i] && state[i] == 0) {
				state[i] = 3-color;
				dfs(i, 3-color);
			}
		}
	}
	
	static boolean isBipartite() {
		for(int i=0;i<vSize;i++) {
			for(int j=0;j<vSize;j++) {
				if(adjMat[i][j] && state[i] == state[j]) return false;
			}
		}
		return true;
	}
}
