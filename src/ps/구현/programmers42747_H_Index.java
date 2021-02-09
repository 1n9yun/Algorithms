package ps.구현;

public class programmers42747_H_Index {
    public int solution(int[] citations) {
        int[] counts = new int[10001];
        for(int citation : citations) counts[citation]++;
        for(int i=1;i<counts.length;i++) counts[i] += counts[i-1];

        for(int i=counts.length-1;i>0;i--){
            if(counts[i-1] <= i && counts[counts.length-1]-counts[i-1] >= i) return i;
        }
        return 0;
    }
}
