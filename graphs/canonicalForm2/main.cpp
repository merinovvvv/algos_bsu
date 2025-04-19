#include <iostream>
#include <fstream>
#include <vector>

int main(int argc, const char * argv[]) {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n;
    input >> n;
    std::vector<int> res (n, 0);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            bool tmp;
            input >> tmp;
            if (tmp) {
                res[j] = i+1;
            }
        }
    }
    for (int i = 0; i < n; i++) {
        output << res[i] << " ";
    }
    return 0;
}