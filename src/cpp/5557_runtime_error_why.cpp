#include <iostream>
#include <string.h>
#include <algorithm>
using namespace std;

//	런타임에러 왜??
int n;
int seq[101];
long long int dp[21][101];

long long int back(int idx, int res){
	if(idx <= 0 || !(0 <= res && res <= 20)) return 0;
	if(dp[res][idx] != 0) return dp[res][idx];

	dp[res][idx] = back(idx - 1, res + seq[idx]) + back(idx - 1, res - seq[idx]);
}

int main(void){
	scanf("%d", &n);

	for(int i=1;i<=n;i++){
		scanf("%d", &seq[i]);
	}

	dp[seq[1]][1] = 1;
	back(n - 1, seq[n]);

	printf("%lld", dp[seq[n]][n-1]);
}



//	이건 안 런타임 에러
// #include <iostream>
// #include <algorithm>
// #include <string.h>
// #include <vector>
// using namespace std;

// int n;
// int seq[101];
// long long int dp[21][101];

// long long int back(int res, int idx){
// 	if(!(0 <= res && res <= 20)) return 0;
// 	if(dp[res][idx] != 0) return dp[res][idx];

// 	if(idx == n-1){
// 		if(res == seq[n]) return 1;
// 		return 0;
// 	}

// 	long long int &ret = dp[res][idx];
// 	return ret += back(res + seq[idx + 1], idx + 1) + back(res - seq[idx + 1], idx + 1);
// }

// int main(void){
// 	scanf("%d", &n);

// 	for(int i=1;i<=n;i++){
// 		scanf("%d", &seq[i]);
// 	}

// 	printf("%lld", back(seq[1], 1));
// }