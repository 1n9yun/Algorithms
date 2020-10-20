package ps.CodingTest.Toss200801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//100	300	10	0	40	0	0	70	65
//40	300	20	10	10	20	100	10	0
//60	0	0	0	10	0	0	0	5
//받을거 빼고 준다

public class Toss_5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer kSt = new StringTokenizer(br.readLine());
		StringTokenizer lSt = new StringTokenizer(br.readLine());
		
		int remains = 0;
		
		while(kSt.hasMoreTokens()) {
			int kim = Integer.parseInt(kSt.nextToken()) + remains;
			int lee = Integer.parseInt(lSt.nextToken());
			remains = 0;
			
			int diff = kim - lee;
			
			if(diff >= 0) System.out.print(diff + " ");
			else {
				remains = diff;
				System.out.print(0 + " ");
			}
		}
		
	}
}
