package ps;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj13306_트리 {
    static int[] vertexSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        vertexSet = new int[n+1];
        for(int i=0;i<vertexSet.length;i++) vertexSet[i] = i;

        int[] link = new int[n+1];
        for(int i=1;i<n;i++)
            link[i+1] = Integer.parseInt(br.readLine());

        List<Integer> queries = new ArrayList<>();
        for(int i=0;i<n-1+q;i++){
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            if(type == 0) link[Integer.parseInt(st.nextToken())] = -1;
            else {
                queries.add(Integer.parseInt(st.nextToken()));
                queries.add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=2;i<link.length;i++){
            if(link[i] == -1) continue;
            union(i, link[i]);
        }
        System.out.println(Arrays.toString(vertexSet));
        for(int i=0;i<queries.size();i+=2){
            System.out.println(find(queries.get(i)) == find(queries.get(i+1)) ? "YES" : "NO");
        }
    }

    static int find(int idx){
        if(idx == vertexSet[idx]) return idx;

        return vertexSet[idx] = find(vertexSet[idx]);
    }

    static void union(int idx1, int idx2){
        int p1 = find(idx1);
        int p2 = find(idx2);

        if(p1 != p2) vertexSet[p2] = p1;
    }
}
