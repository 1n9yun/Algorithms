package ps.Segment_Tree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42883_큰수만들기 {
    class Node {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    class MaximumSegmentTree{
        Node[] arr;
        Node[] source;
        public MaximumSegmentTree(int[] source){
//            트리의 크기는 source.length < 2^k를 만족하는 k에서,  2^(k+1)가 크기. 쉽게 그냥 source.length * 4 하면 됨
            this.source = new Node[source.length];
            this.arr = new Node[source.length * 4];
            for(int i=0;i<this.source.length;i++) this.source[i] = new Node(i, source[i]);

            init(0, this.source.length-1, 1);
        }

        Node init(int left, int right, int idx){
            if(left == right) return this.arr[idx] = this.source[left];

            int mid = (left + right) / 2;
            Node leftNode = init(left, mid, idx * 2);
            Node rightNode = init(mid + 1, right, idx * 2 + 1);

            return this.arr[idx] = leftNode.value < rightNode.value ? rightNode : leftNode;
        }

        Node query(int left, int right) {return query(left, right, 1, 0, source.length-1);}
        Node query(int left, int right, int idx, int nodeLeft, int nodeRight){
            if(right < nodeLeft || nodeRight < left) return new Node(-1, Integer.MIN_VALUE);
            if(left<=nodeLeft && nodeRight<=right) return this.arr[idx];

            int mid = (nodeLeft + nodeRight) / 2;
            Node leftNode = query(left, right, idx * 2, nodeLeft, mid);
            Node rightNode = query(left, right, idx * 2 + 1, mid + 1, nodeRight);

            return leftNode.value < rightNode.value ? rightNode : leftNode;
        }
    }

    public String solution(String number, int k){
        int[] source = new int[number.length()];
        for(int i=0;i<number.length();i++) source[i] = number.charAt(i) - '0';
        MaximumSegmentTree segTree = new MaximumSegmentTree(source);

        StringBuilder answer = new StringBuilder();
        int answerLength = number.length() - k;

        int left = 0;
        int right = number.length() - answerLength;
        while(answer.length() != answerLength){
            Node maxNode = segTree.query(left, right);
            answer.append(maxNode.value);

            left = maxNode.index + 1;
            right++;
        }
        return answer.toString();
    }

    //    public String solution(String number, int k) {
//        int goalLength = number.length() - k;
//        int max = 0;
//        int maxIdx = -1;
//        StringBuilder answer = new StringBuilder();
//
//        while(answer.length() != goalLength){
//            int remain = goalLength - answer.length();
//
//            for(int i=maxIdx+1;i<number.length() - (remain - 1);i++){
//                int num = number.charAt(i) - '0';
//                if(max < num){
//                    max = num;
//                    maxIdx = i;
//                    if(max == 9) break;
//                }
//            }
//            answer.append(max);
//            max = 0;
//        }
//        return answer.toString();
//    }

    @Test
    public void testSolution(){
        assertThat(solution("4177252841", 4)).isEqualTo("775841");
        assertThat(solution("1231234", 3)).isEqualTo("3234");
        assertThat(solution("1924", 2)).isEqualTo("94");
    }
}

