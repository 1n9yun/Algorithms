package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj10217_KCMTravel {
    static class Item{
        int to, cost, time;

        public Item(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }
    static ArrayList<Item>[] adjList;
    static int[][] dp;
    static int n,m,k;
    static int stoi(String s) {return Integer.parseInt(s);}
    static final int MAX = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = stoi(br.readLine());
        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = stoi(st.nextToken());
            m = stoi(st.nextToken());
            k = stoi(st.nextToken());

            adjList = new ArrayList[n+1];
            dp = new int[n+1][m+1];
            for(int[] sub : dp) Arrays.fill(sub, MAX);
            for(int i=0;i<adjList.length;i++) adjList[i] = new ArrayList<>();

//            1 ~ N 까지 가는거야
            for(int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());

                int u = stoi(st.nextToken());
                int v = stoi(st.nextToken());
                int c = stoi(st.nextToken());
                int d = stoi(st.nextToken());

                adjList[u].add(new Item(v, c, d));
            }

            PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
            pq.addAll(adjList[1]);

            int answer = -1;
            while(!pq.isEmpty()){
                Item item = pq.poll();

                if(item.to == n) {
                    answer = item.time;
                    break;
                }
                if(dp[item.to][item.cost] < item.time) continue;

                for(Item next : adjList[item.to]){
                    if(item.cost + next.cost <= m) {
                        int nextCost = next.cost + item.cost;
                        int nextTime = next.time + item.time;
                        if (dp[next.to][nextCost] > nextTime) {
                            dp[next.to][nextCost] = nextTime;
                            pq.add(new Item(next.to, nextCost, nextTime));
                        }
                    }
                }
            }
            System.out.println(answer == -1 ? "Poor KCM" : answer);
//            int result = back(1, 0);
//            System.out.println(result == MAX ? "Poor KCM" : result);
        }
    }
//    static int back(int v, int cost){
//        if(cost > m) return MAX;
//        if(dp[v][cost] != MAX) return dp[v][cost];
//        if(v == n) return 0;
//
//        int result = MAX;
//        for(Item next : adjList[v]){
//            result = Math.min(result, back(next.to, cost + next.cost) + next.time);
//        }
//
//        return dp[v][cost] = result;
//    }
}
