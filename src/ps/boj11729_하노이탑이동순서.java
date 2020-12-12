package ps;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class boj11729_하노이탑이동순서 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        move(n, 2, 1, 3);
        bw.flush();
    }
    static void move(int n, int mid, int from, int to) throws IOException {
        if(n == 0) return;
        move(n-1, to, from, mid);
        bw.write(from + " " + to + "\n");
        move(n-1, from, mid, to);
    }
}
