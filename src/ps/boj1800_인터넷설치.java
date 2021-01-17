package ps;

import java.util.*;

public class boj1800_인터넷설치 {
    static class Pair{
        int to, cost, cnt;

        public Pair(int to, int cost, int cnt) {
            this.to = to;
            this.cost = cost;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p = sc.nextInt();
        int k = sc.nextInt();

        List<Pair>[] adjList = new ArrayList[n+1];
        for(int i=0;i<adjList.length;i++) adjList[i] = new ArrayList<>();

        for(int i=0;i<p;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            adjList[from].add(new Pair(to, cost, 0));
            adjList[to].add(new Pair(from, cost, 0));
        }

        int left = 0;
        int right = 1000000;

        int ans = Integer.MAX_VALUE;
        while(left <= right){
            int mid = (left + right) / 2;
//            System.out.println(left + " " + mid + " " + right);
            PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
                if(o1.cost > mid && o2.cost <= mid) return 1;
                else if(o1.cost > mid && o2.cost > mid) {
                    if(o1.cnt > o2.cnt) return 1;
                }
                return -1;
            });

            int[] check = new int[n+1];
            Arrays.fill(check, Integer.MAX_VALUE);
            check[1] = 0;

            for(int i=0;i<adjList[1].size();i++) {
                Pair pair = adjList[1].get(i);
                pq.add(new Pair(pair.to, pair.cost, pair.cost > mid ? 1 : 0));
            }

            while(!pq.isEmpty()){
                Pair pair = pq.poll();

                if(check[pair.to] <= pair.cnt) continue;
                check[pair.to] = pair.cnt;

//                System.out.println("\ncome " + pair.to);
                for(int i=0;i<adjList[pair.to].size();i++){
                    Pair next = adjList[pair.to].get(i);

//                    if(next.cost > mid) System.out.print(next.to + "(" + next.cost + ")! ");
                    pq.add(new Pair(next.to, next.cost, next.cost > mid ? pair.cnt + 1 : pair.cnt));
                }
            }
//            System.out.println();
//            System.out.println(Arrays.toString(check));
            if(check[n] > k){
                left = mid + 1;
            }else{
                right = mid - 1;
                ans = mid;
            }
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
