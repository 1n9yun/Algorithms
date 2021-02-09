package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1039_교환 {
    static class Item{
        int n;
        int count;

        public Item(int n, int count) {
            this.n = n;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int length = String.valueOf(n).length();

        Queue<Item> q = new LinkedList<>();
        q.add(new Item(n, 0));
        int[] check = new int[(int) Math.pow(10, length)];

        int answer = -1;
        while (!q.isEmpty()) {
            Item item = q.poll();

            if (item.count == k) {
                answer = Math.max(answer, item.n);
                continue;
            }

            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    if (j == length-1 && (item.n / (int) Math.pow(10, i)) % 10 == 0) continue;
                    int swaped = swap(item.n, i, j);
                    if (check[swaped] == item.count + 1) continue;
                    check[swaped] = item.count + 1;

                    q.add(new Item(swaped, item.count + 1));
                }
            }
        }
        System.out.println(answer);
    }
    static int swap(int n, int idx1, int idx2){
        int pos1 = (int)Math.pow(10, idx1);
        int pos2 = (int)Math.pow(10, idx2);

        int digit1 = n / pos1 % 10;
        int digit2 = n / pos2 % 10;

        n += -pos1 * digit1 + pos1 * digit2;
        n += -pos2 * digit2 + pos2 * digit1;

        return n;
    }
}