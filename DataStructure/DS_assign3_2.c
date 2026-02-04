#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

typedef struct element {
    int key;
}element;
typedef struct node* treePointer;
typedef struct node {
    element data;
    treePointer leftChild;
    treePointer rightChild;
};

treePointer root;

void insert(int k){
    treePointer new = malloc(sizeof(struct node));
    new->data.key = k;
    new->leftChild = new->rightChild = NULL;
    if (root == NULL) {
        root = new;
        return;
    }
    treePointer ptr = root;
    treePointer parent = root;
    while (ptr) {
		parent = ptr;
        if (k < ptr->data.key)
            ptr = ptr->leftChild;
        else
            ptr = ptr->rightChild;
    }
	if (k < parent->data.key)
	    parent->leftChild = new;
	else
		parent->rightChild = new;
}

treePointer findMin(treePointer ptr) {
    treePointer parent = malloc(sizeof(struct node));
    while (ptr->leftChild != NULL) {
        parent = ptr;
        ptr = ptr->leftChild;
	}
    parent->leftChild = ptr->rightChild;
	return ptr;
}

void deletion(int k) {
    treePointer ptr = root;
    treePointer parent = NULL;
    treePointer child = NULL;
	while (ptr != NULL && k != ptr->data.key) {
        parent = ptr;
		if (k < ptr->data.key) {
			ptr = ptr->leftChild;
		}
		else if (k > ptr->data.key) {
			ptr = ptr->rightChild;
		}
		else {
            break;
		}
	}
    if (ptr->leftChild == NULL && ptr->rightChild == NULL) {
        free(ptr);
        if (parent != NULL && k < parent->data.key) {
            parent->leftChild = NULL;
        }
        else if(parent != NULL&& k > parent->data.key){
            parent->rightChild = NULL;
        }
        return;
    }
    if (ptr->leftChild == NULL) {
		child = ptr->rightChild;
        free(ptr);
    }
    else if (ptr->rightChild == NULL) {
		child = ptr->leftChild;
        free(ptr);
    }
    else{
        treePointer tmp = findMin(ptr->rightChild);
		tmp->leftChild = ptr->leftChild;
		tmp->rightChild = ptr->rightChild;
        child = tmp;
        free(ptr);
    }
    if (parent == NULL) {
        root = child;
		return;
    }
    if (k < parent->data.key) {
        parent->leftChild = child;
    }
    else {
        parent->rightChild = child;
    }
}

void inorder(treePointer ptr) {
    if (ptr == NULL) return;
    inorder(ptr->leftChild);
    printf("%d ", ptr->data.key);
    inorder(ptr->rightChild);
}

int main() {
    root = NULL;
    char c;
    while (1) {
        printf("Input: ");
        scanf(" %c", &c);
        switch (c) {
            case 'i': {
                int n;
				scanf("%d", &n);
				for (int i = 0; i < n; i++) {
					int k;
					scanf("%d", &k);
					insert(k);
				}
				break;
			}
            case 'd': {
                int k;
                scanf("%d", &k);
                deletion(k);
                break;
            }
			case 'p': {
				inorder(root);
                printf("\n");
				break;
			}
            case 'q': {
                return;
            }
            default:
                printf("Wrong Input\n");
        }
    }
}
