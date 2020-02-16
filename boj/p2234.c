/**
 * @title 성곽 G4
 * @see https://www.acmicpc.net/problem/2234
 * @since 2019.07.31
 * @category graph, ccl, bitmask
 * @complexity O(nm) -> 40ms
 * @description
 *      구현하느라 애먹었던 문제. 그와중에 배열큐를 보면 B까지 염두에 둔 듯
 *      비트연산을 노골적으로 활용하라고 문제에서 알려주고 있고,
 *      2개의 큐를 사용하여 각 컴포넌트를 식별하였음. 효율성은 잘...
 *      각각의 컴포넌트별 크기, 연결상태를 기억하고 있다가 답을 계산하면 됨
 */

 #include <stdio.h>

 enum Direction {
 	WEST = 0b0001,
 	NORTH = 0b0010,
 	EAST = 0b0100,
 	SOUTH = 0b1000
 };

 typedef struct {
 	int x;
 	int y;
 	int room_number;
 } Pos;

 typedef struct {
 	Pos *queue;
 	int first;
 	int last;
 } Queue;

 __inline void init(Queue *q, Pos *queue) {
 	q->queue = queue;
 	q->first = 0;
 	q->last = 0;
 }

 __inline void push(int x, int y, int rn, Queue *q) {
 	Pos *qp = q->queue;
 	int idx = q->last;
 	qp[idx].x = x;
 	qp[idx].y = y;
 	q->last++;
 	qp[idx].room_number = rn;
 }

 __inline void pop(int *x, int *y, int *rn, Queue *q) {
 	Pos *qp = q->queue;
 	int idx = q->first;
 	*x = qp[idx].x;
 	*y = qp[idx].y;
 	q->first++;
 	*rn = qp[idx].room_number;
 }

 __inline int isEmpty(Queue q) {
 	return q.first == q.last;
 }

 int isValid(int x, int y, int n, int m) {
 	if (x >= n || y >= m || x < 0 || y < 0) {
 		return 0;
 	}
 	return 1;
 }

 int adjacent_matrix[2500][2500];
 int main(void) {
 	int n, m;
 	int castle[50][50][2];

 	Pos queue_same[4*2500];
 	Pos queue_diff[4*2500];
 	Queue q_same;
 	Queue q_diff;

 	int visit_count = 0;

 	int room_count = 0;
 	int current_room_size = 0;
 	int max_room_size = 0;
 	int room_size[2500] = { 0 };

 	scanf("%d %d", &n, &m);
 	init(&q_same, queue_same);
 	init(&q_diff, queue_diff);
 	for (int i = 0; i < m; i++) {
 		for (int j = 0; j < n; j++) {
 			scanf("%d", &castle[i][j][0]);
 			castle[i][j][1] = -1;
 		}
 	}

 	push(0, 0, 0, &q_same);
 	while ((!isEmpty(q_same) || !isEmpty(q_diff)) && visit_count < n*m) {
 		int cx, cy;
 		int from;
 		if (isEmpty(q_same)) {
 			pop(&cx, &cy, &from, &q_diff);

 			if (castle[cy][cx][1] != -1) {
 				adjacent_matrix[castle[cy][cx][1]][from] = 1;
 				continue;
 			}
 			else {
 				adjacent_matrix[room_count + 1][from] = 1;
 			}

 			if (max_room_size < current_room_size) {
 				max_room_size = current_room_size;
 			}
 			room_size[room_count] = current_room_size;
 			current_room_size = 0;
 			room_count++;
 		}
 		else {
 			pop(&cx, &cy, &from, &q_same);

 			adjacent_matrix[room_count][from] = 1;
 			if (castle[cy][cx][1] != -1) {
 				continue;
 			}
 		}

 		castle[cy][cx][1] = room_count;
 		visit_count++;
 		current_room_size++;

 		if (isValid(cx, cy - 1, n, m) != 0) {
 			if (castle[cy][cx][0] & NORTH)
 				push(cx, cy - 1, room_count, &q_diff);
 			else
 				push(cx, cy - 1, room_count, &q_same);
 		}
 		if (isValid(cx, cy + 1, n, m) != 0) {
 			if (castle[cy][cx][0] & SOUTH)
 				push(cx, cy + 1, room_count, &q_diff);
 			else
 				push(cx, cy + 1, room_count, &q_same);
 		}
 		if (isValid(cx - 1, cy, n, m) != 0) {
 			if (castle[cy][cx][0] & WEST)
 				push(cx - 1, cy, room_count, &q_diff);
 			else
 				push(cx - 1, cy, room_count, &q_same);
 		}
 		if (isValid(cx + 1, cy, n, m) != 0) {
 			if (castle[cy][cx][0] & EAST)
 				push(cx + 1, cy, room_count, &q_diff);
 			else
 				push(cx + 1, cy, room_count, &q_same);
 		}
 	}
 	room_size[room_count] = current_room_size;
 	if (max_room_size < current_room_size) {
 		max_room_size = current_room_size;
 	}

 	while (!isEmpty(q_diff)) {
 		int cx, cy, from;
 		pop(&cx, &cy, &from, &q_diff);
 		adjacent_matrix[castle[cy][cx][1]][from] = 1;
 	}

 	int max_expanded_room_size = 0;
 	for (int i = 0; i < room_count + 1; i++) {
 		for (int j = i + 1; j < room_count + 1; j++) {
 			if (adjacent_matrix[j][i] == 1) {
 				int size = room_size[i] + room_size[j];
 				if (max_expanded_room_size < size)
 					max_expanded_room_size = size;
 			}
 		}
 	}

 	printf("%d\n", room_count + 1);
 	printf("%d\n", max_room_size);
 	printf("%d\n", max_expanded_room_size);

 	return 0;
 }