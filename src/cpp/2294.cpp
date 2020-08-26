#define _CRT_SECURE_NO_WARNINGS
#define min(x, y) x > y ? y : x
#include <iostream>
#include <cstdio>

using namespace std;

int sum[10001];

int main(void) {
	int n, k;
	int coin[100];
	scanf("%d %d", &n, &k);
	
	for (int i = 1; i < 10001; i++) {
		sum[i] = 100001;
	}
	for (int i = 0; i < n; i++) {
		scanf("%d", &coin[i]);
	}

	for (int i = 0; i < n; i++) {
		for (int j = coin[i]; j <= k; j++) {
			sum[j] = min(sum[j - coin[i]] + 1, sum[j]);
		}
	}

	printf("%d", sum[k] == 100001 ? -1 : sum[k]);
	system("pause");
}
