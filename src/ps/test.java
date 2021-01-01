package ps;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Arrays.toString(getStringArray()));
		System.out.println(getString());
	}

	static String[] getStringArray(){
		return new String[]{"hi1", "hi2", "hi3"};
	}
	static String getString(){
		String test = "Test";
		String test2 = new String("하하");
		return test2;
	}
}
