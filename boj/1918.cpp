#include <stack>
#include <iostream>

int main() {
    using namespace std;
    #ifndef ONLINE_JUDGE
    freopen("in", "r", stdin);
    #endif
    stack<char> s;
    stack<char> op;
    string str;
    cin >> str;
    
    int level = 0;
    for (const auto & x : str) {
        switch(x){
            case '*':
            case '/':
                if (!op.empty()) {
                    while (!op.empty() 
                            && op.top() != '('
                            && op.top() != '+'
                            && op.top() != '-') {
                        s.push(op.top());
                        op.pop();
                    }
                    op.push(x);
                }
                else {
                    op.push(x);
                }
                break;
            case '+':
            case '-':
                if (!op.empty()) {
                    while (!op.empty() && op.top() != '(') {
                        s.push(op.top());
                        op.pop();
                    }
                    op.push(x);
                }
                else {
                    op.push(x);
                }
                break;
            case '(':
                op.push(x);
                break;
            case ')':
                while (!op.empty() && op.top() != '(') {
                    s.push(op.top());
                    op.pop();
                }
                op.pop();
                break;
            default:
                s.push(x);
        }
    }
    while (!op.empty()) {
        s.push(op.top());
        op.pop();
    }
    
    while (!s.empty()) {
        op.push(s.top());
        s.pop();
    }
    while (!op.empty()) {
        cout << op.top();
        op.pop();
    }
}
