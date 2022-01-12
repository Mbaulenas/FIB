#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;
typedef pair<int, int > par;
typedef vector < vector < par>> vpar;


void dijkstra(const vpar &M, int s, vector<int> &d, vector<int> &p){

  int n = M.size();
  d[s] = 0; //vector distancia
  priority_queue<par, vector<par>, greater<par> > cua;
  vector<bool> S(n, false);
  cua.push(par(0, s));

  while(not cua.empty()){

    int u = cua.top().second;
    cua.pop();

    if (not S[u]){
      S[u]=true;
      for(par a: M[u]){
          int v = a.second;
          int c = a.first;
          if(d[v] > d[u]+c or d[v] == -1){
            d[v] = d[u]+c;
            p[v]= u;
            cua.push(par(d[v],v));
          }
      }
    }


  }

}



int main(){

  int n,m;
  int x,y,z;
  while(cin >> n >> m){
  vpar M(n);

  for(int i = 0; i<m; ++i){

    cin >> x >> y >> z;
    par aux = make_pair(z,y);
    M[x].push_back(aux);

  }

  vector<int> d (n, -1);
  vector<int> p(n, -1);
  cin >> x >> y;
  dijkstra(M, x, d, p);

  if(d[y]==-1) cout << "no path from " << x << " to " << y << endl;

  else{
    stack<int> pila;
    while(y != x){
      pila.push(y);
      y = p[y];
    }
    pila.push(x);
    cout << pila.top();
    pila.pop();
    while(not pila.empty()){
      cout << " " << pila.top();
      pila.pop();
    }

    cout << endl;
  }
}
}
