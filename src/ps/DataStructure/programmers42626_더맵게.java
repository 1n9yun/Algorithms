package ps.DataStructure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class programmers42626_더맵게 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.comparingLong(l->l));
        for(int s : scoville) pq.add((long)s);

        int answer = 0;
        while(!pq.isEmpty()){
            long num = pq.poll();
            if(num >= K) return answer;
            else if(pq.isEmpty()) return -1;

            answer++;
            pq.add(num + pq.poll() * 2);
        }
        return -1;
    }
}
