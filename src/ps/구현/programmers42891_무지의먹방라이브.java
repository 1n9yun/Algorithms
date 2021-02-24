package ps.구현;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42891_무지의먹방라이브 {
    public int solution(int[] food_times, long k) {
        int[] sorted_food_times = food_times.clone();
        Arrays.sort(sorted_food_times);

        int prev = 0;
        int length = sorted_food_times.length;
        for (int sorted_food_time : sorted_food_times) {
            long remain = sorted_food_time - prev;

            if (k >= remain * length) {
                k -= remain * length;
                prev += remain;
                length--;
            } else {
                k %= length;
                break;
            }
        }
        for(int i=0;i<food_times.length;i++){
            if(food_times[i] <= prev) {
                continue;
            }
            if(--k < 0) return i+1;
        }
        return -1;
    }

    @Test
    public void testSolution(){
        assertThat(solution(new int[]{5,1,22,7,14,4,3,6,24,1,2,4,51}, 100)).isEqualTo(9);
        assertThat(solution(new int[]{3,1,2}, 5)).isEqualTo(1);
    }
}
