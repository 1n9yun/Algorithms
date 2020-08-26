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
													// 물고기 하나 기준으로 그물 둘레에 위치시키고 찾음;;
int main(void) {
	int n, l, m;
	scanf("%d %d %d", &n, &l, &m);
	vector<pair<int, int>> fishes;

	for (int i = 0; i < m; i++) {
		int x, y;
		scanf("%d %d", &x, &y);
		fishes.push_back(make_pair(x, y));
	}
	
	//sort(fishes.begin(), fishes.end());				//	둘레를 다도니까 소팅할필요가 없지 ;;

	int max = 0;
	for (int i = 1; i < l / 2; i++) {
		int net_x = i;
		int net_y = (l / 2 - net_x);

		for (int j = 0; j < m; j++) {
			for (int movey = 0; movey <= net_y; movey++) {
				for (int move = 0; move <= net_x; move++) {
					int cnt = 0;
					int x = fishes[j].second - move + net_x;
					int y = fishes[j].first - movey + net_y;

					if (!(0 < x - net_x && x <= n && 0 < y - net_y && y <= n)) continue;
					for (int k = 0; k < m; k++) {
						if (x - net_x <= fishes[k].second && fishes[k].second <= x && y - net_y <= fishes[k].first && fishes[k].first <= y) {
							cnt++;
						}
					}
					max = max < cnt ? cnt : max;

					int idx = 0;
					for (int a = 1; a <= n; a++) {
						for (int b = 1; b <= n; b++) {
							if (idx < m && fishes[idx].second == b && fishes[idx].first == a) {
								printf("¡Ú");
								idx++;
							}
							else if (x - net_x <= b && b <= x && y - net_y <= a && a <= y) {
								printf("¡á");
							}
							else printf("¡à");
						}
						printf("\n");
					}
					printf("cnt : %d\nmove : %d\nx : %d\ny : %d\nmax : %d\n", cnt, move, x, y, max);
					getchar();
					system("cls");
				}
			}
		}
	}
	printf("%d", max);
	system("pause");
}