#include <iostream>
#include <algorithm>
using namespace std;

//	LCS 

int dp[1001][1001];

int main(void){
	char s1[1001];
	char s2[1001];

	scanf("%s %s", &s1, &s2);

	int i,j;

	for(i=0;s1[i]!='\0';i++){
		for(j=0;s2[j]!='\0';j++){
			if(s1[i] == s2[j]) dp[i+1][j+1] = dp[i][j] + 1;
			else dp[i+1][j+1] = max(dp[i+1][j], dp[i][j+1]);
		}
	}

	printf("%d", dp[i][j]);
}