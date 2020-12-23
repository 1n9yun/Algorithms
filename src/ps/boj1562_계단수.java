package ps;

import java.util.Scanner;

public class boj1562_계단수 {
    static final int MOD = 1000000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][][] dp = new int[n+1][10][1<<10];

        for(int i=1;i<=9;i++) dp[1][i][1<<i] = 1;

        for(int i=2;i<=n;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<(1<<10);k++){
                    if(j != 0) dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][j-1][k]) % MOD;
                    if(j != 9) dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][j+1][k]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[n][i][(1 << 10) - 1]) % MOD;
        }
        System.out.println(ans);
    }
}
