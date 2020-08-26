#include <iostream>
#include <algorithm>
#include <string.h>
using namespace std;

//	top-down은 메모리 초과, bottom up ㄱㄱㄱ
int minArr[3];
int maxArr[3];	

int main(void){
	int n;
	scanf("%d", &n);

	for(int i=0;i<n;i++){
		int n1,n2,n3;
		scanf("%d %d %d", &n1, &n2, &n3);

		int min1, min2, min3;
		min1 = min(minArr[0] + n1, minArr[1] + n1);
		min2 = min(minArr[0] + n2, min(minArr[1] + n2, minArr[2] + n2));
		min3 = min(minArr[1] + n3, minArr[2] + n3);
		minArr[0] = min1;
		minArr[1] = min2;
		minArr[2] = min3;

		int max1, max2, max3;
		max1 = max(maxArr[0] + n1, maxArr[1] + n1);
		max2 = max(maxArr[0] + n2, max(maxArr[1] + n2, maxArr[2] + n2));
		max3 = max(maxArr[1] + n3, maxArr[2] + n3);
		maxArr[0] = max1;
		maxArr[1] = max2;
		maxArr[2] = max3;
	}

	printf("%d %d", max(maxArr[0], max(maxArr[1], maxArr[2])), min(minArr[0], min(minArr[1], minArr[2])));
}
