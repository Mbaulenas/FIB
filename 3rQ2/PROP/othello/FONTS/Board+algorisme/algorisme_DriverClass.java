import java.awt.Point;

public class algorisme_DriverClass{


    public static void main(String args[]){

        algorisme_DriverClass t = new algorisme_DriverClass();

        System.out.println("Driver de la classe algorisme");


        String entrada = "x";


        while(!entrada.equals("E")){

            System.out.println("Escriu el número del test a executar (1-3), 'E' per sortir o 'H' per veure la descripció dels tests");

            entrada = System.console().readLine();

            System.out.println();

            if(entrada.equals("H")) t.help();

            if(entrada.equals("1")) t.test1();

            if(entrada.equals("2")) t.test2();

            if(entrada.equals("3")) t.test3();

            System.out.println();
            System.out.println();

        }

    }

    //MAN
    void help(){

        String entrada = "x";


        while(!entrada.equals("q")){

            System.out.println("Escriu el número del test a consultar (1-3) o 'q' per sortir");

            entrada = System.console().readLine();

            if(entrada.equals("1")) {
                System.out.println("Donat un tauler inicial carregat desde el directori /boards, un color per jugar i una profunditat mostra quin moviment es jugaria i l'estat final del tauler");
                System.out.println("Útil per veure decisions de l'algorisme i detectar errors");
            }

            if(entrada.equals("2")) {
                System.out.println("Donat un tauler i totes les direccions de captura activades, s'entra un valor per la profunditat de l'algorisme minimax de cada color.");
                System.out.println("Per cada cop que entrem una 'm' veurem avançar un moviment la partida");
                System.out.println("Útil per veure les decisions de l'algorisme respecte la seva profunditat");
            }

            if(entrada.equals("3")) {
                System.out.println("Simulació d'una partida entre dos algorismes minimax de profunditats donades");
                System.out.println("Al acabar la partida podem veure el tauler final i el recompte de fitxes");
                System.out.println("Útil per comparar profunditats i com afecten a la partida");
            }


        }
    }

    //UN MOVIMENT DONAT UN TAULER EN CONCRET
    void test1(){

        GameMode gm = new GameMode(true,true,true);
        algorisme A = new algorisme();


        System.out.println("Test n1");
        System.out.println("Creem un tauler amb totes les direccions de captura activades. Les files i columnes estàn numerades del 0 al 7");
        //System.out.println("Comprovem que donat un tauler, minimax retorna una casella vàlida");

        System.out.println("Entra el nom del fitxer amb el tauler a carregar, guardat a /boards");
        String fitxer = System.console().readLine();
        Board test = new Board(fitxer, gm);

        System.out.println("Estat inicial del tauler:");
        test.print();
        System.out.println();

        System.out.println("Entra 'B' si el proper moviment és de blanques o 'N' si és de negres");
        String color = System.console().readLine();
        boolean blanques = (color.equals("B"));
        System.out.println();

        System.out.println("Entra un valor per la profunditat de l'algorisme minimax");
        int prof = Integer.parseInt(System.console().readLine());
        System.out.println();

        Point p = A.minimax(prof, blanques, test);

        if(p.x!=-1){
            System.out.println("La casella es: " + p.x + " " +  p.y);
            if(blanques)  prof = test.set(p.x, p.y, 'W');
            else  prof = test.set(p.x, p.y, 'B');
        }
        else System.out.println("No hi ha cap moviment disponible");


        System.out.println("Estat final del tauler:");
        test.print();
        System.out.println();


    }

    //MOVIMENT A MOVIMENT
    void test2(){

        GameMode gm = new GameMode(true,true,true);
        Board test = new Board(gm);
        algorisme A = new algorisme();
        Point p = new Point();

        String accio ="x";
        boolean blanques = true;

        System.out.println("Test n2");
        System.out.println("Creem un tauler amb totes les direccions de captura activades.");

        System.out.println("Entra un valor per la profunditat de l'algorisme minimax de les blanques:");
        int profW = Integer.parseInt(System.console().readLine());

        System.out.println("Entra un valor per la profunditat de l'algorisme minimax de les negres:");
        int profB = Integer.parseInt(System.console().readLine());

        while(!accio.equals("q")){

            System.out.println("Estat actual del tauler:");
            test.print();
            System.out.println();

            System.out.print("Proper moviment: ");
            if(blanques) System.out.println("blanques");
            else System.out.println("negres");

            System.out.println("Fitxes blanques: " + test.pieces(0) + "Fitxes negres: " + test.pieces(1));

            System.out.println("Introdueix 'm' per jugar un altre moviment al tauler actual o 'q' per sortir");
            accio = System.console().readLine();

            if(accio.equals("m")){

                if(blanques) {
                    p = A.minimax(profW, true, test);
                    if(p.x!=-1) test.set(p.x,p.y,'W');
                    else
                        System.out.println("Les blanques no tenen cap tirada");
                }

                else{
                    p = A.minimax(profB, false, test);
                    if(p.x!=-1) test.set(p.x,p.y,'B');
                    else System.out.println("Les negres no tenen cap tirada");
                }
                blanques = !blanques;
            }


        }


    }

    //SIMULAR PARTIDA
    void test3(){

        GameMode gm = new GameMode(true,true,true);
        Board test = new Board(gm);
        algorisme A = new algorisme();
        Point p = new Point(0,0);

        boolean blanques = true;
        boolean aux = false;
        boolean end = false;
        System.out.println("Test n3");
        System.out.println("Creem un tauler amb totes les direccions de captura activades");

        System.out.println("Entra un valor per la profunditat de l'algorisme minimax de les blanques:");
        int profW = Integer.parseInt(System.console().readLine());

        System.out.println("Entra un valor per la profunditat de l'algorisme minimax de les negres:");
        int profB = Integer.parseInt(System.console().readLine());

        while(!end){

            if(blanques) {

                p = A.minimax(profW, true, test);

                if(p.x!=-1){
                    test.set(p.x,p.y,'W');
                    if(aux) aux=!aux;
                }

                else{
                    //System.out.println("Les blanques no tenen cap tirada");
                    if (!aux) aux = true;
                    else end = true;
                }

            }

            else{
                p = A.minimax(profB, false, test);
                if(p.x!=-1) {
                    test.set(p.x,p.y,'B');
                    if(aux) aux=!aux;
                }

                else{
                    if (!aux) aux = true;
                    else end = true;
                }
            }
            blanques = !blanques;
        }

        System.out.println("Estat final del tauler:");
        test.print();

        System.out.println("Fitxes blanques: " + test.pieces(0) + "Fitxes negres: " + test.pieces(1));

    }


}