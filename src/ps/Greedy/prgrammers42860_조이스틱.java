package ps.Greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class prgrammers42860_조이스틱 {
    public int solution(String name) {
        int answer = 0;

        Deque<Integer> changePosition = new ArrayDeque<>();
        for(int i=0;i<name.length();i++) if(name.charAt(i) != 'A') changePosition.add(i);

        int nowPosition = 0;
        boolean backPosition = false;
        while(!changePosition.isEmpty()){
            System.out.println(changePosition);
            System.out.println(answer + " " + nowPosition);
            int front = changePosition.getFirst();
            int back = changePosition.getLast();

            int frontDistance = Math.min(
                    Math.abs(nowPosition - front),
                    (backPosition ? (name.length() - 1) - nowPosition + front : nowPosition + (name.length() - 1) - front) + 1
            );
            int backDistance = Math.min(
                    Math.abs(nowPosition - back),
                    (backPosition ? (name.length() - 1) - nowPosition + back : nowPosition + (name.length() - 1) - back) + 1
            );

            if(frontDistance > backDistance){
                nowPosition = back;
                changePosition.pollLast();
                backPosition = true;
                answer += backDistance + Math.min(name.charAt(back) - 'A', 26 - (name.charAt(back) - 'A'));
            }else{
                nowPosition = front;
                changePosition.pollFirst();
                backPosition = false;
                answer += frontDistance + Math.min(name.charAt(front) - 'A', 26 - (name.charAt(front) - 'A'));
            }
        }
        return answer;
    }
}
