#include <iostream>
#include <fstream>
#include <deque>

int main() {
    std::ifstream input("huffman.in");
    std::ofstream output("huffman.out");

    int n;
    input >> n;

    std::deque<long long> deque1;
    std::deque<long long> deque2;

    for (int i = 0; i < n; ++i) {
        long long freq;
        input >> freq;
        deque1.push_back(freq);
    }

    long long resLen = 0;
    
    while (deque1.size() + deque2.size() > 1) {
        long long first = !deque1.empty() && (deque2.empty() || deque1.front() < deque2.front()) ? deque1.front() : deque2.front();
        if (!deque1.empty() && first == deque1.front()) {
            deque1.pop_front();
        } else {
            deque2.pop_front();
        }

        long long second = !deque1.empty() && (deque2.empty() || deque1.front() < deque2.front()) ? deque1.front() : deque2.front();
        if (!deque1.empty() && second == deque1.front()) {
            deque1.pop_front();
        } else {
            deque2.pop_front();
        }

        long long res = first + second;
        resLen += res;
        deque2.push_back(res);
    }

    output << resLen << std::endl;

    input.close();
    output.close();

    return 0;
}