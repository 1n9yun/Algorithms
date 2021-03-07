package ps.String;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class programmers60057_문자열압축 {
    public int solution(String s) {
        int answer = 1001;

        for(int len=1;len<=s.length();len++){
            StringBuilder sb = new StringBuilder();
            int count = 1;
            String prev = "";

            for(int i=0;i<s.length();i++){
                String next = s.substring(i, i + len);
                if(prev.equals(next)){
                    count++;
                }else{
                    if(count != 1) sb.append(count);
                    sb.append(prev);
                    prev = next;
                    count = 1;
                }
                i += len - 1;
                if(i+1 + len > s.length()){
                    if(count != 1) sb.append(count);
                    sb.append(prev);
                    sb.append(s.substring(i+1));
                    break;
                }
            }
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }

    @Test
    public void testSolution(){
        assertEquals(solution("aabbaccc"), 7);
        assertEquals(solution("ababcdcdababcdcd"), 9);
        assertEquals(solution("abcabcdede"), 8);
        assertEquals(solution("abcabcabcabcdededededede"), 14);
        assertEquals(solution("xababcdcdababcdcd"), 17);
    }
}
