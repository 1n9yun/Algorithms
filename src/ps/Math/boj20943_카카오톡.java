package ps.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj20943_카카오톡 {
    static int stoi(String s) {return Integer.parseInt(s);}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());

        HashMap<Double, Integer> slopeMap = new HashMap<>();
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());

            double slope = (double)-a / b;
            if(slope == Double.NEGATIVE_INFINITY || Double.compare(slope, -0.0) == 0) slope *= -1;
            slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
        }
        long answer = (long)n*(n-1) / 2;
        for(Map.Entry<Double, Integer> slope : slopeMap.entrySet()){
            answer -= ((long)slope.getValue() * (slope.getValue() - 1)) / 2;
        }
        System.out.println(answer);
    }
}
