package ps;

import java.util.Scanner;

public class test {
	public static void main(String[] args) throws InterruptedException {
		for(int i=26613980;i<26620000;i++){
			boolean flag = i % 2 != 0;
			for(int j=3;j<=Math.sqrt(i);j++){
				if(i % j == 0) flag = false;
			}

			if(flag) System.out.println(i);
			else continue;
			new Scanner(System.in).nextLine();
		}
	}
}
