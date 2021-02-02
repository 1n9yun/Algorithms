package ps.DataStructure;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42627_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int time = 0;
        int answer = 0;
        for(int i=0;i<jobs.length;i++){
            if(pq.isEmpty() && time < jobs[i][0]) time = jobs[i][0];
            while(i<jobs.length && time>=jobs[i][0]) pq.add(jobs[i++]);
            if(i<jobs.length && time<jobs[i][0]) i--;

            while(!pq.isEmpty()){
                int[] job = pq.poll();
                time += job[1];
                answer += time - job[0];
                if(i < jobs.length) break;
            }
        }
        return answer / jobs.length;
    }
    @Test
    public void testSolution(){
        assertThat(solution(new int[][]{
                {0, 3}, {1, 9}, {2, 6}
        })).isEqualTo(9);
    }
}
