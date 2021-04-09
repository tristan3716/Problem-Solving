#include <iostream>
#include <vector>

template <typename Tv>
void print_arg(Tv v)
{
	for (auto x : v) {
		std::cout << x << " ";
	}
	std::cout << "\n";
}

int main() {
	using namespace std;
	int T;
	cin >> T;
	do {
		int n;
		cin >> n;
		
		size_t max_size = 1u;
		vector<int> v;
		vector<int> max_v;
		v.push_back(1);
		max_v = v;
		int num[3];
		for (int s = 2; s <= n; ++s) {
			v.push_back(s);
			for (auto i = 0u; i < v.size() - 2; ++i) {
				num[0] = v[i];
				for (auto j = i + 1; j < v.size() - 1; ++j) {
					num[1] = v[j];
					for (auto k = j + 1; k < v.size(); ++k) {
						num[2] = v[k];
						int res = v[i] xor v[j] xor v[k];
						if (res == 0) {
							v.erase(v.begin() + i);
						}
					}
				}
			}
			if (v.size() > max_size) {
				max_size = v.size();
				max_v = v;
			}
		}
		cout << max_size << "\n";
		print_arg(max_v);
	} while (--T);
}