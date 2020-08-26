#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>
using namespace std;

typedef struct item {
	pair<int, int> red;
	pair<int, int> blue;
	int preDir;
}item;

int d[4][2] = { {-1, 0}, {1,0}, {0,-1},{0,1} };
char matrix[11][11];
pair<int, int> goal;

// true : red first
bool order(item beads, int dir) {
	if (beads.blue.first == beads.red.first && dir > 1) {
		if (dir == 2) {
			if (beads.blue.second > beads.red.second) return true;
			else return false;
		}
		else if (dir == 3) {
			if (beads.blue.second > beads.red.second) return false;
			else return true;
		}
	}
	else if (beads.blue.second == beads.red.second && dir < 2) {
		if (dir == 0) {
			if (beads.blue.first > beads.red.first) return true;
			else return false;
		}
		else if (dir == 1) {
			if (beads.blue.first > beads.red.first) return false;
			else return true;
		}
	}
	else return true;
}

item tilt(item pos, int dir) {
	pair<int, int> beads[2];
	bool orderResult = order(pos, dir);

	beads[0] = orderResult ? pos.red : pos.blue;
	beads[1] = orderResult ? pos.blue : pos.red;

	for (int i = 0; i < 2; i++) {
		int nextRow = beads[i].first + d[dir][0];
		int nextCol = beads[i].second + d[dir][1];

		while (matrix[nextRow][nextCol] != '#' && matrix[nextRow][nextCol] != 'O') {
			if (i == 1 && beads[0].first == nextRow && beads[0].second == nextCol) break;

			beads[i].first = nextRow;
			beads[i].second = nextCol;

			nextRow = beads[i].first + d[dir][0];
			nextCol = beads[i].second + d[dir][1];
		}
	}

	item ret = { beads[orderResult ? 0 : 1], beads[orderResult ? 1 : 0], dir};
	return ret;
}

bool shouldTilt(int dir, int preDir) {
	switch (preDir) {
	case 0:
		if (dir == 1) return false;
		else return true;
	case 1:
		if (dir == 0) return false;
		else return true;
	case 2:
		if (dir == 3) return false;
		else return true;
	case 3:
		if (dir == 2) return false;
		else return true;
	default:
		return true;
	}
}

int main(void) {
	int row, col;

	queue<pair<item, int>> q;
	scanf("%d %d", &row, &col);

	pair<int, int> red;
	pair<int, int> blue;

	for (int i = 1; i <= row; i++) {
		for (int j = 1; j <= col; j++) {
			cin >> matrix[i][j];

			if (matrix[i][j] == 'B') blue = pair<int, int>(i, j);
			else if (matrix[i][j] == 'R') red = pair<int, int>(i, j);
			else if (matrix[i][j] == 'O') goal = pair<int, int>(i, j);

			if (matrix[i][j] != '.' && matrix[i][j] != '#' && matrix[i][j] != 'O') matrix[i][j] = '.';
		}
	}

	item first = { red, blue, -1 };
	q.push(make_pair(first, 0));

	int res;
	bool flag = false;
	while (!q.empty()) {
		pair<item, int> front = q.front();
		q.pop();

		if (front.second == 10) break;

		for (int i = 0; i < 4; i++) {
			if (!shouldTilt(i, front.first.preDir)) continue;

			item temp = tilt(front.first, i);
			if (temp.red.first + d[i][0] == goal.first && temp.red.second + d[i][1] == goal.second
				&& !(temp.red.first - d[i][0] == temp.blue.first && temp.red.second - d[i][1] == temp.blue.second)) {

				flag = true;
				res = front.second + 1;
				break;
			}
			else if (temp.blue.first + d[i][0] == goal.first && temp.blue.second + d[i][1] == goal.second)
				continue;

			if (front.first.blue.first == temp.blue.first && front.first.blue.second == temp.blue.second
				&& front.first.red.first == temp.red.first && front.first.red.second == temp.red.second)
				continue;

			q.push(make_pair(temp, front.second + 1));
		}

		if (flag) break;
	}
	if (flag) printf("%d", res);
	else printf("-1");
}

