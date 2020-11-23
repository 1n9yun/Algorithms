package ps.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1854_K번째최단경로찾기 {
    static class Pair{
        int v, cost;

        public Pair(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] adjMat = new int[n+1][n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            adjMat[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]
            = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer>[] dists = new PriorityQueue[n+1];

        for(int i=1;i<=n;i++){
            dists[i] = new PriorityQueue<>((o1, o2) -> {
                if(o1 < o2) return 1;
                else return -1;
            });
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.cost > o2.cost) return 1;
            else return -1;
        });

        dists[1].add(0);
        pq.add(new Pair(1, 0));

        while(!pq.isEmpty()){
            Pair front = pq.poll();

            for(int i=1;i<=n;i++){
//                왜 이거 넣으면 틀리지??
//                a == b인 경우가 안들어온다는말은 없긴 한데..
//                아 계속 제자리에서 돌아도 dists의 크기가 계속 커질 수는 없는 구조라서 그런듯?
//                if(i == front.v) continue
                if(adjMat[front.v][i] != 0){
                    int nextCost = front.cost + adjMat[front.v][i];
                    if(dists[i].size() < k || dists[i].peek() > nextCost){
                        if(dists[i].size() == k) dists[i].poll();
                        dists[i].add(nextCost);
                        pq.add(new Pair(i, nextCost));
                    }
                }
            }
        }

        for(int i=1;i<=n;i++){
            if(dists[i].size() < k) System.out.println(-1);
            else System.out.println(dists[i].peek());
        }
    }
}
