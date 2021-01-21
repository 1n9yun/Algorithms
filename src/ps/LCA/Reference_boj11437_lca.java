package ps.LCA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Reference_boj11437_lca {
    static List<Integer>[] adjList;
    static int[][] mark;
    static int[] depth;
    static int stoi(String s) { return Integer.parseInt(s); }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = stoi(br.readLine());
        adjList = new ArrayList[n+1];
        for(int i=0;i< adjList.length;i++) adjList[i] = new ArrayList<>();
        mark = new int[n+1][20];
        depth = new int[n+1];

        for(int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        marking(1, 1, 0);

        for(int j=1;j<20;j++){
            for(int i=1;i<=n;i++) {
                mark[i][j] = mark[mark[i][j - 1]][j - 1];
            }
        }

        int m = stoi(br.readLine());
        for(int i=0;i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = stoi(st.nextToken());
            int right = stoi(st.nextToken());

            if(depth[left] > depth[right]){
                int temp = left;
                left = right;
                right = temp;
            }

            for(int d=19;d>=0;d--){
                if(depth[right] - depth[left] >= (1 << d))
                    right = mark[right][d];
            }
            if(left != right){
                for(int d=19;d>=0;d--){
                    if(mark[left][d] != mark[right][d]){
                        left = mark[left][d];
                        right = mark[right][d];
                    }
                }
                bw.write(mark[left][0] + "\n");
            }else bw.write(left + "\n");
        }
        bw.flush();
    }

    static void marking(int parent, int node, int d){
        depth[node] = d;
        mark[node][0] = parent;
        for(int next : adjList[node]){
            if(mark[next][0] != 0) continue;
            marking(node, next, d + 1);
        }
    }
}
