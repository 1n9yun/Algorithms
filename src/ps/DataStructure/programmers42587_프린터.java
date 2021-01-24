package ps.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class programmers42587_프린터 {
    static class Item{
        int idx;
        int priority;

        public Item(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,1,3,2}, 2));
        System.out.println(solution(new int[]{1,1,9,1,1,1}, 0));
    }
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        List<Item> list = new ArrayList<>();

        for(int i=0;i<priorities.length;i++)
            list.add(new Item(i, priorities[i]));

        int result = -1;
        while(result != location){
            Item item = list.get(0);

            boolean flag = true;
            for(int i=1;i<list.size();i++){
                if(item.priority < list.get(i).priority){
                    list.remove(0);
                    list.add(list.size(), item);
                    flag = false;
                    break;
                }
            }
            if(flag){
                answer++;
                list.remove(0);
                result = item.idx;
            }
        }

        return answer;
    }
}
