import java.util.*;
import java.awt.Point;

/** Classe per contenir l'algorisme
 * @author Marc Baulenas Gallego
 * @version 1.0
 *
 *
 * */
public class algorisme {

    public static void main(String args[]) {



    }

    /**Mètode públic, calcula el moviment usant minimax. Es genera una ArrayList amb tots el moviments possibles i es crida el mètode privat recursiu minimax_rec per obtenir la posició del millor moviment dins del ArrayList.
     * @param depth La profunditat amb la que es vol cridar minimax.
     * @param Max True si volem que retorni un moviment per les blanques, false per negres.
     * @param t Tauler actual de la partida
     * @return Retorna un Point amb les coordenades del moviment a jugar, o amb (-1,-1) si no hi ha cap moviment*/

    public Point minimax(int depth, boolean Max, Board t) {



        ArrayList<Point> moviments = new ArrayList<Point>();
        moviments = t.available_positions();

        int puntuacio = minimax_rec(depth, Max, 0, t);


        if(puntuacio==-1) return new Point(-1,-1); //si retorna el codi arbitrari es que no hi ha cap moviment.

        Point jugada = moviments.get(puntuacio);

        return jugada;

    }

    /**Mètode privat recursiu, calcula cada tauler possible fins a la profunditat màxima. Al arribar al cas base retorna el valor de la heurística fins a h==0, que seleccionarà el moviment amb valor més alt si Max i el valor més baix si !Max.
     * @param depth La profunditat amb la que es vol cridar minimax.
     * @param Max True si volem que retorni un moviment per les blanques, false per negres.
     * @param h Profunditat actual de l'algorisme. Si h==depth, cas base.
     * @param t Tauler actual de la partida
     * @return Retorna la posició del moviment òptim dins del ArrayList amb tots el moviments disponibles, o -1 en cas de que no hi hagi cap moviment disponible */

    private int minimax_rec(int depth, boolean Max, int h, Board t){

        //cas base
        if(depth==h){
            return t.pieces(0)-t.pieces(1);
        }

        ArrayList<Point> moviments = new ArrayList<Point>();
        moviments = t.available_positions();


        int puntuacio;
        int optima = -1;


        if(Max){

            puntuacio = -100;

            for(int i = 0; i<moviments.size(); ++i){

                Board nextmove = new Board(t);
                int jugada = nextmove.set(moviments.get(i).x, moviments.get(i).y, 'W');

                if(jugada!=0) {



                    int aux = minimax_rec(depth, false, h + 1, nextmove);


                        if (puntuacio <= aux ) {
                            puntuacio = aux;
                            optima = i;

                        }

                }

            }
            
            if(h!=0) return puntuacio;

        }

        //En cas de que busquem la heuristica minima

        else{

            puntuacio = 100;

            for(int i = 0; i<moviments.size(); ++i){

                Board nextmove = new Board(t);
                int jugada = nextmove.set(moviments.get(i).x, moviments.get(i).y, 'B');

                if(jugada!=0) {

                    int aux = minimax_rec(depth, true, h + 1, nextmove);

                        if (puntuacio >= aux) {
                            puntuacio = aux;
                            optima = i;
                        }
                   
                }
            }


            if(h!=0) return puntuacio;

        }

        
        return optima;
    }


}



