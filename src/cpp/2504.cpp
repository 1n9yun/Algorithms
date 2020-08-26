#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <math.h>
#include <string>
#include <algorithm>
#include <vector>
#include <stack>
#include <queue>

using namespace std;				//	괄호 안이 뭔지 따로 따지지마

int main(void) {
	stack<char> s;
	string input;
	cin >> input;
	bool flag = true;

	int cur = 1, res = 0;
	
	for (int i = 0; i < input.length(); i++) {
		if (input[i] == '(') {
			cur *= 2;
			s.push('(');
		}
		else if (input[i] == '[') {
			cur *= 3;
			s.push('[');
		}
		else if (input[i] == ')') {
			if (s.empty() || s.top() != '(') {
				flag = false;
				break;
			}
			else if (input[i - 1] == '(') {
				s.pop();
				res += cur;
				cur /= 2;
			}
			else {
				s.pop();
				cur /= 2;
			}
		}
		else if (input[i] == ']') {
			if (s.empty() || s.top() != '[') {
				flag = false;
				break;
			}
			else if (input[i - 1] == '[') {
				s.pop();
				res += cur;
				cur /= 3;
			}
			else {
				s.pop();
				cur /= 3;
			}
		}
	}
	if (flag && s.empty()) printf("%d", res);
	else printf("0");

	system("pause");
}