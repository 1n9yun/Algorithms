package ps.구현;

import java.util.Arrays;

public class programmers42577_전화번호목록 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for(int i=0;i<phone_book.length-1;i++){
            if(phone_book[i].length() > phone_book[i+1].length()) continue;

            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        return true;
    }
}
