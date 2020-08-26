#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main(void) {
	string input = "";
	
	while (true) {
		stack<char> judge;
		getline(cin, input);

		if (input.length() == 1 && input[0] == '.') break;		// 이거때문에 틀렸대 그냥 input == "." 라고만 하면 틀림;; 왜?;;

		for (int i = 0; i < input.size(); i++) {
			if (input[i] == '(' || input[i] == '[') judge.push(input[i]);
			else if (input[i] == ')') {
				if(!judge.empty() && judge.top() == '(')
					judge.pop();
				else {
					cout << "no\n";
					break;
				}
			}
			else if (input[i] == ']') {
				if (!judge.empty() && judge.top() == '[')
					judge.pop();
				else {
					cout << "no\n";
					break;
				}
			}
			if (i == input.size() - 1)
				if (judge.empty()) cout << "yes\n";
				else cout << "no\n";
			
		}
	}
}