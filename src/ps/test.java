package ps;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		List<Character> list = new ArrayList<>();

		Random random = new Random();

		for(int i=0;i<10;i++){
			int temp = 'a' + (Math.abs(random.nextInt()) % 26);
			list.add((char) (temp));
		}

		System.out.println(Arrays.toString(list.stream().toArray()));
		List<Character> subList = list.subList(5, list.size());
		list.remove(0);
		System.out.println(Arrays.toString(list.stream().toArray()));
		System.out.println(Arrays.toString(subList.stream().toArray()));
	}
}
