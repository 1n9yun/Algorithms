package ps.구현;

import java.util.stream.IntStream;

public class programmers42889_실패율 {
    public int[] solution(int N, int[] stages) {
        int[] completed = new int[N+2];
        int[] incompleted = new int[N+2];
        for(int stage : stages) {
            completed[stage]++;
            incompleted[stage]++;
        }
        for(int i=completed.length-1;i>0;i--) completed[i-1] += completed[i];

        return IntStream.rangeClosed(1, N)
                .boxed()
                .sorted((o1, o2) -> -1 * Double.compare(
                        completed[o1] == 0 ? 0 : (double)incompleted[o1] / completed[o1],
                        completed[o2] == 0 ? 0 : (double)incompleted[o2] / completed[o2]
                        )
                )
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
