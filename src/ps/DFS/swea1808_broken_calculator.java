package ps.DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;

public class swea1808_broken_calculator{
	static int target, ans;
	static ArrayList<Integer> numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			numbers = new ArrayList<>();
			for(int i=0;i<10;i++) {
				if(sc.nextInt() == 1) numbers.add(i);
			}
			target = sc.nextInt();
			
			ans = 987654321;
			
			if(target == 1 && numbers.contains((Integer)1)) ans = 2;
			else dfs(0, 1, 0);
			
			System.out.println("#" + tc + " " + (ans == 987654321 ? -1 : ans));
		}
	}
	
	static void dfs(int cnt, int prev, int opr) {
		if(cnt >= ans) return;
		if(prev > target || opr > target) return;
		if(prev == target) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		int multiple = prev * opr;
		if(multiple <= target && prev != 0 && opr != 0 && opr != 1 && target % multiple == 0) 
			dfs(cnt + 1, multiple, 0);
		
		for(int i=0;i<numbers.size();i++) {
			if(opr == 0 && numbers.get(i) == 0) continue;
			dfs(cnt + 1, prev, opr * 10 + numbers.get(i));
		}
	}
}
//public class Main{
//	static class Item{
//		int prev, operand;
//
//		public Item(int prev, int operand) {
//			super();
//			this.prev = prev;
//			this.operand = operand;
//		}
//	}
//	static ArrayList<Integer> numbers;
//	static int target;
//	static HashSet<Integer>[] check;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		int TC = sc.nextInt();
//		check = new HashSet[1000001];
//		for(int i=0;i<1000001;i++) {
//			check[i] = new HashSet<>();
//		}
//		
//		for(int tc=1;tc<=TC;tc++) {
//			numbers = new ArrayList<>();
//			for(int i=0;i<10;i++) {
//				int n = sc.nextInt();
//				
//				if(n == 1) numbers.add(i);
//			}
//			target = sc.nextInt();
//			
//			for(HashSet<Integer> sub : check) {
//				sub.clear();
//			}
//			check[1].add(0);
//			Deque<Item> q = new ArrayDeque<>();
//			q.add(new Item(1, 0));
//			int cnt = 0;
//			int ans = -1;
//			
//			while(!q.isEmpty()) {
//				int size = q.size();
//				for(int idx=0;idx<size;idx++) {
//					Item front = q.poll();
//					if(front.operand * front.prev == target) {
//						ans = cnt + 1;
//						q.clear();
//						break;
//					}
//					if(front.operand > target || front.prev > target) continue;
//					
//					for(int i=0;i<numbers.size();i++) {
//						int nextNumber = front.operand * 10 + numbers.get(i);
//						if(nextNumber <= target && !check[front.prev].contains(nextNumber)) {
//							check[front.prev].add(nextNumber);
//							q.add(new Item(front.prev, nextNumber));
//						}
//					}
//					int nextNumber = front.prev * front.operand;
//					if(nextNumber != 0 && target % nextNumber == 0 && nextNumber < target && !check[nextNumber].contains(0)) {
//						check[nextNumber].add(0);
//						q.add(new Item(nextNumber, 0));
//					}
//				}
//				cnt++;
//			}
//			
//			System.out.println("#" + tc + " " + ans);
//		}
//	}
//}