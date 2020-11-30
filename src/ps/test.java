package ps;

import java.util.TreeSet;

public class test {
	public static void main(String[] args) {
		TreeSet<Integer> tset = new TreeSet<>((o1, o2) -> {
			if(o1 > o2) return 1;
			return -1;
		});

		tset.add(2);
		tset.add(67);
		tset.add(12);
		tset.add(32);
		tset.add(6);
		tset.add(9);

		System.out.println(tset.first());
	}
}
