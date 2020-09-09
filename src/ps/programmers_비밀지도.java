import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[] res = new int[n];
        
        for(int i=0;i<n;i++){
            res[i] = (arr1[i] | arr2[i] | (1<<n));
        }
        
        String[] ans = new String[n];
        for(int i=0;i<n;i++){
            char[] str = Integer.toBinaryString(res[i]).toCharArray();
            
            String temp = "";
            for(int j=1;j<=n;j++){
                if(str[j] == '1') temp += '#';
                else if(str[j] == '0') temp += ' ';
            }
            ans[i] = temp;
        }
        
        return ans;
    }
}