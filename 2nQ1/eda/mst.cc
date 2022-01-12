#include <iostream>
#include <vector>
#include <queue>
using namespace std;

typedef pair<int, int> par;
typedef vector< vector < par> > vpar;


int mst(const vpar &M){
    int n = M.size();
    vector<bool> visitat(n, false);
    visitat[0] = true;
    priority_queue<par, vector<par>, greater<par> > cua;

    for(par x: M[0]) cua.push(x);
    int sz = 1;
    int sum = 0;

    while(sz < n){
      int c = cua.top().first;
      int x = cua.top().second;
      cua.pop();
      if(not visitat[x]){
        visitat[x]= true;
        for(par y: M[x]) cua.push(y);
        sum+=c;
        ++sz;
      }
    }
    return sum;
}

int main(){

  int n, m;
  int x, y, p;
  par aux;
  while(cin>>n>>m){
    vpar M(n);

    for(int i = 0; i<m; ++i){
      cin >> x >> y >> p;
      aux = make_pair(p, y-1);
      M[x-1].push_back(aux);
      aux = make_pair(p, x-1);
      M[y-1].push_back(aux);
    }
    int res = mst(M);

    cout << res << endl;
  }
}
