/**
 * @title 후위 표기식 G5
 * @see https://www.acmicpc.net/problem/1918
 * @since 2020.01.24
 * @category stack
 * @complexity O(n) -> 0ms
 * @description
 *      Stack을 이용한 중위->후위 변환 연습 문제. 괄호는 ()만 포함
 *      사실 다른 문제 풀고 코드 조금 고쳐서 제출함
 * @see https://www.acmicpc.net/problem/2769
 */


#include <stack>
#include <iostream>

int main() {
    using namespace std;
    stack<char> s;
    stack<char> op;
    string str;
    cin >> str;

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
