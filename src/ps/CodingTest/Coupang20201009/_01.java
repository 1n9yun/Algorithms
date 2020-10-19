package ps.CodingTest.Coupang20201009;
import java.util.Arrays;

public class _01 {
	public static void main(String[] args) {
		solution(1000000);
	}
	
	static public int[] solution(int N) {

		int max = -1;
		int maxK = 0;
		
		for(int i=10;i>1;i--) {
			String transformedNum = getTransNum(N, i);
			
			int multi = 1;
			for(int j=0;j<transformedNum.length();j++) {
				multi *= (transformedNum.charAt(j) - '0');
			}
			
			if(max < multi) {
				max = multi;
				maxK = i;
			}
		}
		
		int[] answer = {maxK, max};
		System.out.println(Arrays.toString(answer));
		return answer;
	}
	
	
	static String getTransNum(int n, int k) {
		StringBuilder sb = new StringBuilder();
		
		while(n / k != 0) {
			sb.append(n % k);
			n /= k;
		}
		sb.append(n);
		
		sb.reverse();
		return sb.toString();
	}
}
