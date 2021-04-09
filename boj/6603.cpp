#include <iostream>
#include <vector>

void makeSet(std::vector<int> set, std::vector<bool> visit, int k, std::vector<int> res, int q) {
	if (k == 0) {
		for (int i = 0; i < 6; i++) {
			std::cout << res[i] << " ";
		}
		std::cout << std::endl;
	}
	else {
		for (int i = q; i < set.size(); i++) {
			if (visit[i] == true)
				continue;
			std::vector<int> res_copy = res;
			res_copy.push_back(set[i]);
			visit[i] = true;
			makeSet(set, visit, k - 1, res_copy, i+1);
		}
	}
	
}

int main() {
	int k;
	while (true) {
		std::vector<int> set;
		std::vector<int> res;
		std::cin >> k;
		if (k == 0)
			break;
		else {
			for (int i = 0; i < k; i++) {
				int t;
				std::cin >> t;
				set.push_back(t);
			}
		}
		std::vector<bool> visit(k, false);
		makeSet(set, visit, 6, res, 0);

		std::cout << std::endl;
	}

	return 0;
}