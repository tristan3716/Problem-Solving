#include <iostream>
#include <vector>
using namespace std;

int main(){
    int h, w;
    cin >> h >> w;
    vector<int> height(w);
    for (int i = 0; i < w; ++i) {
        cin >> height[i];
    }
    vector<int> left_max(w);
    left_max[0] = 0;
    vector<int> right_max(w);
    right_max[w-1] = 0;
    for (int i = 1; i < w; ++i) {
        left_max[i] = max(height[i-1], left_max[i-1]);
    }
    for (int i = w-2; i >= 0; --i) {
        right_max[i] = max(height[i+1], right_max[i+1]);
    }
    int answer = 0;
    for (int i = 0; i < w; ++i) {
        if (left_max[i] != 0 && right_max[i] != 0) {
            answer += max(min(left_max[i], right_max[i]) - height[i], 0);
        }
    }
    cout << answer;
}
