package ps.Greedy;

import java.util.Arrays;

public class programmers42862_체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        int[] result = new int[n+1];
        Arrays.fill(result, 1);
        for(int losted : lost) result[losted]--;

        for(int i=0;i<reserve.length;i++) {
            if(result[reserve[i]] == 0){
                result[reserve[i]]++;
                answer++;
                reserve[i] = -1;
            }
        }
        Arrays.sort(reserve);

        for(int i=0;i<reserve.length;i++){
            if(reserve[i] == -1) continue;

            if(result[reserve[i] - 1] == 0) answer++;
            else if(reserve[i] + 1 < n && result[reserve[i] + 1] == 0){
                answer++;
                result[reserve[i] + 1] = 1;
            }
        }
        return answer;
    }
}
