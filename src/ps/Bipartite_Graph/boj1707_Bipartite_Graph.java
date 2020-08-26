package ps.Bipartite_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj1707_Bipartite_Graph {
	static int[] state;
	static ArrayList<Integer>[] adjList;
	static int vSize, eSize;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			vSize = Integer.parseInt(st.nextToken());
			eSize = Integer.parseInt(st.nextToken());
			
			state = new int[vSize+1];
			adjList = new ArrayList[vSize+1];
			
			for(int i=1;i<=vSize;i++) adjList[i] = new ArrayList<>();
			
			for(int i=0;i<eSize;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from].add(to);
				adjList[to].add(from);
			}
			
			for(int i=1;i<=vSize;i++) {
				if(state[i] == 0) {
					state[i] = 1;
					dfs(i, 1);
				}
			}
			
			System.out.println(isBipartite() ? "YES" : "NO");
		}
	}
	
	static void dfs(int vertex, int color) {
		for(int i=0;i<adjList[vertex].size();i++) {
			int to = adjList[vertex].get(i);
			if(state[to] == 0) {
				state[to] = 3-color;
				dfs(to, 3-color);
			}
		}
	}
	
	static boolean isBipartite() {
		for(int i=1;i<=vSize;i++) {
			for(int j=0;j<adjList[i].size();j++) {
				int to = adjList[i].get(j);
				if(state[i] == state[to]) return false;
			}
		}
		return true;
	}
}
