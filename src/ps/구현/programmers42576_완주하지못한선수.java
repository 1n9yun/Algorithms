package ps.구현;

import java.util.Arrays;
import java.util.HashMap;

public class programmers42576_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
//        return solution1(participant, completion);
        return solution2(participant, completion);
    }

    public String solution1(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i=0;i<participant.length;i++){
            if(i == participant.length - 1) return participant[i];
            if(participant[i].equals(completion[i])) continue;
            return participant[i];
        }
        return null;
    }

    public String solution2(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String s : participant)
            map.put(s, map.getOrDefault(s, 0) + 1);

        for(String s : completion)
            if(map.get(s) == 1) map.remove(s);
            else map.put(s, map.get(s) - 1);

        return map.keySet().iterator().next();
    }
}
