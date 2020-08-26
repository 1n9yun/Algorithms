package day0130;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Item{
	int state;
	int cnt;
	//	cnt 필요 없음, stringbuilder 없애고 check를 <char, int>로 바꿔서 to부터 백트래킹하면 경로 저장하고 있을 필요 없음
	StringBuilder res;
	public Item(int state, int cnt, StringBuilder res) {
		this.state = state;
		this.cnt = cnt;
		this.res = res;
	}
}

public class Main {
	static char[] convert = {'D','S','L','R'};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int tc=0;tc<t;tc++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			boolean[] check = new boolean[10000];
			Queue<Item> q = new LinkedList<>();
			check[from] = true;
			q.add(new Item(from, 0, new StringBuilder()));
			
			while(!q.isEmpty()) {
				Item front = q.poll();
				
				if(front.state == to) {
					System.out.println(front.res.toString());
					break;
				}
				
				for(int i=0;i<4;i++) {
					int nState = Transition(front.state, i);
					if(!check[nState]) {
						check[nState] = true;
						StringBuilder nRes = new StringBuilder();
						nRes.append(front.res);
						nRes.append(convert[i]);
						q.add(new Item(nState, front.cnt + 1, nRes));
					}
				}
				front.res.delete(0, front.res.length());
			}
			q.clear();
		}
	}
	static int Transition(int from, int mode) {
		if(mode == 0) return from*2%10000;
		else if(mode == 1) return ((from-1)+10000)%10000;
		//	amazing
		else if(mode == 2) return (from%1000)*10 + from/1000;
		//	amazing
		else return (from%10)*1000 + from/10;
	}
}
