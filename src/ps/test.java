package ps;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String[] arr = new String[]{
				"117",
				"97674223",
				"1195524421",
				"1195524421985",
				"1195524421112"
		};

		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
