// 음수 나눗셈할 때 절댓값은 반올림된 모양이어야함 이거때문에 ㅅㅂ 몇시간을 버린거냐

#include <iostream>
#include <cstdio>
using namespace std;

class PlusMinusArray {
private:
	int *arr;
	int arrlen;
public:
	PlusMinusArray(int len) : arrlen(len) {
		arr = new int[len] {0, };
	}

	int& operator[](int n) {
		if (n == 0) return arr[0];
		else if (n < 0) return arr[n * -1];
		else return arr[n + 4000];
	}
	~PlusMinusArray() {
		delete(arr);
	}
};

int main(void) {
	PlusMinusArray S(8002);
	int N, avg = 0, middle = 0, freq = 4001, min = 4000, max = -4000;
	cin >> N;

	for (int i = 0; i < N; i++) {
		int tmp;
		cin >> tmp;
		S[tmp]++;
	}
	bool checked = false;
	bool changed = false;
	for (int i = -4000; i < 4001; i++) {
		if (S[i] != 0) {
			avg += S[i] * i;
			min = min > i ? i : min;
			max = max > i ? max : i;

			if (!checked && middle <= N / 2) {
				middle += S[i];
				if (middle > N / 2) {
					middle = i;
					checked = true;
				}
			}

			if (S[freq] < S[i]) {
				freq = i;
				changed = true;
			}
			else if (changed && S[freq] == S[i]) {
				freq = i;
				changed = false;
			}
		}
	}
	if(avg / N < 0)
		avg = (int)((abs((double)avg / N)) + 0.5) * -1;
	else avg /= N;

	printf("%d\n%d\n%d\n%d\n",avg, middle, freq, max - min);
	
	system("pause");
}



// system("pause");