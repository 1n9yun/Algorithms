package ps.CodingTest.KakaoBlind2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _02 {
	static Map<Integer, Integer> ansMax;
	static Map<String, Integer>[] ans;
	
	public static void main(String[] args) {
		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = {2,3,4};
		
		solution(orders, course);
			
	}
	public static String[] solution(String[] orders, int[] course) {
		ansMax = new HashMap<>();
		ans = new HashMap[11];
		for(int i=0;i<ans.length;i++) {
			ans[i] = new HashMap<>();
		}
		
        for(int i=0;i<orders.length;i++) {
        	comb(i, 0, orders[i].toCharArray(), "", orders);
        }
        
        List<String> answer = new ArrayList<>();
        
        for(int num : course) {
        	if(ansMax.get(num) == null) continue;
        	int max = ansMax.get(num);
        	for(String key : ans[num].keySet()) {
        		if(ans[num].get(key) == max && max > 1) answer.add(key);
        	}
        }
        
        answer.sort(null);
        
        for(String key : answer) {
        	System.out.println(key);
        }
        String[] ret = new String[answer.size()];
        return answer.toArray(ret);
    }
	
	static void comb(int count, int idx, char[] base, String result, String[] orders) {
		if(idx == base.length) {
			char[] sorted = result.toCharArray();
			Arrays.sort(sorted);
			result = new String(sorted);
			if(ans[result.length()].get(result) != null) return;
			
			int cnt = 1;
			char[] cArrayResult = result.toCharArray();
			for(int i=0;i<orders.length;i++) {
				boolean flag = true;
				if(i == count) continue;
				for(char c : cArrayResult) {
					boolean exist = true;
					for(int j=0;j<orders[i].length();j++) {
//						if(result.equals("ACDE")) System.out.println(i + ":" + j + ", " + c + ":" + orders[i].charAt(j));
						if(c != orders[i].charAt(j)) {
							exist = false;
						}else {
							exist = true;
							break;
						}
					}
					if(!exist) {
						flag = false;
						break;
					}
				}
				if(flag) cnt++;
//				if(result.equals("ACDE")) System.out.println(cnt);
			}
			if(ansMax.get(result.length()) == null || ansMax.get(result.length()) < cnt) ansMax.put(result.length(), cnt);
			ans[result.length()].put(result, cnt);
			return;
		}
		
		comb(count, idx + 1, base, new String(result + base[idx]), orders);
		comb(count, idx + 1, base, new String(result), orders);
	}
}
