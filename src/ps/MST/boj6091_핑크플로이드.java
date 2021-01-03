package ps.MST;

import java.io.*;
import java.util.*;

// MST에서 임의의 정점 사이의 경로는 유일하다.
// 트리 또한 임의의 두 점점 사이의 경로는 유일하다
public class boj6091_핑크플로이드 {
    private static int stoi(String s) { return Integer.parseInt(s); }
    static class Edge{
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static int[] vSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = stoi(br.readLine());
        vSet = new int[n];
        for(int i=0;i<vSet.length;i++) vSet[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.cost > o2.cost) return 1;
            return -1;
        });
        for(int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=i+1;j<n;j++){
//                i, j 연결
                pq.add(new Edge(i, j, stoi(st.nextToken())));
            }
        }

        List<Integer>[] adjList = new ArrayList[n];
        for(int i=0;i<adjList.length;i++) adjList[i] = new ArrayList<>();

        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if(union(e.from, e.to)) {
                n--;
                adjList[e.from].add(e.to);
                adjList[e.to].add(e.from);
                if(n == 1) break;
            }
        }

        for(int i=0;i<adjList.length;i++){
            Collections.sort(adjList[i]);
            bw.write(adjList[i].size() + " ");
            for(int num : adjList[i]){
                bw.write((num+1) + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    static int find(int idx){
        if(idx == vSet[idx]) return idx;
        return vSet[idx] = find(vSet[idx]);
    }

    static boolean union(int p1, int p2){
        p1 = find(p1);
        p2 = find(p2);

        if(p1 == p2) return false;

        vSet[p2] = p1;
        return true;
    }
}
