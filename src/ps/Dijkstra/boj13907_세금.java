package ps.Dijkstra;

import java.util.*;

public class boj13907_세금 {
    static class Pair {
        int v;
        int len;
        int cost;

        public Pair(int v, int len, int cost) {
            this.v = v;
            this.len = len;
            this.cost = cost;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int s = sc.nextInt();
        int d = sc.nextInt();

        int[][] adjMat = new int[n+1][n+1];
        for(int i=0;i<m;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            adjMat[from][to] = cost;
            adjMat[to][from] = cost;
        }

        int[] tax = new int[k+1];
        for(int i=1;i<=k;i++){
            tax[i] = tax[i-1] + sc.nextInt();
        }
        int[][] dists = new int[n+1][n+1];
        dists[s][0] = -1;

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.cost > o2.cost) return 1;
            else return -1;
        });
        pq.add(new Pair(s, 0, 0));

        int[] maxLengths = new int[n+1];
        Arrays.fill(maxLengths, Integer.MAX_VALUE);

        while(!pq.isEmpty()){
            Pair front = pq.poll();

            if(maxLengths[front.v] == Integer.MAX_VALUE) maxLengths[front.v] = front.len + 1;
            else if(maxLengths[front.v] <= front.len + 1) continue;

            for(int i=1;i<=n;i++){
                if(adjMat[front.v][i] != 0){
                    int nextCost = front.cost + adjMat[front.v][i];
                    if(dists[i][front.len + 1] == 0 || dists[i][front.len + 1] > nextCost){
                        dists[i][front.len + 1] = nextCost;
                        pq.add(new Pair(i, front.len + 1, nextCost));
                    }
                }
            }
        }

        for(int i=0;i<=k;i++){
            int ans = Integer.MAX_VALUE;
            for(int j=1;j<=maxLengths[d];j++){
                if(dists[d][j] == 0) continue;
                ans = Math.min(ans, dists[d][j] + j * tax[i]);
            }
            System.out.println(ans);
        }
    }
}