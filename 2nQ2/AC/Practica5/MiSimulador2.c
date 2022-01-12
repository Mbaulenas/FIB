#include "CacheSim.h"

/* Posa aqui les teves estructures de dades globals
 * per mantenir la informacio necesaria de la cache
 * */

 unsigned int via0[64];
 unsigned int via1[64];
 unsigned int lru[64];
 unsigned int nhit = 0;
 unsigned int nmiss = 0;


/* La rutina init_cache es cridada pel programa principal per
 * inicialitzar la cache.
 * La cache es inicialitzada al comen�ar cada un dels tests.
 * */
void init_cache ()
{
    totaltime=0.0;
	/* Escriu aqui el teu codi */

  //buidar la cache
    for(int i = 0; i<64; ++i){
      via0[i]=-1;
      via1[i]=-1;
      lru[i]=-1;
    }


}

/* La rutina reference es cridada per cada referencia a simular */
void reference (unsigned int address)
{
	unsigned int byte;
	unsigned int bloque_m;
	unsigned int conj_mc;
	unsigned int via_mc;
	unsigned int tag;
	unsigned int miss;	   // boolea que ens indica si es miss
	unsigned int replacement;  // boolea que indica si es reempla�a una linia valida
	unsigned int tag_out;	   // TAG de la linia reempla�ada
	float t1,t2;		// Variables per mesurar el temps (NO modificar)

	t1=GetTime();
	/* Escriu aqui el teu codi */

  byte = address & 0x1F;      //5b
  conj_mc = (address & 0x000007E0)/32; //6b
  tag = (address & 0xFFFFF800)/2048;   //21b
  bloque_m = (address & 0xFFFFFFE0)/32;



//buit via0
  if(via0[conj_mc]==-1){
    miss = 1;
    via0[conj_mc] = tag;
    replacement = 0;
    lru[conj_mc] = 1;
    via_mc=0;
    ++nmiss;
  }

  else{
    //hit via0
    if(via0[conj_mc]==tag){
      miss = 0;
      replacement = 0;
      lru[conj_mc] = 1;
      via_mc=0;
      ++nhit;
    }

    else{
      //buit via1
      if(via1[conj_mc]==-1){
        miss = 1;
        via1[conj_mc] = tag;
        replacement = 0;
        lru[conj_mc] = 0;
        via_mc=1;
        ++nmiss;

      }

      else{
        //hit via1
        if(via1[conj_mc]==tag){
          miss = 0;
          replacement = 0;
          lru[conj_mc] = 0;
          via_mc=1;
          ++nhit;
        }

        else{
          //via0 es lru
          if(lru[conj_mc]==0){
            miss=1;
            tag_out = via0[conj_mc];
            via0[conj_mc] = tag;
            replacement = 1;
            lru[conj_mc] = 1;
            via_mc=0;
            ++nmiss;

          }
          //via 1 es lru
          else{
            miss=1;
            tag_out = via1[conj_mc];
            via1[conj_mc] = tag;
            replacement = 1;
            lru[conj_mc] = 0;
            via_mc=1;
            ++nmiss;

          }
        }

      }
    }
  }



	/* La funcio test_and_print escriu el resultat de la teva simulacio
	 * per pantalla (si s'escau) i comproba si hi ha algun error
	 * per la referencia actual. Tamb� mesurem el temps d'execuci�
	 * */
	t2=GetTime();
	totaltime+=t2-t1;
	test_and_print2 (address, byte, bloque_m, conj_mc, via_mc, tag,
			miss, replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */
void final ()
{
 	/* Escriu aqui el teu codi */
printf("aciertos: %d fallos: %d",nhit,nmiss);

}
