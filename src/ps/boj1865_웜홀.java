package ps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class boj1865_웜홀 {
    static class Item{
        int to, cost;

        public Item(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        while(TC-- != 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int w = sc.nextInt();

            ArrayList<Item>[] adjList = new ArrayList[n+1];
            for(int i=0;i<adjList.length;i++) adjList[i] = new ArrayList<>();
            for(int i=0;i<m;i++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                int cost = sc.nextInt();

                adjList[from].add(new Item(to, cost));
                adjList[to].add(new Item(from, cost));
            }
            for(int i=0;i<w;i++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                int cost = sc.nextInt();

                adjList[from].add(new Item(to, -cost));
            }

            int[] dists = new int[n+1];
            Arrays.fill(dists, (int)Math.pow(10, 9));

            for(int t=1;t<n;t++){
                for(int i=1;i<=n;i++){
                    for(int j=0;j<adjList[i].size();j++){
                        Item edge = adjList[i].get(j);
                        dists[edge.to] = Math.min(dists[edge.to], dists[i] + edge.cost);
                    }
                }
            }

            boolean cycle = false;
            out:
            for(int i=1;i<=n;i++){
                for(int j=0;j<adjList[i].size();j++){
                    Item edge = adjList[i].get(j);
                    if(dists[edge.to] > dists[i] + edge.cost) {
                        cycle = true;
                        break out;
                    }
                }
            }
            System.out.println(cycle ? "YES" : "NO");
        }
    }
}
