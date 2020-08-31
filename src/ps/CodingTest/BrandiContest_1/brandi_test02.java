package ps.CodingTest.BrandiContest_1;

import java.util.Scanner;

public class brandi_test02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			long ans = 0;
			long n = sc.nextLong();
			long m = sc.nextLong();
			
			long nDiv5 = n / 5;
			long mRemains = m - nDiv5 * 7;
			if(mRemains < 0) {
				long add = (mRemains * -1) / 7 + 1;
				nDiv5 -= add;
				mRemains += add * 7;
			}
			
			ans += nDiv5;
			n -= nDiv5 * 5;
			if(n >= 5) {
				ans += (n + mRemains) / 12;
			}
			
			System.out.println(ans);
		}
	}
}
