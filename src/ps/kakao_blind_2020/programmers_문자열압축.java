package ps.kakao_blind_2020;

public class programmers_문자열압축 {
	public static int solution1(String s) {
		int answer = 987654321;
		
		for(int i=1;i<=s.length();i++) {
			int temp = i;
			String[] subs = new String[s.length() / i];
			for(int idx=0;idx + i<=s.length();idx+=i) {
				subs[idx / i] = s.substring(idx, idx + i);
			}
			
//			System.out.println(Arrays.toString(subs));
			
			int count = 1;
			int div = 1;
			String base = subs[0];
			for(int j=1;j<subs.length;j++) {
				if(base.equals(subs[j])) {
					if((++count) % div == 0) {
						temp++;
						div *= 10;
					}
				}else {
					count = 1;
					div = 1;
					base = subs[j];	
					temp+=i;
				}
			}
//			남은 문자열 길이
			temp += s.length() - (subs.length * i);
			
			answer = Math.min(answer, temp);
		}
		
		return answer;
	}
}
