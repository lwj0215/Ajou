#include <stdio.h>
#include <time.h>

//sparse matrix structure
typedef struct {
	int row;
	int col;
	int value;
}Term;

//Make sparse matrix from 2D-arrayed matrix
void makeSprs(int arr[12][12], Term* sprs) {
	int i = 1;
	for (int j = 0; j < 12; j++) {
		for (int k = 0; k < 12; k++) {
			if (arr[j][k]) {
				sprs[i].row = j;
				sprs[i].col = k;
				sprs[i].value = arr[j][k];
				i++;
			}
		}
	}
}

//Sparse matrix multiplication
int mmult1D(Term op1[], Term op2[], Term* res) {
	int len = 1;
	for (int i = 1; i <= op1[0].value; i++) {
		for (int j = 1; j <= op2[0].value; j++) {
			if (op1[i].col == op2[j].row) {
				int chk = 0;
				for (int k = 1; k <= len; k++) {
					if (res[k].row == op1[i].row && res[k].col == op2[j].col) {
						res[k].value += op1[i].value * op2[j].value;
						chk++;
						break;
					}
				}
				if(!chk) {
					res[len].row = op1[i].row;
					res[len].col = op2[j].col;
					res[len].value = op1[i].value * op2[j].value;
					len++;
				}
			}
		}
	}
	res[0].row = 12;
	res[0].col = 12;
	res[0].value = len-1;
	return len;
}

//2D-arrayed matrix multiplication
void mmult2D(int op1[12][12], int op2[12][12], int res[][12]) {
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			res[i][j] = 0;
			for (int k = 0; k < 12; k++) {
				res[i][j] += op1[i][k] * op2[k][j];
			}
		}
	}
}

int main() {
	//Name initial alphabet L U J
	int n1[12][12] = {
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0},
		{0,0,0,1,1,1,1,1,1,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0}
	};
	int n2[12][12] = {
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,0,1,1,1,1,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0}
	};
	int n3[12][12] = {
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,0,0,0,0,0,1,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,0,0},
		{0,0,0,0,1,1,1,1,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0}
	};

	Term n1_sprs[16];
	Term n2_sprs[23];
	Term n3_sprs[15];

	//sparsed matrix basic info
	n1_sprs[0].row = 12;
	n1_sprs[0].col = 12;
	n1_sprs[0].value = 15;
	n2_sprs[0].row = 12;
	n2_sprs[0].col = 12;
	n2_sprs[0].value = 22;
	n3_sprs[0].row = 12;
	n3_sprs[0].col = 12;
	n3_sprs[0].value = 14;

	makeSprs(n1, n1_sprs);
	makeSprs(n2, n2_sprs);
	makeSprs(n3, n3_sprs);

	int res2D[12][12];
	Term res1D[145];
	mmult2D(n1, n2, res2D);
	int res1D_len = mmult1D(n1_sprs, n2_sprs, res1D);

	//Measure performance
	int start = clock();
	for (int i = 0; i < 100000; i++)
		mmult2D(n1, n2, res2D);
	int stop = clock();

	// printing 2-dimension arrayed matrix
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			printf("%d", n1[i][j]);
		}
		printf("\n");
	}
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			printf("%d", n2[i][j]);
		}
		printf("\n");
	}
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			printf("%d", n3[i][j]);
		}
		printf("\n");
	}

	// printing sparse matrix
	printf("#####1#####\n");
	for (int i = 0; i < 16; i++) {
		printf("%d %d %d\n", n1_sprs[i].row, n1_sprs[i].col, n1_sprs[i].value);
	}
	printf("#####2#####\n");
	for (int i = 0; i < 23; i++) {
		printf("%d %d %d\n", n2_sprs[i].row, n2_sprs[i].col, n2_sprs[i].value);
	}
	printf("#####3#####\n");
	for (int i = 0; i < 15; i++) {
		printf("%d %d %d\n", n3_sprs[i].row, n3_sprs[i].col, n3_sprs[i].value);
	}

	//printing 2d arrayed matrix multiplication
	printf("####Operand 1####\n");
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			printf("%d", n1[i][j]);
		}
		printf("\n");
	}
	printf("####Operand 2####\n");
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			printf("%d", n2[i][j]);
		}
		printf("\n");
	}
	printf("####Result####\n");
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			printf("%d", res2D[i][j]);
		}
		printf("\n");
	}

	//printing sparse matrix multiplication
	printf("#####Operands 1#####\n");
	for (int i = 0; i < 16; i++) {
		printf("%d %d %d\n", n1_sprs[i].row, n1_sprs[i].col, n1_sprs[i].value);
	}
	printf("#####Operands 2#####\n");
	for (int i = 0; i < 23; i++) {
		printf("%d %d %d\n", n2_sprs[i].row, n2_sprs[i].col, n2_sprs[i].value);
	}
	printf("####Result####\n");
	for (int i = 0; i < res1D_len; i++) {
		printf("%d %d %d\n", res1D[i].row, res1D[i].col, res1D[i].value);
	}

	//Print performance measurement
	printf("PERFORMANCE MEASUREMENT : %lf",((double)(stop-start))/CLK_TCK);
}
