#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

typedef vector < vector <int> > graph;


void topo(const graph &G){

        int n = G.size();
        vector<int> graus(n, 0);

        for(int u = 0; u<n; ++u){

            for(int w : G[u]){

                ++graus[w];
            }
        }

        priority_queue<int, vector<int>, greater<int> > pQ;
        for(int i = 0; i<n; ++i){

            if(graus[i]==0) pQ.push(i);

        }
        //cout << S.size();

        vector<int> L;

        while(not pQ.empty()){
            int t = pQ.top();
            pQ.pop();
            L.push_back(t);

            for(int x : G[t]){

                if(--graus[x]==0) pQ.push(x);

            }
        }

        cout << L[0];
        for (int i = 1; i < n; ++i) {
            cout << " " << L[i];
        } cout << endl;

}


int main(){


    int n, m;
    int x, y;

    while(cin >> n >> m){

    graph G(n);

    for(int i = 0; i<m; ++i){

        cin >> x >> y;
        G[x].push_back(y);

    }

    topo(G);
  }

}
