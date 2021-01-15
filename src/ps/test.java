package ps;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		List<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(4);
		list.add(7);
		list.add(10);

		for(Integer n : list){
			n = n + 1;
		}

		for(Integer n : list){
			System.out.print(n + " ");
		}
	}
}
