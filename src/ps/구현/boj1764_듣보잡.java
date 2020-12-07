package ps.구현;

import java.io.*;
import java.util.*;

public class boj1764_듣보잡 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        PriorityQueue<String> ans = new PriorityQueue<>();
        for(int i=0;i<n;i++) set.add(br.readLine());
        for(int i=0;i<m;i++){
            String noSeen = br.readLine();
            if(!set.add(noSeen)) ans.add(noSeen);
        }
        bw.write(ans.size() + "\n");
        while(!ans.isEmpty()) bw.write(ans.poll() + "\n");
        bw.flush();
    }
}
