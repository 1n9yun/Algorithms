package ps.CodingTest.KakaoBlind2021;

import java.util.HashMap;
import java.util.Map;

public class _05 {
	public String solution(String play_time, String adv_time, String[] logs) {
//      시간을 0 ~ 36만의 배열로 표현
//      Map에 시작/끝 시간 Key/Value로 넣기
//      Set에 시작/끝 시간 모두 넣기
//      List로 변환 후 정렬
//      모든 구간이 생기는데 각 구간별 몇명이 봤는지 구하기
 //       Map<Integer, Integer>
 //       각 log 시작시간.put(+1)
 //       각 log 끝시간.put(-1)
//      구간의 시작 마다 몇명이 보고있는지 * 시간 계산 최대값 구하기
		Map<Integer, Integer> map = new HashMap<>();
		for(String s : logs) {
			String[] split = s.split("-");
			
			int start = getSeconds(split[0]);
			int end = getSeconds(split[1]);
			
			if(map.get(start) == null) map.put(start, 1);
			else map.put(start, map.get(start) + 1);
			
			if(map.get(end) == null) map.put(end, -1);
			else map.put(end, map.get(end) - 1);
		}
		
		int add = 0;
		int play_seconds = getSeconds(play_time);
		int adv_seconds = getSeconds(adv_time);
		int[] timeline = new int[play_seconds + 1];
		for(int i=0;i<=play_seconds;i++) {
			if(map.get(i) == null) timeline[i] = add;
			else {
				add = map.get(i);
				timeline[i] = add;
			}
			timeline[i] += (i == 0 ? 0 : timeline[i-1]);
		}
		
		for(int i=0;i<play_seconds - adv_seconds + 1; i++) {
			
		}
	}
	
	public int getSeconds(String time) {
		String[] times = time.split(":");
		return Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
	}
}
