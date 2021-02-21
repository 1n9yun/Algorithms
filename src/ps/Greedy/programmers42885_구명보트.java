package ps.Greedy;

import java.util.Arrays;

public class programmers42885_구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0;
        int tail = people.length - 1;
        for(int i=0;i<=tail;i++){
            while(i<=tail){
                answer++;
                if(limit >= people[i] + people[tail--]) break;
            }
        }
        return answer;
    }
}
