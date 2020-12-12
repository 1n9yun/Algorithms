package ps.Union_Find;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj13306_트리 {
    static class Item{
        int type, n1, n2;

        public Item(int type, int n1, int n2) {
            this.type = type;
            this.n1 = n1;
            this.n2 = n2;
        }
    }
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

        List<Item> inputs = new ArrayList<>();
        for(int i=0;i<n-1+q;i++){
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int n1 = -1, n2 = -1;
            n1 = Integer.parseInt(st.nextToken());
            if(type == 0) link[n1] *= -1;
            else n2 = Integer.parseInt(st.nextToken());

            inputs.add(new Item(type, n1, n2));
        }

        for(int i=2;i<link.length;i++){
            if(link[i] < 0) continue;
            union(i, link[i]);
        }

        List<String> answerList = new ArrayList<>();
        for(int i=inputs.size() - 1;i>=0;i--){
            Item item = inputs.get(i);
            if(item.type == 0) union(item.n1, link[item.n1] * -1);
            else answerList.add(find(item.n1) == find(item.n2) ? "YES" : "NO");
        }
        for(int i=answerList.size()-1;i>=0;i--) bw.write(answerList.get(i) + "\n");
        bw.flush();
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
