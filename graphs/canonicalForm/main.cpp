#include <iostream>
#include <fstream>
#include <vector>

int main(int argc, const char * argv[]) {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n;
    input >> n;
    std::vector<int> res(n, 0);
    for (int i = 0; i < n-1; i++) {
        int u, v;
        input >> u >> v;
        res[v-1] = u;
    }
    for (int i = 0; i < n; i++) {
        output << res[i] << " ";
    }
    return 0;
}