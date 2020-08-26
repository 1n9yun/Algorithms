package ps.Unclassified;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class swea7701_yeomra_name_sort {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			String[] names = new String[n];
			for(int i=0;i<n;i++) {
				names[i] = sc.next();
			}
			Arrays.sort(names, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					if(s1.length() == s2.length()) {
						return s1.compareTo(s2);
					}else if(s1.length() > s2.length()) return 1;
					else return -1;
				}
			});
			
			System.out.println("#" + tc);
			System.out.println(names[0]);
			for(int i=1;i<n;i++) {
				if(!names[i-1].equals(names[i])) System.out.println(names[i]);
			}
		}
	}
}
