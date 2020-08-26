package ps.Union_Find;

import java.util.Arrays;

public class Reference {
	static int[] disjointSet;
//	보통 랭크를 안써도 괜찮다고 함
	static int[] rank;
	
	public static void main(String[] args) {
		int n = 10;
		disjointSet = new int[n];
		rank = new int[n];
		
		init();
		
		union(0, 1);
		union(2, 3);
		union(4, 5);
		union(6, 7);
		union(8, 9);
		
		union(1, 2);
		union(3, 4);
		union(5, 6);
		union(7, 8);
		union(9, 0);
	}
	
	static void init() {
		for(int i=0;i<disjointSet.length;i++) {
			disjointSet[i] = i;
			rank[i] = 1;
		}
	}
	
//	find parent
	static int find(int idx) {
		if(idx == disjointSet[idx]) return idx;
		
		return disjointSet[idx] = find(disjointSet[idx]);
	}
	
//	unite
	static void union(int idx1, int idx2) {
//		for test
		System.out.println("Unite " + idx1 + " " + idx2);
		
		idx1 = find(idx1);
		idx2 = find(idx2);
		if(idx1 == idx2) return;
		
//		깊이가 얕은 쪽이 깊은 쪽 밑에 합쳐지도록
		if(rank[idx1] > rank[idx2]) {
//			swap( idx1, idx2 )
			int temp = idx1;
			idx1 = idx2;
			idx2 = temp;
		}
		disjointSet[idx1] = idx2;
		
//		큰 쪽에 작은 쪽 넣었으니까 그만큼 랭크 ++ 
		rank[idx2] += rank[idx1];
		
//		for test
		int[] indexes = new int[rank.length];
		for(int i=0;i<indexes.length;i++) indexes[i] = i;
		System.out.println(Arrays.toString(indexes) + " index");
		System.out.println(Arrays.toString(disjointSet));
		System.out.println(Arrays.toString(rank));
		System.out.println();
	}
}
