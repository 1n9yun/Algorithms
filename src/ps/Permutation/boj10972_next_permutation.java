package ps.Permutation;

import java.util.Scanner;

public class boj10972_next_permutation {
	static int[] permutation;
	static int n;
	 public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		permutation = new int[n];
		
		for(int i=0;i<n;i++) {
			permutation[i] = sc.nextInt();
		}

		if(nextPermutation()) {
			for(int i=0;i<n;i++) {
				System.out.print(permutation[i] + " ");
			}
		}else System.out.println(-1);
	}
	 
	 static boolean nextPermutation() {
		 int left = -1;
		 for(int i=0;i<n-1;i++) {
			 if(permutation[i] < permutation[i+1]) left = i;
		 }
		 if(left == -1) return false;
		 
		 int right = -1;
		 for(int i=n-1;i>=0;i--) {
			 if(permutation[i] > permutation[left]) {
				 right = i;
				 break;
			 }
		 }
		 
		 swap(left, right);
		 
		 int sLeft = left + 1;
		 int sRight = n - 1;
		 
		 while(sLeft < sRight) {
			 swap(sLeft, sRight);
			 sLeft++; sRight--;
		 }
		 
		 return true;
	 }
	 
	 static void swap(int left, int right) {
		 int temp = permutation[left];
		 permutation[left] = permutation[right];
		 permutation[right] = temp;
	 }
}
