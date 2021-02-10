package ps.구현;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class programmers42888_오픈채팅방 {
    class Item{
        String command, uid;

        Item(String command, String uid) {
            this.command = command;
            this.uid = uid;
        }
    }
    public String[] solution(String[] record) {
        Map<String, String> user = new HashMap<>();

        ArrayList<Item> answer = new ArrayList<>();
        for(String input : record){
            String[] split = input.split(" ");
            if(!split[0].equals("Leave")) user.put(split[1], split[2]);
            if(!split[0].equals("Change")) answer.add(new Item(split[0], split[1]));
        }

        return answer.stream().map(i->user.get(i.uid) + (i.command.equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다.")).toArray(String[]::new);
    }
}