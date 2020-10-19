package ps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class swea10888_음식배달 {
	static class Item{
		int r, c;
		int cost;

		public Item(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
	static List<Item> store;
	static List<Item> house;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			ans = 987654321;
			
			int n = sc.nextInt();
			
			int[][] map = new int[n][n];
			store = new ArrayList<>();
			house = new ArrayList<>();
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j] = sc.nextInt();
					
					if(map[i][j] == 1) house.add(new Item(i, j, 0));
					else if(map[i][j] != 0) store.add(new Item(i, j, map[i][j]));
				}
			}
			
			for(int i=1;i<=store.size();i++) {
				comb(0, 0, store.size(), new int[i]);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void comb(int idx, int cnt, int size, int[] result) {
		if(cnt == result.length) {
			int temp = 0;
			for(int i=0;i<result.length;i++) temp += store.get(result[i]).cost;
			
			for(int i=0;i<house.size();i++) {
				int min = 987654321;
				Item from = house.get(i);
				
				for(int j=0;j<result.length;j++) {
					Item to = store.get(result[j]);
					min = Math.min(min, Math.abs(from.r - to.r) + Math.abs(from.c - to.c));
				}
				temp += min;
			}

			ans = Math.min(ans, temp);
			return;
		}
		
		for(int i=idx;i<size;i++) {
			result[cnt] = i;
			comb(i + 1, cnt + 1, size, result);
		}
	}
}
