#include <iostream>
#include <vector>

using namespace std;

int forest[501][501];
int dp[501][501];
int n;
int dir[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
bool isin(int row, int col){
	if(1<=row && row<=n && 1<=col && col<=n) return true;
	else return false;
}

//	void로 dp 갱신하면서 다니면 하루종일 걸림 int return하여 그 위치에서 시작하여 이동할 수 있는 거리를 저장해야
//	dp에 값이 저장되어 있으면 바로 리턴할 수 있어 시간 단축이 가능함
int dfs(int row, int col){
	if(dp[row][col] != 0) return dp[row][col];
	dp[row][col] = 1;

	pair<int,int> next;

	for(int i=0;i<4;i++){
		next.first = row + dir[i][0];
		next.second = col + dir[i][1];

		int nextBb = forest[next.first][next.second];
		int hereBb = forest[row][col];

		if(isin(next.first, next.second) && hereBb < nextBb){
			dp[row][col] = max(dp[row][col], dfs(next.first, next.second) + 1);
		}
	}

	return dp[row][col];
}

int main(){
	
	scanf("%d", &n);

	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			scanf("%d", &forest[i][j]);
		}
	}

	int res = 0;
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			if(dp[i][j] == 0){
				res = max(res, dfs(i,j));
			}
		}
	}
	
	printf("%d", res);
	return 0;
}