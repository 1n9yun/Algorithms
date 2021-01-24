package ps.구현;

import java.util.ArrayList;
import java.util.List;

public class programmers42586_기능개발 {

    public int[] solution(int[] progresses, int[] speeds) {

        int[] count = new int[100];
        List<Integer> answer = new ArrayList<>();

        int deploy = 0;
        for(int i=0;i<progresses.length;i++){
            deploy = (int)Math.max(deploy, Math.ceil((double)(100 - progresses[i]) / speeds[i]));

            count[deploy]++;
        }
        for(int i=0;i<count.length;i++){
            if(count[i] != 0) answer.add(count[i]);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

}
