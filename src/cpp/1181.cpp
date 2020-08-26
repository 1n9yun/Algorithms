#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

bool len_first_Dict_second(string a, string b) {	// Sort �Լ��� �̿�**		+ ������� c ����� ����
	if (a.length() != b.length()) {
		return a.length() < b.length();
	}
	else {
		return a < b;
	}
}

int main(void) {
	int N;
	cin >> N;
	vector<string> V;

	for (int i = 0; i < N; i++) {
		string tmp;
		cin >> tmp;
		V.push_back(tmp);
	}

	sort(V.begin(), V.end(), len_first_Dict_second);

	for (int i = 0; i < V.size(); i++) {
		if (i == 0) {
			cout << V[i] << endl;
			continue;
		}
		if (V[i] == V[i - 1]) continue;
		else cout << V[i] << endl;
	}
}

