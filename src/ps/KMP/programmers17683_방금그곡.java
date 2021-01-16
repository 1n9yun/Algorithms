package ps.KMP;

import java.util.Arrays;

public class programmers17683_방금그곡 {
    class Music{
        int idx;
        String name;
        String info;

        public Music(int idx, String name, String info) {
            this.idx = idx;
            this.name = name;
            this.info = info;
        }
    }
    public String solution(String m, String[] musicinfos) {

        Music[] musics = new Music[musicinfos.length];

        for(int i=0;i<musicinfos.length;i++){
            String[] split = musicinfos[i].split(",");
            musics[i] = new Music(i, split[2], getFullMusic(split[0], split[1], convertEachNoteToSingleLetter(split[3])));
        }

        Arrays.sort(musics, (o1, o2) -> {
            if(o1.info.length() < o2.info.length()) return 1;
            else if(o1.info.length() == o2.info.length()){
                if(o1.idx > o2.idx) return 1;
            }
            return -1;
        });

        m = convertEachNoteToSingleLetter(m);
        int[] fail = new int[m.length()];
        for(int i=1, j=0;i<m.length();i++) {
            while(j > 0 && m.charAt(i) != m.charAt(j)) {
                j = fail[j-1];
            }
            if(m.charAt(i) == m.charAt(j)) {
                fail[i] = ++j;
            }
        }

        for(int idx=0;idx<musics.length;idx++){
            String fullMusic = musics[idx].info;

            for(int i=0,j=0;i<fullMusic.length();i++) {
                while(j>0 && fullMusic.charAt(i) != m.charAt(j)) {
                    j = fail[j-1];
                }
                if(fullMusic.charAt(i) == m.charAt(j)) {
                    if(j == m.length()-1) return musics[idx].name;
                    else j++;
                }
            }
        }

        return "(None)";
    }

    String getFullMusic(String from, String to, String info){
        String[] split = from.split(":");
        int start = stoi(split[0]) * 60 + stoi(split[1]);

        split = to.split(":");
        int end = stoi(split[0]) * 60 + stoi(split[1]);

        int diff = end - start;
        int times = diff / info.length();
        int additional = diff % info.length();

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<times;i++) sb.append(info);
        return sb.append(info.substring(0, additional)).toString();
    }

    String convertEachNoteToSingleLetter(String music){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<music.length();i++){
            if(music.charAt(i) == '#') sb.setCharAt(sb.length() - 1, (char)(sb.charAt(sb.length() - 1) + 6));
            else sb.append(music.charAt(i));
        }
        return sb.toString();
    }
    int stoi(String s) { return Integer.parseInt(s); }
}
