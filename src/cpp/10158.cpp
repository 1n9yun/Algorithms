#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <math.h>
#include <string>
#include <algorithm>
#include <vector>
#include <stack>
#include <queue>

using namespace std;
// ㅅㅂ 이걸 우예아노

int main(void) {
	int w, h, p, q, t;
	scanf("%d %d", &w, &h);
	scanf("%d %d", &p, &q);
	scanf("%d", &t);

	p += t; q += t;
	p %= 2 * w; q %= 2 * h;

	if (p > w) p = 2 * w - p;
	if (q > h) q = 2 * h - q;
	
	printf("%d %d", p, q);
	system("pause");
}