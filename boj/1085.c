#include <stdio.h>

int main() {
	int x, y, w, h;
    int d[4];
	int min, i;
    
	scanf("%d %d %d %d", &x, &y, &w, &h);
    
	d[0] = h - y;
	d[1] = y;
	d[2] = x;
	d[3] = w - x;
	
	for (i = 0, min = d[0]; i < 4; i++) {
		if (min > d[i]) {
			min = d[i];
		}
	}
	printf("%d", min);
}