#include <iostream>
#include <vector>

using namespace std;

typedef vector < vector < int> > VI;

vector<int> despi{2,1,-1,-2,-2,-1, 1, 2};
vector<int> despj{1,2, 2, 1,-1,-2,-2,-1};
int minim = -1;

void cavall(VI &M, int x, int y, int s){

  if(x>=0 and x<=M.size()-1 and y>=0 and y<=M[0].size()-1){

    if(M[x][y]==1){
      if(minim==-1 or minim>s) minim = s;
    }

    else if(M[x][y]==0){
      M[x][y]= -1;
      ++s;
      for(int i = 0; i<8; ++i){
          cavall(M, x+despi[i], y+despj[i], s);
      }

    }
  }

}








int main(){

//llegir matriu

int n,m;
while(cin >> n >> m){
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

  int x,y;
  cin >> x >> y;

  minim = -1;
  cavall(M, x-1, y-1, 0);
  if(minim!=-1) cout << minim << endl;
  else cout << "no" << endl;
}



}
