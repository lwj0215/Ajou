#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

typedef struct threadedTree* threadedPointer;
typedef struct threadedTree {
	short int leftThread;
	threadedPointer leftChild;
	char data;
	threadedPointer rightChild;
	short int rightThread;
}threadedTree;
threadedPointer prev;

void buildTree(threadedPointer root, threadedPointer nodes[], int n) {
	threadedPointer ptr;
	for (int i = 0; i < n / 2; i++) {
		ptr = nodes[i];
		if (i * 2 + 1 < n && nodes[i * 2 + 1]->data != '0') {
			ptr->leftThread = 0;
			ptr->leftChild = nodes[i * 2 + 1];
		}
		else {
			ptr->leftThread = 1;
			ptr->leftChild = NULL;
		}			
		if (i * 2 + 2 < n && nodes[i * 2 + 2]->data != '0') {
			ptr->rightThread = 0;
			ptr->rightChild = nodes[i * 2 + 2];
		}
		else {
			ptr->rightThread = 1;
			ptr->rightChild = NULL;
		}
	}
}

threadedPointer findParent(threadedPointer root, threadedPointer ptr) {
	if (root == NULL || ptr == NULL)
		return NULL;
	if (root->leftThread == 0 && root->leftChild == ptr) 
		return root;
	if(root->rightThread == 0 && root->rightChild == ptr)
		return root;
	threadedPointer tmp = NULL;
	if (root->leftThread == 0 && root->leftChild) {
		tmp = findParent(root->leftChild, ptr);
		if (tmp != NULL) 
			return tmp;
	}
	if (root->rightThread == 0 && root->rightChild) {
		tmp = findParent(root->rightChild, ptr);
		if (tmp != NULL) 
			return tmp;
	}
	return NULL;
}

threadedPointer postorder(threadedPointer root) {
	threadedPointer ptr = root;
	while (ptr->leftThread != 1 && ptr->rightThread != 1) {
		if (ptr->leftThread == 0)
			ptr = ptr->leftChild;
		else if (ptr->rightThread == 0)
			ptr = ptr->rightChild;
	}
	return ptr;
}

threadedPointer succ(threadedPointer root, threadedPointer ptr) {
	if (ptr == NULL) return NULL;
	if (ptr->rightThread == 1) {
		return ptr->rightChild;
	}
	threadedPointer parent = findParent(root, ptr);
	if (parent == NULL)
		return NULL;
	if (parent->leftThread == 0 && parent->leftChild == ptr && parent->rightThread == 0) {
		return postorder(parent->rightChild);
	}
	return parent;
}


void makeThreaded(threadedPointer ptr) {
	if (ptr == NULL)
		return;
	if (ptr->leftThread == 0)
		makeThreaded(ptr->leftChild);
	if (ptr->rightThread == 0)
		makeThreaded(ptr->rightChild);
	if (ptr->leftThread == 1) {
		if (prev == NULL)
			ptr->leftChild = ptr;
		else
			ptr->leftChild = prev;
	}
	if (prev && prev->rightThread == 1) {
		prev->rightChild = ptr;
	}
	prev = ptr;
}

void tpostorder(threadedPointer root) {
	threadedPointer ptr = postorder(root);
	while (ptr != NULL) {
		printf("%c ", ptr->data);
		ptr = succ(root, ptr);
	}
}

int main() {
	threadedPointer root;
	int n, a, max;
	char c;
	printf("Number of nodes: ");
	scanf("%d", &n);
	threadedPointer* nodes = (threadedPointer)malloc(n*sizeof(threadedPointer));
	printf("Input: ");
	for (int i = 0; i < n; i++) {
		nodes[i] = (threadedPointer)malloc(sizeof(threadedPointer));
		nodes[i]->data = '0';
		nodes[i]->leftChild = NULL;
		nodes[i]->rightChild = NULL;
		nodes[i]->leftThread = 1;
		nodes[i]->rightThread = 1;
	}
	for (int i = 0; i < n; i++) {
		scanf("%d %c", &a, &c);
		nodes[a]->data = c;
	}
	root = nodes[0];
	buildTree(root, nodes, n);
	prev = NULL;
	makeThreaded(root);
	tpostorder(root);
}