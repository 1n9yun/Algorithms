package ps.Permutation;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42839_소수찾기 {
    Set<Integer> answer = new HashSet<>();
    public int solution(String numbers) {
        for(int i=1;i<=numbers.length();i++){
            perm(i, numbers, new boolean[numbers.length()], new StringBuilder());
        }
        return answer.size();
    }
    void perm(int cnt, String numbers, boolean[] check, StringBuilder result){
        if(cnt == 0){
            int intResult = Integer.parseInt(result.toString());
            if(intResult < 2 || (intResult != 2 && intResult % 2 == 0) || answer.contains(intResult)) return;

            boolean flag = true;
            for(int i=3;i<=Math.sqrt(intResult);i+=2){
                if(intResult % i == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer.add(intResult);
            return;
        }

        for(int i=0;i<numbers.length();i++){
            if(check[i]) continue;
            check[i] = true;
            perm(cnt - 1, numbers, check, result.append(numbers.charAt(i)));
            result.deleteCharAt(result.length() - 1);
            check[i] = false;
        }
    }

    @Test
    public void testSolution(){
        assertThat(solution("17")).isEqualTo(3);
        answer.clear();
        assertThat(solution("011")).isEqualTo(2);
        answer.clear();
        assertThat(solution("217")).isEqualTo(6);
    }
}
