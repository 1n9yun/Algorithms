package ps.BFS;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42894_블록게임 {
    class Item{
        int r, c;
        public Item(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    class Block{
        ArrayList<Item> points;
        Item base;
        int[] blank;
        public Block(ArrayList<Item> points, Item base, int[] blank) {
            this.points = points;
            this.base = base;
            this.blank = blank;
        }
    }
    HashMap<Integer, int[]> blankType = new HashMap<>();
    int[][] delta = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    public int solution(int[][] board) {
        init();

        final int n = board.length;
        ArrayList<Block> blocks = new ArrayList<>();

        boolean[][] check = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(check[i][j] || board[i][j] == 0) continue;
                ArrayList<Item> points = new ArrayList<>();
                Item base = new Item(n+1, n+1);

                Queue<Item> q = new LinkedList<>();
                check[i][j] = true;
                q.add(new Item(i,j));

                int number = board[i][j];
                while(!q.isEmpty()){
                    Item item = q.poll();

                    base.r = Math.min(base.r, item.r);
                    base.c = Math.min(base.c, item.c);
                    points.add(item);

                    for(int[] dir : delta){
                        int nRow = item.r + dir[0];
                        int nCol = item.c + dir[1];
                        if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol] && board[nRow][nCol] == number){
                            check[nRow][nCol] = true;
                            q.add(new Item(nRow, nCol));
                        }
                    }
                }

                int type = 0;
                for(Item item : points){
                    type |= 1<<((item.r - base.r) * 3 + (item.c - base.c));
                }
                blocks.add(new Block(points, base, blankType.get(type)));
            }
        }

        int answer = 0;
        while(!blocks.isEmpty()){
            Iterator<Block> it = blocks.iterator();

            boolean removedSomething = false;
            while (it.hasNext()) {
                Block block = it.next();
                boolean possible = true;
                for (int blank : block.blank) {
                    int i = block.base.r + (blank / 3);
                    int j = block.base.c + (blank % 3);

                    for(int r=i;r>=0;r--){
                        if(board[r][j] != 0) {
                            possible = false;
                            break;
                        }
                    }
                    if(!possible) break;
                }
                if (possible) {
                    for (Item point : block.points) board[point.r][point.c] = 0;
                    it.remove();
                    removedSomething = true;
                    answer++;
                }
            }
            if (!removedSomething) break;
        }
        return answer;
    }
    public void init(){
        blankType.put(getTypeKey(0, 3, 4, 6), new int[]{1,7});
        blankType.put(getTypeKey(0, 1, 2, 4), new int[]{3,5});
        blankType.put(getTypeKey(1, 3, 4, 7), new int[]{0,6});
        blankType.put(getTypeKey(1, 3, 4, 5), new int[]{0,2});

        blankType.put(getTypeKey(0, 1, 3, 6), new int[]{4,7});
        blankType.put(getTypeKey(0, 1, 2, 5), new int[]{3,4});
        blankType.put(getTypeKey(1, 4, 6, 7), new int[]{0,3});
        blankType.put(getTypeKey(0, 3, 4, 5), new int[]{1,2});

        blankType.put(getTypeKey(0, 1, 4, 7), new int[]{3,6});
        blankType.put(getTypeKey(2, 3, 4, 5), new int[]{0,1});
        blankType.put(getTypeKey(0, 3, 6, 7), new int[]{1,4});
        blankType.put(getTypeKey(0, 1, 2, 3), new int[]{4,5});
    }
    public int getTypeKey(int... bits){
        int result = 0;
        for(int bit : bits) result |= 1<<bit;
        return result;
    }
    
    @Test
    public void testSolution(){
        assertThat(solution(new int[][]{{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,4,0,0,0},{0,0,0,0,0,4,4,0,0,0},{0,0,0,0,3,0,4,0,0,0},{0,0,0,2,3,0,0,0,5,5},{1,2,2,2,3,3,0,0,0,5},{1,1,1,0,0,0,0,0,0,5}}))
                .isEqualTo(2);
        assertThat(solution(new int[][]{
                {1, 1, 2},
                {1, 0, 2},
                {1, 2, 2}
        })).isEqualTo(0);
        assertThat(solution(new int[][]{
                {0, 0, 1, 2, 3, 0},
                {1, 1, 1, 2, 3, 0},
                {4, 0, 2, 2, 3, 3},
                {4, 0 ,0, 5, 0, 0},
                {4, 4, 5, 5, 5, 0},
                {0, 0, 0, 0, 0, 0}
        })).isEqualTo(5);
        assertThat(solution(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 2, 2, 0, 0},
                {0, 2, 1, 0, 0},
                {0, 2, 1, 0, 0},
                {0, 0, 1, 1, 0}
        })).isEqualTo(1);
    }
}
