package ps.Permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class boj3967_magicStar {
	static int[][] starIdx = {
			{0,2,5,7},
			{0,3,6,10},
			{7,8,9,10},
			{1,2,3,4},
			{1,5,8,11},
			{4,6,9,11}
	};
	static boolean flag = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] magicStar = new char[12];		
		int inputIdx = 0;
		for(int i=0;i<5;i++) {
			char[] input = sc.next().toCharArray();
			
			for(int j=0;j<9;j++) {
				if(input[j] != '.')
					magicStar[inputIdx++] = input[j];
			}
		}
		
		char[] base = new char[12];
		for(int i=0;i<12;i++) base[i] = (char)('A' + i);
		boolean[] check = new boolean[12];
		for(int i=0;i<magicStar.length;i++) 
			if(magicStar[i] != 'x') check[magicStar[i] - 'A'] = true;
		
		perm(0, base, magicStar, check);
	}
	static void perm(int idx, char[] base, char[] result, boolean[] check) {
		if(flag) return;
		if(idx == result.length) {
			for(int i=0;i<6;i++) {
				int sum = 0;
				for(int j=0;j<4;j++) {
					sum += (int)(result[starIdx[i][j]] - 'A');
				}
				if(sum != 22) return;
			}
			
			System.out.println("...." + result[0] + "....");
			System.out.println("." + result[1] + "." + result[2] + "." +  result[3] + "." + result[4] + ".");
			System.out.println(".." + result[5] + "..." + result[6] + "..");
			System.out.println("." + result[7] + "." + result[8] + "." +  result[9] + "." + result[10] + ".");
			System.out.println("...." + result[11] + "....");
			flag = true;
			return;
		}
		
		if(result[idx] != 'x') perm(idx + 1, base, result, check);
		else {
			for(int i=0;i<base.length;i++) {
				if(check[i]) continue;
				result[idx] = base[i];
				check[i] = true;
				perm(idx + 1, base, result, check);
				result[idx] = 'x';
				check[i] = false;
			}
		}
	}
	
	static boolean deepEquals(char[] _1, char[] _2) {
		for(int i=0;i<_1.length;i++) {
			if(_1[i] != _2[i]) return false;
		}
		return true;
	}
}
