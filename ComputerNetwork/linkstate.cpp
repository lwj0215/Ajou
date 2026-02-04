#include <iostream>
#include <climits>
#include <set>
#include <vector>

using namespace std;

vector<vector<int>> graph(6, vector<int>(6,INT_MAX));
pair<int, int> route[6]; // 0 to n, <cost, pre>
vector<int> forwardTable(6);

void forwarding() {
	for (int i = 1; i < 6; i++) {
		int r = i;
		while (1) {
			if (route[r].second == 0) break;
			r = route[r].second;
		}
		forwardTable[i]=r;
	}
}

void printTable() {
	cout << "Forwarding Table at 0" << endl;
	for (int i = 1; i < 6; i++) {
		cout << "dest: " << i << ",  next: " << forwardTable[i] <<endl;
	}
}

void setCost(int a, int b, int cost) {
	graph[a][b] = cost;
	graph[b][a] = cost;
}

int findMin(set<int> N) {
	int res, minCost = 0;
	for (int i = 0; i < 6; i++) {
		if (N.find(i) == N.end() && route[i].first != 0) { // if i not in N, there is route 0 to i
			if (route[i].first < minCost || minCost == 0) {
				minCost = route[i].first;
				res = i;
			}
		}
	}
	return res;
}

void dijkstra() {
	set<int> N;
	N.insert(0);
	for (int i = 0; i < 5; i++) { // step = NUMBER_OF_NODES - 1 = 6 - 1 = 5
		pair<int, int> min = { INT_MAX,6 }; // cost, node
		for (int n : N) {
			for (int j = 1; j < 6; j++) {
				if (N.find(j) == N.end() && graph[n][j] != INT_MAX) { // if j not in N, there is n-j edge
					int cost = route[n].first + graph[n][j];
					if (cost < route[j].first || route[j].first == 0) {
						route[j] = { cost, n };
					}
				}
			}
		}
		N.insert(findMin(N));
	}
}

int main()
{
	setCost(0, 1, 2);
	setCost(0,2,5);
	setCost(0,3,1);
	setCost(1,2,3);
	setCost(1,3,2);
	setCost(2,3,3);
	setCost(2,4,1);
	setCost(2,5,5);
	setCost(3,4,1);
	setCost(4,5,2);
	for (int i = 0; i < 6; i++) {
		graph[i][i] = 0;
	}
	dijkstra();
	forwarding();
	printTable();
}