package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj15972_물탱크 {
    static class Item{
        int from;
        Pair p;

        public Item(int from, Pair p) {
            this.from = from;
            this.p = p;
        }
    }
    static class Pair{
        int to, cost;

        public Pair(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static int[][] delta = {{-1,0}, {1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        
        List<Pair>[] adjList = new ArrayList[(n+1)*(m+2)];
        for(int i=0;i<adjList.length;i++) adjList[i] = new ArrayList<>();

        for(int i=0;i<n+1;i++){
            st = new StringTokenizer(br.readLine());
//            i == 0 || i == n 일 때 바깥과 연결
            for(int j=1;j<m+1;j++){
                int cost = Integer.parseInt(st.nextToken());
                if(cost == -1) continue;

                int pos = (i == n) ? i*m+j-m : i*m+j;
                int nextPos = (i == 0) ? 0 : (i == n) ? 0 : pos - m;

                adjList[pos].add(new Pair(nextPos, cost));
                adjList[nextPos].add(new Pair(pos, cost));
            }
        }
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
//            j == 1 || j == m+1 일 때 바깥과 연결
            for(int j=1;j<=m+1;j++){
                int cost = Integer.parseInt(st.nextToken());
                if(cost == -1) continue;

                int pos = (j == m+1) ? i*m+j-1 : i*m+j;
                int nextPos = (j == 1) ? 0 : (j == m+1) ? 0 : pos-1;

                adjList[pos].add(new Pair(nextPos, cost));
                adjList[nextPos].add(new Pair(pos, cost));
            }
        }

        PriorityQueue<Item> pq = new PriorityQueue<>((o1,o2)->{
           if(o1.p.cost > o2.p.cost) return 1;
           return -1;
        });

        int[] minDists = new int[n*m+1];
        Arrays.fill(minDists, Integer.MAX_VALUE);
        minDists[0] = 0;

        for(int i=0;i<adjList[0].size();i++)
            pq.add(new Item(0, adjList[0].get(i)));

        while(!pq.isEmpty()){
            Item item = pq.poll();

            if(minDists[item.p.to] != Integer.MAX_VALUE) continue;
            minDists[item.p.to] = item.p.cost;

            for(int i=0;i<adjList[item.p.to].size();i++){
                Pair p = adjList[item.p.to].get(i);
                if(minDists[p.to] != Integer.MAX_VALUE) continue;

                if(item.p.cost >= p.cost){

                }else{

                }
            }
        }

        System.out.println(Arrays.toString(minDists));
        int ans = 0;
        for(int height : minDists){
            if(height == Integer.MAX_VALUE) ans += h;
            else ans += height;
        }
        System.out.println(ans);
    }
}
