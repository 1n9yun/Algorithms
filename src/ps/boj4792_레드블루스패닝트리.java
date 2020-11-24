package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj4792_레드블루스패닝트리 {
    static class Item{
        int idx;
        int from, to;
        int cost;

        public Item(int idx, int from, int to, int cost) {
            this.idx = idx;
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        while(!(n == 0 && m == 0 && k == 0)){
            List<Item> edges = new ArrayList<>();
            int[] blueCounter = new int[2];
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int color = st.nextToken().charAt(0) == 'B' ? 1 : 0;
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges.add(new Item(i, from, to, color));
            }
            PriorityQueue<Item>[] pq = new PriorityQueue[2];
            pq[0] = new PriorityQueue<>((o1, o2) -> {
                if(o1.cost > o2.cost) return 1;
                return -1;
            });
            pq[1] = new PriorityQueue<>((o1, o2) -> {
                if(o1.cost < o2.cost) return 1;
                return -1;
            });
            HashMap<Integer, Item>[] mst = new HashMap[2];
            for(int i=0;i<mst.length;i++) mst[i] = new HashMap<>();

            int[] disjointSet = new int[n+1];
            for(int idx=0;idx<2;idx++){
                init(disjointSet);
                for(Item item : edges) pq[idx].add(item);
                int cnt = 1;
                while(!pq[idx].isEmpty()){
                    Item edge = pq[idx].poll();
                    if(cnt == n) break;
                    if(union(disjointSet, edge.from, edge.to)){
                        cnt++;
                        mst[idx].put(edge.idx, edge);
                        if(edge.cost == 1) blueCounter[idx]++;
                    }
                }
            }
            while(!(blueCounter[0] == blueCounter[1] || blueCounter[0] == k)){
                Integer[] keySet = new Integer[mst[1].size()];
                mst[1].keySet().toArray(keySet);
                for(Integer key : keySet){
                    if(!mst[0].containsKey(key)){
                        Item edge = mst[1].get(key);
                        if(edge.cost == 1) blueCounter[0]++;
                        mst[0].put(edge.idx, edge);
                        break;
                    }
                }
                keySet = new Integer[mst[0].size()];
                mst[0].keySet().toArray(keySet);
                for(Integer key : keySet){
                    if(!mst[1].containsKey(key)){
                        mst[0].remove(key);
                    }
                }
            }
            System.out.println(blueCounter[0] == k ? 1 : 0);

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
        }
    }

    static void init(int[] set){
        for(int i=0;i<set.length;i++) set[i] = i;
    }

    static int find(int[] set, int idx){
        if(set[idx] == idx) return idx;

        return set[idx] = find(set, set[idx]);
    }

    static boolean union(int[] set, int idx1, int idx2){
        int p1 = find(set, idx1);
        int p2 = find(set, idx2);

        if(p1 == p2) return false;

        set[p1] = p2;
        return true;
    }
}
