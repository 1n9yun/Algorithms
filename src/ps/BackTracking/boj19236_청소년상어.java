package ps.BackTracking;

import java.util.Scanner;

public class boj19236_청소년상어 {
	static class Fish{
		int r, c;
		int num;
		int dir;
		boolean alive;
		public Fish() {}
		public Fish(int r, int c, int num, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
			this.alive = true;
		}
		public void turn_anti45() {
			this.dir = this.dir % 8 + 1;
		}
		public Fish clone() {
			Fish res = new Fish();
			res.r = this.r;
			res.c = this.c;
			res.dir = this.dir;
			res.alive = this.alive;
			res.num = this.num;
			return res;
		}
	}
	static class Shark extends Fish{
		public Shark() {
			super();
			this.num = 0;
		}
		public Shark clone() {
			Shark res = new Shark();
			res.r = this.r;
			res.c = this.c;
			res.dir = this.dir;
			return res;
		}
	}
	static int[][] delta = {{0,0},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static final int FIELD_SIZE = 4;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Fish[] fishes = new Fish[FIELD_SIZE * FIELD_SIZE + 1];
		int[][] field = new int[FIELD_SIZE][FIELD_SIZE];
		
		for(int i=0;i<FIELD_SIZE;i++) {
			for(int j=0;j<FIELD_SIZE; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				fishes[a] = new Fish(i,j,a,b);
				field[i][j] = a;
			}
		}
		fishes[0] = new Shark();
		int init = field[0][0];
		fishes[0].r = fishes[init].r;
		fishes[0].c = fishes[init].c;
		fishes[0].dir = fishes[init].dir;
		fishes[init].alive = false;
		field[fishes[0].r][fishes[0].c] = 0;
		
		System.out.println(init + solve(field, fishes));
	}
	
	static int solve(int[][] field, Fish[] fishes) {
		int res = 0;
		
		int[][] newField = new int[FIELD_SIZE][FIELD_SIZE];
		Fish[] newFishes = new Fish[fishes.length];
		
		for(int i=0;i<FIELD_SIZE;i++) for(int j=0;j<FIELD_SIZE;j++) {
			newField[i][j] = field[i][j];
		}
		for(int i=1;i<fishes.length;i++) newFishes[i] = fishes[i].clone();
		newFishes[0] = ((Shark) fishes[0]).clone();
		
		for(int i=1;i<newFishes.length;i++) {
			if(!newFishes[i].alive) continue;
			
			boolean moved = false;
			int nowDir = newFishes[i].dir;
			
			while(!moved) {
				int nRow = newFishes[i].r + delta[newFishes[i].dir][0];
				int nCol = newFishes[i].c + delta[newFishes[i].dir][1];
				
				if(0<=nRow && nRow<FIELD_SIZE && 0<=nCol && nCol<FIELD_SIZE && newField[nRow][nCol] != 0) {
					moved = true;
					fishSwap(i, newField[nRow][nCol], newFishes, newField);
				}else {
					if(newFishes[i].dir % 8 + 1 == nowDir) break;
					else newFishes[i].turn_anti45();
				}
			}
		}
		
		Shark shark = (Shark) newFishes[0];
		
		int nRow = shark.r + delta[shark.dir][0];
		int nCol = shark.c + delta[shark.dir][1];
		
		while(true) {
			if(0<=nRow && nRow<FIELD_SIZE && 0<=nCol && nCol<FIELD_SIZE) {
				if(newField[nRow][nCol] != -1) {
					int target = newField[nRow][nCol];
					int sR = shark.r;
					int sC = shark.c;
					int sDir = shark.dir;
					
					newField[shark.r][shark.c] = -1;
					newField[newFishes[target].r][newFishes[target].c] = 0;
					newFishes[target].alive = false;
					shark.r = newFishes[target].r;
					shark.c = newFishes[target].c;
					shark.dir = newFishes[target].dir;
					
					res = Math.max(res, solve(newField, newFishes) + target);
					
					shark.dir = sDir;
					shark.c = sC;
					shark.r = sR;
					newFishes[target].alive = true;
					newField[newFishes[target].r][newFishes[target].c] = target;
					newField[sR][sC] = 0;
				}
				nRow += delta[shark.dir][0];
				nCol += delta[shark.dir][1];
			}else return res;
		}
	}		
	
	static void fishSwap(int left, int right, Fish[] fishes, int[][] field) {
		if(right == -1) {
			field[fishes[left].r][fishes[left].c] = -1;
			field[fishes[left].r + delta[fishes[left].dir][0]][fishes[left].c + delta[fishes[left].dir][1]] = left;
			fishes[left].r += delta[fishes[left].dir][0];
			fishes[left].c += delta[fishes[left].dir][1];
			return;
		}
		
		field[fishes[left].r][fishes[left].c] = right;
		field[fishes[right].r][fishes[right].c] = left;
		
		int tR = fishes[left].r;
		fishes[left].r = fishes[right].r;
		fishes[right].r = tR;
		
		int tC = fishes[left].c;
		fishes[left].c = fishes[right].c;
		fishes[right].c = tC;
	}
}
