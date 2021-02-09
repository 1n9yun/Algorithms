package ps.Brute_Force;

import java.util.ArrayList;

public class programmers42840_모의고사 {
    public int[] solution(int[] answers) {
        int[] firstMan = {1, 2, 3, 4, 5};
        int[] secondMan = {2,1,2,3,2,4,2,5};
        int[] thirdMan = {3,3,1,1,2,2,4,4,5,5};

        int firstScore = 0;
        int secondScore = 0;
        int thirdScore = 0;
        int max = 0;
        for(int i=0;i<answers.length;i++){
            if(answers[i] == firstMan[i % firstMan.length]) firstScore++;
            if(answers[i] == secondMan[i % secondMan.length]) secondScore++;
            if(answers[i] == thirdMan[i % thirdMan.length]) thirdScore++;
        }
        max = Math.max(max, Math.max(firstScore, Math.max(secondScore, thirdScore)));

        ArrayList<Integer> answer = new ArrayList<>();
        if(firstScore == max) answer.add(1);
        if(secondScore == max) answer.add(2);
        if(thirdScore == max) answer.add(3);

        return answer.stream().mapToInt(i->i).toArray();
    }
}
