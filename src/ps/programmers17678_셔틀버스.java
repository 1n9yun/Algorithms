package ps;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers17678_셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] timeline = new int[24 * 60 + 1];
        for(String s : timetable) timeline[toMinutes(s)]++;
        for(int i=1;i<timeline.length;i++) timeline[i] += timeline[i-1];

        int base = toMinutes("09:00");
        int remain = 0;
        int takeTime = 0;
        for(int i=0;i<n;i++){
            int start = base + t * i;
            int before = i == 0 ? 0 : start - t;
            int temp = start;
            int ahead = timeline[start] - timeline[before];

            while(ahead + remain + 1 > m && before < temp){
                ahead = timeline[--temp] - timeline[before];
            }
            if(ahead + remain + 1 <= m) takeTime = temp;

            remain = Math.max((timeline[start] - timeline[before] + remain) - m, 0);
        }
        return String.format("%02d:%02d", takeTime / 60, takeTime % 60);
    }
    int toMinutes(String s){
        String[] split = s.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
    @Test
    public void testSolution(){
        assertThat(solution(1, 1, 5, new String[]{
                "08:00", "08:01", "08:02", "08:03"
        })).isEqualTo("09:00");
        assertThat(solution(2, 10, 2, new String[]{
                "09:10", "09:09", "08:00"
        })).isEqualTo("09:09");
        assertThat(solution(2, 1, 2, new String[]{
                "09:00", "09:00", "09:00", "09:00"
        })).isEqualTo("08:59");
        assertThat(solution(1, 1, 5, new String[]{
                "00:01", "00:01", "00:01", "00:01", "00:01"
        })).isEqualTo("00:00");
        assertThat(solution(1, 1, 1, new String[]{
                "23:59"
        })).isEqualTo("09:00");
        assertThat(solution(10, 60, 45, new String[]{
                "23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"
        })).isEqualTo("18:00");
    }
}
