#include <iostream>
#include <vector>

using namespace std;
typedef vector < vector <char> > VC;
typedef vector < vector <bool> > VB;

void llegir_mat(VC M){
    for(int i = 0; i<M.size(); ++i){
      for(int j = 0; j<M[0].size();++j){
        if( M[i][j]=='k' ) cout << 'K';
        else cout << '.';
      }
      cout << endl;
    }
    cout << "----------" << endl;
}


void marcar(VC &pos, int x, int y){
  pos[x][y]='k';
  if(x>0) pos[x-1][y] = 'f';
  if(x<pos.size()-1) pos[x+1][y] = 'f';

  if(y>0) pos[x][y-1] = 'f';
  if(y<pos[y].size()-1) pos[x][y+1] = 'f';

  if(x>0 and y>0) pos[x-1][y-1] = 'f';
  if(x>0 and y<pos[y].size()-1) pos[x-1][y+1] = 'f';
  if(x<pos.size()-1 and y>0) pos[x+1][y-1] = 'f';
  if(x<pos.size()-1 and y<pos[y].size()-1) pos[x+1][y+1] = 'f';
}

void kings(VC M, int x, int y, int k){

    if(M[x][y]=='i' and k!=0){
      VC Mrec = M;
      marcar(Mrec,x,y);
      int krec = k-1;
      if(krec == 0) llegir_mat(Mrec);

      if(y==M[0].size()-1 and x!=M.size()-1) kings(Mrec, x+1, 0, krec);
      else if(y!=M[0].size()-1) kings(Mrec, x, y+1, krec);

    }
    else M[x][y]=='f';
    if (k!=0){
      if(y==M[0].size()-1 and x!=M.size()-1) kings(M, x+1, 0, k);
      else if(y!=M[0].size()-1) kings(M, x, y+1, k);
    }


}


int main(){

int x,k;

cin >> x >> k;

VC M(x, vector<char> (x,'i'));

kings(M,0,0,k);
/*
for(int i = 0; i<M.size(); ++i){
  for(int j = 0; j<M[0].size();++j){
    kings(M,pos,i,j,k);
  }
}*/
}
