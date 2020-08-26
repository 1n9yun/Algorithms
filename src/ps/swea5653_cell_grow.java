package ps;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class swea5653_cell_grow {
	static class Item{
		int row, col, health, remain;
		boolean active;
		
		public Item(int row, int col, int health, int remain, boolean active) {
			super();
			this.row = row;
			this.col = col;
			this.health = health;
			this.remain = remain;
			this.active = active;
		}
		@Override
		public boolean equals(Object obj) {
			Item t = (Item) obj;
			if(this.row == t.row && this.col == t.col) return true;
			else return false;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + row;
			result = prime * result + col;
			
			return result;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			
			Set<Item> set = new HashSet<>();
			
			Queue<Item> q = new ArrayDeque<>();
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					int health = sc.nextInt();
					
					set.add(new Item(i, j, health, health, false));
					q.add(new Item(i, j, health, health, false));
				}
			}
			
			int cnt = 0;
			while(!q.isEmpty()) {
				cnt++;
				int size = q.size();
				if(cnt == k) {
					System.out.println(size);
					break;
				}
				
				System.out.println("Time " + cnt);
				for(int i=0;i<size;i++) {
					Item front = q.poll();
					System.out.println(front.row + " " + front.col + " " + front.remain + " " + front.active);
					if(front.active && front.health == front.remain) {
						for(int dir=0;dir<4;dir++) {
							int nRow = front.row + delta[dir][0];
							int nCol = front.col + delta[dir][1];
							
							Item t = new Item(nRow, nCol, front.health, front.health, false);
							if(set.add(t)) {
								System.out.println(nRow + " " + nCol + " added");
								q.add(t);
							}else {
								System.out.println("already exists");
							}
						}
						q.add(new Item(front.row, front.col, front.health, front.remain - 1, front.active));
					}else{
						if(front.active) {
							System.out.println("active remaining");
							if(front.remain > 1) {
								q.add(new Item(front.row, front.col, front.health, front.remain - 1, front.active));
							}
						}else {
							System.out.print("non-active ");
							if(front.remain > 1) {
								System.out.println("remaining");
								q.add(new Item(front.row, front.col, front.health, front.remain - 1, front.active));
							}else if(front.remain == 1) {
								System.out.println("activated");
								q.add(new Item(front.row, front.col, front.health, front.health, true));
							}
						}
					}
				}
				System.out.println();
			}
		}
	}
}
