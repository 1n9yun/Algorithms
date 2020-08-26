#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

int lDummy[2001];
int rDummy[2001];
int n;

int dp[2001][2001];

int back(int left, int right){
	if(left > n || right > n) return 0;

	int& res = dp[left][right];
	if(res != -1) return res;
	//	if(res != 0) return res;
	//	이렇게 했더니 시간 초과. 왜냐면 값이 0이 나올 수도 있는데 방문 여부를 0으로하면 안되지 임마

	if(lDummy[left] > rDummy[right]) res = back(left, right + 1) + rDummy[right];
	return res = max(res, max(back(left + 1, right), back(left + 1, right + 1)));
}

int main(void){
	scanf("%d", &n);

	for(int i=1;i<=n;i++){
		scanf("%d", &lDummy[i]);
	}
	for(int i=1;i<=n;i++){
		scanf("%d", &rDummy[i]);
	}

	memset(dp, -1, sizeof(dp));
	printf("%d", back(1,1));
}