#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int main() {
    int cubesAmount;
    std::cin >> cubesAmount;
    
    if (cubesAmount == 0) {
        std::cout << 0 << std::endl;
        return 0;
    }
    
    std::vector<int> a(cubesAmount + 1);
    std::vector<int> b(cubesAmount + 1);
    
    for (int i = 1; i <= cubesAmount; i++) {
        std::cin >> a[i];
        std::cin >> b[i];
    }

    std::vector<std::vector<long long>> result(cubesAmount + 1, std::vector<long long>(cubesAmount + 1, LLONG_MAX));

    for (int i = 1; i <= cubesAmount; i++) {
        result[i][i] = 0;
    }

    for (int cubes = 2; cubes <= cubesAmount; cubes++) {
        for (int l = 1; l <= cubesAmount - cubes + 1; l++) {
            int r = l + cubes - 1;
            for (int k = l; k < r; k++) {
                long long cost = static_cast<long long>(a[l]) * b[r];;
                long long petCost = result[l][k] + result[k + 1][r] + cost;
                if (petCost < result[l][r]) {
                    result[l][r] = petCost;
                }
            }
        }
    }
    
    std::cout << result[1][cubesAmount] << std::endl;
    return 0;
}