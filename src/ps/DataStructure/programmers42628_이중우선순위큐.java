package ps.DataStructure;

import java.util.Map;
import java.util.TreeMap;

public class programmers42628_이중우선순위큐 {
    private int stoi(String s) {return Integer.parseInt(s);}
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> doublyPriorityQueue = new TreeMap<>();

        for(String s : operations){
            String[] split = s.split(" ");

            if(split[0].equals("I")){
                doublyPriorityQueue.put(stoi(split[1]), doublyPriorityQueue.getOrDefault(stoi(split[1]), 0) + 1);
            }else if(split[0].equals("D")){
                if(doublyPriorityQueue.isEmpty()) continue;

                int target = stoi(split[1]);
                if(target == 1){
//                    최대
                    Map.Entry<Integer, Integer> lastEntry = doublyPriorityQueue.lastEntry();
                    if(lastEntry.getValue() > 1) doublyPriorityQueue.put(lastEntry.getKey(), lastEntry.getValue() - 1);
                    else doublyPriorityQueue.pollLastEntry();
                }else if(target == -1){
//                    최소
                    Map.Entry<Integer, Integer> firstEntry = doublyPriorityQueue.firstEntry();
                    if(firstEntry.getValue() > 1) doublyPriorityQueue.put(firstEntry.getKey(), firstEntry.getValue() - 1);
                    else doublyPriorityQueue.pollFirstEntry();
                }
            }
        }

        if(doublyPriorityQueue.isEmpty()) return new int[]{0, 0};
        else return new int[]{doublyPriorityQueue.lastKey(), doublyPriorityQueue.firstKey()};
    }
}
