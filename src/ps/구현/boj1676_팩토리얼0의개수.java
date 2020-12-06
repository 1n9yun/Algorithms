package ps.구현;

import java.util.Scanner;

public class boj1676_팩토리얼0의개수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int five = 0;
        int two = 0;
        for(int i=n;i>=2;i--){
            int temp = i;
            while(temp % 5 == 0){
                temp /= 5;
                five++;
            }
            temp = i;
            while(temp % 2 == 0){
                temp /= 2;
                two++;
            }
        }
        System.out.println(Math.min(five, two));
    }
}
