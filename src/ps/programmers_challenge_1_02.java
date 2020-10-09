package ps;

public class programmers_challenge_1_02 {
	public static void main(String[] args) {
		for(int i=1;i<=1000;i++) {
			solution(i);
		}
	}
    public static int[] solution(int n) {
    	int offset_left = 1;
    	int offoffset_left = 0;
    	
    	int offset_down = 1;
    	int offset_up = n;
    	
    	int size = 0;
    	while(n != 0) size += (n--);
    	
    	int[] answer = new int[size];
    	
    	int left = offset_left;
    	int down = offset_down;
    	int up = offset_up;
    	
    	int start = 1;
    	int idx = 0;
    	
    	answer[idx] = start++;
//    	left, down, up 0, 1, 2
    	int state = 0;
    	while(start < size + 1) {
    		if(state == 0) {
    			if(idx + left < size && answer[idx + left] == 0) {
    				idx += left++;
    				answer[idx] = start++;
    			}else {
    				if(offoffset_left == 0) {
    					offset_left += 1;
    					offoffset_left = 1;
    				}else {
    					offset_left += 2;
    				}
    				left = offset_left;
    				state = (state + 1) % 3;
    			}
    		}else if(state == 1) {
    			if(idx + down < size && answer[idx + down] == 0) {
    				idx += down;
    				answer[idx] = start++;
    			}else state = (state + 1) % 3;
    		}else if(state == 2) {
    			if(idx - up >= 0 && answer[idx - up] == 0) {
    				idx -= up--;
    				answer[idx] = start++;
    			}else {
    				up = --offset_up;
    				state = (state + 1) % 3;
    			}
    		}
    	}
    	return answer;
    }
}
