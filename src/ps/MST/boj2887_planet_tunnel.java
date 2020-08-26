package ps.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2887_planet_tunnel {
	static class Planet{
		int idx, x, y, z;

		public Planet(int idx, int x, int y, int z) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	static class Triple{
		int first, second, third;

		public Triple(int first, int second, int third) {
			super();
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}
	
	static int[] planetSet;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Planet[] planets = new Planet[n];
		planetSet = new int[n];
		for(int i=0;i<n;i++) planetSet[i] = i;
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			planets[i] = new Planet(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		PriorityQueue<Triple> pq = new PriorityQueue<>(new Comparator<Triple>() {
			@Override
			public int compare(Triple t1, Triple t2) {
				return t1.third - t2.third;
			}
		});
		
		Arrays.sort(planets, (o1, o2) -> {return o1.x - o2.x;});
		for(int i=0;i<n-1;i++) pq.add(new Triple(planets[i].idx, planets[i+1].idx, Math.abs(planets[i].x - planets[i+1].x)));
		
		Arrays.sort(planets, (o1, o2) -> {return o1.y - o2.y;});
		for(int i=0;i<n-1;i++) pq.add(new Triple(planets[i].idx, planets[i+1].idx, Math.abs(planets[i].y - planets[i+1].y)));
		
		Arrays.sort(planets, (o1, o2) -> {return o1.z - o2.z;});
		for(int i=0;i<n-1;i++) pq.add(new Triple(planets[i].idx, planets[i+1].idx, Math.abs(planets[i].z - planets[i+1].z)));
		
		int cnt = 0;
		int ans = 0;
		while(!pq.isEmpty()) {
			Triple front = pq.poll();
			
			if(cnt == n - 1) break;
			if(union(front.first, front.second)) {
				cnt++;
				ans += front.third;
			}
		}
		System.out.println(ans);
	}
	
	static int find(int idx) {
		if(planetSet[idx] == idx) return idx;
		else return planetSet[idx] = find(planetSet[idx]);
	}
	
	static boolean union(int p1, int p2) {
		p1 = find(p1);
		p2 = find(p2);
		
		if(p1 == p2) return false;
		
		planetSet[p1] = p2;
		return true;
	}
}
