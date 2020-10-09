package ps;

import java.util.HashSet;
import java.util.Set;

public class programmers_challenge_1_01 {
	public static void main(String[] args) {
	}
	public Integer[] solution(int[] numbers) {
        Set<Integer> res = new HashSet<>();
        
        for(int i=0;i<numbers.length-1;i++){
            for(int j=i+1;j<numbers.length;j++){
                res.add(numbers[i] + numbers[j]);
            }
        }
        
        Integer[] answer = null;
        res.toArray(answer);
        
        
        return answer;
    }
}
