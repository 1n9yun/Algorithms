#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
#include <limits.h>
using namespace std;

char str[2501];
int dp[2501];

bool isPalindrome(int left, int right){
	while(left < right){
		if(str[left++] != str[right--]) return false;
	}
	return true;
}

int main(void){
	scanf("%s", str + 1);

	int len = strlen(str + 1);

	for(int i=1;i<=len;i++){
		dp[i] = 987654321;
		for(int j=1;j<=i;j++){
			if(isPalindrome(j, i))
				dp[i] = min(dp[i], dp[j-1] + 1);
		}
	}

	printf("%d", dp[len]);
}