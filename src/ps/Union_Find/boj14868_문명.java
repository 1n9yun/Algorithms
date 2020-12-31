package ps.Union_Find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj14868_문명 {
    static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[] rank;
    static int[] civilSet;
    static Set<Integer> civilList;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        N = n+1;
        civilSet = new int[N*N];
        rank = new int[N*N];

        for(int i=0;i<civilSet.length;i++){
            civilSet[i] = i;
            rank[i] = 1;
        }

        civilList = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int point = r * N + c;
            rank[point] = 2;
            civilList.add(point);
            q.add(point);

            for(int[] dir : delta){
                int nRow = r + dir[0];
                int nCol = c + dir[1];
                int nPoint = nRow * N + nCol;
                if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n && civilList.contains(nPoint)){
                    union(point, nPoint);
                }
            }
        }

        int ans = 0;
        while(!q.isEmpty()){
//            System.out.println(ans + " times...");
//            for(Integer num : q){
//                System.out.print("(" + num / N + " " + num % N + ") ");
//            }
//            System.out.println();
            if(civilList.size() == 1) break;
            else ans++;

            int size = q.size();
            for(int i=0;i<size;i++){
                int pos = q.poll();

                int r = pos / N;
                int c = pos % N;

                for(int[] dir : delta){
                    int nRow = r + dir[0];
                    int nCol = c + dir[1];
                    int nPoint = nRow * N + nCol;
                    if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n){
                        int result = union(pos, nPoint);
                        if(result == 1) q.add(nPoint);
//                        System.out.println(Arrays.toString(civilSet));
                    }
                }
            }

            for(int pos : q){
                int r = pos / N;
                int c = pos % N;

                for(int[] dir : delta){
                    int nRow = r + dir[0];
                    int nCol = c + dir[1];
                    int nPoint = nRow * N + nCol;
                    if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n){
                        int p1 = find(pos);
                        int p2 = find(nPoint);

                        if(civilList.contains(p1) && civilList.contains(p2)){
                            union(p1, p2);
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static int find(int idx){
        if(civilSet[idx] == idx) return idx;
        return civilSet[idx] = find(civilSet[idx]);
    }

    static int union(int idx1, int idx2){
        int p1 = find(idx1);
        int p2 = find(idx2);

        if(p1 == p2) return 0;

        boolean civiled = false;
        if(civilList.contains(p1) && civilList.contains(p2)){
            civiled = true;
//            세력끼리 합쳐지는 경우
            if(p1 > p2) {
                int temp = p1;
                p1 = p2;
                p2 = temp;
            }
            civilList.remove(p1);
//            System.out.println(p2 / N + " " + p2 % N +"가 " + p1 / N + " " + p1 % N + "를 먹었당!");
//            System.out.print("현재 문명 리스트 : ");
//            for(Integer num : civilList) System.out.print("(" + num / N + " " + num % N + ") ");
//            System.out.println();
        }else if(rank[p1] > rank[p2]) {
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }
        rank[p2] += rank[p1];
        civilSet[p1] = p2;
        return civiled ? 2 : 1;
    }

    static String toString(int num){
        return "(" + num / N + " " + num % N + ")";
    }
}
