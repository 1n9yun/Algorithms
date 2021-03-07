package ps.Math;

import java.util.ArrayList;
import java.util.Scanner;

public class boj20946_합성인수분해 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        ArrayList<Long> result = new ArrayList<>();
        if(solve(n, result)) result.forEach(i->System.out.print(i + " "));
        else System.out.println(-1);
    }

    static boolean solve(long n, ArrayList<Long> result){
        if(isPrimeNumber(n)) return false;
        if(Math.sqrt(n) < 4) {
            result.add(n);
            return true;
        }

        for(int i=4;i<=Math.sqrt(n);i++){
            if(isPrimeNumber(i)) continue;
            if(n % i == 0) {
                result.add((long)i);
                if(solve(n / i, result)) return true;
                result.remove(result.size() - 1);
            }
        }
        result.add(n);
        return true;
    }

    static boolean isPrimeNumber(long number){
        if(number == 2) return true;
        if(number % 2 == 0) return false;

        for(int i=3;i<=Math.sqrt(number);i+=2){
            if(number % i == 0) return false;
        }
        return true;
    }
}
