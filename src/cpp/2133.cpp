#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>
using namespace std;

int wall[31];

int main(){
	int n;
	scanf("%d", &n);

	wall[0] = 1;
	wall[1] = 0;
	wall[2] = 3;

	for(int i=4;i<=n;i+=2){
		wall[i] = wall[i-2] * 3;

		for(int j=4;j<=i;j+=2){
			//	2칸이 늘어날 때마다 새로운 경우가 2가지씩 계속 생김
			wall[i] += wall[i-j] * 2;
		}	
	}

	printf("%d", wall[n]);
	return 0;	
}