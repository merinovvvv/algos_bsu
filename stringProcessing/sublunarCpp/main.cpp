#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>

using namespace std;

const int MOD = 1e9 + 7;
const int P = 31;
const int MAXLEN = 250005;

vector <long long> power(MAXLEN), inv_power(MAXLEN);
 long long modpow (long long a, long long b) {
 long long res = 1;
    while (b) {
        if (b & 1) res = res * a % MOD;
        a = a * a % MOD;
        b >>= 1;
    }
    return res;
}

void precompute_powers() {
    power[0] = 1;
    for (int i = 1; i < MAXLEN; i++)
        power[i] = (power[i - 1] * P) % MOD;

 long long invP = modpow(P, MOD - 2);
    inv_power[0] = 1;
    for (int i = 1; i < MAXLEN; i++)
        inv_power[i] = (inv_power[i - 1] * invP) % MOD;
}

struct Hasher {
    vector <long long> h;
    Hasher(const string &s) {
        int n = s.size();
        h.resize(n + 1, 0);
        for (int i = 0; i < n; ++i)
            h[i + 1] = (h[i] + (s[i] - 'a' + 1) * power[i]) % MOD;
    }

 long long get_hash(int l, int r) {
     long long res = (h[r + 1] - h[l] + MOD) % MOD;
        return res * inv_power[l] % MOD;
    }
};

bool is_palindrome(int l, int r, Hasher &hash, Hasher &rev_hash, int len) {
    int rl = len - 1 - r;
    int rr = len - 1 - l;
    return hash.get_hash(l, r) == rev_hash.get_hash(rl, rr);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    precompute_powers();

    int n;
    cin >> n;
    vector<string> words(n);
    unordered_map<string, int> freq; //словарь со всеми строками, где freq[str] - счетчик строк
    for (int i = 0; i < n; ++i) {
        cin >> words[i];
        freq[words[i]]++;
    }

 long long ans = 0;
    for (int i = 0; i < n; ++i) {
        string &s = words[i];
        string rev_s = s;
        reverse(rev_s.begin(), rev_s.end());
        int len = s.size();

        Hasher hash(s);
        Hasher rev_hash(rev_s);

        if (freq.count(rev_s)) {
            ans += freq[rev_s];
            if (rev_s == s) ans--;
        }

        for (int j = 1; j < len; ++j) {
            if (is_palindrome(0, j - 1, hash, rev_hash, len)) {
                string suffix = s.substr(j);
                reverse(suffix.begin(), suffix.end());
                if (freq.count(suffix))
                    ans += freq[suffix];
            }

            if (is_palindrome(j, len - 1, hash, rev_hash, len)) {
                string prefix = s.substr(0, j);
                reverse(prefix.begin(), prefix.end());
                if (freq.count(prefix))
                    ans += freq[prefix];
            }
        }
    }

    cout << ans << "\n";
    return 0;
}