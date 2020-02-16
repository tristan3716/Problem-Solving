/**
 * @title DFS와 BFS S1
 * @see https://www.acmicpc.net/problem/1260
 * @since 2020.02.06
 * @category graph, dfs, bfs
 * @complexity O(V+E) -> 4ms
 * @description
 *      bfs, dfs 구현 연습 문제
 *      vertex 방문 순서에 유의
 */

#include <iostream>
#include <vector>
#include <list>
#include <queue>
#include <algorithm>
using namespace std;

struct Edge {
    int start;
    int end;
    Edge(int start, int end) : start(start), end(end) {}
};

struct Node {
    vector<int> connect;
    int idx;
    Node() { idx = 0; connect.reserve(250); }
    int nextEdge() {
        return connect[idx++];
    }
    void makeEdge(int e) {
        connect.push_back(e);
    }
};

void dfs(const int& n, vector<Node>& g, const int s, vector<bool>& vst, vector<int>& vsto) {
    vsto.emplace_back(s);
    for (auto x : g[s].connect) {
        if (vst[x] == false) {
            vst[x] = true;
            dfs(n, g, x, vst, vsto);
        }
    }
}

void dfs(const int& n, vector<Node>& g, const int& s) {
    vector<bool> vst(n + 1, false);
    vector<int> vsto;
    vst[s] = true;
    dfs(n, g, s, vst, vsto);
    for (const auto& x : vsto) {
        cout << x << " ";
    }
    cout << "\n";
}

void bfs(const int& n, vector<Node>& g, const int& s) {
    vector<bool> vst(n+1, false);
    queue<int> vsto;

    queue<int> q;
    q.push(s);
    vst[s] = true;
    while (!q.empty()) {
        int t = q.front(); q.pop();
        vsto.push(t);
        for (size_t i = 0u; i < g[t].connect.size(); ++i) {
            int x = g[t].nextEdge();
            if (vst[x] == false) {
                vst[x] = true;
                q.push(x);
            }
        }
    }

   while (!vsto.empty()) {
        cout << vsto.front() << " ";
        vsto.pop();
   }
}

int main() {
    int n, m;
    int s;

    cin >> n;
    cin >> m;
    cin >> s;
    vector<Edge> v;
    v.reserve(m);
    vector<Node> g(n+1);
    for (int i = 0; i < m; ++i) {
        int s, e;
        cin >> s >> e;
        //v.emplace_back(s, e);
        g[s].makeEdge(e);
        g[e].makeEdge(s);
    }
    for (int i = 1; i <= n; ++i) {
        sort(g[i].connect.begin(), g[i].connect.end());
        g[i].connect.erase(unique(g[i].connect.begin(), g[i].connect.end()), g[i].connect.end());
    }

    dfs(n, g, s);
    bfs(n, g, s);
}