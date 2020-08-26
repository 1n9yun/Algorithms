#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <limits.h>
using namespace std;

//	부분 문제가 분할되는 모습 기억 
//	Kruth's Optimization 으로 O(n^2) 까지 줄일 수 있다고 함. 

int dp[501][501];
int cost[501];
int pSum[501];
int t, k;

int back(int left, int right){
	if(dp[left][right] != INT_MAX) return dp[left][right];
	if(left == right) return 0;

	for(int i=left;i<right;i++){
		int lSide = back(left, i);
		int rSide = back(i+1, right);
		dp[left][right] = min(dp[left][right], lSide + rSide);
	}

	return dp[left][right] += pSum[right] - pSum[left-1];
}

int main(){
	scanf("%d", &t);

	while(t--){
		scanf("%d", &k);

		for(int i=1;i<=k;i++){
			scanf("%d", &cost[i]);
			pSum[i] = pSum[i-1]+cost[i];
		}
		fill(&dp[0][0], &dp[501][0], INT_MAX);

		for(int i=1;i<k;i++) dp[i][i+1] = cost[i] + cost[i+1];

		printf("%d\n", back(1,k));
	}
}