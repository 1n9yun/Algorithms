package ps.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj13458_시험감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] participants = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) participants[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long ans = 0;
        for(int i=0;i<n;i++){
            ans++;
            double required = (double)(participants[i] - b) / c;
            if(required > 0) ans += Math.ceil(required);
        }

        System.out.println(ans);
    }
}
