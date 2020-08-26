#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

//	동전 종류 하나씩 넣어서 각각 돈 만들기
//	동전 종류를 기준으로, 만들 돈이 기준이 아니라
//	그럼 중복 안생김

int coin[21];
int dp[10001];

int main(void){
	int t;
	scanf("%d", &t);

	while(t--){
		int n, m;
		scanf("%d", &n);
		for(int i=0;i<n;i++){
			scanf("%d", &coin[i]);
		}
		scanf("%d", &m);

		fill(&dp[0], &dp[10001], 0);
		dp[0] = 1;
		for(int i=0;i<n;i++){
			for(int j=coin[i];j<=m;j++){
				dp[j] += dp[j - coin[i]];
			}
		}
		printf("%d\n", dp[m]);
	}
}