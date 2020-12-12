package ps;

import java.util.Scanner;

public class boj1780_종이의개수 {
    static int n;
    static int[] ans;
    static int[][] paper;
    static class Pair{
        int left, right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
//
//        @Override
//        public String toString() {
//            return "Pair{" +
//                    "left=" + left +
//                    ", right=" + right +
//                    '}';
//        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        paper = new int[n+1][n+1];
        ans = new int[3];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                paper[i][j] = sc.nextInt();
            }
        }

        solve(n, new Pair(1, 1), new Pair(n, n));
        for(int a : ans) System.out.println(a);
    }

    static void solve(int size, Pair leftTop, Pair rightBottom){
//        System.out.println(leftTop + " " + rightBottom);
        int base = paper[leftTop.left][leftTop.right];
        boolean flag = true;
        out:
        for(int i=leftTop.left;i<=rightBottom.left;i++){
            for(int j=leftTop.right;j<=rightBottom.right;j++){
                if(paper[i][j] != base){
                    flag = false;
                    break out;
                }
            }
        }
        if(flag) ans[base+1]++;
        else{
//        n / 3개 만큼 이동
            int jump = size / 3;
            Pair firstRightBottom = new Pair(leftTop.left + (rightBottom.left - leftTop.left) / 3, leftTop.right + (rightBottom.right - leftTop.right) / 3);
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    solve(jump, new Pair(leftTop.left + (jump * i), leftTop.right + (jump * j)),
                        new Pair(firstRightBottom.left + (jump * i), firstRightBottom.right + (jump * j)));
                }
            }
        }
    }
}
