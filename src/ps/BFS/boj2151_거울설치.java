package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj2151_거울설치 {
    static class Item{
//        세로 가로
        int r, c, dir, count;

        public Item(int r, int c, int dir, int count) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.count = count;
        }
    }
    static int delta[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        char[][] map = new char[n][];

        int doorRow = 0, doorCol = 0;
        for(int i=0;i<n;i++){
            map[i] = sc.next().toCharArray();
            for(int j=0;j<map[i].length;j++){
                if(map[i][j] == '#') {
                    doorRow = i;
                    doorCol = j;
                }
            }
        }

        boolean[][][] check = new boolean[n][n][4];
        check[doorRow][doorCol][0] = true;
        check[doorRow][doorCol][2] = true;
        Queue<Item> q = new LinkedList<>();
        q.add(new Item(doorRow, doorCol, 0, 0));
        q.add(new Item(doorRow, doorCol, 2, 0));

        while(!q.isEmpty()){
            Item item = q.poll();

            if(map[item.r][item.c] == '#' && !(item.r == doorRow && item.c == doorCol)){
                System.out.println(item.count - 1);
                return;
            }

            int nDir = item.dir * -1 + 2;
            for(int dir=nDir;dir<=nDir+1;dir++){
                int nRow = item.r + delta[dir][0];
                int nCol = item.c + delta[dir][1];

                while(true){
                    if(!(0<=nRow && nRow<n && 0<=nCol && nCol<n) || check[nRow][nCol][dir] || map[nRow][nCol] == '*') break;
                    if(map[nRow][nCol] == '!' || map[nRow][nCol] == '#'){
                        check[nRow][nCol][dir] = true;
                        q.add(new Item(nRow, nCol, nDir, item.count + 1));
                    }
                    nRow += delta[dir][0];
                    nCol += delta[dir][1];
                }
            }
        }
    }
}
