package ps.Binary_Search;

import java.util.Random;
import java.util.Scanner;

public class boj1981_배열에서이동 {
    static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][] map;
    static boolean[][] check;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        int n = sc.nextInt();
        int n = 100;
        map = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
//                map[i][j] = sc.nextInt();
                map[i][j] = Math.abs(new Random().nextInt()) % 201;
            }
        }

        int answer = 201;
        int left = 0;
        int right = 200;
        while(left <= right){
            int mid = (left + right) / 2;

            check = new boolean[n][n];
            check[0][0] = true;
            path = "[0, 0] ";
            if(back(mid, map[0][0], map[0][0], 0, 0)) {
                right = mid - 1;
                answer = mid;
            } else left = mid + 1;
        }

        System.out.println(answer);
    }

    static String path = null;
    static boolean back(int limit, int max, int min, int r, int c){
//        System.out.println((max - min) + " " + path);
        if(r == check.length-1 && c == check.length-1) return true;

        for(int[] dir : delta){
            int nRow = r + dir[0];
            int nCol = c + dir[1];
            if(0<=nRow && nRow<map.length && 0<=nCol && nCol<map.length && !check[nRow][nCol]){
                int nMax = Math.max(max, map[nRow][nCol]);
                int nMin = Math.min(min, map[nRow][nCol]);
                if(limit >= nMax - nMin){
                    check[nRow][nCol] = true;
//                    path += String.format("[%d, %d] ", r, c);
                    if(back(limit, nMax, nMin, nRow, nCol)) return true;
//                    path = path.substring(0, path.length() - 7);
                    check[nRow][nCol] = false;
                };
            }
        }
        return false;
    }
}
