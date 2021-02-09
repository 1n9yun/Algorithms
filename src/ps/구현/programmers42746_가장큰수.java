package ps.구현;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42746_가장큰수 {
    public String solution(int[] numbers) {
        Object[] toStringArray = Arrays.stream(numbers).mapToObj(Integer::toString).sorted((o1, o2) -> o1.concat(o2).compareTo(o2.concat(o1)) * -1).toArray();
        if(toStringArray[0].equals("0")) return "0";
        else return (String) Arrays.stream(toStringArray).reduce((o1, o2)->((String)o1).concat((String)o2)).get();
    }

    @Test
    public void testSolution(){
        assertThat(solution(new int[]{6, 10, 2})).isEqualTo("6210");
        assertThat(solution(new int[]{3, 30, 34, 5, 9})).isEqualTo("9534330");
        assertThat(solution(new int[]{0,1,25,12,3,2,1,65,5,12,651,23,1,65646666, 6564})).isEqualTo("65656466666564651532523212121110");
        assertThat(solution(new int[]{661, 6})).isEqualTo("6661");
        assertThat(solution(new int[]{0, 0, 0, 0})).isEqualTo("0");
        assertThat(solution(new int[]{23, 232})).isEqualTo("23232");
        assertThat(solution(new int[]{21, 212})).isEqualTo("21221");
    }
}
