package ps.Union_Find;

import java.io.*;
import java.util.*;

public class boj3830_교수님은기다리지않는다 {
    static int[] sampleSet;
    static long[] dist;
    static int[] belong;
    public static void main(String[] args) throws IOException {
//        File in = new File("C:\\Users\\1n9yun\\Desktop\\F.in");
//        File ans = new File("C:\\Users\\1n9yun\\Desktop\\F.ans");
//
//        BufferedReader br = new BufferedReader(new FileReader(in));
//        BufferedReader br_ans = new BufferedReader(new FileReader(ans));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        while(n != 0 && m != 0){
            sampleSet = new int[n+1];
            dist = new long[n+1];
            belong = new int[n+1];

            for(int i=1;i<sampleSet.length;i++) sampleSet[i] = i;

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                find(a);
                find(b);
                if(type == '!'){
                    long w = Integer.parseInt(st.nextToken());

                    if(belong[a] == 0 && belong[b] == 0){
                        dist[b] = w;
                        belong[a] = a;
                        belong[b] = a;
                        union(a, b);
                    }else if(belong[a] == 0 && belong[b] != 0){
                        dist[a] = dist[b] - w;
                        belong[a] = belong[b];
                        union(b, a);
                    }else if(belong[a] != 0 && belong[b] == 0){
                        dist[b] = dist[a] + w;
                        belong[b] = belong[a];
                        union(a, b);
                    }else{
//                    둘 다 그룹인 경우, b를 b의 그룹장으로 교체
                        w = w - dist[b];
                        b = belong[b];
                        belong[b] = belong[a];
                        dist[b] = dist[a] + w;
                        union(a, b);
                    }
                }else{
                    String res;
                    if(find(a) != find(b))
                        bw.write("UNKNOWN\n");
//                        System.out.println("UNKNOWN");
//                        res = "UNKNOWN";
                    else
                        bw.write(dist[b] - dist[a] + "\n");
//                        System.out.println(dist[b] - dist[a]);
//                        res = Long.toString(dist[b] - dist[a]);
//                    String answer = br_ans.readLine();
//
//                    System.out.println(answer + ": " + res);
//                    if(!answer.equals(res))
//                        new Scanner(System.in).nextLine();
                }
            }

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }
        bw.flush();
    }

    static int find(int idx){
//        그룹장의 소속이 다른지 체크
        while(belong[belong[idx]] != belong[idx]){
//            소속 따라가기
//            System.out.println(idx + ": " + belong[idx] + " " + belong[belong[idx]]);
            dist[idx] += dist[belong[idx]];
            belong[idx] = belong[belong[idx]];
        }

        if(idx == sampleSet[idx]) return idx;
        return sampleSet[idx] = find(sampleSet[idx]);
    }
    static void union(int p1, int p2){
        p1 = find(p1);
        p2 = find(p2);

        if(p1 != p2){
            sampleSet[p2] = p1;
        }
    }
}
