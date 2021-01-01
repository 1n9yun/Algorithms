package ps.Union_Find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 처음엔 빈 칸도 disjointSet에 포함시키고 문명발생지의 rank를 높게 유지하면서 union-find를 했는데
// 거기서 뭔가 예외가 있었나보다.
// 그냥 map에 표시하면서 문명만 union하는 것으로 통과.
public class boj14868_문명 {
    static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[] civilSet;

    static class Pair{
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][n+1];
        civilSet = new int[k+1];
        for(int i=1;i<civilSet.length;i++) civilSet[i] = i;

        Queue<Pair> q = new LinkedList<>();
        for(int i=1;i<=k;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

//            i번 문명
            map[r][c] = i;
            q.add(new Pair(r, c));
        }

        int ans = 0;
        while(!q.isEmpty()){
            for(Pair p : q){
                for(int[] dir : delta){
                    int nRow = p.r + dir[0];
                    int nCol = p.c + dir[1];

                    if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n && map[nRow][nCol] != 0){
                        if(union(map[p.r][p.c], map[nRow][nCol])) k--;
                    }
                }
            }
            if(k == 1) break;
            ans++;

            int size = q.size();
            for(int i=0;i<size;i++){
                Pair p = q.poll();

                for(int[] dir : delta){
                    int nRow = p.r + dir[0];
                    int nCol = p.c + dir[1];

                    if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n){
                        if(map[nRow][nCol] != 0) {
                            if(union(map[p.r][p.c], map[nRow][nCol])) k--;
                        }else{
                            map[nRow][nCol] = map[p.r][p.c];
                            q.add(new Pair(nRow, nCol));
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

    static boolean union(int p1, int p2){
        p1 = find(p1);
        p2 = find(p2);

        if(p1 == p2) return false;

        civilSet[p2] = p1;
        return true;
    }
//    static int[] rank;
//    static Set<Integer> civilList;
//    static int N;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//        N = n+1;
//        civilSet = new int[N*N];
//        rank = new int[N*N];
//
//        for(int i=0;i<civilSet.length;i++){
//            civilSet[i] = i;
//            rank[i] = 1;
//        }
//
//        civilList = new HashSet<>();
//        Queue<Integer> q = new LinkedList<>();
//        for(int i=0;i<k;i++){
//            st = new StringTokenizer(br.readLine());
//            int c = Integer.parseInt(st.nextToken());
//            int r = Integer.parseInt(st.nextToken());
//
//            int point = r * N + c;
//            rank[point] = 2;
//            civilList.add(point);
//            q.add(point);
//
//            for(int[] dir : delta){
//                int nRow = r + dir[0];
//                int nCol = c + dir[1];
//                int nPoint = nRow * N + nCol;
//                if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n && civilList.contains(nPoint)){
//                    union(point, nPoint);
//                }
//            }
//        }
//
//        int ans = 0;
//        while(!q.isEmpty()){
////            System.out.println(ans + " times...");
////            for(Integer num : q){
////                System.out.print("(" + num / N + " " + num % N + ") ");
////            }
////            System.out.println();
//            if(civilList.size() == 1) break;
//            else ans++;
//
//            int size = q.size();
//            for(int i=0;i<size;i++){
//                int pos = q.poll();
//
//                int r = pos / N;
//                int c = pos % N;
//
//                for(int[] dir : delta){
//                    int nRow = r + dir[0];
//                    int nCol = c + dir[1];
//                    int nPoint = nRow * N + nCol;
//                    if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n){
//                        int result = union(pos, nPoint);
//                        if(result == 1) q.add(nPoint);
////                        System.out.println(Arrays.toString(civilSet));
//                    }
//                }
//            }
//
//            for(int pos : q){
//                int r = pos / N;
//                int c = pos % N;
//
//                for(int[] dir : delta){
//                    int nRow = r + dir[0];
//                    int nCol = c + dir[1];
//                    int nPoint = nRow * N + nCol;
//                    if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n){
//                        int p1 = find(pos);
//                        int p2 = find(nPoint);
//
//                        if(civilList.contains(p1) && civilList.contains(p2)){
//                            union(p1, p2);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(ans);
//    }
//
//    static int find(int idx){
//        if(civilSet[idx] == idx) return idx;
//        return civilSet[idx] = find(civilSet[idx]);
//    }
//
//    static int union(int idx1, int idx2){
//        int p1 = find(idx1);
//        int p2 = find(idx2);
//
//        if(p1 == p2) return 0;
//
//        boolean civiled = false;
//        if(civilList.contains(p1) && civilList.contains(p2)){
//            civiled = true;
////            세력끼리 합쳐지는 경우
//            if(p1 > p2) {
//                int temp = p1;
//                p1 = p2;
//                p2 = temp;
//            }
//            civilList.remove(p1);
////            System.out.println(p2 / N + " " + p2 % N +"가 " + p1 / N + " " + p1 % N + "를 먹었당!");
////            System.out.print("현재 문명 리스트 : ");
////            for(Integer num : civilList) System.out.print("(" + num / N + " " + num % N + ") ");
////            System.out.println();
//        }else if(rank[p1] > rank[p2]) {
//            int temp = p1;
//            p1 = p2;
//            p2 = temp;
//        }
//        rank[p2] += rank[p1];
//        civilSet[p1] = p2;
//        return civiled ? 2 : 1;
//    }

//    static String toString(int num){
//        return "(" + num / N + " " + num % N + ")";
//    }
}
