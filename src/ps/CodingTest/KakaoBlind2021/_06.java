package ps.CodingTest.KakaoBlind2021;

import java.util.LinkedList;
import java.util.Queue;

public class _06 {
	public static void main(String[] args) {
		int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
		
		solution(board, 1, 0);
	}
	static class Pos{
		int r, c;
		
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static class Pair{
		Pos c1, c2;

		public Pair(Pos c1, Pos c2) {
			super();
			this.c1 = c1;
			this.c2 = c2;
		}
	}
	static int[][] delta = {{0,-1},{0,1},{-1,0},{1,0}};
	static public int solution(int[][] board, int r, int c) {
		Pair[] cardPair = new Pair[9];
		boolean[] check = new boolean[9];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(board[i][j] == 0) continue;
				
				check[board[i][j]] = true;
				if(cardPair[board[i][j]] == null) {
					cardPair[board[i][j]] = new Pair(new Pos(i, j), null);
				}else {
					cardPair[board[i][j]].c2 = new Pos(i,j);
				}
			}
		}
		
		System.out.println(getControlCount(new Pos(r, c), new Pos(2,3), board, check));
		System.exit(0);
//		처음으로 없앨 카드
		for(int i=1;i<=8;i++) {
			
		}
		
		return 1;
    }
	
	static class Item{
		Pos pos;
		int count;
		public Item(Pos pos, int count) {
			super();
			this.pos = pos;
			this.count = count;
		}
	}
//	이동 키 횟수
	static int getControlCount(Pos pos, Pos target, int[][] board, boolean[] check) {
		boolean[][] countCheck = new boolean[4][4];
		
		Queue<Item> q = new LinkedList<>();
		q.add(new Item(pos, 0));
		countCheck[pos.r][pos.c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i=0;i<size;i++) {
				Item front = q.poll();
				
				if(front.pos.r == target.r && front.pos.c == target.c) return front.count;
				
				for(int dir=0;dir<4;dir++) {
					int nRow = front.pos.r + delta[dir][0];
					int nCol = front.pos.c + delta[dir][1];
					
					if(0<=nRow && nRow<4 && 0<=nCol && nCol<4 && !countCheck[nRow][nCol]) {
						countCheck[nRow][nCol] = true;
						q.add(new Item(new Pos(nRow, nCol), front.count + 1));
					}
					
					int pRow = front.pos.r + delta[dir][0];
					int pCol = front.pos.c + delta[dir][1];
					
					boolean moved = false;
					while(0<=pRow && pRow<4 && 0<=pCol && pCol<4) {
						if((board[pRow][pCol] != 0 && check[board[pRow][pCol]]) && !countCheck[pRow][pCol]) {
							moved = true;
							countCheck[pRow][pCol] = true;
							q.add(new Item(new Pos(pRow, pCol), front.count + 1));
							break;
						}else if((board[pRow][pCol] != 0 && check[board[pRow][pCol]]) && countCheck[pRow][pCol]) break;
						else {
							pRow = front.pos.r + delta[dir][0];
							pCol = front.pos.c + delta[dir][1];
						}
					}
					if(!moved && !(0<=pRow && pRow<4 && 0<=pCol && pCol<4)) {
						pRow = front.pos.r - delta[dir][0];
						pCol = front.pos.c - delta[dir][1];
						
						if(!countCheck[pRow][pCol]) {
							countCheck[pRow][pCol] = true;
							q.add(new Item(new Pos(pRow, pCol), front.count + 1));
						}
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}
