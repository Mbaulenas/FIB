
#include<iostream>
#include<vector>
#include<queue>
using namespace std;
typedef vector<vector<int>> Graph;
bool dos_colors(Graph &graf){
    queue<int> Q;
    vector<int> colors(graf.size(), -1);
    for(int i = 0; i < graf.size(); ++i){
        if (colors[i] < 0){
            colors[i] = 1;
            Q.push(i);
            while(not Q.empty()){
                int n;
                n = Q.front();
                Q.pop();
                for (int j = 0; j < graf[n].size(); ++j){
                    int k = graf[n][j];
                    if (colors[k] < 0){
                        colors[k] = 1 - colors[n];
                        Q.push(k);
                    }
                    else if(colors[k] == colors[n]){
                        return  false;
                    }
                }
            }
        }

    }
    return true;}

int main(){
    int n,m;
    while(cin >> n >> m){
        Graph graf(n);
        for(int i = 0; i < m; ++i){
            int x,y;
            cin >> x >> y;
            graf[x].push_back(y);
            graf[y].push_back(x);
        }

        if (dos_colors(graf)) cout << "yes" << endl;
        else cout << "no" << endl;

    }}
