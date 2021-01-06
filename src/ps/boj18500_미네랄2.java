package ps;

import java.util.*;

public class boj18500_미네랄2 {
    static class Pair{
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Cluster{
        List<Integer>[] list;
        int min;

        public Cluster(List<Integer>[] list, int min) {
            this.list = list;
            this.min = min;
        }
    }
    static char[][] map;
    static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
    static int r, c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        map = new char[r+1][c+1];
        for(int i=1;i<=r;i++){
            char[] input = sc.next().toCharArray();
            for(int j=1;j<=c;j++){
                map[i][j] = input[j-1];
            }
        }

//        for(char[] sub : map) System.out.println(Arrays.toString(sub));

        int n = sc.nextInt();
        int shootDir = 3;
        for(int i=0;i<n;i++){
            int shootRow = (r+1) - sc.nextInt();
//            System.out.println("row " + shootRow);
            for(int shootCol=shootDir == 2 ? c : 1;1<=shootCol && shootCol<=c;shootCol+=delta[shootDir][1]){
//                System.out.println("col " + shootCol + " " + map[shootRow][shootCol]);
                if(map[shootRow][shootCol] == '.') continue;

                boolean[][] check = new boolean[r+1][c+1];
                check[shootRow][shootCol] = true;
                map[shootRow][shootCol] = '.';
//                System.out.println("shoot! " + shootRow + " " + shootCol);
                Cluster cluster = getCluster(shootRow - 1, shootCol, check);
                if(cluster == null) cluster = getCluster(shootRow, shootCol + delta[shootDir][1], check);
                if(cluster != null){
                    List<Integer>[] list = cluster.list;
//                    System.out.println("떨궈! " + cluster.min);
                    for(int row=r;row>=1;row--){
                        for(int col : list[row]){
                            map[row][col] = '.';
                            map[row + cluster.min][col] = 'x';
                        }
                    }
                }
                break;
            }
            shootDir = shootDir == 2 ? 3 : 2;
        }

        for(int i=1;i<=r;i++){
            for(int j=1;j<=c;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static Cluster getCluster(int row, int col, boolean[][] check){
        if(!(1<=row && row<=r && 1<=col && col<=c)) return null;
        if(check[row][col] || map[row][col] == '.') return null;
//        System.out.println("헬룽 " + row + " " + col);
        List<Integer>[] list = new ArrayList[r+1];
        for(int i=0;i<list.length;i++) list[i] = new ArrayList<>();

        boolean fall = true;
        int[] maxRow = new int[c+1];
        Arrays.fill(maxRow, -1);
        maxRow[col] = row;
//        System.out.println(Arrays.toString(maxRow));
//        r, c와 이어진 클러스터에서 미네랄들 좌표 리턴
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        check[row][col] = true;

        while(!q.isEmpty()){
            Pair p = q.poll();
            list[p.r].add(p.c);

            for(int[] dir : delta){
                int nRow = p.r + dir[0];
                int nCol = p.c + dir[1];
                if(1<=nRow && nRow<=r && 1<=nCol && nCol<=c && map[nRow][nCol] == 'x' && !check[nRow][nCol]){
//                    System.out.println(nRow + " " + nCol);
                    check[nRow][nCol] = true;
                    q.add(new Pair(nRow, nCol));
                    maxRow[nCol] = Math.max(maxRow[nCol], nRow);
                    if(maxRow[nCol] == r) fall = false;
                }
            }
        }
//        if(!fall) System.out.println("안떨어져!");
        if(!fall) return null;

        int fallCount = 101;
//        System.out.println(Arrays.toString(maxRow));

        for(int i=1;i<=c;i++){
            if(maxRow[i] == -1) continue;
//            System.out.println("열 " + i);
            int count = 0;
            for(int h=maxRow[i]+1; h<=r; h++){
                if(map[h][i] == '.') count++;
//                System.out.println(count + " " + map[h][i] + " " + h + "행 " + i + "열");
                if(h == r || map[h][i] == 'x') break;
            }
            fallCount = Math.min(fallCount, count);
        }

        return new Cluster(list, fallCount);
    }
}
