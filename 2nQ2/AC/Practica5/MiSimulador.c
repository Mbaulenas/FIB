#include "CacheSim.h"

/* Posa aqui les teves estructures de dades globals
 * per mantenir la informacio necesaria de la cache
 * */
unsigned int tags[128];
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
  for(int i = 0; i<128; ++i){
    tags[i]=-1;
  }


}

/* La rutina reference es cridada per cada referencia a simular */
void reference (unsigned int address)
{
	unsigned int byte;
	unsigned int bloque_m;
	unsigned int linea_mc;
	unsigned int tag;
	unsigned int miss;	   // boolea que ens indica si es miss
	unsigned int replacement;  // boolea que indica si es reempla�a una linia valida
	unsigned int tag_out;	   // TAG de la linia reemplaçada
	float t1,t2;		// Variables per mesurar el temps (NO modificar)

	t1=GetTime();
	/* Escriu aqui el teu codi */

  byte = address & 0x1F;      //5b
  linea_mc = (address & 0x00000FE0)/32; //7b
  tag = (address & 0xFFFFF000)/4096;   //20b
  bloque_m = (address & 0xFFFFFFE0)/32;

  //buit
  if (tags[linea_mc]==-1){
    miss = 1;
    tags[linea_mc]=tag;
    replacement = 0;
    ++nmiss;
  }

  else{

      if(tags[linea_mc]!=tag){
        miss = 1;
          tag_out = tags[linea_mc];
        tags[linea_mc]=tag;
        replacement = 1;
        ++nmiss;
      }

      else{
        miss = 0;
        replacement = 0;
        ++nhit;
      }

  }



	/* La funcio test_and_print escriu el resultat de la teva simulacio
	 * per pantalla (si s'escau) i comproba si hi ha algun error
	 * per la referencia actual. Tamb� mesurem el temps d'execuci�
	 * */
	t2=GetTime();

	totaltime+=t2-t1;
	test_and_print (address, byte, bloque_m, linea_mc, tag,
			miss, replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */
void final ()
{
 	/* Escriu aqui el teu codi */
printf("aciertos: %d fallos: %d",nhit,nmiss);

}
