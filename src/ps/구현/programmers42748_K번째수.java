package ps.구현;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42748_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        int answerIdx = 0;
        for(int[] command : commands){
            int[] sub = Arrays.copyOfRange(array, command[0]-1, command[1]);
            Arrays.sort(sub);
            answer[answerIdx++] = sub[command[2]-1];
        }
        return answer;
    }

    @Test
    public void testSolution(){
        assertThat(solution(new int[]{1,5,2,6,3,7,4}, new int[][]{{2,5,3},{4,4,1},{1,7,3}})).isEqualTo(new int[]{5,6,3});
    }
}
