#include "Player.hh"
#include <queue>

/**
 * Write the name of your player and save this file
 * with the same name and .cc extension.
 */
#define PLAYER_NAME Balue


struct PLAYER_NAME : public Player {

  /**
   * Factory: returns a new instance of this class.
   * Do not modify this function.
   */
  static Player* factory () {
    return new PLAYER_NAME;
  }

  /**
   * Types and attributes for your player can be defined here.
   */
   typedef vector<int> VI;
   typedef pair<int, Pos> par;
   typedef vector<vector<bool>> VB;

   void anar(const int &id, const Pos &newpos){

      Pos p = unit(id).pos;
      Dir newdir;
      int sumax = p.i- newpos.i;
      int sumay = p.j - newpos.j;
      queue<Dir> possibles;

      if(sumax>0){
        if(sumay>0){
          newdir = TL;
          possibles.push(Left); possibles.push(Top);
        }

        else if(sumay<0){
          newdir = RT;
          possibles.push(Top); possibles.push(Right);
        }

        else{
          newdir=Top;
          possibles.push(RT);possibles.push(TL);
        }
      }

      else if(sumax<0){
        if(sumay>0){
          newdir = LB;
          possibles.push(Left);possibles.push(Bottom);
        }

        else if(sumay<0){
          newdir = BR;
          possibles.push(Bottom);possibles.push(Right);
        }
        else{
          newdir = Bottom;
          possibles.push(BR);possibles.push(LB);
        }

      }

      else if(sumax==0){
        if(sumay<0){
          newdir = Right;
          possibles.push(RT);possibles.push(BR);
        }
        if(sumay>0){
          newdir = Left;
          possibles.push(TL);possibles.push(LB);
        }
      }

      bool fi = false;

      if(cell(p+newdir).type== 1 or cell(p+newdir).type== 0){
        command(id, newdir);
      }

      else{

        while(not fi){

          if(possibles.empty()) fi = true;

          else{

            newdir = possibles.front(); possibles.pop();

            if(cell(p+newdir).type== 1 or cell(p+newdir).type== 0){
              command(id, newdir);
              fi = true;
            }
          }

        }

    }

   }

   void anarmags(const int &id, const Pos &newpos){

      Pos p = unit(id).pos;
      Dir newdir;
      int sumax = p.i- newpos.i;
      int sumay = p.j - newpos.j;

      if(sumax>0){
        newdir=Top;
      }

      else if(sumax<0){
        newdir = Bottom;
      }

      else if(sumax==0){
        if(sumay<0) newdir = Right;
        else if(sumay>0) newdir = Left;
      }

      command(id, newdir);

   }



   void bfs(){

     VI D = dwarves(me());
     int n = D.size();

      for(int dw = 0; dw<n; ++dw){

          int id = D[dw];//identificador nan

          priority_queue <par, vector<par>, greater<par> > cua;
          VB visitat(60, vector<bool>(60, false));

          par ini;//posicio actual nan
          ini.second = unit(id).pos;
          ini.first = 0;
          cua.push(ini);

          Pos p;
          int dis;

          Pos alt;

          bool balt = false;
          bool comanda = false;
          bool atac = round()<=150;



          while(not comanda){

            Pos newpos;
            p = cua.top().second; //Pos
            dis = cua.top().first;//int
            cua.pop();
            int id2 = cell(p).id;
            visitat[p.i][p.j] = true;

            if(atac){
              if(id2!=-1 and unit(id2).player!=me() and unit(id2).player!=-1){
                anar(id, p);
                comanda = true;
              }
            }

            else{
              if(cell(p).type == 1 and cell(p).owner!=me() and not cell(p).treasure){
                anar(id, p);
                comanda = true;
              }
            }

            if (dis>8) comanda = true;

            if(not balt){

              if(cell(p).type == 1 and cell(p).owner!=me() and not cell(p).treasure){
                balt = true;
                alt = p;
              }

            }

            if(not atac and balt){
              anar(id, alt);
              comanda = true;
            }

            else if(not comanda){
              par newpar;

              for(int i = 0; i<8; ++i){

                newpos = p+Dir(i);

                if(pos_ok(newpos) and not visitat[newpos.i][newpos.j] and (cell(newpos).type==0 or cell(newpos).type==1)){
                  newpar.second = newpos;
                  newpar.first = dis+1;
                  cua.push(newpar);
                }

              }

              if(cua.empty()) comanda = true;
            }

          }

      }

   }



   void move_wizards() {
     vector<int> W = wizards(me());
     for (int id : W) {

       priority_queue <par, vector<par>, greater<par> > cua;
       VB visitat(60, vector<bool>(60, false));

       par ini;//posicio actual nan
       ini.second = unit(id).pos;
       ini.first = 0;
       cua.push(ini);
       vector<Dir> MM {Top,Left,Bottom,Right};
       bool comanda = false;

       while(not comanda){

         Pos newpos;
         Pos p = cua.top().second; //Pos
         int dis = cua.top().first;//int
         cua.pop();
         int id2 = cell(p).id;
         visitat[p.i][p.j] = true;

         if(dis>5) comanda = true;

         if(cell(p).id !=-1 and unit(cell(p).id).player==me()){
           comanda = true;
           anarmags(id, p);
         }



         else if(not comanda){
           par newpar;

           for(int i = 0; i<4; ++i){

             newpos = p+Dir(MM[i]);

             if(pos_ok(newpos) and not visitat[newpos.i][newpos.j]){
               newpar.second = newpos;
               newpar.first = dis+1;
               cua.push(newpar);
             }

           }

           if(cua.empty()) comanda = true;
         }

       }
    }
  }

  /**
   * Play method, invoked once per each round.
   */
  virtual void play () {

    bfs();
    move_wizards();
  }

};


/**
 * Do not modify the following line.
 */
RegisterPlayer(PLAYER_NAME);
