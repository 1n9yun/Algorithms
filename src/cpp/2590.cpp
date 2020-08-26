#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <math.h>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

bool plate[6][6];

int AttachingPaper(int size, int max) {
	int count = 0;
	if (max == 0) return 0;
	for (unsigned int j = 0; j < sizeof(*plate) - (size - 1); j++) {
		for (unsigned int i = 0; i < sizeof(*plate) - (size - 1); i++) {
			bool ok = true;
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if (plate[i + x][j + y]) {
						ok = false; break;
					}
				}
				if (!ok) break;
			}
			if (ok) {
				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						plate[i + x][j + y] = true;
					}
				}
				count++;
				if (count == max) return count;
			}
		}
	}
	return count;
}

int main(void) {
	int papers[7] = { 0, };
	int count = 0;
	for (int i = 1; i < 7; i ++){
		scanf("%d", &papers[i]);
	}

	count += papers[6];
	papers[6] = 0;

	for (int i = 5; i > 0; i--) {
		while (papers[i] != 0) {
			memset(plate, false, sizeof(plate));

			papers[i] -= AttachingPaper(i, papers[i]);
			
			for (int j = 6 - i; j > 0; j--) {
				papers[j] -= AttachingPaper(j, papers[j]);
			}
			count++;
		}
	}

	printf("%d", count);
	system("pause");
}