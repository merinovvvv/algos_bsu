#include <iostream>
#include <fstream>
#include <vector>

int main(int argc, const char * argv[]) {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n, m;
    input >> n >> m;
    std::vector<std::vector<int>> result (n, std::vector<int>(n, 0));
    for (int i = 0; i < m; ++i) {
        int u, v;
        input >> u >> v;
        result[u-1][v-1] = 1;
        result[v-1][u-1] = 1;
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            output << result[i][j] << " ";
        }
        output << '\n';
    }
    
    return 0;
}