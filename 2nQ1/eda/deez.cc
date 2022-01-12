#include <iostream>
#include <vector>
#include <queue>
using namespace std;
typedef pair<int, int> par;
typedef vector < vector < par >> VI;



void dijkstra(const VI &M, int s, vector<int> &d){

  int n = M.size();
  d[s]=0;
  priority_queue<par, vector<par>, greater<par> > cua;
  vector<bool> visitat(n,false);
  cua.push(par(0,s));
  while(not cua.empty()){

    int u = cua.top().second;
    cua.pop();

    if(not visitat[u]){
      visitat[u]=true;
      for(par a: M[u]){
        int v = a.second;
        int c = a.first;

        if(d[v] > d[u]+c or d[v]==-1){
          d[v] = d[u]+c;
          cua.push(par(d[v], v));
        }
      }
    }
  }

}






int main(){

  int n, m;
  int x, y, p;
  par aux;

  while(cin >> n >> m){

    VI M(n);

    for(int i = 0; i<m; ++i){
      cin >> x >> y >> p;
      aux = make_pair(p, y);
      M[x].push_back(aux);

    }

    vector<int> d (n, -1);
    cin >> x >> y;
    dijkstra(M, x, d);
    if(d[y]!=-1) cout << d[y] << endl;
    else cout << "no path from " << x << " to " << y << endl;
  }
}
