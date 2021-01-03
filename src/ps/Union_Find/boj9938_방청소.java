package ps.Union_Find;

import java.io.*;
import java.util.StringTokenizer;

public class boj9938_방청소 {
    static int[] set;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        set = new int[l+1];
        init();
        check = new boolean[l+1];
        for(int t=0;t<n;t++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!check[a]){
                check[a] = true;
                union(a, b);
            }else if(!check[b]){
                check[b] = true;
                union(b, a);
            }else{
                if(!check[find(a)]){
                    check[find(a)] = true;
                    union(a, b);
                }else if(!check[find(b)]){
                    check[find(b)] = true;
                    union(b, a);
                }else{
                    bw.write("SMECE\n");
                    continue;
                }
            }
            bw.write("LADICA\n");
        }
        bw.flush();
    }

    static void init(){
        for(int i=0;i<set.length;i++) set[i] = i;
    }
    static int find(int idx){
        if(idx == set[idx]) return idx;
        return set[idx] = find(set[idx]);
    }
    static boolean union(int p1, int p2){
        p1 = find(p1);
        p2 = find(p2);

        if(p1 == p2) return false;

        set[p1] = p2;
        return true;
    }
}
