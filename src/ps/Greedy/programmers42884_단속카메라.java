package ps.Greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42884_단속카메라 {
    class Item{
        boolean type;
        int pos;

        public Item(boolean type, int pos) {
            this.type = type;
            this.pos = pos;
        }
    }
    public int solution(int[][] routes) {
        Item[] line = new Item[routes.length * 2];
        for(int i=0;i<line.length;i+=2) {
            line[i] = new Item(true, routes[i/2][0]);
            line[i+1] = new Item(false, routes[i/2][1]);
        }
        Arrays.sort(line, Comparator.comparingInt(o->o.pos));

        boolean ended = false;
        int lastPos = -30001;
        int answer = 0;
        for(int i=0;i<line.length;i++){
            if(line[i].type && !ended && lastPos != line[i].pos) {
                answer++;
                ended = true;
            }else if(!line[i].type && ended && lastPos != line[i].pos){
                lastPos = line[i].pos;
                ended = false;
            }
        }
        return answer;
    }

    @Test
    public void testSolution(){
        assertThat(solution(new int[][]{{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}})).isEqualTo(2);
        assertThat(solution(new int[][]{{-20,15}, {-13,-5}, {-18,-14}, {-5,-3}})).isEqualTo(2);
        assertThat(solution(new int[][]{{1,2}, {2,3}, {3,4}, {4,5}})).isEqualTo(3);
    }
}
