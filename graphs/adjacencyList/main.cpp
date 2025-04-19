#include <iostream>
#include <fstream>
#include <vector>

int main(int argc, const char * argv[]) {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");
    int n, m;
    input >> n >> m;
    std::vector<std::vector<int>> res(n, std::vector<int>({0}));
    for (int i = 0; i < m; i++) {
        int u, v;
        input >> u >> v;
        res[u-1].push_back(v);
        res[u-1][0]++;
        res[v-1].push_back(u);
        res[v-1][0]++;
        
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < res[i].size(); j++) {
            output << res[i][j] << " ";
        }
        output << '\n';
    }
    return 0;
}