package ps.KMP;

import java.util.Scanner;

public class boj4354_문자열제곱 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            String input = sc.next();
            if(input.equals(".")) break;

            int[] fail = new int[input.length()];
            for(int i=1, j=0;i<fail.length;i++){
                while(j>0 && input.charAt(i) != input.charAt(j)){
                    j = fail[j-1];
                }
                if(input.charAt(i) == input.charAt(j)){
                    fail[i] = ++j;
                }
            }

//            이 크기가 반복될 것임
            int size = input.length() - fail[input.length() - 1];

            if(input.length() % size != 0) System.out.println(1);
            else System.out.println(input.length() / size);
        }
    }
}
