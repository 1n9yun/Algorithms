package ps.BitMasking;

import java.util.Arrays;
import java.util.Scanner;

public class programmers_카카오블라인드2018_비밀지도 {
    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        System.out.println(Arrays.toString(solution(n, arr1, arr2)));
    }
    static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i=0;i<n;i++) arr1[i] |= (arr2[i] | 1<<n);
        for(int i=0;i<n;i++) answer[i] = Integer.toBinaryString(arr1[i])
                .replace("1", "#")
                .replace("0", " ")
                .substring(1);

        return answer;
    }
}
