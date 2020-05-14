#include <vector>
#include <iostream>
#include <list>
#include <map>
#include <string>

struct Alloc {
	int s, e;
	Alloc(int s, int e) {
		this->s = s;
		this->e = e;
	}
};

using namespace std;
using iter = list<Alloc>::iterator;
int main() {
	// Java는 iterator 순회중에 데이터 삽입을 못한다...? 어떻게 처리해야하는지 모르겠음
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int n;
	cin >> n;
	map<string, iter> map;
	list<Alloc> list;
	list.push_back({ 1, 1 });
	list.push_back({ 100001, 100001 });

	for (int i = 0; i < n; i++) {
		string cm;
		cin >> cm;
		string varname = "";
		iter cur;
		iter next;
		int size;
		switch (cm.at(4)) {
			case '=':
				varname = cm.substr(0, 4);
				map.erase(varname);

				size = stoi(cm.substr(cm.find_first_of('(') + 1, cm.length() - 2));

				cur = list.begin();
				next = list.begin(); next++;
				for (; next != list.end(); cur++, next++) {
					if (next->s - cur->e >= size) break;
				}

				if (next == list.end()) { continue; }

				map[varname] = list.insert(next, { cur->e, cur->e + size });
				break;
			case 't':
				varname = cm.substr(6, 4);
				if (map.count(varname)) {
					cout << map[varname]->s << '\n';
				}
				else {
					cout << 0 << '\n';
				}
				break;
			default:
				varname = cm.substr(5, 4);

				if (map.count(varname)) {
					iter it = map[varname];
					list.erase(it);
					map.erase(varname);
				}
				break;
		}
	}
}