package ps.CodingTest.KakaoBlind2021;

public class _01 {
	public static void main(String[] args) {
		System.out.println(solution("=.="));
	}
    public static String solution(String new_id) {
        
//        to_LowerCase();
//        소문자, 숫자, -, _, .을 제외한 문자 제거
//        .이 연속된거 하나로 압축
//        처음이나 끝 . 제거
//		빈 문자열이면 a 대입  
//        16자 이상이면 15자까지 잘라 제거후 마지막에 .이면 제거
//        길이가 2자 이하면 마지막 문자를 3이 될 때 까지 복사붙이기
        String temp = "";
        char[] str = new_id.toLowerCase().toCharArray();
        for(int i=0;i<str.length;i++) {
        	char c = str[i];
        	if(!('a' <= c && c <= 'z') && !('0'<= c && c <= '9') && c != '-' && c != '_' && c != '.') {
        		str[i] = ' ';
        	}
        	if(str[i] != ' ') temp += str[i];
        }
        
        str = temp.toCharArray();
        temp = "";
        boolean flag = false;
        for(int i=0;i<str.length;i++) {
        	if(str[i] == '.') {
        		if(flag) continue;
        		else flag = true;
        	}else flag = false;
        	temp += str[i];
        }
        
        if(temp.equals(".")) temp = "";
        else {
        	int left = temp.charAt(0) == '.' ? 1 : 0;
        	int right = temp.charAt(temp.length() - 1) == '.' ? temp.length() - 1 : temp.length();
        	temp = temp.substring(left, right);
        }
        
        if(temp == "") temp += 'a';
        if(temp.length() >= 16) {
        	if(temp.charAt(14) == '.') temp = temp.substring(0, 14);
        	else temp = temp.substring(0, 15);
        }
        
        if(temp.length() <= 2) {
        	char last = temp.charAt(temp.length() - 1);
        	while(temp.length() != 3) {
        		temp += last;
        	}
        }
        
        return temp;
    }
}
