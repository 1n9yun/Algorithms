package ps;

import java.util.Scanner;

public class boj2108_통계학 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int sum = 0;
		int max = -4001;
		int min = 4001;
		int mostFreq = 0;
		int center = 0;
		
		int[] arr = new int[8001];
		for(int i=0;i<n;i++) {
			int num = sc.nextInt();
			arr[num + 4000]++;
			
			max = Math.max(max, num);
			min = Math.min(min, num);
			sum += num;
			if(arr[num + 4000] > arr[mostFreq + 4000]) mostFreq = num;
		}
		int count = 0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] == 0) continue;
			count += arr[i];
			if(count > n / 2) {
				center = i - 4000;
				break;
			}
			
		}
		
		System.out.println(Math.round((double)sum / n));
		System.out.println(center);
		System.out.println(mostFreq);
		System.out.println(max - min);
	}
}
