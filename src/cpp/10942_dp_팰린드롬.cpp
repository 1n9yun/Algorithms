#include <iostream>
#include <string.h>
using namespace std;

int seq[2001];
int dp[2001][2001];


int main(){
	int n, m, len = 0;
	scanf("%d", &n);

	memset(dp, -1, sizeof(dp));

	for(int i=1;i<=n;i++){
		len++;
		scanf("%d", &seq[i]);
	}
	scanf("%d", &m);

	for(int i=1;i<=len;i++){
		dp[i][i] = 1;
		if(i != len){
			if(seq[i] == seq[i+1]){
				dp[i][i+1] = 1;
			}
			else dp[i][i+1] = 0;
		}
	}

	for(int i=3;i<=len;i++){
		for(int j=0;j<len-i+1;j++){
			if(seq[j+1] == seq[i+j]){
				dp[j+1][i+j] = dp[j+2][i+j-1];
			}
			else dp[j+1][i+j] = 0;
		}
	}

	for(int t=0;t<m;t++){
		int s,e;
		scanf("%d %d", &s, &e);

		printf("%d\n", dp[s][e]);
	}

	return 0;
}