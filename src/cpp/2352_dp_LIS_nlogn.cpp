#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

// 그냥 n^2 LIS하면 시간초과였어
// 인덱스 트리, Lower Bound
// https://dyngina.tistory.com/16

// int dp[40001];
// int seq[40001];

// int main(void){
// 	int n;
// 	int ans = -1;
// 	scanf("%d", &n);

// 	for(int i=1;i<=n;i++){
// 		scanf("%d", &seq[i]);
// 	}

// 	for(int i=1;i<=n;i++){
// 		int maxIdx = 0;
// 		for(int j=i-1;j>=0;j--){
// 			// if(seq[i] > seq[j]) maxIdx = dp[maxIdx] < dp[j] ? j : maxIdx;
// 			if(seq[i] > seq[j] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
// 		}

// 		// dp[i] = dp[maxIdx] + 1;
// 		ans = ans < dp[i] ? dp[i] : ans;
// 	}

// 	printf("%d", ans);
// }

vector<pair<int,int>> seq;
int dp[40001];
int n;
int ans;

int main(void){
	scanf("%d", &n);
	seq.emplace_back(0,0);

	for(int i=1;i<=n;i++){
		int num;
		scanf("%d", &num);
		seq.emplace_back(num, i);
	}

	sort(seq.begin() + 1, seq.end());

	for(int i=1;i<=n;i++){
		int maxIdx = -1;
		int end = seq[i].second;
		for(int j=0;j<end;j++){
			// if(dp[maxIdx] < dp[j]){
			// 	if(seq[i-1].first == seq[i].first) continue;
			// }	중복 허용 X
			maxIdx = dp[maxIdx] < dp[j] ? j : maxIdx;
		}
		dp[end] = dp[maxIdx] + 1;
		ans = max(ans, dp[end]);
	}

	printf("%d", ans);
}