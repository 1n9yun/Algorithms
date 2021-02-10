package ps.구현;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42890_후보키 {
    ArrayList<ArrayList<Integer>> candidateKeys = new ArrayList<>();
    public int solution(String[][] relation) {
        for(int i=1;i<=relation[0].length;i++){
            comb(0, 0, IntStream.range(0, relation[0].length).toArray(), new int[i], relation);
        }
        return candidateKeys.size();
    }

    void comb(int idx, int cnt, int[] base, int[] result, String[][] relation){
        if(cnt == result.length){
            ArrayList<Integer> candidateKey = new ArrayList<>(Arrays.asList(Arrays.stream(result).boxed().toArray(Integer[]::new)));
            for(ArrayList<Integer> key : candidateKeys) {
                if (candidateKey.containsAll(key)) return;
            }

            for(int i=0;i<relation.length-1;i++){
                for(int j=i+1;j<relation.length;j++){
                    boolean diff = false;

                    for(int column : result){
                        if(!relation[i][column].equals(relation[j][column])){
                            diff = true;
                            break;
                        }
                    }
                    if(!diff) return;
                }
            }

            candidateKeys.add(candidateKey);
            return;
        }

        for(int i=idx;i<base.length;i++){
            result[cnt] = base[i];
            comb(i + 1, cnt + 1, base, result, relation);
        }
    }

    @Test
    public void testSolution(){
        assertThat(solution(new String[][]{
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        })).isEqualTo(2);
        candidateKeys.clear();
        assertThat(solution(new String[][] {
                {"100", "1", "a", "b"}
        })).isEqualTo(4);
    }
}
