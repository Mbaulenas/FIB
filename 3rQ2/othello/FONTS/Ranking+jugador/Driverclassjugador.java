import java.awt.Point;

public class Driverclassjugador{

    void testConstructoraJugador(){
        System.out.println("Prem 1 per provar la costructora sense parametres o 2 per la constructora amb parametres");
        System.out.println("Qualsevol altre parametre per sortir del test");
        String aux = System.console().readLine();
        if(aux.equals("1")){
            jugador j = new jugador();
            System.out.println("El jugador s'ha creat pero no te cap username");
        }
        else if (aux.equals("2")){
            System.out.println("Introdueix el nom del jugador");
            String data = System.console().readLine();
            jugador j = new jugador(data);
            System.out.print("El jugador s'ha creat i te com a username: ");
            System.out.println(data);
        }
        System.out.println("---------------------------------------------");
    }

    void testConstructoraMaquina(){
        boolean control2 = true;
        while(control2){
            System.out.println("Escull l'algorisme conta el que vols jugar entre els seguents seleccionant el numero");
            System.out.println("1 -> Minimax");
            String aux = System.console().readLine();
            if (aux.equals("1")){

                boolean control = true;
                while(control){
                    System.out.println("Escull la profunditat de l'algorisme entrant un numro de l'1 al 10");
                    String aux2 = System.console().readLine();
                    int prof = 0;
                    try{
                        prof = Integer.parseInt(aux2);
                    }
                    catch (NumberFormatException e1){
                        System.out.println("El caracter entrat no es un numero");
                    }

                    if (prof > 0 && prof < 11){
                       // this.algorisme = "Minimax";
                        String name = "Minimax" + aux2;
                       // this.setUsername(name);
                        control = false;
                        control2 = false;

                        maquina m = new maquina(prof,"Minimax",name);

                        System.out.println("Despres de crear la instancia de maquina els atributs queden de la seguent manera");
                        System.out.print("El nom de l'algorisme utilitzat es: ");
                        String prova = m.getAlgorisme();
                        System.out.println(prova);
                        System.out.print("El nom d'usuari que rep la maquina es: ");
                        prova = m.getUsername();
                        System.out.println(prova);
                        System.out.print("La profunditat escollida per l'algorisme es: ");
                        int prova2 = m.getProfunditat();
                        System.out.println(prova2);
                        System.out.println("---------------------------------------------");
                    }
                    else {
                        System.out.println("La valor entrat de profunditat no es un valor correcte");
                    }
                }

            }
            else{
                System.out.println("El valor entrat no es un dels valors de la llista");
            }
        }

    }

    void testSetiGetUsernameJugador(){
        System.out.println("Creem un jugador sense parametres");
        jugador j = new jugador();
        System.out.println("Escull un nom per al jugador");
        String aux = System.console().readLine();
        j.setUsername(aux);
        System.out.print("El nom que ha rebut el jugador es: ");
        System.out.println(j.getUsername());
        System.out.println("---------------------------------------------");
    }

    void testGetAlgrismProfunditMaquina(){
        System.out.println("Creem una maquina amb els seguents parametres:");
        System.out.println("Algorisme -> Minimax");
        System.out.println("Profunditat -> 7");
        System.out.println("Username -> Minimax7");
        maquina m = new maquina(7,"Minimax","Minimax7");
        System.out.print("La funcio getAlgorisme ens retorna: ");
        System.out.println(m.getAlgorisme());
        System.out.print("La funcio getProfunditat ens retorna: ");
        System.out.println(m.getProfunditat());
        System.out.print("La funcio getUsername ens retorna: ");
        System.out.println(m.getUsername());
        System.out.println("---------------------------------------------");
    }

    void testIsMaquina(){
        System.out.println("Per provar la funcio amb un jugador prem 1 per provar-la amb una maquina prem 2");
        String aux = System.console().readLine();
        if(aux.equals("1")){
            jugador j = new jugador();
            System.out.println("Si es maquina retorna true i si es jugador retorna false");
            if(j.isMaquina()) System.out.println("True");
            else System.out.println("False");
        }
        else if (aux.equals("2")){
            jugador m = new maquina(7,"Minimax","Minimax7");
            System.out.println("Si es maquina retorna true i si es jugador retorna false");
            if(m.isMaquina()) System.out.println("True");
            else System.out.println("False");
        }
        System.out.println("---------------------------------------------");
    }

    void testPensaJugada(){
        System.out.println("Aquesta funcio es l'encarregada de cridar a minimax amb els seus parametres");
        System.out.println("Els parametres que s'han de passar a la funcio son un boolean idicant si juges blanques (true) o negres (false)");
        System.out.println("Indica si vols jugar blanques -> 1 o negres -> 2");
        String aux = System.console().readLine();
        //System.out.println("Tambe cal passar un tauler");
       // System.out.println("La funcio retorna un punt");
        jugador m = new maquina (7,"Minimax","Minimax7");
        tauler t = new tauler();
        boolean aux2 = true;
        if (aux.equals("2")) aux2 = false;
        Point p = ((maquina)m).pensa_jugada(aux2,t);
        System.out.print("El valor retornat com a punt es: ");
        System.out.println("(" + p.x + "," + p.y + ")");
        System.out.println("---------------------------------------------");
    }


    //Encara cal que les funcions es puguin provar en bucle i fer el main corresponent.
    public static void main (String []args){
        Driverclassjugador d = new Driverclassjugador();
        boolean control = true;
        while(control){
            System.out.println("Driver per provar el funcionament de les classes jugador i maquina");
            System.out.println("Entra el numero corresponent per provar la funcio desitjada");
            System.out.println("1 -> Constructora jugador");
            System.out.println("2 -> Constructora maquina");
            System.out.println("3 -> Set i get username en l'objecte jugador");
            System.out.println("4 -> Get algorisme i profunditat de l'objecte maquina");
            System.out.println("5 -> Is maquina");
            System.out.println("6 -> Pensa jugada");
            System.out.println("Qualsevol altre entrada per sortir del driver");
            String aux = System.console().readLine();
            if (aux.equals("1")){
                d.testConstructoraJugador();
            }
            else if (aux.equals("2")){
                d.testConstructoraMaquina();
            }
            else if (aux.equals("3")){
                d.testSetiGetUsernameJugador();
            }
            else if (aux.equals("4")){
                d.testGetAlgrismProfunditMaquina();
            }
            else if (aux.equals("5")){
                d.testIsMaquina();
            }
            else if (aux.equals ("6")){
                d.testPensaJugada();
            }
            else {
                control = false;
            }
        }
    }

}