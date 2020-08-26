#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <math.h>
#include <string>
#include <algorithm>
#include <vector>
#include <stack>
#include <queue>

using namespace std;

int r, dist, _max = 0;
int N;
int dist_max;
vector<pair<int, int>> leaves;
bool visited[100001];

bool compare_dist(pair<int, int> p1, pair<int, int> p2) {
	if (p1.first + p1.second > p2.first + p2.second) return true;
	else return false;
}

bool isOverLapped(int p1, int p2) {	//	p1 ; target    p2 ; other
	if ((p2 <= p1 && p1 <= p2 + r) || (p2 <= p1 + r && p1 + r <= p2 + r)) 
		return true;
	else return false;
}

int dfs(int x, int y, int idx, int prev) {
	if (x == 0 && y == 0) return prev;

	visited[idx] = true;
	for (int _i = 0; _i < N; _i++) {
		if (visited[_i] || (abs(leaves[idx].first - x) + abs(leaves[idx].second - y)) > dist_max) {
			continue;
		}
		pair<int, int> i = leaves[_i];
		if (isOverLapped(x, i.first)) {
			if (y + r < i.second && i.second <= y + r + dist)
				return dfs(i.first, i.second, _i, prev);
			else if (i.second <= y - dist && y - dist <= i.second + r)
				return dfs(i.first, i.second, _i, prev);
		}
		else if (isOverLapped(y, i.second)) {
			if (x + r <= i.first && i.first <= x + r + dist)
				return dfs(i.first, i.second, _i, prev);
			else if (i.first <= x - dist && x - dist <= i.first + r)
				return dfs(i.first, i.second, _i, prev);
		}
	}
}

int main(void) {
	scanf("%d %d", &N, &r);

	for (int i = 0; i < N; i++) {
		int x, y;
		scanf("%d %d", &x, &y);
		leaves.push_back(make_pair(x, y));
	}
	scanf("%d", &dist);
	dist_max = r + r + dist;

	sort(leaves.begin(), leaves.end(), compare_dist);

	int res = 0;
	for (int i = 0; i < N; i++) {
		if (res != 0) break;
		else {
			res = dfs(leaves[i].first, leaves[i].second, i, i);
		}
	}

	printf("%d", leaves[res].first + leaves[res].second + r + r);

	system("pause");
}