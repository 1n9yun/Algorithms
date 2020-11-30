package ps.Unclassified;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Pair{
	int left;
	int right;
	
	public Pair(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

public class boj2261_가장가까운두점2{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		Pair[] input = new Pair[n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(input, new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				return p1.left - p2.left;
			}
		});
		//	BST,
		TreeSet<Pair> candidates = new TreeSet<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				if(p1.right == p2.right) return p1.left - p2.left;
				return p1.right - p2.right;
			}
		});
		candidates.add(input[0]);
		candidates.add(input[1]);
		
		int minDist = (int)getSquaredDist(input[0], input[1]);
		int start = 0;
		
		for(int i=2;i<n;i++) {
			Pair now = input[i];
			
			while(start < i) {
				Pair p = input[start];
				int xDist = now.left - p.left;
				if(xDist * xDist > minDist) {
					candidates.remove(p);
					start++;
				}else break;
			}
			
			int mDist = (int)Math.sqrt((double)minDist)+1;
			Pair low = new Pair(-100000, now.right-mDist);
			Pair high = new Pair(100000, now.right+mDist);
			
			for(Pair p : candidates.subSet(low, high)) {
				minDist = Math.min(minDist, (int)getSquaredDist(now, p));
			}
			candidates.add(now);
		}
		
		System.out.println(minDist);
	}
	
	static double getSquaredDist(Pair p1, Pair p2) {
		return Math.pow(p1.left - p2.left, 2) + Math.pow(p1.right-p2.right, 2);
	}
}