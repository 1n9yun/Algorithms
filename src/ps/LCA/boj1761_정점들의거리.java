package ps.LCA;

import java.util.ArrayList;
import java.util.Scanner;

public class boj1761_정점들의거리 {
    static class Item{
        int to, dist;

        public Item(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    static ArrayList<Item>[] adjList;
    static int[][] parents;
    static int[] depths;
    static int[] distSum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        adjList = new ArrayList[n+1];
        for(int i=1;i<=n;i++) adjList[i] = new ArrayList<>();

        for(int i=0;i<n-1;i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int dist = sc.nextInt();

            adjList[from].add(new Item(to, dist));
            adjList[to].add(new Item(from, dist));
        }

        parents = new int[n+1][17];
        distSum = new int[n+1];
        depths = new int[n+1];
//        1을 루트로
        setParentAndDepth(1, 1, 0, 0);

        for(int i=1;i<=16;i++){
            for(int j=1;j<=n;j++){
                parents[j][i] = parents[parents[j][i-1]][i-1];
            }
        }

        int m = sc.nextInt();
        while(m-- != 0){
            int left = sc.nextInt();
            int right = sc.nextInt();

            int n1 = left, n2 = right;
            if(depths[n1] < depths[n2]){
                int temp = n1;
                n1 = n2;
                n2 = temp;
            }

            for(int d=16;d>=0;d--){
                if(depths[n1] - depths[n2] >= (1<<d)){
                    n1 = parents[n1][d];
                }
            }
            int lca = -1;
            if(n1 == n2) lca = n2;
            else{
                for(int d=16;d>=0;d--){
                    if(parents[n1][d] != parents[n2][d]){
                        n1 = parents[n1][d];
                        n2 = parents[n2][d];
                    }
                }

                lca = parents[n1][0];
            }

            System.out.println(distSum[left] - distSum[lca] + distSum[right] - distSum[lca]);
        }
    }

    static void setParentAndDepth(int v, int prev, int depth, int dist){
        depths[v] = depth;
        distSum[v] = dist;
        parents[v][0] = prev;

        for(Item item : adjList[v]){
            if(item.to == prev) continue;
            setParentAndDepth(item.to, v, depth + 1, dist + item.dist);
        }
    }
}
