package ps.구현;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers17684_압축 {
    public int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> dict = new HashMap<>();
        int idx = 1;

//        기본 문자들 넣어놓기
        for(int i=0;i<26;i++) dict.put(Character.toString((char)('A' + i)), idx++);

        for(int i=0;i<msg.length();i++){
            StringBuilder w = new StringBuilder(Character.toString(msg.charAt(i)));
            while(++i < msg.length() && dict.containsKey(w.toString() + msg.charAt(i))){
                w.append(msg.charAt(i));
            }
            answer.add(dict.get(w.toString()));

            if(i < msg.length()) {
                dict.put(w.toString() + msg.charAt(i--), idx++);
            }
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    @Test
    public void testSolution(){
        assertThat(solution("KAKAO")).isEqualTo(new int[]{11, 1, 27, 15});
        assertThat(solution("TOBEORNOTTOBEORTOBEORNOT")).isEqualTo(new int[]{20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34});
        assertThat(solution("ABABABABABABABAB")).isEqualTo(new int[]{1, 2, 27, 29, 28, 31, 30});
    }
}
