package ps.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1185_유럽여행 {
    static class Item{
        int from, to, cost;

        public Item(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static int[] nationSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        nationSet = new int[n+1];
        for(int i=1;i<=n;i++) nationSet[i] = i;

        int[] fee = new int[n+1];
        int minFee = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++) {
            fee[i] = Integer.parseInt(br.readLine());
            minFee = Math.min(minFee, fee[i]);
        }

        PriorityQueue<Item> pq = new PriorityQueue<>((o1, o2)->{
            return Integer.compare(o1.cost, o2.cost);
        });
        for(int i=0;i<p;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            cost = fee[from] + fee[to] + cost + cost;

            pq.add(new Item(from, to, cost));
        }

        int ans = 0;
        while(!pq.isEmpty()){
            Item edge = pq.poll();
            if(union(edge.from, edge.to)){
                n--;
                ans += edge.cost;
                if(n == 1) break;
            }
        }
        System.out.println(ans + minFee);
    }

    static int find(int idx){
        if(idx == nationSet[idx]) return idx;
        return nationSet[idx] = find(nationSet[idx]);
    }

    static boolean union(int n1, int n2){
        n1 = find(n1);
        n2 = find(n2);

        if(n1 == n2) return false;

        nationSet[n2] = n1;
        return true;
    }
}
