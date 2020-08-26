#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <string>
#include <iostream>
#include <algorithm>						// ¸Ó¸®°¡ ¶ò! Ä­ ¼ö ¼¼±â ¿´À½;;

int main(void) {
	bool paper[100][100] = { false , };
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		int x, y;
		scanf("%d %d", &x, &y);

		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 10; k++) {
				if (!paper[x + j][y + k]) paper[x + j][y + k] = true;
			}
		}
	}
	int cnt = 0;
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 100; j++) {
			if (paper[i][j]) cnt++;
		}
	}
	printf("%d", cnt);
}