#include <iostream>

using namespace std;
int main() {
	int a[] = {6,2,4,8};
	string in;
	cin >> in;
	int v = 0;
	int l = in.length()-1;
    
	if (in == "0") {cout << 1; exit(0);}
	v = in[l]-'0';
	if (l != 0) {v += (in[l-1]-'0')*10;}

	cout << a[v%4];
}