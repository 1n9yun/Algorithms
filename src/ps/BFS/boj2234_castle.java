package ps.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj2234_castle {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Room{
		ArrayList<Pair> pList;
		ArrayList<Integer> adjRoom;
		public Room(ArrayList<Pair> pList, ArrayList<Integer> adjRoom) {
			super();
			this.pList = pList;
			this.adjRoom = adjRoom;
		}
	}
//	 서북동남
	static int[][] delta = {{0,-1},{-1,0},{0,1},{1,0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		int[][] map = new int[n][m];
		boolean[][] check = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		ArrayList<Room> rooms = new ArrayList<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(!check[i][j]) {
					int roomNumber = rooms.size();
					check[i][j] = true;
					
					ArrayList<Pair> pList = new ArrayList<>();
					Queue<Pair> q = new LinkedList<>();

					Pair start = new Pair(i,j);
					pList.add(start);
					q.add(start);
					while(!q.isEmpty()) {
						Pair front = q.poll();
						
						for(int dir=0;dir<4;dir++) {
							int nRow = front.first + delta[dir][0];
							int nCol = front.second + delta[dir][1];
							if((map[front.first][front.second] & 1<<dir) == 0 && !check[nRow][nCol]) {
								check[nRow][nCol] = true;
								pList.add(new Pair(nRow, nCol));
								q.add(new Pair(nRow, nCol));
							}
						}
						map[front.first][front.second] = roomNumber;
					}
					rooms.add(new Room(pList, new ArrayList<>()));
				}
			}
		}
		int[] roomSize = new int[rooms.size()];
		int maxRoomSize = -1;
//		인접한 방 구하기
		boolean[] roomCheck = new boolean[rooms.size()];
		for(int i=0;i<rooms.size();i++) {
			roomCheck[i] = true;
			Room cRoom = rooms.get(i);
			roomSize[i] = cRoom.pList.size();
			maxRoomSize = Math.max(maxRoomSize, roomSize[i]);
			for(Pair p : cRoom.pList) {
				for(int dir=0;dir<4;dir++) {
					int nRow = p.first + delta[dir][0];
					int nCol = p.second + delta[dir][1];
					
					if(0<=nRow && nRow<n && 0<=nCol && nCol<m && map[p.first][p.second] != map[nRow][nCol] && !roomCheck[map[nRow][nCol]]) {
						roomCheck[map[nRow][nCol]] = true;
						cRoom.adjRoom.add(map[nRow][nCol]);
					}
				}
			}
			Arrays.fill(roomCheck, false);
		}
		
//		벽 부숴서 합쳐버리기
		int maxCombinedSize = -1;
		int rIdx = 0;
		for(Room r : rooms) {
			for(int idx : r.adjRoom) {
				maxCombinedSize = Math.max(maxCombinedSize, roomSize[rIdx] + roomSize[idx]);
			}
			rIdx++;
		}
		
		System.out.println(rooms.size());
		System.out.println(maxRoomSize);
		System.out.println(maxCombinedSize);
	}
}
