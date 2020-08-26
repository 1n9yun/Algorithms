#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <math.h>
using namespace std;

typedef struct vector<vector<pair<int,int>>> Block;

int delta[2][2] = {{1,0},{0,1}};
int main(void){
	int board[50][50];
	bool check[50][50];
	int n;
	scanf("%d", &n);

	int u,v,w,x,y;
	scanf("%d %d %d %d %d", &u, &v, &w, &x, &y);

	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			scanf("%d", &board[i][j]);
		}
	}

	vector<Block> blocks;

	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			if(!check[i][j] && board[i][j] == 1){
				
			}
		}
	}
}
