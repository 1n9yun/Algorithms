package ps.DataStructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class programmers42584_주식가격 {
    class Pair{
        int idx, value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Deque<Pair> stack = new ArrayDeque<>();

        for(int i=0;i<prices.length;i++){
            while(!stack.isEmpty() && stack.getLast().value > prices[i]){
                Pair p = stack.pollLast();
                answer[p.idx] = i - p.idx;
            }

            stack.addLast(new Pair(i, prices[i]));
        }
        while(!stack.isEmpty()){
            Pair p = stack.pollLast();
            answer[p.idx] = (prices.length - 1) - p.idx;
        }

        return answer;
    }
}
