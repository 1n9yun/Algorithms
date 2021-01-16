package ps.구현;

import java.util.*;

public class programmers17680_캐시 {
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(solution(cacheSize, cities));
    }
    public static int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;

        LinkedHashSet<String> cache = new LinkedHashSet<>();

        int answer = 0;
        for(int i=0;i<cities.length;i++){
            String city = cities[i].toLowerCase();

            if(cache.contains(city)){
                answer++;

                cache.remove(city);
            }else{
                answer += 5;

                if(cache.size() >= cacheSize) {
                    Iterator<String> it = cache.iterator();
                    it.next();
                    it.remove();
                }
            }
            cache.add(city);
        }
        return answer;
    }
}
