package ps;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        for(int i=0;i<n;i++){
            arr1[i] = (arr1[i] | arr2[i] | (1<<n));
        }
        String[] ans = new String[n];
        for(int i=0;i<n;i++){
            String str = Integer.toBinaryString(arr1[i]).substring(1);
            str = str.replaceAll("1", "#");
            ans[i] = str.replaceAll("0", " "); 
        }
        return ans;
    }
}