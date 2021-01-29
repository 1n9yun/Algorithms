package ps.구현;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers17678_셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] timeline = new int[timetable.length];
        for(int i=0;i<timeline.length;i++) timeline[i] = toMinutes(timetable[i]);
        Arrays.sort(timeline);

        int base = toMinutes("09:00");
        int lineIdx = 0;
        int lastTime = 0;
        for(int i=0;i<n;i++){
            int departure = base + i*t;
            int limit = m;

            while(lineIdx < timeline.length && timeline[lineIdx] <= departure && limit > 0){
                limit--;
                if(limit == 0) lastTime = timeline[lineIdx] - 1;
                lineIdx++;
            }
            if(limit > 0) lastTime = departure;
        }

        return String.format("%02d:%02d", lastTime / 60, lastTime % 60);
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