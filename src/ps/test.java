package ps;

import sun.awt.image.ImageWatched;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		LinkedHashSet<String> set = new LinkedHashSet<>();
		HashSet<String> set = new HashSet<>();

		set.add("3");
		printSet(set);
		set.add("6");
		printSet(set);
		set.add("8");
		printSet(set);
		set.add("11");
		printSet(set);
		set.add("12345");
		printSet(set);
		set.add("1");
		printSet(set);
	}

	static void printSet(Set<String> set){
		System.out.println();
		for(String s : set){
			System.out.print(s + " ");
		}
		System.out.println();
	}
}
