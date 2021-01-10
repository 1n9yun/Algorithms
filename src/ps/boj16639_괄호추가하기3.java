package ps;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class boj16639_괄호추가하기3 {
    static int ans = 0;
    static String input;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        input = sc.next();

        String postfix = infixToPostfix(input);
        System.out.println(postfix);
        System.out.println(calc(postfix));

    }

    static String infixToPostfix(String input){
        Deque<Character> optr = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);

            if(c == '('){
                optr.addLast(c);
            }else if(c == ')'){
                while(optr.getLast() != '('){
                    result.append(optr.removeLast());
                }
                if(optr.getLast() == '(') optr.removeLast();

            }else if(c == '+' || c == '-' || c == '*'){
                while (!optr.isEmpty() && getPriority(optr.getLast()) >= getPriority(c)) {
                    result.append(optr.removeLast());
                }
                optr.addLast(c);
            }else{
                result.append(c);
            }
        }
        while(!optr.isEmpty()){
            result.append(optr.removeLast());
        }

        return result.toString();
    }

    static int calc(String postfix){
        Deque<Integer> opnd = new ArrayDeque<>();

        for(int i=0;i<postfix.length();i++){
            char c = postfix.charAt(i);

            if(c == '+' || c == '-' || c == '*'){
                int opnd2 = opnd.removeLast();
                int opnd1 = opnd.removeLast();

                opnd.addLast(doCalc(opnd1, c, opnd2));
            }else{
                opnd.addLast(c - '0');
            }
        }

        return opnd.removeLast();
    }

    static int getPriority(char c){
        if(c == '+' || c == '-') return 2;
        else if(c == '*') return 3;
        else if(c == '(' || c == ')') return 1;
        return -1;
    }

    static int doCalc(int n1, char optr, int n2){
        if(optr == '+') return n1 + n2;
        else if(optr == '-') return n1 - n2;
        else return n1 * n2;
    }
}
