package ps.Permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class boj17825_dice_yootnori {
	static ArrayList<Integer>[] adjList = getBoard();
	static int[] input;
	static int[] points = {0, 2, 4, 6, 8, 10, 13, 16, 19, 12, 14, 16, 18, 20, 22, 24, 22, 24, 26, 28, 30, 28, 27, 26, 32, 34, 36, 38, 25, 30, 35, 40, 0};
	static int[] markerStates;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		input = new int[10];
		markerStates = new int[points.length];
		
		for(int i=0;i<10;i++) input[i] = sc.nextInt();
		
		perm(0, new int[10]);
		
		System.out.println(ans);
	}
	static void perm(int idx, int[] result) {
		if(idx == result.length) {
			int[] markers = new int[4];
			int res = 0;
			Arrays.fill(markerStates, -1);
			
			for(int i=0;i<input.length;i++) {
				if(markers[result[i]] == points.length - 1) return;
				
				int prevPos = markers[result[i]];
				int nextPos = moveNext(markers[result[i]], input[i]);
				if(markerStates[nextPos] == -1 || nextPos == points.length - 1) {
					markers[result[i]] = nextPos;
					markerStates[nextPos] = result[i];
					markerStates[prevPos] = -1;
					
					res += points[markers[result[i]]];
				}else return;
			}
			ans = Math.max(ans, res);
			return;
		}
		
		for(int i=0;i<4;i++) {
			result[idx] = i;
			perm(idx + 1, result);
		}
	}
	
	static int moveNext(int pos, int len) {
		pos = adjList[pos].get(0);
		while(--len != 0) {
			if(pos == points.length - 1) return pos;
			pos = adjList[pos].get(adjList[pos].size() - 1);
		}
		return pos;
	}
	
	static ArrayList<Integer>[] getBoard(){
		adjList = new ArrayList[33];
		for(int i=0;i<33;i++) adjList[i] = new ArrayList<>();
		
		adjList[0].add(1);
		adjList[1].add(2);
		adjList[2].add(3);
		adjList[3].add(4);
		adjList[4].add(5);
		
		adjList[5].add(6);
		adjList[5].add(9);

		adjList[6].add(7);
		adjList[7].add(8);
		adjList[8].add(28);
		
		adjList[9].add(10);
		adjList[10].add(11);
		adjList[11].add(12);
		adjList[12].add(13);
		
		adjList[13].add(14);
		adjList[13].add(16);
		
		adjList[14].add(15);
		adjList[15].add(28);
		
		adjList[16].add(17);
		adjList[17].add(18);
		adjList[18].add(19);
		adjList[19].add(20);
		
		adjList[20].add(21);
		adjList[20].add(24);
		
		adjList[21].add(22);
		adjList[22].add(23);
		adjList[23].add(28);
		
		adjList[24].add(25);
		adjList[25].add(26);
		adjList[26].add(27);
		adjList[27].add(31);
		
		adjList[28].add(29);
		adjList[29].add(30);
		adjList[30].add(31);
		
		adjList[31].add(32);
		return adjList;
	}
}
