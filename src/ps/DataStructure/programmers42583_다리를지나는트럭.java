package ps.DataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class programmers42583_다리를지나는트럭 {
    class Item{
        int onTime;
        int weight;

        public Item(int onTime, int weight) {
            this.onTime = onTime;
            this.weight = weight;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Item> q = new LinkedList<>();
        int weightOnBridge = 0;
        for(int i=0;i<truck_weights.length;i++){
            answer++;

            while(weightOnBridge + truck_weights[i] > weight){
                Item item = q.poll();

                weightOnBridge -= item.weight;
                int add = bridge_length - (answer - item.onTime);
                if(add > 0) answer += add;
            }

            q.add(new Item(answer, truck_weights[i]));
            weightOnBridge += truck_weights[i];
        }
        while(!q.isEmpty()){
            Item item = q.poll();
            answer += bridge_length - (answer - item.onTime);
        }
        return answer;
    }
}
