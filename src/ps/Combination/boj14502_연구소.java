package ps.Combination;

import java.util.*;

public class boj14502_연구소 {
    static class Pair{
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    static int n,m,ans = -1, safe = -3;
    static int[][] map;
    static List<Pair> virus;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        virus = new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
//                1벽 2바이러스
                map[i][j] = sc.nextInt();
                if(map[i][j] == 2) virus.add(new Pair(i, j));
                else if(map[i][j] == 0) safe++;
            }
        }
        combination(0, 0, n * m, new int[3]);
        System.out.println(ans);
    }

    static void combination(int idx, int cnt, int max, int[] result){
        if(cnt == result.length){
//            System.out.println(Arrays.toString(result));
            for(int loc : result) map[loc / m][loc % m] = 1;

            int temp = safe;
            boolean[][] check = new boolean[n][m];
            Queue<Pair> q = new LinkedList<>(virus);
            while (!q.isEmpty()) {
                Pair p = q.poll();

                for (int[] dir : delta) {
                    int nRow = p.first + dir[0];
                    int nCol = p.second + dir[1];

                    if (0 <= nRow && nRow < n && 0 <= nCol && nCol < m && !check[nRow][nCol] && map[nRow][nCol] == 0) {
                        check[nRow][nCol] = true;
                        temp--;
                        q.add(new Pair(nRow, nCol));
                    }
                }
            }
            ans = Math.max(ans, temp);

            for(int loc : result) map[loc / m][loc % m] = 0;
            return;
        }

        for(int i=idx;i<max;i++){
            if(map[i / m][i % m] != 0) continue;
            result[cnt] = i;
            combination(i+1, cnt+1, max, result);
        }
    }
}
