package ps.KMP;

import java.util.Arrays;
import java.util.Scanner;

public class boj11585_속타는저녁메뉴 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String target = sc.nextLine().replace(" ", "");
        String input = sc.nextLine().replace(" ", "");
        input += input.substring(0, input.length() - 1);


        int[] fail = new int[target.length()];
        for(int i=1,j=0;i<target.length();i++){
            while(j>0 && target.charAt(i) != target.charAt(j)){
                j = fail[j-1];
            }
            if(target.charAt(i) == target.charAt(j)){
                fail[i] = ++j;
            }
        }

        int count = 0;
        for(int i=0,j=0;i<input.length();i++){
            while(j>0 && input.charAt(i) != target.charAt(j)){
                j = fail[j-1];
            }
            if(input.charAt(i) == target.charAt(j)){
                if(j == target.length() - 1){
                    count++;
                    j = fail[j];
                }else j++;
            }
        }
        System.out.println(target.length() + " " + count);
        int gcd = getGCD(target.length(), count);
        System.out.println(count / gcd + "/" + target.length() / gcd);
    }

    static int getGCD(int a, int b){
        if(b == 0) return a;

        return getGCD(b, a%b);
    }
}
