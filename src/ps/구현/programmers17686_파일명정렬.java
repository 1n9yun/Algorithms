package ps.구현;

import java.util.Arrays;

public class programmers17686_파일명정렬 {
    static class Split{
        int idx;
        String head;
        String number;
        String original;

        public Split(int idx, String head, String number, String original) {
            this.idx = idx;
            this.head = head;
            this.number = number;
            this.original = original;
        }
    }
    public String[] solution(String[] files) {
        Split[] splits = new Split[files.length];

        for(int i=0;i<splits.length;i++){
            String head = "";
            String number = "";
            int numStart = 0;
            int numEnd = 0;
            for(int j=0;j<files[i].length();j++){
                char c = files[i].charAt(j);
                if(numStart == 0 && Character.isDigit(c)) {
                    head = files[i].substring(0, j).toLowerCase();
                    numStart = j;
                }else if(numStart != 0 && !Character.isDigit(c)){
                    numEnd = j;
                    break;
                }
            }
            if(numEnd == 0) numEnd = numStart + Math.min(files[i].length() - numStart, 5);
            splits[i] = new Split(i, head, files[i].substring(numStart, numEnd), files[i]);
        }

        Arrays.sort(splits, (s1, s2) -> {
            int equality = s1.head.compareTo(s2.head);
            if(equality > 0) return 1;
            else if(equality == 0){
                equality = Integer.compare(Integer.parseInt(s1.number), Integer.parseInt(s2.number));
                if(equality > 0) return 1;
                else if(equality == 0){
                    if(s1.idx > s2.idx) return 1;
                }
            }
            return -1;
        });

        for(int i=0;i<splits.length;i++) files[i] = splits[i].original;

        return files;
    }
}
