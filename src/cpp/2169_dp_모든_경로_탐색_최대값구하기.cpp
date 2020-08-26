#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

int n,m;
int mars[1001][1001];
int dir[3][2] = {{0,-1}, {1,0}, {0,1}};
//	from 방향 당 최대 값을 모두 저장하고 있어야 모든 경로 탐색 가능 
int dp[1001][1001][3];
bool isin(int row, int col){
	return (1 <= row && row <= n && 1 <= col && col <= m);
}

int dfs(int row, int col, int from){
	if(!isin(row, col)) return INT_MIN + 101;
	if(row == n && col == m) return mars[row][col];
	if(dp[row][col][from] != 0) return dp[row][col][from];

	int &ret = dp[row][col][from];

	int res = INT_MIN + 101;
	for(int i = 0;i<3;i++){
		if(from != 1 && i == abs(from - 2)) continue;
		int nr = row + dir[i][0];
		int nc = col + dir[i][1];

		res = max(res, dfs(nr, nc, i) + mars[row][col]);
	}
	return ret = res;
}

int main(void){
	scanf("%d %d", &n, &m);

	for(int i=1;i<=n;i++){
		for(int j=1;j<=m;j++){
			scanf("%d", &mars[i][j]);
		}
	}
	printf("%d", dfs(1, 1, 1));

	// printf("\n");
	// for(int i=1;i<=n;i++){
	// 	for(int k=0;k<3;k++){
	// 		for(int j=1;j<=m;j++){
	// 			printf("%9d\t", dp[i][j][k]);
	// 		}
	// 		printf("\n");
	// 	}
	// 	for(int j=1;j<=m;j++){
	// 		printf("%9d\t", mars[i][j]);
	// 	}
	// 	printf("\n\n");
	// }
}