import java.awt.Point;

/**
 * Classe per contenir la partida
 * @author Genis Nin
 * @version 1.0
 */
public class partida {
    private  jugador j1;
    private  jugador j2;
    private board t1;
    private mode_joc mj;
    private boolean torn;
    private int num_maquines;
    private boolean ordre; //Si ordre 1 j1 juga blanques, si ordre 0 jugador 1 juga negres


    /**
     * Metode per comprovar de qui es el torn. Si torn es true tiren les blanques, si torn es false tiren les negres
     * @return Un boolea que indica qui tira.
     */
    public boolean isTorn() {
        return torn;
    }

    /**
     * Metode per obtenir el numero de maquines que juguen la partida. De moment pot ser 1 o 0
     * @return Numero de maquines que juguen la partida
     */
    public int getNum_maquines() {
        return num_maquines;
    }


    /**
     * Constructora de la classe partida amb parametres
     * @param J1 jugador 1
     * @param J2 jugador 2
     * @param T tauler
     * @param m mode de joc
     * @param torn boolea que indica qui tira
     * @param num nombre de maquines
     * @param ordre indica si el jugador 1 juga amb blanques o negres
     */
    public partida (jugador J1, jugador J2, board T, mode_joc m, boolean torn, int num, boolean ordre){
        this.j1 = J1;
        this.j2 = J2;
        this.t1 = T;
        this.mj = m;
        this.torn = torn;
        this.num_maquines = num;
        this.ordre = ordre;
    }

    /**
     * Constructora de la classe partida sense parametres.
     * Inicialitza tots els atributs pero no insereix cap valor
     */
    public partida (){
        this.j1 = new jugador();
        this.j2 = new jugador();
        this.t1 = new board();
        this.mj = new mode_joc();
        this.torn = true;
        this.num_maquines = 0;
        this.ordre = true;
    }


    /**
     * Metode que permet al jugador j fer una tirada en el punt a. En cas de que la partida sigui contra una maquina
     * despres de la tirada del jugador j provocara la tirada de la maquina
     * @param a punt en el que es vol posar la fitxa
     * @param j jugador que vol fer la tirada
     */
    public void jugar(Point a,jugador j){

        int k = getNum_maquines();
        char w = 'W';
        char b = 'B';
        int control = 0;
        if (k == 0){
            //Els dos jugadors son persones
            if (torn){
                control = t1.set(a,w);
                //if (jugador pot tirar) si no no canviem el bool
                if (control > 0) torn = false;
            }
            else{
                control = t1.set(a,b);
                //if (jugador pot tirar) si no no canviem el bool
                if (control > 0) torn = true;
            }
        }
        else if (k == 1){

           //Un dels jugadors es una mquina
            if (torn){
                if(j.isMaquina()){
                    System.out.println("La maquina juga amb blanques");
                    boolean max = true;
                    Point po = ((maquina)j).pensa_jugada(max,t1);
                    control = t1.set(po,w);
                    //if (jugadodr pot tirar) si no pot tirar el torn no canvia
                    torn = false;
                }
                else {
                    System.out.println("La persona juga amb blanques");
                    control = t1.set(a,w);
                    if (control > 0){
                        //if (maquinap pot tirar) si no pot tirar no canviem el bool i no la fem tirar
                        torn = false;
                        Point res = new Point();
                        jugar (res,this.j2);
                    }
                }
            }
            else{

                if (!j.isMaquina()){
                    System.out.println("La persona juga amb negres");

                    control = control = t1.set(a,b);
                    if (control > 0) {
                        //if (maquina pot tirar) si no no canviem el bool i no la fem tirar
                        torn = true;
                        Point res = new Point();
                        jugar (res,this.j2);
                    }

                }
                else {
                    System.out.println("La maquina juga amb negres");
                    boolean max = false;
                    Point po = ((maquina)j).pensa_jugada(max,t1);
                    control = t1.set(po,w);
                    //if (jugador pot tirar) si no pot tirar no canviem el bool
                    torn = true;
                }
            }
        }
    }

    /**
     * Metode que provoca la primera tirada de la maquina en cas de jugar contra la maquina si aquesta juga amb blanques.
     */
    public void ini_partida (){
        if(this.num_maquines == 1){
            if (!this.ordre){
                //Juga una persona contra la maquina i la maquina juga amb blanques.
                //Cal fer la primera tira de la maquina
                Point p = new Point();
                this.jugar(p,this.j2);

            }
        }

    }

    public static void main (String[] args){
       /* jugador h1 = new jugador();
        jugador h2 = new jugador();
        tauler t = new tauler();
        mode_joc jj = new mode_joc();
        boolean ayuda = true;*/
      /*  partida p = new partida();
        partida bona = p.ini_partida();
        Point po = new Point();
        bona.jugar(po,p.j1);*/

    }
}