#include <iostream>
#include <vector>

using namespace std;

const int SIFS = 16;
const int SLOT_TIME = 9;
const int DIFS = SIFS + 2 * SLOT_TIME;
int CW_MIN = 15;
const int CW_MAX = 1023;
const int AP = 30; // time of sending ACK

vector<pair<int, int>> station(4); // time of seding data, timing of send;
vector<int> backoff(4);
int currentTime = DIFS;

void doubleCW() {
	CW_MIN = min(CW_MIN * 2, CW_MAX);
	cout << "Double CW_MIN to " << CW_MIN << endl;
}

void setBO(int t) {
	srand(time(NULL));
	if (t >= 0 && t <= 3) {
		backoff[t] = (rand() % (CW_MIN + 1)) * SLOT_TIME;
		cout << "Set station" << t << "backoff * SLOT_TIME to " << backoff[t] << ", current time is " << currentTime << endl;
		return;
	}
	cout << "Set backoff * SLOT_TIME for all stations, ";
	for (int i = 0; i < 4; i++) {
		backoff[i] = (rand() % (CW_MIN + 1)) * SLOT_TIME;
		cout << "Station" << i << ": " << backoff[i] << ", ";
	}
	cout << endl;
}

void updateBO(int a) {
	int tmp = backoff[a];
	for (int i = 0; i < 4; i++) {
		backoff[i] -= tmp;
	}
}

void update() {
	for (int i = 0; i < 4; i++) {
		station[i].second = max(currentTime, station[i].second);
	}
}

void send(int a) {
	cout << "Station" << a << " send data, current time is " << currentTime << endl;
	currentTime += station[a].first;
	station[a].first = 0;
	cout << "ACK sent, current time is " << currentTime << endl;
	currentTime += SIFS + AP;
	cout << "End sending, current time is " << currentTime << endl;
	updateBO(a);
	update();
}

int main() {
	station[0] = { 70, 100 };
	station[1] = { 90, 100 };
	station[2] = { 100, 0 };
	station[3] = { 50, 100 };

	// initial send
	send(2);
	currentTime += DIFS;
	update();
	setBO(-1);

	while (1) {
		int target = -1;
		bool collision = 0;
		for (int i = 0; i < 4; i++) {
			if (station[i].second + backoff[i] == currentTime) {
				if (station[i].first != 0) {
					if (target != -1) { // Collision check
						cout << "Collision occured, current time is " << currentTime << endl;
						doubleCW();
						setBO(-1);
						collision = 1;
					}
					else
						target = i;
				}
			}
		}
		if (collision) {
			currentTime += DIFS;
			update();
			continue;
		}
		if (target != -1) {
			send(target);
		}
		bool flag = 0;
		for (int i = 0; i < 4; i++) {
			if (station[i].first != 0) {
				flag = 1;
			}
		}
		if (!flag) {
			cout << "Finish, current time is " << currentTime << endl;
			break;
		}
		currentTime++;
	}
}