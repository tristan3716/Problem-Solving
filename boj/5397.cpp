#include <iostream>
#include <list>

int main(){
    //freopen("in", "r", stdin);
    using namespace std;
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i){
        string str;
        cin >> str;
        std::list<char> l(str.begin(), str.end());
        std::list<char> res = {};
        auto position = l.begin();
        auto pos = res.begin();
        
        for (auto li = l.begin(); li != l.end();) {
            switch (*li) {
                case '<':
                    if (pos != res.begin()) {
                        --pos;
                    }
                    break;
                case '>':
                    if (pos != res.end()) {
                        ++pos;
                    }
                    break;
                case '-':
                    if (pos != res.begin()) {
                        --pos;
                        pos = res.erase(pos);
                    }
                    break;
                default:
                    res.insert(pos, *li);
            }
            li++;
        }
        cout << string(res.begin(), res.end()) << "\n";
    }
}
