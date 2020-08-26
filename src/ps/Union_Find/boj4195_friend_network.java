package ps.Union_Find;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class boj4195_friend_network {
	static int[] friendSet;
	static int[] rank;
	static Map<String, Integer> names;
	static class Pair{
		String first, second;

		public Pair(String first, String second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int e = sc.nextInt();
			Pair[] edge = new Pair[e];
			names = new HashMap<>();
			
			for(int i=0;i<e;i++) {
				String n1 = sc.next();
				String n2 = sc.next();
				
				edge[i] = new Pair(n1, n2);
				
				if(names.get(n1) == null) {
					names.put(n1, names.size());
				}
				if(names.get(n2) == null) {
					names.put(n2, names.size());
				}
			}
			
			friendSet = new int[names.size()];
			rank = new int[names.size()];
			init();
			
			for(int i=0;i<e;i++) {
				System.out.println(union(names.get(edge[i].first), names.get(edge[i].second)));
			}
		}
	}
	
	static void init() {
		for(int i=0;i<friendSet.length;i++) {
			friendSet[i] = i;
			rank[i] = 1;
		}
	}
	
	static int find(int idx) {
		if(friendSet[idx] == idx) return idx;
		else return friendSet[idx] = find(friendSet[idx]); 
	}
	
	static int union(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if(rank[p1] < rank[p2]) {
			int temp = p1;
			p1 = p2;
			p2 = temp;
		}
		if(p1 == p2) return rank[p1];
		friendSet[p2] = p1;
		
		return rank[p1] += rank[p2];
	}
}
