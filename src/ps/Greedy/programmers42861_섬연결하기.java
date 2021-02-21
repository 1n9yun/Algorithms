package ps.Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class programmers42861_섬연결하기 {
    int[] islandSet;
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(o->o[2]));
        islandSet = new int[n];
        for(int i=0;i< islandSet.length;i++) islandSet[i] = i;

        int answer = 0;
        for(int i=0;i<costs.length;i++){
            if(union(costs[i][0], costs[i][1])){
                answer += costs[i][2];
                n--;
                if(n == 1) break;
            }
        }
        return answer;
    }

    int find(int idx){
        if(idx == islandSet[idx]) return idx;

        return islandSet[idx] = find(islandSet[idx]);
    }

    boolean union(int p1, int p2){
        p1 = find(p1);
        p2 = find(p2);

        if(p1 == p2) return false;

        islandSet[p1] = p2;
        return true;
    }
}
