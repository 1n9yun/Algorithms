package ps.Math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// 행렬 멱법
// https://zzonglove.tistory.com/31
public class boj13328_messagePassing {
    static final int MOD = 31991;
    static HashMap<Integer, Integer[][]> mats;
    static int d, t;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        d = sc.nextInt();
        t = sc.nextInt();
        mats = new HashMap<>();

//        2의 1 ~ d-1 승 까지의 수 구하기
        Integer[][] base = new Integer[d][1];
        base[d-1][0] = 0;
        base[d-2][0] = 1;
        if(d > 2) base[d-3][0] = 1;
        for(int i=d-4;i>=0;i--){
            base[i][0] = (base[i+1][0] * 2) % MOD;
        }

//        for Matrix Exponentiation
        Integer[][] baseMat = new Integer[d][d];
        for(Integer[] sub : baseMat) Arrays.fill(sub, 0);
        Arrays.fill(baseMat[0], 1);
        for(int i=1;i<d;i++) baseMat[i][i-1] = 1;
        mats.put(1, baseMat);

        Integer[][] result = multiplyMat(powerMat(baseMat, t), base);
        System.out.println(result[d - 2][0]);
    }

    static Integer[][] powerMat(Integer[][] baseMat, int pow){
        if(mats.containsKey(pow)) return mats.get(pow);

        Integer[][] res;
        if(pow % 2 == 0){
            res = multiplyMat(powerMat(baseMat, pow / 2), powerMat(baseMat, pow / 2));
        }else{
            res = multiplyMat(powerMat(baseMat, pow - 1), baseMat);
        }

        mats.put(pow, res);
        return res;
    }

    static Integer[][] multiplyMat(Integer[][] mat1, Integer[][] mat2){
        Integer[][] ret = new Integer[mat1.length][mat2[0].length];
        for(int i=0;i<ret.length;i++){
            for(int j=0;j<ret[0].length;j++){
                int res = 0;
                for(int k=0;k<d;k++){
                    res = (res + (mat1[i][k] * mat2[k][j]) % MOD) % MOD;
                }
                ret[i][j] = res;
            }
        }
        return ret;
    }
}
