package ps;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		List<Character> list = new ArrayList<>();

		Random random = new Random();

		for(int i=0;i<10;i++){
			list.add((char) (random.nextInt() % 128));
		}

		System.out.println(Arrays.toString(list.stream().toArray()));
	}
}
