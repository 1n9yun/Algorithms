#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
using namespace std;

int gcd(int n1, int n2) {
	while (n2 != 0) {
		int tmp = n1 % n2;
		n1 = n2;
		n2 = tmp;
	}
	return n1;
}

int lcm(int n1, int n2) {
	return n1 * n2 / gcd(n1, n2);
}

int main(void) {
	int m, n, x, y;
	int t;
	scanf("%d", &t);

	int cnt = 0;
	vector<int> ans;

	for (int i = 0; i < t; i++) {
		bool ext = false;
		int basex = 0, basey = 0;
		cnt = 0;
		scanf("%d %d %d %d", &m, &n, &x, &y);
		cnt = x;
		basex = x;
		basey = x % n == 0 ? n : x % n;

		int lcmn = lcm(m, n);
		while (cnt <= lcmn && (basex != x || basey != y)) {
			basey = ((basey + m) % n == 0) ? n : (basey + m) % n;
			cnt += m;
		}
		if (cnt > lcmn) ans.push_back(-1);
		else ans.push_back(cnt);
	}

	for (int i : ans) printf("%d\n", i);

	system("pause");
}