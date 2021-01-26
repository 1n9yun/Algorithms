package ps.DFS;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj1167_트리의지름 {
    static class Item{
        int to, cost;

        public Item(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static int stoi(String s) { return Integer.parseInt(s); }
    static int answer = -1;
    static List<Item>[] tree;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = stoi(br.readLine());
        tree = new ArrayList[v+1];
        check = new boolean[v+1];
        for(int i=0;i<tree.length;i++) tree[i] = new ArrayList<>();

        for(int i=0;i<v;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = stoi(st.nextToken());
            while(true){
                int child = stoi(st.nextToken());
                if(child == -1) break;
                int cost = stoi(st.nextToken());

//                트리라서 양방향 그래프이지만
//                입력에서 보면 입력 자체가 양방향 모두를 주고있다.
//                한 정점에서 연결된 간선들의 정보의 나열이기 때문에 렇다.
//                그래서 양방향을 신경쓸 필요 없이 입력받는 대로 만들어주면 된다.ㅎ
                tree[p].add(new Item(child, cost));
            }
        }
        check[1] = true;
        back(1);
        System.out.println(answer);
    }

    static int back(int v){
//        가장 큰거 두개만 결정해서 더해
        int sum = 0;
        int max1 = 0;
        int max2 = 0;

        for(int i=0;i<tree[v].size();i++){
            Item item = tree[v].get(i);
            if(check[item.to]) continue;
            check[item.to] = true;

            int result = back(item.to) + item.cost;
            if(result > max1){
                max2 = max1;
                max1 = result;
            }else if(result > max2){
                max2 = result;
            }
        }
        answer = Math.max(answer, max1 + max2);

        return max1;
    }
}
