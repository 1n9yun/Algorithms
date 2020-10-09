package ps.CodingTest.KakaoBlind2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _03 {
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		System.out.println(Arrays.toString(solution(info, query)));
	}
	static public int[] solution(String[] info, String[] query) {
		Map<String, Integer>[] infofo = new HashMap[4];
		for(int i=0;i<infofo.length;i++) infofo[i] = new HashMap<>();
		
		infofo[0].put("-", 0);
		infofo[0].put("cpp", 1);
		infofo[0].put("java", 2);
		infofo[0].put("python", 3);
		
		infofo[1].put("-", 0);
		infofo[1].put("backend", 1);
		infofo[1].put("frontend", 2);
		
		infofo[2].put("-", 0);
		infofo[2].put("junior", 1);
		infofo[2].put("senior", 2);
		
		infofo[3].put("-", 0);
		infofo[3].put("chicken", 1);
		infofo[3].put("pizza", 2);
		
		int[][][][][] chart= new int[4][3][3][3][100001];
		
		for(String s : info) {
			String[] splited = s.split(" ");
//			System.out.println(Arrays.toString(splited));
			int[] div = {infofo[0].get(splited[0]), infofo[1].get(splited[1]), infofo[2].get(splited[2]), infofo[3].get(splited[3])};
			int point = Integer.parseInt(splited[4]);
			comb(0, div, new int[div.length], chart, point);
		}
		for(int i=0;i<chart.length;i++) {
			for(int j=0;j<chart[i].length;j++) {
				for(int k=0;k<chart[i][j].length;k++) {
					for(int l=0;l<chart[i][j][k].length;l++) {
						for(int p=1;p<chart[i][j][k][l].length;p++) {
							chart[i][j][k][l][p] += chart[i][j][k][l][p-1];
						}
					}
				}
			}
		}
		int[] answer = new int[query.length];
		
		for(int qc=0;qc<query.length;qc++) {
			String q = query[qc];
			String[] sQuery = q.split(" and ");
			String[] temp = sQuery[sQuery.length - 1].split(" ");
			sQuery[sQuery.length - 1] = temp[0];
			int point = Integer.parseInt(temp[1]);
//			System.out.println(Arrays.toString(sQuery) + point);
			
			int[] div = {sQuery[0].equals("-") ? 0 : infofo[0].get(sQuery[0]),
					  sQuery[1].equals("-") ? 0 : infofo[1].get(sQuery[1]),
					  sQuery[2].equals("-") ? 0 : infofo[2].get(sQuery[2]),
					  sQuery[3].equals("-") ? 0 : infofo[3].get(sQuery[3])};
//			System.out.println(Arrays.toString(div));
			answer[qc] = chart[div[0]][div[1]][div[2]][div[3]][100000] -
							  (point == 0 ? 0 : chart[div[0]][div[1]][div[2]][div[3]][point - 1]);
		}
//		System.out.println(chart[0][0][0][0][149]);
		return answer;
    }
	
	static void comb(int idx, int[] base, int[] result, int[][][][][] chart, int point) {
		if(idx == result.length) {
//			System.out.println(Arrays.toString(result));
			chart[result[0]][result[1]][result[2]][result[3]][point]++;
			return;
		}
		
		result[idx] = base[idx];
		comb(idx + 1, base, result, chart, point);
		result[idx] = 0;
		comb(idx + 1, base, result, chart, point);
	}
}

