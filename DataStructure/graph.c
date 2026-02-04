#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const int MAX_DIST = 9999;

typedef struct node* nodePointer;
typedef struct node {
	int v; //vertex
	int w; //weight
	nodePointer link;
}node;

nodePointer adjList[20] = { NULL };
int adjMat[20][20];
int edgeIndex;

void generate_graph(int n, int e, int flag) {
	srand((unsigned int)time(NULL));
    int	edgeResidual = e - n;
	for (int i = 0; i < n; i++) {
		int r = edgeResidual/(n-i);
		int j = r>0 ? rand() % r + 1 : 0;
		while (j >= 0) {
			int dest = rand() % n; // edge i to dest
			int w = rand() % 99 + 1; // weight
			int sign = rand() % 2; // sign
			if (flag == 1) { // check flag for negative weight
				if (sign == 0) w = -w;
			}
			if (dest == i) continue; // self-edge
			if (adjMat[i][dest] != 0) continue; // multi-graph
			adjMat[i][dest] = w;
			nodePointer adjNode = (nodePointer)malloc(sizeof(node));
			adjNode->v = dest;
			adjNode->w = w;
			if (adjList[i] != NULL) {
				adjNode->link = adjList[i]->link;
				adjList[i]->link = adjNode;
			}
			else {
				adjList[i] = adjNode;
				adjList[i]->link = NULL;
			}
			edgeIndex++;
			edgeResidual--;
			j--;
		}
	}
}

void show_graph() {
	printf("Adjacency Matrix:\n");
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
			printf("%3d ", adjMat[i][j]);
		}
		printf("\n");
	}
}

void printSeq(int prev[20]) {
	int p = prev[19];
	int route[20];
	int i = 19;
	while (p != 0) {
		route[i] = p;
		p = prev[p];
	}
	printf("Shortest path sequence: 0");
	for (int j = i; j < 20; j++) {
		printf(" -> %d", route[j]);
	}
	printf(" -> 19\n");
}

void matrix_dijkstra(int start) {
	int found[20];
	int dist[20];
	for (int i = 0; i < 20; i++) {
		found[i] = 0;
	}
	int prev[20];
	for (int i = 0; i < 20; i++) {
		dist[i] = adjMat[start][i] ? adjMat[start][i] : MAX_DIST;
		prev[i] = start;
	}
	found[start] = 1;
	dist[start] = 0;
	for (int i = 0; i < 19; i++) {
		int min = MAX_DIST;
		int v = -1;
		for (int j = 0; j < 20; j++) {
			if (!found[j] && dist[j] < min) {
				min = dist[j];
				v = j;
			}
		}
		if (v == -1) break; // all vertexs are found
		found[v] = 1;
		for (int j = 0; j < 20; j++) {
			if (!found[j] && adjMat[v][j]) {
				if (dist[v] + adjMat[v][j] < dist[j]) {
					dist[j] = dist[v] + adjMat[v][j];
					prev[j] = v;
				}
			}
		}
	}
	if (dist[19] == MAX_DIST) {
		printf("No path from 0 to 19\n");
		return;
	}
	printf("Shortest path length(Maxtrix): %d\n", dist[19]);
	printSeq(prev);
}

void matrix_bellman_ford(int start) {
	int dist[20];
	int prev[20];
	for (int i = 0; i < 20; i++) {
		dist[i] = adjMat[start][i] ? adjMat[start][i] : MAX_DIST;
		prev[i] = start;
	}
	dist[start] = 0;
	for (int k = 1; k < 19; k++) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (adjMat[i][j]) {
					if (dist[i] + adjMat[i][j] < dist[j]) {
						dist[j] = dist[i] + adjMat[i][j];
						prev[j] = i;
					}
				}
			}
		}
	}
	//negative cycle detection
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
			if (adjMat[i][j]) {
				if (dist[i] + adjMat[i][j] < dist[j]) {
					printf("Negative cycle detected between %d and %d\n",i,j);
					return;
				}
			}
		}
	}
	if (dist[19] == MAX_DIST) {
		printf("No path from 0 to 19\n");
		return;
	}
	printf("Shortest path length(Maxtrix): %d\n", dist[19]);
	printSeq(prev);
}

void list_dijkstra(int start) {
	int found[20];
	int dist[20];
	for (int i = 0; i < 20; i++) {
		found[i] = 0;
		dist[i] = MAX_DIST;
	}
	int prev[20];
	nodePointer p = adjList[start];
	while (p) {
		dist[p->v] = p->w;
		prev[p->v] = start;
		p = p->link;
	}
	found[start] = 1;
	dist[start] = 0;
	for (int i = 0; i < 19; i++) {
		int min = MAX_DIST;
		int v = -1;
		for (int j = 0; j < 20; j++) {
			if (!found[j] && dist[j] < min) {
				min = dist[j];
				v = j;
			}
		}
		if (v == -1) break; // all vertexs are found
		found[v] = 1;
		p = adjList[v];
		while (p) {
			int j = p->v;
			if (!found[j]) {
				if (dist[v] + p->w < dist[j]) {
					dist[j] = dist[v] + p->w;
					prev[j] = v;
				}
			}
			p = p->link;
		}
	}
	if (dist[19] == MAX_DIST) {
		printf("No path from 0 to 19\n");
		return;
	}
	printf("Shortest path length(List): %d\n", dist[19]);
	printSeq(prev);
}

void list_bellman_ford(int start) {
	int dist[20];
	for (int i = 0; i < 20; i++) {
		dist[i] = MAX_DIST;
	}
	int prev[20];
	nodePointer p = adjList[start];
	while (p) {
		dist[p->v] = p->w;
		prev[p->v] = start;
		p = p->link;
	}
	dist[start] = 0;
	for (int k = 1; k < 19; k++) {
		for (int i = 0; i < 20; i++) {
			p = adjList[i];
			while(p) {
				if (dist[i] + p->w < dist[p->v]) {
					dist[p->v] = dist[i] + p->w;
					prev[p->v] = i;
				}
				p = p->link;
			}
		}
	}
	//negative cycle detection
	for (int i = 0; i < 20; i++) {
		p = adjList[i];
		while(p) {
			if (dist[i] + p->w < dist[p->v]) {
				printf("Negative cycle detected between %d and %d\n", i, p->v);
				return;
			}
		}
	}
	if (dist[19] == MAX_DIST) {
		printf("No path from 0 to 19\n");
		return;
	}
	printf("Shortest path length(Maxtrix): %d\n", dist[19]);
	printSeq(prev);
}

void shortest_path_from_matrix_graph(int flag) {
	if (flag)
		matrix_bellman_ford(0);
	else
		matrix_dijkstra(0);
}

void shortest_path_from_list_graph(int flag) {
	if (flag)
		list_bellman_ford(0);
	else
		list_dijkstra(0);
}

int main() {
	int e, flag, n = 20;
	printf("Graph has 20 vertexs, Enter number of edges(20~400), flag(0 or 1): ");
	scanf("%d %d",&e, &flag);
	generate_graph(n, e, flag);
	show_graph();
	shortest_path_from_matrix_graph(flag); // No.0 vertex to No.19 vertex
	shortest_path_from_list_graph(flag); // No.0 vertex to No.19 vertex
}