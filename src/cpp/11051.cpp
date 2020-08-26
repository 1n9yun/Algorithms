#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
									//	DP 기초 이항계수 문제 
using namespace std;
int dp[1001][1001];

int main(void) {
	int n, k;
	scanf("%d %d", &n, &k);

	for (int row = 1; row <= n; row++) {
		for (int col = 0; col <= row; col++) {
			if (col == 0 || row == col) {
				dp[row][col] = 1;
				continue;
			}
			dp[row][col] = (dp[row - 1][col - 1] + dp[row - 1][col]) % 10007;
			
		}
	}
	printf("%d", dp[n][k]);
}