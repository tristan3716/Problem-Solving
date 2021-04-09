/**
 * @title 논리식 비교 P1
 * @see https://www.acmicpc.net/problem/2769
 * @since 2020.01.25
 * @category stack, simulation, brute-force, parsing
 * @complexity O(T * 2^n * m) -> 12ms (n = 10, m = 100)
 * @description
 *      변수가 10개 이하로 주어지는 두 논리식을 파싱하여 비교
 *      정해는 논리식에 대한 정규화이지만, n이 10으로 작아서 변수 n에 대한 진리표를 작성(2^n)하여
 *      evaluation(2m) 결과가 같은지 비교하여도 해를 얻을 수 있음
 *      (논리식 파싱은 main 에 포함)
 *      각각의 변수 상태에 대한 진리표를 생성해주는 solve 함수 (2^n)
 *      스택을 이용하여 논리식을 계산하는 evaluation 함수 (m)
 *      중위식을 후위식으로 변환하는 convert 함수 (m) 로 구성되어 있음
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <regex>
#include <stack>
#include <map>

using namespace std;

int getPriority(const char x) {
    switch (x) {
        case '~':
            return 1;
        case '&':
            return 2;
        case '^':
            return 3;
        case '|':
            return 4;
        case '(':
        case ')':
            return 6;
        default:
            return 9;
    }
}

vector<char> convert(const string & expression) {
    stack<char> s;
    vector<char> e;

    for (size_t i = 0; i < expression.length(); ++i) {
        const char x = expression.at(i);
        switch (x) {
            case '^':
            case '&':
            case '|':
                if (s.empty()) {
                    s.push(x);
                }
                else {
                    while (!s.empty()) {
                        if (getPriority(x) < getPriority(s.top())) {
                            break;
                        }
                        e.push_back(s.top());
                        s.pop();
                    }
                    s.push(x);
                }
                break;
            case '~':
                s.push(x);
                break;
            case '(':
                s.push(x);
                break;
            case ')':
                while (!s.empty() && s.top() != '(') {
                    e.push_back(s.top());
                    s.pop();
                }
                s.pop();
                break;
            default:
                e.push_back(x);
        }
    }
    while (!s.empty()) {
        e.push_back(s.top());
        s.pop();
    }

    return e;
}

bool boolean(int x){
    return x == 1;
}

bool evaluation(map<char, int> m, const vector<char> &ex) {
    stack<bool> s;

    bool a, b;
    for (size_t i = 0; i < ex.size(); ++i) {
        switch(ex[i]) {
            case '~':
                a = s.top(); s.pop();
                s.push(!a);
                break;
            case '&':
                a = s.top(); s.pop();
                b = s.top(); s.pop();
                s.push(a&b);
                break;
            case '^':
                a = s.top(); s.pop();
                b = s.top(); s.pop();
                s.push(a^b);
                break;
            case '|':
                a = s.top(); s.pop();
                b = s.top(); s.pop();
                s.push(a|b);
                break;
            default:
                s.push(boolean(m[ex[i]]));
                break;
        }
    }
    return s.top();
}

bool solve(map<char, int> m, vector<pair<const char, int> > &v, int depth, const vector<char> & a, const vector<char> & b) {
    if (depth == static_cast<int>(v.size())) {
        return evaluation(m, a) == evaluation(m, b);
    }

    bool d, f;
    v[depth].second = 0;
    m[v[depth].first] = 0;
    d = solve(m, v, depth + 1, a, b);

    v[depth].second = 1;
    m[v[depth].first] = 1;
    f = solve(m, v, depth + 1, a, b);
    return d & f;
}

int main() {
    int n;
    cin >> n;
    cin.ignore();
    for (int i = 0; i < n; ++i) {
        string source;
        getline(cin, source);
        string reg = regex_replace(source, regex("[^a-z|^&()~|\\n]"), "");

        string a;
        string b;
        map<char, int> m;
        auto old = '\0';
        int level = -1;
        for (size_t j = 0; j < reg.length(); ++j) {
            const auto & x = reg[j];
            switch (x) {
                case '^':
                case '&':
                case '|':
                    old = x;
                    break;
                case '~':
                    if (old == '*' || old == ')') {
                        a = reg.substr(0, j);
                        b = &reg[j];
                        goto done;
                    }
                    old = '~';
                    break;
                case '(':
                    if (level == 0 || (old == '*')) {
                        a = reg.substr(0, j);
                        b = &reg[j];
                        goto done;
                    }
                    if (level == -1) {
                        level = 1;
                    }
                    else {
                        level++;
                    }
                    old = '(';
                    break;
                case ')':
                    level--;
                    old = ')';
                    break;
                default:
                    if (old == '*' || old == ')') {
                        a = reg.substr(0, j);
                        b = &reg[j];
                        goto done;
                    }
                    old = '*';
                    m[x] = 1;
            }
        }
    done:
        for (size_t j = 0; j < b.length(); ++j) {
            const auto & x = b[j];
            switch (x) {
                case '^':
                case '&':
                case '|':
                case '~':
                case '(':
                case ')':
                    break;
                default:
                    m[x] = 1;
            }
        }
        const vector<pair<const char, int> > v;
        vector<char> ea = convert(a);
        vector<char> eb = convert(b);

        vector<pair<const char, int> > q;
        for (auto it = m.begin(); it != m.end(); ++it) {
            q.emplace_back(*it);
        }

        cout << "Data set " << i+1 << ": " << (solve(m, q, 0, ea, eb) ? "Equivalent" : "Different") << "\n";
    }
}
