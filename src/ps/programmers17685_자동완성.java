package ps;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers17685_자동완성 {
    public int solution(String[] words) {
        Arrays.sort(words);
//        System.out.println(Arrays.toString(words));
        int answer = 0;
        for(int i=0;i<words.length;i++){
            int temp = 0;
//            한번 달랐으면 영원히 다른거임
            boolean leftDiff = false;
            boolean rightDiff = false;
            for(int j=0;j<words[i].length();j++){
//                양 옆 중 가장 멀리까지 같았던 j
                if(i > 0 && !leftDiff) {
                    if(words[i-1].length() > j){
                        if(words[i-1].charAt(j) == words[i].charAt(j)) temp = Math.max(temp, j+1);
                        else leftDiff = true;
                    }
                }
                if(i < words.length - 1 && !rightDiff){
                    if(words[i+1].length() > j){
                        if(words[i].charAt(j) == words[i+1].charAt(j)) temp = Math.max(temp, j+1);
                        else rightDiff = true;
                    }
                }
            }
            temp = Math.min(temp + 1, words[i].length());
//            System.out.println(words[i] + " " + temp);
            answer += temp;
        }
        return answer;
    }

    @Test
    public void testSolution(){
        assertThat(solution(new String[]{"go","gone","guild"})).isEqualTo(7);
        assertThat(solution(new String[]{"abc","def","ghi","jklm"})).isEqualTo(4);
        assertThat(solution(new String[]{"word","war","warrior","world"})).isEqualTo(15);
        assertThat(solution(new String[]{"obcje", "o", "ob", "objc", "obce", "obcjc", "object"})).isEqualTo(25);
        assertThat(solution(new String[]{"wo", "word", "work"})).isEqualTo(10);
        assertThat(solution(new String[]{"aaaaa", "aaaab", "aaabb", "aabbb", "abbbb"})).isEqualTo(19);
    }
}
