package ps.cupang20201009;

public class _01 {
	public static void main(String[] args) {
		solution(3);
	}
	
	static public int[] solution(int N) {
		int[] answer = {};

		System.out.println(getTransNum(5, 2));
		
		return answer;
	}
	
	
	static String getTransNum(int n, int k) {
		StringBuilder sb = new StringBuilder();
		
		while(n / k != 0) {
			sb.append(n % k);
			n /= k;
		}
		
		sb.reverse();
		return sb.toString();
	}
}
