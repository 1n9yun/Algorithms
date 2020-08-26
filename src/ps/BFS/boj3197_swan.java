package ps.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj3197_swan {
	static class MyQ<T>{
		T[] data;
		int maxSize;
		int push;
		int front;
		int size;
		
		public MyQ(int maxSize) {
			this.maxSize = maxSize;
			front = -1;
			push = -1;
			size = 0;
		}
		public void add(T o) {
			size++;
			push = (push + 1) % maxSize;
			data[++push] = o;
		}
		public T poll() {
			size--;
			front = (front + 1) % maxSize;
			return data[++front];
		}
		public boolean isEmpty() {
			return push == front;
		}
		public int size() {
			return size; 
		}
	}
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Swan{
		Pair pos;
		int number;
		
		public Swan(Pair pos, int number) {
			super();
			this.pos = pos;
			this.number = number;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[][] lake = new char[r][c];
		int[][] day = new int[r][c];
		
//		Queue<Swan> swanQ = new LinkedList<>();
//		Queue<Pair> waterQ = new LinkedList<>();
		MyQ<Swan> swanQ = new MyQ(r*c);
		MyQ<Pair> waterQ = new MyQ(r*c);
		swanQ.data = new Swan[r*c];
		waterQ.data = new Pair[r*c];
		
		for(int i=0;i<r;i++) {
			lake[i] = br.readLine().toCharArray();
			for(int j=0;j<c;j++) {
				if(lake[i][j] == 'L') {
					lake[i][j] = '.';
					int number = swanQ.isEmpty() ? 1 : 2;
					day[i][j] = number;
					swanQ.add(new Swan(new Pair(i, j), number));
				}
			}
		}
		
		while(!swanQ.isEmpty()) {
			Swan swanFront = swanQ.poll();
			
			for(int dir=0;dir<4;dir++) {
				int nRow = swanFront.pos.first + delta[dir][0];
				int nCol = swanFront.pos.second + delta[dir][1];
				
				if(0<=nRow && nRow<r && 0<=nCol && nCol<c && lake[nRow][nCol] == '.') {
					if(day[nRow][nCol] == 0) {
						day[nRow][nCol] = swanFront.number;
						swanQ.add(new Swan(new Pair(nRow, nCol), swanFront.number));
					}else if(day[nRow][nCol] != swanFront.number) {
						System.out.println(0);
						return;
					}
				}
			}
		}
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(lake[i][j] == '.') {
					for(int dir=0;dir<4;dir++) {
						int nRow = i + delta[dir][0];
						int nCol = j + delta[dir][1];
						
						if(0<=nRow && nRow<r && 0<=nCol && nCol<c) {
							if(lake[nRow][nCol] == 'X') {
//								얼음과 인접한 물 push
								waterQ.add(new Pair(i, j));
								break;
							}
						}
					}
				}
			}
		}
		
		int count = 0;
		while(!waterQ.isEmpty()){
			int waterSize = waterQ.size();
			count++;
			for(int i=0;i<waterSize;i++) {
				Pair waterFront = waterQ.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = waterFront.first + delta[dir][0];
					int nCol = waterFront.second + delta[dir][1];
					
					if(0<=nRow && nRow<r && 0<=nCol && nCol<c && lake[nRow][nCol] == 'X') {
						lake[nRow][nCol] = '.';
						waterQ.add(new Pair(nRow, nCol));
						
						for(int nDir=0;nDir<4;nDir++) {
							int nnRow = nRow + delta[nDir][0];
							int nnCol = nCol + delta[nDir][1];
							if(0<=nnRow && nnRow<r && 0<=nnCol && nnCol<c && day[nnRow][nnCol] != 0) {
								int number = day[nnRow][nnCol];
								day[nRow][nCol] = number; 
								swanQ.add(new Swan(new Pair(nRow, nCol), number));
								break;
							}
						}
					}
				}
			}
			while(!swanQ.isEmpty()) {
				Swan swanFront = swanQ.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = swanFront.pos.first + delta[dir][0];
					int nCol = swanFront.pos.second + delta[dir][1];
					
					if(0<=nRow && nRow<r && 0<=nCol && nCol<c && lake[nRow][nCol] == '.') {
						if(day[nRow][nCol] == 0) {
							day[nRow][nCol] = swanFront.number;
							swanQ.add(new Swan(new Pair(nRow, nCol), swanFront.number));
						}else {
							if(day[nRow][nCol] != swanFront.number) {
								System.out.println(count);
								return;
							}
						}
					}
				}
			}
		}
	}
}
