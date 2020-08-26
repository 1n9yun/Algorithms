package ps.Simulation;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class bj3954_BrainFuck {
	static int memorySize;
	static int codeSize;
	static int inputSize;
	static int[] memory;
	static char[] code;
	static char[] input;
	static class MyStack{
		private int[] data;
		private int top;
		public void add(int n) {
			data[++top] = n;
		}
		public int pop() {
			return data[top--];
		}
		public boolean isEmpty() {
			return top == -1;
		}
		public int peek() {
			return data[top];
		}
		public MyStack(int size) {
			data = new int[size + 1];
			top = -1;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t= sc.nextInt();
		
		for(int tc=0;tc<t;tc++) {
			memorySize = sc.nextInt();
			memory = new int[memorySize];
			codeSize = sc.nextInt();
			code = new char[codeSize];
			inputSize = sc.nextInt();
			input = new char[inputSize];

			String inputStr = sc.next();
			for(int i=0;i<codeSize;i++) {
				code[i] = inputStr.charAt(i);
			}
			inputStr = sc.next();
			for(int i=0;i<inputSize;i++) {
				input[i] = inputStr.charAt(i);
			}
			
			int inputIdx = 0;
			int codeIdx = 0;
			int pointer = 0;
			int commandCnt = 0;
			boolean terminated = false;
//			Stack<Integer> loopStack = new Stack<>();
//			MyStack loopStack = new MyStack(codeSize / 2);   이게 더 빠름 Deque보다
			Deque<Integer> loopStack = new ArrayDeque<>();
			while(commandCnt < 50000000) {
				if(codeIdx == codeSize) {
					terminated = true;
					break;
				}
				char c = code[codeIdx];
				if(c == '[') {
					if(loopStack.isEmpty() || loopStack.peekLast() != codeIdx) loopStack.addLast(codeIdx);
//					점프 
					if(memory[pointer] == 0) {
						int innerLoop = 0;
						while(innerLoop != -1) {
							do {
								codeIdx++;
							}while(code[codeIdx] != '[' && code[codeIdx] != ']');
							if(code[codeIdx] == '[') innerLoop++;
							else if(code[codeIdx] == ']') innerLoop--;
						}
					}
//					루프 돌기
					else codeIdx++;
				}
				else if(c == ']') {
//					루프 탈출
					if(memory[pointer] == 0) {
						loopStack.pollLast();
						codeIdx++;
					}
//					루프 돌기
					else {
						codeIdx = loopStack.peekLast();
					}
				}
				else{
					if(c == '+') plus(pointer);
					else if(c == '-') minus(pointer);
					else if(c == '<') pointer = left(pointer);
					else if(c == '>') pointer = right(pointer);
//					else if(c == '.') dot(pointer);
					else if(c == ',') rest(pointer, inputIdx == inputSize ? '\0' : input[inputIdx++]);
					codeIdx++;
				}
				commandCnt++;
			}
			if(terminated) System.out.println("Terminates");
			else {
				int ansLeft = 0, ansRight = codeIdx;
				
				if(codeIdx == codeSize - 1) {
					ansLeft = loopStack.pollLast();
				}else {
					while(!loopStack.isEmpty()) {
						ansLeft = loopStack.pollLast();
						int innerLoop = 0;
						while(innerLoop != -1) {
							do {
								if(!(code[ansRight] != '[' && code[ansRight] != ']')) break;
								ansRight++;
							}while(code[ansRight] != '[' && code[ansRight] != ']');
							
							if(code[ansRight] == '[') innerLoop++;
							else if(code[ansRight] == ']') innerLoop--;
							System.out.println(ansLeft + " " + ansRight);
						}
						if(!loopStack.isEmpty()) ansRight++;
					}
				}
				System.out.println("Loops " + ansLeft + " " + ansRight);
			}
		}
	}
	
	static void plus(int pointer) {
		memory[pointer] = (memory[pointer] + 1) % 256;
	}
	static void minus(int pointer) {
		memory[pointer] = (memory[pointer] - 1 + 256) % 256;
	}
	
	static int left(int pointer) {
		return ((pointer - 1) + memorySize) % memorySize;
	}
	static int right(int pointer) {
		return (pointer + 1) % memorySize;
	}
	static void dot(int pointer) {
		
	}
	static void rest(int pointer, char c) {
		if(c == '\0') memory[pointer] = 255;
		else memory[pointer] = (int)c;
	}
}
