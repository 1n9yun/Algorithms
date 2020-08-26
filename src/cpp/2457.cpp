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

int main(void) {
	int n;
	int base = 301, target = 1130;
	scanf("%d", &n);
	vector<pair<int, int>> flowers;

	for (int i = 0; i < n; i++) {
		int fm, fd, tm, td;
		scanf("%d %d %d %d", &fm, &fd, &tm, &td);
		flowers.push_back(make_pair(fm * 100 + fd, tm * 100 + td));		// ���ܵ�
	}
	sort(flowers.begin(), flowers.end());

	int prev = base, cnt = 0, max = base;
	// int i = -1;
	// while(prev <= target && i < n){ i++				�� ���� �ٸ���??	�� for�� �������� üũ�ϰ� �Ѿ���� �����µ� while�� �ϴ� ���Ѱ� �����ϰ� Ȯ���ϳ�
	//	�ؼ� ������ ��� ��쿡 printf("0")�� ������ �ȵǴ� ��찡 ����. �ؼ� while�� ���°� �´µ�
	for (int i = 0; prev <= target && i < n; i++) {
		bool flag = false;
		
		for (int j = i; j < n; j++) {
			if (flowers[j].first > prev) break;
			if (flowers[j].second > max) {
				max = flowers[j].second;
				flag = true;
				i = j;
			}
		}
		if (flag) {
			prev = max;
			cnt++;
		}
		else {
			printf("0");
			return 0;
		}
	}
	printf("%d", cnt);

	system("pause");
}