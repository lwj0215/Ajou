#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX_TERMS 100

//1
typedef struct {
	float coef;
	int expon;
} polynomial;
polynomial terms[MAX_TERMS];
int avail;

//2
typedef struct polyNode* polyPointer;
typedef struct polyNode {
	int coef;
	int expon;
	polyPointer link;
};

bool valid1() {
	if (avail >= MAX_TERMS) printf("Array out of range");
	return avail >= MAX_TERMS;
}

void pread1(int start) {
	puts("Number of terms: ");
	int n;
	scanf("%d", &n);
	puts("Polynomial input example: 4(coef1) 3(exp1) -2(coef2) 2(exp2) 3(coef3) 1(exp3) 5(coef4) 0(exp4), 반드시 내림차순일것, coef와 exp에 해당하는 값들만 순차 입력할것");
	float c;
	int e;
	for (int i = 0; i < n; i++) {
		if (valid1()) return;
		scanf("%f %d", &c, &e);
		terms[start + i].coef = c;
		terms[start + i].expon = e;
		avail = start + i;
	}
	avail++;
}

void pwrite1(int b, int c, int d, int e) {
	printf("Polynomial A: ");
	for (int i = 0; i < b; i++) {
		if (terms[i].coef > 0 && i) {
			printf("+ %.1fX%d ", terms[i].coef, terms[i].expon);
		}
		else {
			printf("%.1fX%d ", terms[i].coef, terms[i].expon);
		}
	}
	printf("\nPolynomial B: ");
	for (int i = b; i < c; i++) {
		if (terms[i].coef > 0 && i > b) {
			printf("+ %.1fX%d ", terms[i].coef, terms[i].expon);
		}
		else {
			printf("%.1fX%d ", terms[i].coef, terms[i].expon);
		}
	}
	printf("\nPolynomial C: ");
	for (int i = c; i < d; i++) {
		if (terms[i].coef > 0 && i > c) {
			printf("+ %.1fX%d ", terms[i].coef, terms[i].expon);
		}
		else {
			printf("%.1fX%d ", terms[i].coef, terms[i].expon);
		}
	}
	printf("\nPolynomial D: ");
	for (int i = d; i < e; i++) {
		if (terms[i].coef > 0 && i > d) {
			printf("+ %.1fX%d ", terms[i].coef, terms[i].expon);
		}
		else {
			printf("%.1fX%d ", terms[i].coef, terms[i].expon);
		}
	}
	printf("\nPolynomial E: ");
	for (int i = e; i < avail; i++) {
		if (terms[i].coef > 0 && i > e) {
			printf("+ %.1fX%d ", terms[i].coef, terms[i].expon);
		}
		else {
			printf("%.1fX%d ", terms[i].coef, terms[i].expon);
		}
	}
	printf("\n");
}

void padd1(int startA, int startB) {
	int i = startA, j = startB, startC = avail; 
	while (i < startB && j < startC) {
		if (valid1()) return;
		if (terms[i].expon > terms[j].expon) {
			terms[avail].coef = terms[i].coef;
			terms[avail].expon = terms[i].expon;
			avail++;
			i++;
		}
		else if (terms[i].expon < terms[j].expon) {
			terms[avail].coef = terms[j].coef;
			terms[avail].expon = terms[j].expon;
			avail++;
			j++;
		}
		else {
			terms[avail].coef = terms[i].coef +terms[j].coef;
			terms[avail].expon = terms[i].expon;
			avail++;
			i++;
			j++;
		}
	}
	while (i < startB) {
		if (valid1()) return;
		terms[avail].coef = terms[i].coef;
		terms[avail].expon = terms[i].expon;
		avail++;
		i++;
	}
	while (j < startC) {
		if (valid1()) return;
		terms[avail].coef = terms[j].coef;
		terms[avail].expon = terms[j].expon;
		avail++;
		j++;
	}
}

void psub1(int startA, int startB, int startC) {
	int i = startA, j = startB;
	while (i < startB && j < startC) {
		if (valid1()) return;
		if (terms[i].expon > terms[j].expon) {
			terms[avail].coef = terms[i].coef;
			terms[avail].expon = terms[i].expon;
			avail++;
			i++;
		}
		else if (terms[i].expon < terms[j].expon) {
			terms[avail].coef = -terms[j].coef;
			terms[avail].expon = terms[j].expon;
			avail++;
			j++;
		}
		else {
			terms[avail].coef = terms[i].coef - terms[j].coef;
			terms[avail].expon = terms[i].expon;
			avail++;
			i++;
			j++;
		}
	}
	while (i < startB) {
		if (valid1()) return;
		terms[avail].coef = terms[i].coef;
		terms[avail].expon = terms[i].expon;
		avail++;
		i++;
	}
	while (j < startC) {
		if (valid1()) return;
		terms[avail].coef = -terms[j].coef;
		terms[avail].expon = terms[j].expon;
		avail++;
		j++;
	}
}

void pmult1(int startB, int startC) {
	float tmp[99999] = { 0 };
	int max = terms[0].expon + terms[startB].expon;
	for (int i = 0; i < startB; i++) {
		for (int j = startB; j < startC; j++) {
			tmp[terms[i].expon + terms[j].expon] += terms[i].coef * terms[j].coef;
		}
	}
	for (int i = max; i > 0; i--) {
		if (tmp[i] < 0 || tmp[i] > 0) {
			if (valid1()) return;
			terms[avail].coef = tmp[i];
			terms[avail].expon = i;
			avail++;
		}
	}
}

double eval1(int startB, int startC) {
	printf("X에 대입할 값 입력: ");
	double l;
	double res=0;
	scanf("%lf", &l);
	printf("계산할 Poloynomial 선택 a/b: ");
	char c;
	scanf("%c%c",&c, &c);
	if (c == 'a') {
		for (int i = 0; i < startB; i++) {
			res += terms[i].coef * pow(l, terms[i].expon);
		}
	}
	else {
		for (int i = startB; i < startC; i++) {
			res += terms[i].coef * pow(l, terms[i].expon);
		}
	}
	return res;
}

void pread2(polyPointer p) {
	p->coef = 0;
	p->expon = -1;
	p->link = p;
	puts("Number of terms: ");
	int n;
	scanf("%d", &n);
	puts("Polynomial input example: 4(coef1) 3(exp1) -2(coef2) 2(exp2) 3(coef3) 1(exp3) 5(coef4) 0(exp4), 반드시 내림차순일것, coef와 exp에 해당하는 값들만 순차 입력할것");
	int c;
	int e;
	polyPointer ptr = p;
	for (int i = 0; i < n; i++) {
		if (valid1()) return;
		scanf("%d %d", &c, &e);
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		tmp->coef = c;
		tmp->expon = e;
		tmp->link = p;
		ptr->link = tmp;
		ptr = tmp;
	}
	ptr->link = p;
}

void pwrite2(polyPointer a) {
	polyPointer start = a;
	polyPointer ptr = a->link;
	if (ptr == a) {
		printf("Empty Polynomial\n");
		return;
	}
	printf("%dX%d", ptr->coef, ptr->expon);
	ptr = ptr->link;
	while (ptr != a) {
		printf(" + %dX%d",ptr->coef,ptr->expon);
		ptr = ptr->link;
	}
	printf("\n");
}

void padd2(polyPointer a, polyPointer b, polyPointer c) {
	c->coef = 0;
	c->expon = -1;
	c->link = c;
	polyPointer ptrA = a->link;
	polyPointer ptrB = b->link;
	polyPointer ptrC = c->link;
	while (ptrA != a && ptrB != b) {
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		if (ptrA->expon > ptrB->expon) {
			tmp->coef = ptrA->coef;
			tmp->expon = ptrA->expon;
			ptrA = ptrA->link;
		}
		else if (ptrA->expon == ptrB->expon) {
			if (ptrA->coef + ptrB->coef != 0) {
				tmp->coef = ptrA->coef + ptrB->coef;
				tmp->expon = ptrA->expon;
				ptrA = ptrA->link;
				ptrB = ptrB->link;
			}
			else {
				ptrA = ptrA->link;
				ptrB = ptrB->link;
				free(tmp);
				continue;
			}
		}
		else {
			tmp->coef = ptrB->coef;
			tmp->expon = ptrB->expon;
			ptrB = ptrB->link;
		}
		ptrC->link = tmp;
		ptrC = ptrC->link;
	}
	while (ptrA != a) {
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		tmp->coef = ptrA->coef;
		tmp->expon = ptrA->expon;
		ptrA = ptrA->link;
		ptrC->link = tmp;
		ptrC = ptrC->link;
	}
	while (ptrB != b) {
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		tmp->coef = ptrB->coef;
		tmp->expon = ptrB->expon;
		ptrB = ptrB->link;
		ptrC->link = tmp;
		ptrC = ptrC->link;
	}
	ptrC->link = c;
}

void psub2(polyPointer a, polyPointer b, polyPointer c) {
	c->coef = 0;
	c->expon = -1;
	c->link = c;
	polyPointer ptrA = a->link;
	polyPointer ptrB = b->link;
	polyPointer ptrC = c->link;
	while (ptrA != a && ptrB != b) {
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		if (ptrA->expon > ptrB->expon) {
			tmp->coef = ptrA->coef;
			tmp->expon = ptrA->expon;
			ptrA = ptrA->link;
		}
		else if (ptrA->expon == ptrB->expon) {
			if (ptrA->coef - ptrB->coef != 0) {
				tmp->coef = ptrA->coef - ptrB->coef;
				tmp->expon = ptrA->expon;
				ptrA = ptrA->link;
				ptrB = ptrB->link;
			}
			else {
				ptrA = ptrA->link;
				ptrB = ptrB->link;
				free(tmp);
				continue;
			}
		}
		else {
			tmp->coef = -ptrB->coef;
			tmp->expon = ptrB->expon;
			ptrB = ptrB->link;
		}
		ptrC->link = tmp;
		ptrC = ptrC->link;
	}
	while (ptrA != a) {
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		tmp->coef = ptrA->coef;
		tmp->expon = ptrA->expon;
		ptrA = ptrA->link;
		ptrC->link = tmp;
		ptrC = ptrC->link;
	}
	while (ptrB != b) {
		polyPointer tmp = (polyPointer)malloc(sizeof(polyNode));
		tmp->coef = -ptrB->coef;
		tmp->expon = ptrB->expon;
		ptrB = ptrB->link;
		ptrC->link = tmp;
		ptrC = ptrC->link;
	}
	ptrC->link = c;
}

void pmult2(polyPointer a, polyPointer b, polyPointer c) {
	c->coef = 0;
	c->expon = -1;
	c->link = c;
	polyPointer ptrA = a->link;
	polyPointer ptrB = b->link;
	polyPointer ptrC = c->link;
	int tmp[99999] = { 0 };
	int max = a->link->expon + b->link->expon;
	for (;ptrA != a; ptrA=ptrA->link) {
		for (;ptrB!=b;ptrB=ptrB->link) {
			tmp[ptrA->expon + ptrB->expon] += ptrA->coef * ptrB->coef;
		}
	}
	for (int i = max; i > 0; i--) {
		if (tmp[i] != 0) {
			polyPointer temp = (polyPointer)malloc(sizeof(polyNode));
			temp->coef = tmp[i];
			temp->expon = i;
			ptrC->link = temp;
			ptrC = ptrC->link;
		}
	}
	ptrC->link = c;
}

double eval2(polyPointer a) {
	printf("X에 대입할 값 입력: ");
	double l;
	double res = 0;
	scanf("%lf", &l);
	polyPointer ptr = a->link;
	if (ptr == a) return 0;
	for (; ptr != a; ptr = ptr->link) {
		res += ptr->coef * pow(l, ptr->expon);
	}
	return res;
}

void perase(polyPointer a) {
	polyPointer ptr = a->link;
	while (ptr != a) {
		polyPointer tmp = ptr;
		ptr = ptr->link;
		free(tmp);
	}
	free(a);
}

int main() {
	int startA = 0;
	pread1(startA);
	int startB = avail;
	pread1(startB);
	int startC = avail;
	padd1(startA, startB);
	int startD = avail;
	psub1(startA, startB, startC);
	int startE = avail;
	pmult1(startB, startC);
	double l = eval1(startB, startC);
	printf("%lf\n", l);
	pwrite1(startB, startC, startD, startE);
	

	polyPointer a = (polyPointer)malloc(sizeof(polyNode));
	pread2(a);
	pwrite2(a);
	polyPointer b = (polyPointer)malloc(sizeof(polyNode));
	pread2(b);
	pwrite2(b);
	polyPointer c = (polyPointer)malloc(sizeof(polyNode));
	padd2(a, b, c);
	pwrite2(c);
	polyPointer d = (polyPointer)malloc(sizeof(polyNode));
	psub2(a, b, d);
	pwrite2(d);
	polyPointer e = (polyPointer)malloc(sizeof(polyNode));
	pmult2(a, b, e);
	pwrite2(e);
	double ll = eval2(a);
	printf("%lf\n", ll);
	perase(a);
}