package ps.Greedy;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42883_큰수만들기 {
    public String solution(String number, int k) {
        int goalLength = number.length() - k;
        int max = 0;
        int maxIdx = -1;
        StringBuilder answer = new StringBuilder();

        while(answer.length() != goalLength){
            int remain = goalLength - answer.length();

            for(int i=maxIdx+1;i<number.length() - (remain - 1);i++){
                int num = number.charAt(i) - '0';
                if(max < num){
                    max = num;
                    maxIdx = i;
                    if(max == 9) break;
                }
            }
            answer.append(max);
            max = 0;
        }
        return answer.toString();
    }

    @Test
    public void testSolution(){
        assertThat(solution("4177252841", 4)).isEqualTo("775841");
        assertThat(solution("1231234", 3)).isEqualTo("3234");
        assertThat(solution("1924", 2)).isEqualTo("94");
    }
}

