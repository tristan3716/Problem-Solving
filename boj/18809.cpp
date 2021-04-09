#include <iostream>
#include <algorithm>

#include <sys/stat.h>
#include <sys/mman.h>

#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

class fio {
  size_t rsize;
  unsigned char* rbuf;
  int ridx;

  public:
  fio() : ridx(0) {
    struct stat rstat;
    fstat(0, &rstat);
    rsize = rstat.st_size;
    rbuf = (unsigned char*)mmap(0,rsize,PROT_READ,MAP_FILE|MAP_PRIVATE,0,0);
  }

  inline bool isBlank() {
    return rbuf[ridx] == '\n' || rbuf[ridx] == ' ';
  }
  inline void consumeBlank() { while (isBlank()) ridx++; }

  inline int readInt(){
    int res = 0, flag = 0;
    consumeBlank();
    while (!isBlank() && ridx < rsize)
      res = 10 * res + rbuf[ridx++] - '0';
    return flag ? -res : res;
  }
} io;

using namespace std;

int ds[][2] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

int ans;
int rqr[5000];
int rqc[5000];
int rqb, rqe;

int gqr[5000];
int gqc[5000];
int gqb, gqe;

int n, m, g, r;
int map[52][52];
int copied[52][52];

int gr[10];
int gc[10];

__inline void cb2(const int src[], const int &l, const int& r) {
	int bits = (1 << r) - 1;
	
	while (bits < (1 << l)) {
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < m + 2; j++) {
				copied[i][j] = map[i][j];
			}
		}
	
		rqb = 0;
		rqe = 0;
		gqb = 0;
		gqe = 0;
	
		int c = 1 << (l - 1);
		int idx = 0;
		while (c != 0) {
			int cr = gr[src[idx]];
			int cc = gc[src[idx]];
			copied[cr][cc] = 1;
			if ((c & bits) != 0) {
				gqr[gqe] = cr;
				gqc[gqe++] = cc;
			}
			else {
				rqr[rqe] = cr;
				rqc[rqe++] = cc;
			}
			idx++;
			c >>= 1;
		}
	
		int flower = 0;
		int time = 3;
	
		while ((rqb != rqe) && (gqb != gqe)) {
			time++;
			int rqs = rqe - rqb;
			for (int i = 0; i < rqs; i++) {
				int cr = rqr[rqb];
				int cc = rqc[rqb++];
	
				if (copied[cr][cc] == -1) continue;
	
				for (int i = 0; i < 4; ++i) {
					int nr = cr + ds[i][0];
					int nc = cc + ds[i][1];
	
					if (copied[nr][nc] == 0) {
						copied[nr][nc] = time;
						rqr[rqe] = nr;
						rqc[rqe++] = nc;
					}
				}
			}
	
			int gqs = gqe - gqb;
			for (int i = 0; i < gqs; i++) {
				int cr = gqr[gqb];
				int cc = gqc[gqb++];

				for (int i = 0; i < 4; ++i) {
					int nr = cr + ds[i][0];
					int nc = cc + ds[i][1];
	
					if (copied[nr][nc] == 0) {
						copied[nr][nc] = 1;
						gqr[gqe] = nr;
						gqc[gqe++] = nc;
					}
					else if (copied[nr][nc] == time) {
						copied[nr][nc] = -1;
						flower++;
					}
				}
			}
	
		}
	
		ans = max(flower, ans);
	
		int t = bits | ((bits & -bits) - 1);
		bits = (t + 1) | (((~t & -~t) - 1) >> (__builtin_ctz(bits) + 1));
	}
}

int arr[15];
int arr2[15];
__inline void go(const int src[], const int &l, const int &r) {
	int bits = (1 << r) - 1;

	while (bits < (1 << l)) {
		for (int i = 1; i < r; i++) {
			arr[i] = i;
		}
		int v = 0;
		int c = 1 << (l - 1);
		int idx = 0;
		while (c != 0) {
			if ((c & bits) != 0) {
				arr[v++] = src[idx];
			}
			idx++;
			c >>= 1;
		}

		cb2(arr, r, g);

		int t = bits | ((bits & -bits) - 1);
		bits = (t + 1) | (((~t & -~t) - 1) >> (__builtin_ctz(bits) + 1));
	}
}

int main() {
    n = io.readInt();
    m = io.readInt();
    g = io.readInt();
    r = io.readInt();

	for (int i = 0; i < n + 2; i++) {
		map[i][0] = 1;
		map[i][m + 1] = 1;
	}
	for (int i = 0; i < m + 2; i++) {
		map[0][i] = 1;
		map[n + 1][i] = 1;
	}

	int t;
	int available = 0;
	for (int i = 1; i < n + 1; i++) {
		for (int j = 1; j < m + 1; j++) {
            t = io.readInt();
			if (t == 2) {
				gr[available] = i;
				gc[available++] = j;
			}
			else if (t == 0) {
				map[i][j] = 1;
			}
		}
	}

	for (int i = 1; i < available; i++) {
		arr2[i] = i;
	}

	go(arr2, available, g + r);

	cout << ans;
}
