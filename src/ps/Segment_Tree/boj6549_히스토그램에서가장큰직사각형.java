package ps.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj6549_히스토그램에서가장큰직사각형 {
    static int stoi(String s) {return Integer.parseInt(s);}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = stoi(st.nextToken());
            if(n == 0) break;

            answer = -1;
            int[] histogram = new int[n];
            int[] segTree = new int[n*4];
            Arrays.fill(segTree, Integer.MAX_VALUE);
            for(int i=0;i<n;i++) histogram[i] = stoi(st.nextToken());

            init(histogram, segTree, 0, histogram.length-1, 1);
            System.out.println(Arrays.toString(segTree));
            System.out.println(answer);
        }
    }

    static long answer = -1;
    static int init(int[] source, int[] segTree, int nodeLeft, int nodeRight, int idx){
        if(nodeLeft == nodeRight) return segTree[idx] = source[nodeLeft];
        int mid = (nodeLeft + nodeRight) / 2;
        return segTree[idx] = Math.min(
                init(source, segTree, nodeLeft, mid, idx * 2),
                init(source, segTree, mid + 1, nodeRight, idx * 2 + 1)
        );
    }
    static int query(int[] segTree, int left, int right, int nodeLeft, int nodeRight, int idx){
        if(left > nodeRight || nodeLeft > right) return Integer.MAX_VALUE;
        if(left <= nodeLeft && nodeRight <= right) return segTree[idx];

        int mid = (nodeLeft + nodeRight) / 2;
        return Math.min(
                query(segTree, left, right, nodeLeft, mid + 1, idx * 2),
                query(segTree, left, right, mid, nodeRight, idx * 2 + 1));
    }
}
