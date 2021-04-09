#include <iostream>
#include <string>
#include <cstring>
using namespace std;
#include <sys/mman.h>
#include <sys/stat.h>

int ridx = 0;
unsigned char* rbuf;
inline bool isBlank() {
	return rbuf[ridx] == '\n';
}

inline void consumeBlank() {
	while (isBlank()) ridx++;
}

inline char readChar() {
	char res = rbuf[ridx++];
	return res;
}

inline bool isEnd(){
	if (rbuf[ridx] == 'E' && rbuf[ridx+1] == 'N' && rbuf[ridx+2] == 'D')
		return true;
	return false;
}

int cnt[333];
constexpr int *f = cnt+96;
int l;
constexpr int tcv[] = {10, 100, 1000};
char buf[120000];
char *bufIdx = buf;

constexpr char head[] = "Case ";
constexpr char sa[] = ": same\n";
constexpr char df[] = ": different\n";
int le = 0;

int main() {
    struct stat rstat;
	fstat(0, &rstat);
	size_t rsize = rstat.st_size;
	rbuf = (unsigned char*)mmap(0, rsize, PROT_READ, MAP_FILE | MAP_PRIVATE, 0, 0);

	int tc = 1;
	int tcc = 1;
    int tcd = 10;
	char ch;
	while (true){
		if (tc == tcd) {
			tcd = tcv[tcc]; tcc++;
        }
		memset(f, 0, 123);
        l = 0;
		consumeBlank();
		if (isEnd()) break;
		while (!isBlank()) {
			ch = readChar();
			cnt[ch]++; l++;
		}
		consumeBlank();
		while (!isBlank()) {
			ch = readChar();
			cnt[ch]--; l--;
		}
		if (l != 0) goto fail;
		for (int i = 'a'; i <= 'z'; ++i)
			if (cnt[i]!=0)
				goto fail;
		memcpy(bufIdx, head, 5); bufIdx += 5;
		memcpy(bufIdx, to_string(tc).c_str(), tcc); bufIdx += tcc; le += tcc; ++tc;
		memcpy(bufIdx, sa, 7); bufIdx += 7; le += 12;
		continue;
		fail:
		memcpy(bufIdx, head, 5); bufIdx += 5;
		memcpy(bufIdx, to_string(tc).c_str(), tcc); bufIdx += tcc; le += tcc; ++tc;
		memcpy(bufIdx, df, 12); bufIdx += 12; le += 17;
	}
	fwrite(buf, le, 1, stdout);
}