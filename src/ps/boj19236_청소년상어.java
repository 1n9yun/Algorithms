package ps;

import java.util.Arrays;
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
		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", num=" + num + ", dir=" + dir + "]";
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
		public int eat(int target, Fish[] fishes, int[][] field) {
			field[this.r][this.c] = -1;
			field[fishes[target].r][fishes[target].c] = 0;
			fishes[target].alive = false;
			
			this.r = fishes[target].r;
			this.c = fishes[target].c;
			this.dir = fishes[target].dir;
			
			return fishes[target].num;
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
		int ans = ((Shark) fishes[0]).eat(field[0][0], fishes, field);
		
		System.out.println(ans + solve(field, fishes));
	}
	
	static int solve(int[][] field, Fish[] fishes) {
		System.out.println(Arrays.toString(fishes));
		print(field, fishes);
		new Scanner(System.in).nextLine();
		
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
				
				if(0<=nRow && nRow<FIELD_SIZE && 0<=nCol && nCol<FIELD_SIZE && !(newField[nRow][nCol] == 0 || newField[nRow][nCol] == -1)) {
					moved = true;
					fishSwap(i, newField[nRow][nCol], newFishes, newField);
				}else {
					if(newFishes[i].dir % 8 + 1 == nowDir) break;
					else newFishes[i].turn_anti45();
				}
			}
//			print(newField, newFishes);
//			new Scanner(System.in).nextLine();
		}
		
		System.out.println("Moved!");
		print(newField, fishes);
		new Scanner(System.in).nextLine();
		
		Shark shark = (Shark) newFishes[0];
		
		int nRow = shark.r + delta[shark.dir][0];
		int nCol = shark.c + delta[shark.dir][1];
		
		while(true) {
			if(0<=nRow && nRow<FIELD_SIZE && 0<=nCol && nCol<FIELD_SIZE) {
				if(newField[nRow][nCol] != -1) {
					System.out.println("eat : " + newField[nRow][nCol]);
					
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
			}else {
				System.out.println("끗");
				return res;
			}
		}
	}		
	
	static void fishSwap(int left, int right, Fish[] fishes, int[][] field) {
		field[fishes[left].r][fishes[left].c] = right;
		field[fishes[right].r][fishes[right].c] = left;
		
		if(right == -1) {
			fishes[left].r += delta[fishes[left].dir][0];
			fishes[left].c += delta[fishes[left].dir][1];
			return;
		}
		
		int tR = fishes[left].r;
		fishes[left].r = fishes[right].r;
		fishes[right].r = tR;
		
		int tC = fishes[left].c;
		fishes[left].c = fishes[right].c;
		fishes[right].c = tC;
	}
	
	static void print(int[][] field, Fish[] fishes) {
		System.out.println(Arrays.toString(fishes));
		for(int i=0;i<field.length;i++) {
			for(int j=0;j<field[i].length;j++) {
				int arrow = 0;
				if(field[i][j] != -1) arrow = fishes[field[i][j]].dir;
				
				System.out.print(field[i][j] + arrows[arrow] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	static String[] arrows = {"","↑", "↖", "←","↙","↓","↘","→","↗"};
}
