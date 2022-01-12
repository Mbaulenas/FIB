#include "CacheSim.h"

/* Posa aqui les teves estructures de dades globals
 * per mantenir la informacio necesaria de la cache
 * */

	unsigned int tags[128];
	unsigned int dirty[128];
	unsigned int nhit = 0;
	unsigned int nmiss = 0;


/* La rutina init_cache es cridadunsigned int tags[128];a pel programa principal per
 * inicialitzar la cache.
 * La cache es inicialitzada al comen�ar cada un dels tests.
 * */
void init_cache ()
{
	/* Escriu aqui el teu codi */

	for(int i = 0; i<128; ++i){
		tags[i]=-1;
		dirty[i]=0;
	}

}

/* La rutina reference es cridada per cada referencia a simular */
void reference (unsigned int address, unsigned int LE)
{
	unsigned int byte;
	unsigned int bloque_m;
	unsigned int linea_mc;
	unsigned int tag;
	unsigned int miss;
	unsigned int lec_mp;
	unsigned int mida_lec_mp;
	unsigned int esc_mp;
	unsigned int mida_esc_mp;
	unsigned int replacement;
	unsigned int tag_out;

	/* Escriu aqui el teu codi */

	byte = address & 0x1F;      //5b
	linea_mc = (address & 0x00000FE0)/32; //7b
	tag = (address & 0xFFFFF000)/4096;   //20b
	bloque_m = (address & 0xFFFFFFE0)/32;

	//lectura
	if(LE == 0){

		//buit
		if (tags[linea_mc]==-1){
			miss = 1;
			++nmiss;
			tags[linea_mc]=tag;

			lec_mp = 1;
			mida_lec_mp = 32;

			esc_mp = 0;

			replacement = 0;
		}

		else{
			//miss
				if(tags[linea_mc]!=tag){
					miss = 1;
					++nmiss;
					tag_out = tags[linea_mc];
					tags[linea_mc]=tag;
					replacement = 1;


					lec_mp = 1;
					mida_lec_mp = 32;

					if(dirty[linea_mc]!=0){
						esc_mp = 1;
						mida_esc_mp = 32;
					}
					else{
						esc_mp = 0;
					}
					dirty[linea_mc] = 0;
				}

				else{
					miss = 0;
					++nhit;
					replacement = 0;
					lec_mp = 0;
					esc_mp = 0;
				}

		}

	}


	//escrpitura
	else{
		//buit
		if (tags[linea_mc]==-1){
			miss = 1;
			++nmiss;
			tags[linea_mc]=tag;

			lec_mp = 1;
			mida_lec_mp = 32;

			esc_mp = 0;
			++dirty[linea_mc];
			replacement = 0;
		}

		else{

		//miss
		if(tags[linea_mc]!=tag){
			miss = 1;
			++nmiss;
			replacement = 1;
			lec_mp = 1;
			mida_lec_mp = 32;

			tag_out = tags[linea_mc];

				if(dirty[linea_mc]!=0){
					esc_mp = 1;
					mida_esc_mp = 32;
				}

				else{
					esc_mp = 0;
				}

			tags[linea_mc]=tag;
			dirty[linea_mc] = 0;
			++dirty[linea_mc];

		}

		//hit
		else{
			miss = 0;
			++nhit;
			replacement = 0;
			lec_mp = 0;
			esc_mp = 0;
			++dirty[linea_mc];
		}
	}
	}













	/* La funcio test_and_print escriu el resultat de la teva simulacio
	 * per pantalla (si s'escau) i comproba si hi ha algun error
	 * per la referencia actual
	 * */
	test_and_print (address, LE, byte, bloque_m, linea_mc, tag,
			miss, lec_mp, mida_lec_mp, esc_mp, mida_esc_mp,
			replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */
void final ()
{
 	/* Escriu aqui el teu codi */

	printf("aciertos: %d fallos: %d",nhit,nmiss);


}
