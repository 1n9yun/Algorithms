package ps.Segment_Tree;

import java.util.Arrays;
import java.util.Random;

public class Reference_Segment_Tree {
    static class MinimumSegmentTree{
        int[] arr;
        int[] source;
        public MinimumSegmentTree(int[] source){
//            트리의 크기는 source.length < 2^k를 만족하는 k에서,  2^(k+1)가 크기. 쉽게 그냥 source.length * 4 하면 됨
            this.arr = new int[source.length * 4];
            Arrays.fill(this.arr, Integer.MAX_VALUE);
            this.source = source;

            init(0, source.length-1, 1);
            System.out.println("Tree : " + Arrays.toString(arr));
        }

        int init(int left, int right, int idx){
            if(left == right) return this.arr[idx] = source[left];
            int mid = (left + right) / 2;
            return arr[idx] = Math.min(init(left, mid, idx * 2), init(mid + 1, right, idx * 2 + 1));
        }

        int query(int left, int right) {return query(left, right, 1, 0, source.length-1);}
        int query(int left, int right, int idx, int nodeLeft, int nodeRight){
            if(right < nodeLeft || nodeRight < left) return Integer.MAX_VALUE;
            if(left<=nodeLeft && nodeRight<=right) return arr[idx];

            int mid = (nodeLeft + nodeRight) / 2;
            return Math.min(query(left, right, idx * 2, nodeLeft, mid), query(left, right, idx * 2 + 1, mid + 1, nodeRight));
        }
        int update(int index, int value, int idx, int nodeLeft, int nodeRight){
            if(index < nodeLeft || nodeRight < index) return arr[idx];
            if(nodeLeft == nodeRight) return arr[idx] = value;

            int mid = (nodeLeft + nodeRight) / 2;
            return arr[idx] = Math.min(update(index, value, idx * 2, nodeLeft, mid), update(index, value, idx * 2 + 1, mid + 1, nodeRight));
        }
    }

    public static void main(String[] args) {
        int size = 10;
        Random random = new Random();
        int[] source = Arrays.stream(new int[size]).map(i->Math.abs(random.nextInt()) % size).toArray();
        System.out.println(Arrays.toString(source));

        MinimumSegmentTree segTree = new MinimumSegmentTree(source);

        int count = 10;
        while(--count != 0) {
            int left = Math.abs(random.nextInt()) % size;
            int right = -1;
            while (left > right) right = Math.abs(random.nextInt()) % size;

            int correct = Integer.MAX_VALUE;
            for(int i=left;i<=right;i++) correct = Math.min(correct, source[i]);

            int myAnswer = segTree.query(left, right);
            System.out.println("[" + left + " ~ " + right + "] " + myAnswer + " " + correct);
            if(myAnswer != correct) break;
        }
        System.out.println(count == 0 ? "Passed" : "Failed");
    }
}
