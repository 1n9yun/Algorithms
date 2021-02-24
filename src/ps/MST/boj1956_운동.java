package ps.MST;

import java.util.Scanner;

public class boj1956_운동 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        int[][] costs = new int[v+1][v+1];
        while(e-- != 0){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            costs[from][to] = cost;
        }

        for(int k=1;k<=v;k++){
            for(int i=1;i<=v;i++){
                if(k == i) continue;
                for(int j=1;j<=v;j++){
                    if(k == j || i == j) continue;
                    if(costs[i][k] != 0 && costs[k][j] != 0){
                        costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=1;i<=v;i++){
            for(int j=1;j<=v;j++){
                if(i == j || costs[i][j] == 0 || costs[j][i] == 0) continue;
                answer = Math.min(answer, costs[i][j] + costs[j][i]);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
