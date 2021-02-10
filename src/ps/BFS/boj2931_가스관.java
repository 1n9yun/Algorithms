package ps.BFS;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class boj2931_가스관 {
    static int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] map = new int[r+1][c+1];

        Map<Character, Integer> parts = new HashMap<>();
        parts.put('|', 1<<0 | 1<<2);
        parts.put('-', 1<<1 | 1<<3);
        parts.put('+', (1<<4) - 1);
        parts.put('1', 1<<0 | 1<<1);
        parts.put('2', 1<<1 | 1<<2);
        parts.put('3', 1<<2 | 1<<3);
        parts.put('4', 1<<3 | 1<<0);

        for(int i=1;i<=r;i++){
            String input = sc.next();
            for(int j=1;j<=c;j++){
                char part = input.charAt(j - 1);
                if(part == '.') {
                    ;
                } else if(part == 'M' || part == 'Z'){
                    map[i][j] = 1<<4;
                }else {
                    map[i][j] = parts.get(part);
                    map[i][j] |= 1<<4;

                    for (int dir = 0; dir < delta.length; dir++) {
                        if ((map[i][j] & (1 << dir)) == 0) continue;
                        int nRow = i + delta[dir][0];
                        int nCol = j + delta[dir][1];
                        if (1 <= nRow && nRow <= r && 1 <= nCol && nCol <= c) {
                            map[nRow][nCol] |= 1<<opposite(dir);
                        }
                    }
                }
            }
        }
        for(int i=1;i<=r;i++){
            for(int j=1;j<=c;j++){
                if(map[i][j] == 0) continue;
//                길은 있는데 블록이 없어
                if((map[i][j] & (1<<4)) == 0){
                    for(char key : parts.keySet()){
                        if(map[i][j] == parts.get(key)){
                            System.out.println(i + " " + j + " " + key);
                            return;
                        }
                    }
                }
            }
        }
    }

    static int opposite(int dir){
        return (dir + 2) % delta.length;
    }
}
