#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

int n, lastStatus;
int costs[17][17];
int dp[17][1<<17];

int dfs(int cur, int status){
	if(status == lastStatus){
		if(costs[cur][1] == 0) return INT_MAX - 1000001;
		else return costs[cur][1];
	}
	if(dp[cur][status] != 0) return dp[cur][status];

	int &ret = dp[cur][status];
	int res = INT_MAX - 1000001;

	for(int i=1;i<=n;i++){
		if((status & (1<<i)) == 0 && costs[cur][i] != 0){
			res = min(res, dfs(i, (status|1<<i)) + costs[cur][i]);
		}
	}

	return ret = res;
}

int main(void){
	scanf("%d", &n);
	
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			scanf("%d", &costs[i][j]);
		}
	}

	lastStatus = (1 << (n+1)) - 2;
	printf("%d", dfs(1, 1<<1));
}
