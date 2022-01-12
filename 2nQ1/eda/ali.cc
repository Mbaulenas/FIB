#include <iostream>

using namespace std;

typedef vector < vector < int> > VI;
int main(){

//llegir matriu
int n,m;
cin >> n >> m;
VI M(n, vector<int>(m));
char c;
  for(int i = 0; i<n; ++i){
    for(int j= 0; j<m; ++j){
      cin >> c;

      if(c=='.') M[i][j] = 0; //visitable
      else if(c=='X') M[i][j] = -1;//mur
      else if(c=='p') M[i][j] = 1;//pastanaga(objectiu)
    }
  }


}
