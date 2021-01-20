package ps.구현;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class programmers17684_압축 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("KAKAO")));
        System.out.println(Arrays.toString(solution("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(solution("ABABABABABABABAB")));
    }
    public static int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> dict = new HashMap<>();
        int idx = 1;
        for(int i=0;i<26;i++) dict.put(Character.toString((char)('A' + i)), idx++);

        for(int i=0;i<msg.length();i++){
            StringBuilder w = new StringBuilder(Character.toString(msg.charAt(i)));
            System.out.println(w.toString());
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
}
