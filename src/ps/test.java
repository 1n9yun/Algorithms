package ps;

public class test {
	public static void main(String[] args) {
		out:
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				for(int k=0;k<10;k++) {
					System.out.println(j + " " + k);
					if(k == 5) break out;
				}
			}
			System.out.println("에에에~~");
		}
	}
}
