/**
 * @title 효율적인 해킹 S2
 * @see https://www.acmicpc.net/problem/1325
 * @since 2019.07.29
 * @category graph, dfs, bfs
 * @complexity O(V+E) -> 4396ms
 * @description
 *      기본적인 bfs, dfs... 였던 것 같은데... 왤케 느린지 모르겠음 나중에 확인해봐야할 듯
 */

 #include <iostream>
 #include <vector>
 #include <algorithm>

 int hack(std::vector<std::vector<int>> &adj, std::vector<bool> &visit, int id) {
 	int sum = 0;
 	visit[id] = true;
 	for (unsigned int i = 1; i < adj[id].size(); i++) {
 		if (visit[adj[id][i]] == false)
 			sum += hack(adj, visit, adj[id][i]);
 	}
 	return 1 + sum;
 }

 int main() {
 	int n, m;
 	std::cin >> n >> m;
 	std::vector<std::vector<int>> adj(n + 1, std::vector<int>(1, 0));
 	for (int i = 0; i < m; i++) {
 		int a, b;
 		std::cin >> a >> b;
 		adj[b].push_back(a);
 	}
 	int max = -1;
 	std::vector<int> id;
 	id.reserve(n);
 	for (int cur_id = 1; cur_id <= n; cur_id++) { // O(n)
 		std::vector<bool> visit(n + 1, false);
 		int temp = hack(adj, visit, cur_id);
 		if (max < temp) {
 			id.clear();
 			id.push_back(cur_id);
 			max = temp;
 		}
 		else if (max == temp) {
 			id.push_back(cur_id);
 		}
 	}

 	for (unsigned int i = 0; i < id.size(); i++) {
 		std::cout << id[i] << " ";
 	}

 	return 0;
 }