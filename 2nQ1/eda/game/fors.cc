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
   typedef pair<int, int> par;
   typedef vector<vector<bool>> VB;


   void anar(const int &id, const Pos &newpos){

      Pos p = unit(id).pos;
      Dir newdir;
      int sumax = p.i- newpos.i;
      int sumay = p.j - newpos.j;

      if(sumax>0){
        if(sumay>0) newdir = TL;
        else if(sumay<0) newdir = RT;
        else newdir=Top;
      }

      else if(sumax<0){
        if(sumay>0) newdir = LB;
        else if(sumay<0) newdir = BR;
        else newdir = Bottom;
      }

      else if(sumax==0){
        if(sumay<0) newdir = Right;
        if(sumay>0) newdir = Left;
      }

      if(pos_ok(p+newdir) and cell(p+newdir).type != 2 and cell(p+newdir).type != 3) command(id, newdir);

      else{
        newdir = Dir(random(0,7));
        while(not pos_ok(p+newdir)) newdir = Dir(random(0,7));
        command(id, newdir);
      }

   }



   void buscar_lluita(){

     vector<int> despx{1,0,-1,0};
     vector<int> despy{0,1,0,-1};

      VI D = dwarves(me());
      int n = D.size();

      for(int dw = 0; dw<n; ++dw){

        int id = D[dw];//identificador nan
        Pos p = unit(id).pos;//posicio actual nan
        bool close = false;



        //close
        for (int k = 0; not close and k < 8; ++k) {

          if (pos_ok(p+Dir(k))) {

            int id2 = cell(p+Dir(k)).id;
            if (id2 != -1 and unit(id2).player != me()) {
              close = true;
              Pos newpos = p+Dir(k);
              anar(id, newpos);
            }
          }
        }

        //si esta fora
        if(cell(p).type==0){
          close = true;
          entrar(id, p);
        }

        //recon 11x11
        if(not close){


          vector<Dir> pd{RT,TL, BR, LB};

          for(int i = 0; i<4 and not close; ++i){
            Pos newpos = p+Dir(pd[i])+Dir(pd[i])+Dir(pd[i]);
            for(int j = 0; j<8 and not close; ++j){
            newpos += Dir(j);
            if(pos_ok(newpos)){
              int id2 = cell(newpos).id;
              if(id2!=-1 and unit(id2).player != me()){
                close = true;
                anar(id, newpos);
              }
            }
            }
          }
        }

        //res interessant a prop
        if(not close){

          for (int k = 0; not close and k < 8; ++k) {
            Pos newpos = p + Dir(k);
            if(pos_ok(newpos)){
              int type = cell(newpos).type;
              int id2 = cell(newpos).owner;
              if(type == 1 and id2!=me()){
                close = true;
                anar(id, newpos);
              }
            }
          }

          for(int i = 0; i<8 and not close; ++i){
            Pos newpos = p+Dir(i)+Dir(i)+Dir(i);
            for(int j = 0; j<9 and not close; ++j){
              newpos = newpos + Dir(j);
              if(pos_ok(newpos)){
                int type = cell(newpos).type;
                int id2 = cell(newpos).owner;
                if(type == 1 and id2!= me()){
                  close = true;
                  anar(id, newpos);
                }
              }
            }
          }

          //ultima oportunitat
          if(not close){
            Pos cdm; cdm.i = 30; cdm.j = 30;
            anar(id,cdm);
          }

        }//if

      }//for
   }//busca_lluita

   void entrar(const int &id, const Pos &p){
     bool fi = false;
     vector<Dir> pd{RT,TL, BR, LB};

     for(int i = 0; i<4 and not fi; ++i){
       for(int j = 0; j<8 and not fi; ++j){

       Pos newpos = p+Dir(pd[i])+Dir(j);

       if(pos_ok(newpos)){
         int id2 = cell(newpos).type;
         if(id2==1 ){
           fi = true;
           anar(id, newpos);
         }
       }
       }
     }

     if(not fi) command(id, Dir(random(0,7)));

   }

   void move_wizards() {
     vector<int> W = wizards(me());
     for (int id : W) {
       Dir randir = Dir(random(0,7));
       if(pos_ok(unit(id).pos+randir)) command(id, randir);
    }
   }

  /**
   * Play method, invoked once per each round.
   */
  virtual void play () {

    buscar_lluita();
    move_wizards();
  }

};


/**
 * Do not modify the following line.
 */
RegisterPlayer(PLAYER_NAME);
