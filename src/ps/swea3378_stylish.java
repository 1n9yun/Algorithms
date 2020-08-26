package ps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class swea3378_stylish {
	static class Pair<U, V>{
		U left;
		V right;
		
		public Pair(U left, V right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static class Triple{
		int first, second, third;

		public Triple(int first, int second, int third) {
			super();
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			
			int[] counts = new int[3];
			
			ArrayList<Pair<Triple, Integer>> conditions = new ArrayList<>();
			
			for(int i=0;i<p;i++) {
				boolean start = false;
				int dot = 0;
				String line = sc.next();
				
				for(int j=0;j<line.length();j++) {
					char c = line.charAt(j);
					
					if(c == '.') {
						if(!start) dot++;
					}else if(!start) {
						conditions.add(new Pair(new Triple(counts[0], counts[1], counts[2]), dot));
						start = true;
					}
					
					nextCounts(counts, c);
				}
			}
			
			for(Pair pt : conditions) {
				Triple temp = (Triple)pt.left;
				System.out.println(temp.first + " " + temp.second + " " + temp.third + "," + pt.right);
			}

			int[] fRCS = new int[3];
			boolean[] indeterminates = new boolean[3];
			boolean[] appeared = new boolean[3];
			boolean start = false;
			for(int R=1;R<=20;R++) {
				for(int C=1;C<=20;C++) {
					for(int S=1;S<=20;S++){
						if(R == 0 && C == 0 && S == 0) continue;
						boolean flag = true;
						for(Pair pt : conditions) {
							Triple temp = (Triple)pt.left;
							if(conditions.size() > 2) {
								if(temp.first != 0) appeared[0] = true;
								if(temp.second != 0) appeared[1] = true;
								if(temp.third != 0) appeared[2] = true;
							}else {
								appeared[0] = true;
								appeared[1] = true;
								appeared[2] = true;
							}
							
							if(!(temp.first * R + temp.second * C + temp.third * S == (Integer)pt.right)) {
								flag = false;
								break;
							}
						}
						if(flag) {
//							조건 맞는 경우가 있음
//							if(start) {
//								if(R != 0 && C != 0 && S != 0) {
//									fRCS[0] = R;
//									fRCS[1] = C;
//									fRCS[2] = S;
//								}
//							}

							if(start) {
								if(R != 0 && R != fRCS[0]) indeterminates[0] = true;
								if(C != 0 && C != fRCS[1]) indeterminates[1] = true;
								if(S != 0 && S != fRCS[2]) indeterminates[2] = true;
							}
							if(fRCS[0] == 0 && fRCS[1] == 0 && fRCS[2] == 0) {
								start = true;
								fRCS[0] = R;
								fRCS[1] = C;
								fRCS[2] = S;
								continue;
							}
						}
					}
				}
			}
			
			System.out.println(Arrays.toString(indeterminates));
			System.out.println(Arrays.toString(fRCS));
			System.out.println(Arrays.toString(appeared));
			System.out.print("#" + tc + " ");
			Arrays.fill(counts, 0);
			
			int dot = 0;
			for(int i=0;i<q;i++) {
				String line = sc.next();
				System.out.print(dot + " ");
				dot = 0;
				for(int j=0;j<line.length();j++) {
					nextCounts(counts, line.charAt(j));
				}
				
				boolean same = true;
				int count = 0;
				int temp = 0;
				for(int idx=0;idx<3;idx++) {
					if(indeterminates[idx] && counts[idx] != 0) {
						count++;
						if(temp == 0) temp = counts[idx];
						else if(counts[idx] != temp) {
							same = false;
							break;
						}
					}
				}
//				count == 1 || !same 인 경우 -1
				if(count == 1 || !same) dot = -1;
				else {
					for(int idx=0;idx<3;idx++) {
						if(!appeared[idx] && counts[idx] != 0) {
							dot = -1;
							break;
						}
						dot += counts[idx] * fRCS[idx];
					}
				}
			}
			System.out.println();
		}
	}
	
	static void nextCounts(int[] counts, char c) {
		if(c == '(') counts[0]++;
		else if(c == ')') counts[0]--;
		else if(c == '{') counts[1]++;
		else if(c == '}') counts[1]--;
		else if(c == '[') counts[2]++;
		else if(c == ']') counts[2]--;
	}
}
//import java.util.*;
 
//public class swea3378_stylish {
//    static int ab, cd, ef, p, q;
//    static String[] M, My;
//    static int[] res;
// 
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int T = sc.nextInt();
//        for (int tc = 1; tc <= T; tc++) {
//            p = sc.nextInt();
//            q = sc.nextInt();
//            M = new String[p];
//            My = new String[q];
//            res = new int[q];
//            Arrays.fill(res, -2);
//            for (int i = 0; i < p; i++)
//                M[i] = sc.next();
//            for (int i = 0; i < q; i++)
//                My[i] = sc.next();
//            for (int R = 1; R < 21; R++)
//                for (int C = 1; C < 21; C++)
//                    for (int S = 1; S < 21; S++)
//                        if (able(R, C, S)) {
//                            ab = cd = ef = 0;
//                            for (int i = 0; i < q; i++) {
//                                String s = My[i];
//                                int cnt = 0, ans = R * ab + C * cd + S * ef;
//                                while (s.charAt(cnt) == '.')
//                                    cnt++;
//                                if (res[i] == -2)
//                                    res[i] = ans;
//                                else if (res[i] != ans)
//                                    res[i] = -1;
//                                chk(s);
//                            }
//                        }
//            System.out.print("#" + tc);
//            for (int n : res)
//                System.out.print(" " + n);
//            System.out.println();
//        }
//    }
// 
//    static boolean able(int R, int C, int S) {
//        ab = cd = ef = 0;
//        for (int i = 0; i < p; i++) {
//            String s = M[i];
//            int cnt = 0;
//            while (s.charAt(cnt) == '.')
//                cnt++;
//            if (R * ab + C * cd + S * ef != cnt)
//                return false;
//            chk(s);
//        }
//        return true;
//    }
// 
//    static void chk(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            char C = s.charAt(i);
//            if (C == '(')
//                ab++;
//            else if (C == ')')
//                ab--;
//            else if (C == '{')
//                cd++;
//            else if (C == '}')
//                cd--;
//            else if (C == '[')
//                ef++;
//            else if (C == ']')
//                ef--;
//        }
//    }
//}