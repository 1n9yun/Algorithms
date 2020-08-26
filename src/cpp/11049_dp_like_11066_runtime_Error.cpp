#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

// int dp[501][501];
// pair<int,int> mat[501];

// int main(void){
// 	int n;
// 	scanf("%d", &n);

// 	for(int i=1;i<=n;i++){
// 		scanf("%d %d", &mat[i].first, &mat[i].second);
// 	}

// 	for(int i=n-1;i>0;i--){
// 		for(int j=i+1;j<=n;j++){
// 			dp[i][j] = INT_MAX;
// 			for(int k=i;k<=j;k++){
// 				dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j] + (mat[i].first*mat[k].second*mat[j].second));
// 			}
// 		}
// 	}

// 	printf("%d", dp[1][n]);
// }


//	Runtime Error,,
vector<pair<int,int>> v;
int n;
int dp[501][501];

int getMultipleCount(pair<int,int> left, pair<int,int> right){
	if(left.second != right.first) return INT_MAX;
	return left.first * left.second * right.second;
}

int back(int left, int right){
	if(dp[left][right] != INT_MAX) return dp[left][right];
	if(left == right) {
		dp[left][right] = 0;
		return dp[left][right];
	}

	for(int i=left; i<right;i++){
		int lSide = back(left, i);
		int rSide = back(i+1,right);

		dp[left][right] = min(dp[left][right], lSide + rSide + getMultipleCount(make_pair(v[left].first, v[i].second), make_pair(v[i+1].first, v[right].second)));
	}
}

int main(void){
	fill(&dp[0][0], &dp[501][0], INT_MAX);

	scanf("%d", &n);

	v.emplace_back(-1, -1);
	for(int i=0;i<n;i++){
		int r,c;
		scanf("%d %d", &r, &c);
		v.emplace_back(r, c);
	}

	for(int i=1;i<n;i++){
		dp[i][i+1] = getMultipleCount(v[i], v[i+1]);
	}
	back(1, n);

	printf("%d", dp[1][n]);
}