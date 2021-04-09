#include <stdio.h>

#
define INFINITY 1073741824
using ll = long long;
typedef struct {
    int source;
    int destination;
    int weight;
}
Edge;

int bellman_ford(const Edge * e, ll * distance,
    const int n,
        const int m) {
    int i, j;
    for (i = 1; i < n; i++) {
        for (j = 0; j < m; j++) {
            if (distance[e[j].source] != INFINITY) {
                if (distance[e[j].destination] > distance[e[j].source] + e[j].weight) {
                    distance[e[j].destination] = distance[e[j].source] + e[j].weight;
                }
            }
        }
    }
    for (j = 0; j < m; j++) {
        if (distance[e[j].source] != INFINITY) {
            if (distance[e[j].destination] > distance[e[j].source] + e[j].weight) {
                return 0;
            }
        }
    }
    return 1;
}

int main(void) {
    int n, m;
    int i, j;
    int a, b, c;
    Edge e[6000];
    ll distance[501];
    scanf("%d %d", & n, & m);

    for (i = 2; i <= n; i++) {
        distance[i] = INFINITY;
    }
    distance[1] = 0;

    for (i = 0; i < m; i++) {
        scanf("%d %d %d", & e[i].source, & e[i].destination, & e[i].weight);
    }

    if (bellman_ford(e, distance, n, m)) {
        for (i = 2; i < n + 1; i++) {
            printf("%lld\n", distance[i] == INFINITY ? -1 : distance[i]);
        }
    } else {
        printf("%d", -1);
    }
}