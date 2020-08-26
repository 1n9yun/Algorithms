package ps.MST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class bj17472_bridge2 {
	static class Pair{
		int left, right;
		public Pair(int left, int right){
			this.left = left;
			this.right = right;
		}
	}
	static class Triple{
		int from, to, cost;
		public Triple(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] field;
	static int[] disjointSet;
	static int[] rank;
	static int n,m;
	static ArrayList<Pair>[] islands;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		field = new int[n][m];

		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				int input = sc.nextInt();
				
				if(input == 0) field[i][j] = -1;
				else field[i][j] = 1;
			}
		}

		islands = new ArrayList[101];
		int islandsCnt = 0;
		boolean[][] check = new boolean[n][m];
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(field[i][j] == 1 && !check[i][j]){
					islands[islandsCnt] = new ArrayList<Pair>();
					Queue<Pair> q = new LinkedList<>();

					q.add(new Pair(i,j));
					islands[islandsCnt].add(new Pair(i,j));
					check[i][j] = true;
					while(!q.isEmpty()){
						Pair front = q.poll();
						field[front.left][front.right] = islandsCnt;
						for(int dir=0;dir<4;dir++){
							int nRow = front.left + delta[dir][0];
							int nCol = front.right + delta[dir][1];

							if(0<=nRow && nRow <n && 0<=nCol && nCol<m && field[nRow][nCol] == 1 && !check[nRow][nCol]){
								check[nRow][nCol] = true;
								islands[islandsCnt].add(new Pair(nRow, nCol));
								q.add(new Pair(nRow, nCol));
							}
						}
					}
					islandsCnt++;
				}
			}
		}
		ArrayList<Triple> edges = new ArrayList<>();
		for(int i=0;i<islandsCnt;i++) {
			// left : next node, right : cost
			for(int j=0;j<islands[i].size();j++) {
				Pair point = islands[i].get(j);
				for(int dir=0;dir<4;dir++) {
					int nRow = point.left + delta[dir][0];
					int nCol = point.right + delta[dir][1];
					
					if(0<=nRow && nRow <n && 0<=nCol && nCol<m && field[nRow][nCol] == -1) {
						int cost = 0;
						while(0<=nRow && nRow <n && 0<=nCol && nCol<m && field[nRow][nCol] == -1) {
							nRow += delta[dir][0];
							nCol += delta[dir][1];
							cost++;
							
						}
						if(0<=nRow && nRow <n && 0<=nCol && nCol<m && cost > 1) {
							boolean flag = true;
							for(int idx = 0; idx < edges.size(); idx++) {
								Triple temp = edges.get(idx);
								if((temp.from == i && temp.to == field[nRow][nCol] && temp.cost == cost) ||
										temp.from == field[nRow][nCol] && temp.to == i && temp.cost == cost) {
									flag = false;
									break;
								}
							}
							if(flag && !(field[nRow][nCol] == i)) {
								edges.add(new Triple(i, field[nRow][nCol], cost));
							}
						}
					}
				}
			}
		}

		edges.sort(new Comparator<Triple>() {
			@Override
			public int compare(Triple t1, Triple t2) {
				return t1.cost - t2.cost;
			}
		});
		int ans = 0;
		disjointSet = new int[islandsCnt];
		rank = new int[islandsCnt];
		init();
		
		for(int i=0;i<edges.size();i++) {
			Triple edge = edges.get(i);
			if(union(edge.from, edge.to)) ans += edge.cost;
		}
		// 모두 연결이 안되는 경우 체크
		boolean allConnected = true;
		for(int i=1;i<disjointSet.length;i++) {
			if(union(i, i-1)) {
				allConnected = false;
				break;
			}
		}
		System.out.println(ans == 0 || !allConnected ? -1 : ans);
	}
	
	static void init() {
		for(int i=0;i<disjointSet.length;i++) {
			disjointSet[i] = i;
			rank[i] = 1;
		}
	}
	static int find(int idx) {
		if(idx == disjointSet[idx]) return idx;
		
		return disjointSet[idx] = find(disjointSet[idx]);
	}
	
//	rank 쓰나마나 차이없네 이건??
	static boolean union(int idx1, int idx2) {
		idx1 = find(idx1);
		idx2 = find(idx2);
		if(idx1 == idx2) return false;;
		
		if(rank[idx1] > rank[idx2]) {
			int temp = idx1;
			idx1 = idx2;
			idx2 = temp;
		}
		disjointSet[idx1] = idx2;
		
		if(rank[idx1] == rank[idx2]) rank[idx2]++;
		return true;
	}
}