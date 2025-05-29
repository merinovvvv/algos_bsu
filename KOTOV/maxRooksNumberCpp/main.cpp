#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <map>
#include <chrono>
#include <unordered_set>
using namespace std;
int best = 0;
vector<pair<int, int>> cur;
vector<vector<pair<int, int>>> res;
map<pair<int, int>, pair<int, int>> edgeToCell;

void dfs(map<int, unordered_set<int>>& g,  vector<vector<bool>>& table, vector<int>& matchLeft, vector<int>& matchRight,
    vector<bool>& usedLeft, vector<bool>& usedRight, int idx, vector<int>& leftNodes) {

    if (leftNodes.size() - idx + cur.size() < best) {
        return;
    }

    if (idx == leftNodes.size()) {

        int matched = cur.size();
        if (matched == best) {
            res.push_back(cur);
        }
        else if (matched > best) {
            res.clear();
            res.push_back(cur);
            best = matched;
        }
        return;
    }

    int u = leftNodes[idx];
    for (int v : g[u]) {

        if (usedRight[v]) {
            continue;
        }

        matchLeft[u] = v;
        matchRight[v] = u;
        usedRight[v] = true;
        cur.push_back(edgeToCell[{u, v}]);

        dfs( g, table, matchLeft, matchRight, usedLeft, usedRight, idx+1, leftNodes);

        matchLeft[u] = -1;
        matchRight[v] = -1;
        usedRight[v] = false;
        cur.pop_back();
    }


    dfs(g, table, matchLeft, matchRight, usedLeft, usedRight, idx + 1, leftNodes);

}


int main() {
    ifstream in("input.txt");
    ofstream out("output.txt");
    int n, m, k;
    in >> n >> m >> k;
    vector<vector<bool>> table(n, vector<bool>(m, 0));
    int x, y;
    for (int i = 0; i < k; ++i) {
        in >> x >> y;
        table[x - 1][y - 1] = 1;
    }
    int left = 0, right = 0;
    map<int, unordered_set<int>> graph;

    int numRows = 0, numCols = 0;
    vector<vector<int>> rowId(n, vector<int>(m, -1));
    vector<vector<int>> colId(n, vector<int>(m, -1));

    for (int i = 0; i < n; ++i) {
        int j = 0;
        while (j < m) {
            if (table[i][j]) {
                ++j;
                continue;
            }
            while (j < m && !table[i][j]) {
                rowId[i][j] = numRows;
                ++j;
            }
            ++numRows;
        }
    }

    for (int j = 0; j < m; ++j) {
        int i = 0;
        while (i < n) {
            if (table[i][j]) {
                ++i;
                continue;
            }
            while (i < n && !table[i][j]) {
                colId[i][j] = numCols;
                ++i;
            }
            ++numCols;
        }
    }
    best = 0;
    int greedy = 0;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (table[i][j]) {
                continue;
            }
            int r = rowId[i][j];
            int c = colId[i][j];
            if (r != -1 && c != -1) {
                graph[r].insert(c);
                edgeToCell[{r, c}] = { i, j };
            }
        }
    }

    vector<int> matchLeft(numRows, -1);
    vector<int> matchRight(numCols, -1);
    vector<bool> usedRight(numCols, false);
    vector<bool> usedLeft(numCols, false);
    vector<int> leftNodes;

    for (int u = 0; u < numRows; ++u) {
        for (int v : graph[u]) {
            if (!usedRight[v]) {
                usedRight[v] = true;
                greedy++;
                break;
            }
        }
    }
    fill(usedRight.begin(), usedRight.end(), 0);
    best = greedy;

    for (int i = 0; i < numRows; ++i) {
        if (!graph[i].empty()) {
            leftNodes.push_back(i);
        }
    }

    dfs(graph, table, matchLeft, matchRight, usedLeft, usedRight, 0, leftNodes);
    sort(res.begin(), res.end());
    for (auto i : res) {
        for (auto j : i) {
            cout <<j.second+1 <<" "<< j.first + 1 << '\n';
        }
        cout << "<--->\n";
    }
    cout << res.size();

    vector<bool> visited(numRows + numCols, 0);

    return 0;
}