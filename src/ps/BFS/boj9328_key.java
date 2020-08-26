package ps.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class boj9328_key {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Item{
		Pair pos;
		int keys;
		
		public Item(Pair pos, int keys) {
			super();
			this.pos = pos;
			this.keys = keys;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			
			char[][] map = new char[h+2][w+2];
			ArrayList<Pair>[] doors = new ArrayList[26];
			for(int i=0;i<26;i++) {
				doors[i] = new ArrayList<>();
			}
			
			for(int i=0;i<h+2;i++) {
				if(i == 0 || i == h+1) Arrays.fill(map[i], '.');
				else {
					char[] input = sc.next().toCharArray();
					for(int j=0;j<w+2;j++) {
						if(j == 0 || j == w+1) map[i][j] = '.';
						else {
							map[i][j] = input[j-1];
							if('A' <= map[i][j] && map[i][j] <= 'Z') {
								doors[map[i][j] - 'A'].add(new Pair(i, j));
							}
						}
					}
				}
			}
			String keys = sc.next();
			int initKeys = 0;
			if(!keys.equals("0")) {
				for(int i=0;i<keys.length();i++) {
					int key = keys.charAt(i) - 'a';
					initKeys |= 1<<key;
					
					for(int j=0;j<doors[key].size();j++) {
						Pair pos = doors[key].get(j);
						map[pos.first][pos.second] = '.';
					}
					doors[key].clear();
				}
			}
			
			
			int[][] check = new int[h+2][w+2];
			for(int[] sub : check) Arrays.fill(sub, -1);
			
//			Queue<Item> q = new LinkedList<>();
			PriorityQueue<Item> q = new PriorityQueue<>((o1, o2) -> {
				int o1Cnt = 0, o2Cnt = 0;
				
				for(int i=0;i<26;i++) {
					if((o1.keys & (1<<i)) != 0) o1Cnt++;
					if((o2.keys & (1<<i)) != 0) o2Cnt++;
				}
				
				if(o1Cnt < o2Cnt) return 1;
				return -1;
			});
			
			check[0][0] = initKeys;
			q.add(new Item(new Pair(0, 0), initKeys));
			
			int ans = 0;
			while(!q.isEmpty()) {
				Item front = q.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = front.pos.first + delta[dir][0];
					int nCol = front.pos.second + delta[dir][1];
					
					if(0<=nRow && nRow<h+2 && 0<=nCol && nCol<w+2 && check[nRow][nCol] < front.keys) {
						check[nRow][nCol] = front.keys;
						if(map[nRow][nCol] == '.') {
							q.add(new Item(new Pair(nRow, nCol), front.keys));
							
						}else if(map[nRow][nCol] == '$') {
							ans++;
							q.add(new Item(new Pair(nRow, nCol), front.keys));
							map[nRow][nCol] = '.';
							
						}else if('a' <= map[nRow][nCol] && map[nRow][nCol] <= 'z') {
							int newKey = map[nRow][nCol] - 'a';
							q.add(new Item(new Pair(nRow, nCol), front.keys | (1<<newKey)));
							
							for(int i=0;i<doors[newKey].size();i++) {
								Pair pos = doors[newKey].get(i);
								map[pos.first][pos.second] = '.';
							}
							doors[newKey].clear();
							map[nRow][nCol] = '.';
						}
					}
				}
			}
			System.out.println(ans);
		}
	}
}