package ps.구현;

import java.util.Scanner;

public class boj2108_통계학 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int sum = 0;
		int max = -4001;
		int min = 4001;
		int mostFreq = -4000;
		int center = 0;
		
		int[] arr = new int[8001];
		for(int i=0;i<n;i++) {
			int num = sc.nextInt();
			arr[num + 4000]++;
			
			max = Math.max(max, num);
			min = Math.min(min, num);
			sum += num;
			
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
		boolean flag = false;
		for(int i=1;i<arr.length;i++) {
			if(arr[i] == 0) continue;
			if(arr[i] >= arr[mostFreq + 4000]) {
				if(arr[i] == arr[mostFreq + 4000]) {
					if(!flag) {
						mostFreq = i - 4000;
						flag = true;
					}
				}else {
					mostFreq = i - 4000;
					flag = false;
				}
			}
		}
		System.out.println(Math.round((double)sum / n));
		System.out.println(center);
		System.out.println(mostFreq);
		System.out.println(max - min);
	}
}
