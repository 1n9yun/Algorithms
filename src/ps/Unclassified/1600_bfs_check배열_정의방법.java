import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	int first;
	int second;
	int k;
	int move;

	public Pair(int first, int second, int k, int move) {
		this.first = first;
		this.second = second;
		this.k = k;
		this.move = move;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[][] deltaHorse = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } };
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int k = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();

		int[][] land = new int[h][w];
		//	말 점프 사용 횟수에 따라 저장하면 됨;
		boolean[][][] check = new boolean[h][w][31];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				land[i][j] = sc.nextInt();
			}
		}

		Queue<Pair> q = new LinkedList<>();

		check[0][0][0] = true;
		q.add(new Pair(0, 0, 0, 0));
		int ans = -1;
		while (!q.isEmpty()) {
			Pair front = q.poll();
			if (front.first == h - 1 && front.second == w - 1) {
				ans = front.move;
				break;
			}
			if (front.k < k) {
				for (int i = 0; i < 8; i++) {
					int nRow = front.first + deltaHorse[i][0];
					int nCol = front.second + deltaHorse[i][1];

					if (0 <= nRow && nRow < h && 0 <= nCol && nCol < w)
						if (!check[nRow][nCol][front.k+1] && land[nRow][nCol] == 0) {
							check[nRow][nCol][front.k+1]= true;
							q.add(new Pair(nRow, nCol, front.k + 1, front.move + 1));
						}
				}
			}
			for (int i = 0; i < 4; i++) {
				int nRow = front.first + delta[i][0];
				int nCol = front.second + delta[i][1];

				if (0 <= nRow && nRow < h && 0 <= nCol && nCol < w)
					if (!check[nRow][nCol][front.k] && land[nRow][nCol] == 0) {
						check[nRow][nCol][front.k]= true; 
						q.add(new Pair(nRow, nCol, front.k, front.move + 1));
					}
			}
		}
		System.out.println(ans);
	}
}
