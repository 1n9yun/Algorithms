package ps.구현;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers17687_n진수게임 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder str = new StringBuilder();

        int num = 0;
        for(int i=0;i<t;i++){
            while(str.length()-1 < p-1) str.append(getnBase(num++, n));
            answer.append(str.charAt(p-1));
            p += m;
        }
        return answer.toString();
    }

    StringBuilder getnBase(int num, int n){
        StringBuilder sb = new StringBuilder();

        while(num / n >= 0){
            int res = num % n;
            if(res >= 10) sb.append((char)('A' + (res - 10)));
            else sb.append(res);

            num /= n;
            if(num == 0) break;
        }

        return sb.reverse();
    }

    @Test
    public void testSolution(){
        assertThat(solution(2,4,2,1)).isEqualTo("0111");
        assertThat(solution(16, 16,2,1)).isEqualTo("02468ACE11111111");
        assertThat(solution(16, 16,2,2)).isEqualTo("13579BDF01234567");
    }
}
