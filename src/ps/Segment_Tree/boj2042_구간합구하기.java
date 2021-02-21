package ps.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2042_구간합구하기 {
    private static int stoi(String s){return Integer.parseInt(s);}
    private static long stol(String s){return Long.parseLong(s);}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());
        int k = stoi(st.nextToken());

        long[] arr = new long[n];
        long[] segArr = new long[n*4];
        for(int i=0;i<n;i++) arr[i] = stol(br.readLine());

        init(arr, segArr, 0, arr.length - 1, 1);

        for(int i=0;i<m+k;i++){
            st = new StringTokenizer(br.readLine());

            int type = stoi(st.nextToken());
            if(type == 1){
                int index = stoi(st.nextToken()) - 1;
                long value = stol(st.nextToken());

                update(segArr, index, value, 0, arr.length - 1, 1);
            }else{
                int idx1 = stoi(st.nextToken()) - 1;
                int idx2 = stoi(st.nextToken()) - 1;

                System.out.println(query(segArr, idx1, idx2, 0, arr.length - 1, 1));
            }
        }
    }

    static long init(long[] arr, long[] segArr, int nodeLeft, int nodeRight, int idx){
        if(nodeLeft == nodeRight) return segArr[idx] = arr[nodeLeft];
        int mid = (nodeLeft + nodeRight) / 2;
        return segArr[idx] = init(arr, segArr, nodeLeft, mid, idx * 2) + init(arr, segArr, mid + 1, nodeRight, idx * 2 + 1);
    }

    static long query(long[] segArr, int left, int right, int nodeLeft, int nodeRight, int idx){
        if(right < nodeLeft || nodeRight < left) return 0;
        if(left <= nodeLeft && nodeRight <= right) return segArr[idx];

        int mid = (nodeLeft + nodeRight) / 2;
        return query(segArr, left, right, nodeLeft, mid, idx * 2) + query(segArr, left, right, mid + 1, nodeRight, idx * 2 + 1);
    }

    static long update(long[] segArr, int index, long value, int nodeLeft, int nodeRight, int idx){
        if(index < nodeLeft || nodeRight < index) return segArr[idx];
        if(nodeLeft == nodeRight) return segArr[idx] = value;

        int mid = (nodeLeft + nodeRight) / 2;
        return segArr[idx] = update(segArr, index, value, nodeLeft, mid, idx * 2) + update(segArr, index, value, mid + 1, nodeRight, idx * 2 + 1);
    }
}