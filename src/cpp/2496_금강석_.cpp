#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>
using namespace std;

vector<pair<int,int>> diamonds;
int n,m,t,k;

int counting(int x, int y){
	int cnt = 0;
	for(pair<int,int> i : diamonds){
		if(x<=i.first && i.first <= x + k && y <= i.second && i.second <= y + k) cnt++;
	}
	return cnt++;
}

int main(void){
	
	scanf("%d %d %d %d", &n, &m, &t, &k);

	for(int i=0;i<t;i++){
		int x, y;
		scanf("%d %d", &x, &y);

		// 좌표 45도 회전
		diamonds.emplace_back(x+y,x-y);	
	}

	pair<int,int> center;
	int max = -1;
	for(int i=0;i<t;i++){
		for(int j=0;j<t;j++){
			int res = counting(diamonds[i].first, diamonds[j].second);

			if(max < res){
				max = res;
				center.first = diamonds[i].first + k / 2;
				center.second = diamonds[j].second + k / 2;
			}
		}
	}

	//	좌표 다시 원래대로
	int res_x = (center.first + center.second) / 2;
	int res_y = center.first - res_x;

	printf("%d %d\n%d", res_x, res_y, max);
}