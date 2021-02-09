package ps.구현;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42842_카펫 {
    public int[] solution(int brown, int yellow) {
        for(int i=1;i<=Math.sqrt(yellow);i++){
            if(yellow % i == 0 && ((yellow / i + 2) + i) * 2 == brown) return new int[]{yellow / i + 2, i + 2};
        }
        return null;
    }

    @Test
    public void testSoltion(){
        assertThat(solution(10, 2)).isEqualTo(new int[]{4, 3});
        assertThat(solution(8, 1)).isEqualTo(new int[]{3, 3});
        assertThat(solution(24, 24)).isEqualTo(new int[]{8, 6});
    }
}
