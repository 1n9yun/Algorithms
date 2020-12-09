package ps.구현;

import java.util.Arrays;
import java.util.Scanner;

public class boj14503_로봇청소기 {
    static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
    static class Robot{
        int r, c, dir;

        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
        public int Rotate(){
            return this.dir = (4 + this.dir - 1) % 4;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n][m];
        boolean[][] check = new boolean[n][m];
        Robot robot = new Robot(sc.nextInt(), sc.nextInt(), sc.nextInt());
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j] = sc.nextInt();
            }
        }

        int ans = 0;
        while(true){
            if(!check[robot.r][robot.c]) ans++;
            check[robot.r][robot.c] = true;

            while (true) {
                boolean flag = false;
                for(int i=1;i<=4;i++){
                    int nDir = robot.Rotate();
                    int nRow = robot.r + delta[nDir][0];
                    int nCol = robot.c + delta[nDir][1];

                    if(map[nRow][nCol] == 1 || check[nRow][nCol]) continue;
                    flag = true;

                    robot.r = nRow;
                    robot.c = nCol;
                    break;
                }
                if(!flag){
                    int backDir = (robot.dir + 2) % 4;
                    int backR = robot.r + delta[backDir][0];
                    int backC = robot.c + delta[backDir][1];
                    if(map[backR][backC] == 1) {
                        System.out.println(ans);
                        return;
                    }else {
                        robot.r = backR;
                        robot.c = backC;
                    }
                }else break;
            }
        }
    }
}
