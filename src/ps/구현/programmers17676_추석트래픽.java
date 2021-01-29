package ps.구현;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers17676_추석트래픽 {
    double stod(String s) {return Double.parseDouble(s);}
    public int solution(String[] lines) {
        ArrayList<Integer> startTimes = new ArrayList<>();
        ArrayList<Integer> endTimes = new ArrayList<>();

        for (String s : lines) {
            String[] split = s.split(" ");
            String[] timeSplit = split[1].split(":");

            int end = (int) ((stod(timeSplit[0]) * 3600 + stod(timeSplit[1]) * 60 + stod(timeSplit[2])) * 1000);
            int start = end - ((int) (stod(split[2].substring(0, split[2].length() - 1)) * 1000) - 1);

            startTimes.add(start);
            endTimes.add(end);
        }

        int answer = 0;
        int temp = 0;
        startTimes.sort(Comparator.comparingInt(o -> o));
        endTimes.sort(Comparator.comparingInt(o -> o));

        int endIdx = 0;
        for (int startTime : startTimes) {
            temp++;
            for (; endIdx < endTimes.size(); endIdx++) {
                if (startTime - 999 > endTimes.get(endIdx)) temp--;
                else break;
            }
            answer = Math.max(answer, temp);
        }
        return answer;
    }

    @Test
    public void solutionTest(){
        assertThat(solution(new String[]{
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"
        })).isEqualTo(1);
        assertThat(solution(new String[]{
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        })).isEqualTo(2);
        assertThat(solution(new String[]{
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        })).isEqualTo(7);
        //assertThat(solution(new String[]{

        //})).isEqualTo(2);
        //assertThat(solution(new String[]{

        //})).isEqualTo(2);

    }
}
