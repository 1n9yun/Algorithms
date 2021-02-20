package ps.구현;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class programmers42893_매칭점수 {
    class Page{
        int index, basic;
        ArrayList<String> externalLink;

        public Page(int index, int basic, ArrayList<String> externalLink) {
            this.index = index;
            this.basic = basic;
            this.externalLink = externalLink;
        }
    }
    public int solution(String word, String[] pages) {
        word = word.toLowerCase();
        Map<String, Page> pageMap = new HashMap<>();

        int index = 0;
        for(String page : pages){
            String[] tags = page.split("<");
            String url = "";
            int basic = 0;
            ArrayList<String> externalLink = new ArrayList<>();

//            boolean inHead = false;
            for(String tag : tags){
                String[] line = tag.split(">");

                if(!tag.startsWith("/")){
                    String[] properties = line[0].split(" ");
//                    if(properties[0].equals("head")) inHead = true;
                    for(String property : properties){
                        if(properties[0].equals("meta") && property.startsWith("content")){
//                        if(inHead && properties[0].equals("meta") && property.startsWith("content")){
                            url = property.split("\"")[1];
                        }
                        if(properties[0].equals("a") && property.startsWith("href")){
                            externalLink.add(property.split("\"")[1]);
                        }
                    }
                }
//                else{
//                    if(line[0].contains("head")) inHead = false;
//                }

                if(line.length == 1) continue;
//                line[1] 에서 검색해야 해 이제
                String text = line[1].toLowerCase();
                for(int i=0;i<text.length();i++){
                    int idx = text.indexOf(word, i);
                    if(idx == -1) break;
                    i = idx;

                    if(
                            (idx - 1 < 0 || !('a' <= text.charAt(idx - 1) && text.charAt(idx - 1) <= 'z')) &&
                            (idx + word.length() >= text.length() || !('a' <= text.charAt(idx + word.length()) && text.charAt(idx + word.length()) <= 'z'))
                    ){
                        basic++;
                    }
                }
            }
            pageMap.put(url, new Page(index++, basic, externalLink));
        }

        double[] point = new double[pageMap.size()];
        for(String url : pageMap.keySet()){
            Page page = pageMap.get(url);

            point[page.index] += page.basic;
            double linkPoint = (double)page.basic / page.externalLink.size();

            Set<String> used = new HashSet<>();
            for(String link : page.externalLink){
                Page externalPage = pageMap.get(link);
                if(externalPage == null || used.contains(link)) continue;

                point[externalPage.index] += linkPoint;
                used.add(link);
            }
        }

        double max = -1;
        int answer = -1;
        for(int i=0;i<point.length;i++){
            if(max < point[i]) {
                max = point[i];
                answer = i;
            }
        }
        return answer;
    }

    @Test
    public void testSolution(){
        assertThat(solution("blind",
                new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"}
                )).isEqualTo(0);
        assertThat(solution("Muzi",
                new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}
                )).isEqualTo(1);
    }
}
