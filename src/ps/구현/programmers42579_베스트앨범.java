package ps.구현;

import java.util.*;

public class programmers42579_베스트앨범 {
    class Node{
        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
    class Item {
        int sum = 0;
        PriorityQueue<Node> pq;

        public Item() {
            pq = new PriorityQueue<>((o1, o2) -> {
               if(o1.time < o2.time) return 1;
               else if(o1.time == o2.time) {
                   if(o1.idx > o2.idx) return 1;
               }
               return -1;
            });
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Item> map = new HashMap<>();
        List<String> genreList = new ArrayList<>();

        for(int i=0;i<genres.length;i++){
            Item item = map.get(genres[i]);
            if(item == null) {
                genreList.add(genres[i]);
                item = new Item();
            }
            item.sum += plays[i];
            item.pq.add(new Node(i, plays[i]));

            map.put(genres[i], item);
        }
        Collections.sort(genreList, (o1, o2) -> {
            if(map.get(o1).sum < map.get(o2).sum) return 1;
            return -1;
        });

        List<Integer> answer = new ArrayList<>();
        for(String s : genreList){
            PriorityQueue<Node> pq = map.get(s).pq;
            answer.add(pq.poll().idx);
            if(!pq.isEmpty()) answer.add(pq.poll().idx);
        }
        return answer.stream().mapToInt(i->i).toArray();
    }
}
