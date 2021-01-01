package ps.Union_Find;

import java.util.Scanner;

public class boj1756_닭싸움팀정하기 {
    static int[] friendSet;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        friendSet = new int[n+1];
        for(int i=1;i<=n;i++) friendSet[i] = i;
        ans = n;

        int[] enemy = new int[n+1];
        for(int i=0;i<m;i++){
            char type = sc.next().charAt(0);
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            if(type == 'E'){
//                원수인 경우. 원수에 추가하고 다른 원수랑 친구맺어주기
                if(enemy[n1] == 0) enemy[n1] = n2;
                else{
                    System.out.println(n1 + "이랑 원수래요!");
//                    enemy[n1], n2 유니온
                    union(enemy[n1], n2);
                }
                if(enemy[n2] == 0) enemy[n2] = n1;
                else{
                    System.out.println(n2 + "랑 원수래요!");
//                    enemy[n2], n1 유니온
                    union(enemy[n2], n1);
                }
            }else{
//                친구인 경우. 그냥 union
                System.out.println("친구래요!");
                union(n1, n2);
            }
        }
        System.out.println(ans);
    }

    static int find(int idx){
        if(friendSet[idx] == idx) return idx;
        return friendSet[idx] = find(friendSet[idx]);
    }

    static void union(int p1, int p2){
        p1 = find(p1);
        p2 = find(p2);

        if(p1 != p2) {
            System.out.println("헤윙");
            friendSet[p2] = p1;
            ans--;
        }
    }
}
