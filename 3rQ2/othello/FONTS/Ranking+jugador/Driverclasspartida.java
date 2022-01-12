import java.awt.Point;
public class Driverclasspartida {

    //Es una funcio auxiliar per escriure per pantalla com queda el mode de joc
    public void escriu_mj(mode_joc m){
        if (m.isDiagonal()){
            System.out.println("Es diagonal");
            if (m.isHoritzontal()){
                System.out.println("Es horitzontal");
                if (m.isVertical()){
                    System.out.println("Es vertical");
                }
                else{
                    System.out.println("No es vertical");
                }
            }
            else {
                System.out.println("No es horitzontal");
                if (m.isVertical()){
                    System.out.println("Es vertical");
                }
                else{
                    System.out.println("No es vertical");
                }
            }

        }
        else {
            System.out.println("No es diagonal");
            if (m.isHoritzontal()){
                System.out.println("Es horitzontal");
                if (m.isVertical()){
                    System.out.println("Es vertical");
                }
                else{
                    System.out.println("No es vertical");
                }
            }
            else {
                System.out.println("No es horitzontal");
                if (m.isVertical()){
                    System.out.println("Es vertical");
                }
                else{
                    System.out.println("No es vertical");
                }
            }

        }
        System.out.println("-------------------------------------------------------");
    }

    //Ens permet provar la creadora sense parametres
    public void TestCreadoraSP(){
        System.out.println("Es crea una instancia de partida amb els parametres buits");
        partida p = new partida();
        System.out.println("----------------------------------------------------------");
    }

    //De moment encara quedaria poder escollir un tauler per defecte o un dels creats per l'usuari
    public void TestCreadoraAP(){
        System.out.println("Es crea una instancia de partida amb els parametres que indicarem a continuacio");
        System.out.println("Escull si vols jugar contra un altre jugador o contra la maquina");
        System.out.println("1 -> jugador vs jugador");
        System.out.println("0 -> jugador vs maquina");
        String data2 = System.console().readLine();
        partida p = new partida();
        boolean ordre = true;
        board t1 = new board();
        int maq = -1;

        if (data2.equals("1")){
            maq = 0;
            //Persona contra persona
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador(nom_j1);
            System.out.println("Introduix nom del jugador 2: ");
            String nom_j2 = System.console().readLine();
            jugador j2 = new jugador(nom_j2);
            System.out.println ("Escull si vols jugar amb blanques o amb negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            //Aquest bool el fem servir per inicialitzar el torn
            boolean nrot = true;

            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;
            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);

            System.out.println ("Per comprovar que el funcionament es correcte anem a veure com ha quedat la instancia de partida que acabem de crear");
            System.out.println ("Has escollit jugar contra un altre jugador");
            System.out.print ("El nom del jugador 1 es: ");
            System.out.println (j1.getUsername());
            System.out.print ("El nom del jugador 2 es: ");
            System.out.println (j2.getUsername());
            if (ordre) System.out.println ("Has escollit jugar amb blanques");
            else System.out.println ("Has escollit jugar amb negres");
            System.out.println ("Les normes de captura que has escollit son: ");
            escriu_mj(m);
        }
        else{
            //Persona contra maquina
            maq = 1;
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador (nom_j1);
            //Aqui s'ha de deixar escollir els parametres de la maquina dintre dels possibles
            jugador j2 = new maquina(7,"Minimax","Minimax7");
            System.out.println ("Escull si vols jugar amb blanques o negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;

            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);

            boolean nrot = true;
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);
            System.out.println ("Per comprovar que el funcionament es correcte anem a veure com ha quedat la instancia de partida que acabem de crear");
            System.out.println("Has escollit jugar contra la maquina");
            System.out.print ("El nom del jugador 1 es: ");
            System.out.println (j1.getUsername());
            System.out.print ("El nom de la maquina contra la que jugues es: ");
            System.out.println (j2.getUsername());
            if (ordre) System.out.println ("Has escollit jugar amb blanques");
            else System.out.println ("Has escollit jugar amb negres");
            System.out.println ("Les normes de captura que has escollit son: ");
            escriu_mj(m);
        }
    }

    //Ens serveix per provar la funcio ini_partida que nomes actua en cas de que la maquina jugui amb blanques
    public void Testinipartida(){
        System.out.println("Aquesta funcio nomes dona resultat si la maquina juga amb blanques");
        System.out.println("Per a comprovar el funcionament crearem una partida i just despres executarem ini_partida");
        System.out.println("Per a crear la partida farem servir el mateix metode que en el test de la creadora amb parametres");

        System.out.println("Es crea una instancia de partida amb els parametres que indicarem a continuacio");
        System.out.println("Escull si vols jugar contra un altre jugador o contra la maquina");
        System.out.println("1 -> jugador vs jugador");
        System.out.println("0 -> jugador vs maquina");
        String data2 = System.console().readLine();
        partida p = new partida();
        boolean ordre = true;
        board t1 = new board();
        int maq = -1;

        if (data2.equals("1")){
            maq = 0;
            //Persona contra persona
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador(nom_j1);
            System.out.println("Introduix nom del jugador 2: ");
            String nom_j2 = System.console().readLine();
            jugador j2 = new jugador(nom_j2);
            System.out.println ("Escull si vols jugar amb blanques o amb negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            //Aquest bool el fem servir per inicialitzar el torn
            boolean nrot = true;

            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;
            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);
            p.ini_partida();
        }
        else{
            //Persona contra maquina
            maq = 1;
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador (nom_j1);
            //Aqui s'ha de deixar escollir els parametres de la maquina dintre dels possibles
            jugador j2 = new maquina(7,"Minimax","Minimax7");
            System.out.println ("Escull si vols jugar amb blanques o negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;

            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);

            boolean nrot = true;
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);
            p.ini_partida();
        }
    }

    //Ens permet realitzar una partida de prova per comprovar la funcio jugar
    //Aquesta funcio tambe ens permet provar la funcio isTorn
    public void Testjugar(){
        System.out.println("Per provar aquesta funcio crearem una instancia de partida i provarem de jugar");
        System.out.println("Es crea una instancia de partida amb els parametres que indicarem a continuacio");
        System.out.println("Escull si vols jugar contra un altre jugador o contra la maquina");
        System.out.println("1 -> jugador vs jugador");
        System.out.println("0 -> jugador vs maquina");
        String data2 = System.console().readLine();
        partida p = new partida();
        boolean ordre = true;
        board t1 = new board();
        int maq = -1;

        if (data2.equals("1")){
            maq = 0;
            //Persona contra persona
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador(nom_j1);
            System.out.println("Introduix nom del jugador 2: ");
            String nom_j2 = System.console().readLine();
            jugador j2 = new jugador(nom_j2);
            System.out.println ("Escull si vols jugar amb blanques o amb negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            //Aquest bool el fem servir per inicialitzar el torn
            boolean nrot = true;

            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;
            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);
            p.ini_partida();
            boolean control = true;
            while (control){
                boolean torn = p.isTorn();
                Point punt = new Point();
                if(torn){
                    System.out.print("Torn del jugador 1: ");
                    System.out.println(j1.getUsername());
                    System.out.println("Prem m per a simular una tirada, qualsevol altre caracter per sortir");
                    String tirada = System.console().readLine();
                    if (tirada.equals("m")) p.jugar(punt,j1);
                    else control = false;
                }
                else {
                    System.out.print("Torn del jugador 2: ");
                    System.out.println(j2.getUsername());
                    System.out.println("Prem m per a simular una tirada, qualsevol altre caracter per sortir");
                    String tirada = System.console().readLine();
                    if (tirada.equals("m")) p.jugar(punt,j1);
                    else control = false;
                }

            }
        }
        else{
            //Persona contra maquina
            maq = 1;
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador (nom_j1);
            //Aqui s'ha de deixar escollir els parametres de la maquina dintre dels possibles
            jugador j2 = new maquina(7,"Minimax","Minimax7");
            System.out.println ("Escull si vols jugar amb blanques o negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;

            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);

            boolean nrot = true;
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);
            p.ini_partida();
            boolean control = true;
            while (control){
                boolean torn = p.isTorn();
                Point punt = new Point();
                if(torn){
                    System.out.print("Torn del jugador 1: ");
                    System.out.println(j1.getUsername());
                    System.out.println("Prem m per a simular una tirada, qualsevol altre caracter per sortir");
                    String tirada = System.console().readLine();
                    if (tirada.equals("m")) p.jugar(punt,j1);
                    else control = false;

                }
                else {
                    System.out.print("Torn del jugador 2: ");
                    System.out.println(j1.getUsername());
                    System.out.println("Prem m per a simular una tirada, qualsevol altre caracter per sortir");
                    String tirada = System.console().readLine();
                    if (tirada.equals("m")) p.jugar(punt,j1);
                    else control = false;
                }

            }
        }
    }

    public void TesGetNumMaquines(){
        System.out.println("Per a provar aquesta funcio crearem una instancia de partida com hem fet anteriorment");
        System.out.println("En cas de jugar contra la maquina en ha de retornar un 1 i en cas de jugar contra una persona un 0");


        System.out.println("Escull si vols jugar contra un altre jugador o contra la maquina");
        System.out.println("1 -> jugador vs jugador");
        System.out.println("0 -> jugador vs maquina");
        String data2 = System.console().readLine();
        partida p = new partida();
        boolean ordre = true;
        board t1 = new board();
        int maq = -1;

        if (data2.equals("1")){
            maq = 0;
            //Persona contra persona
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador(nom_j1);
            System.out.println("Introduix nom del jugador 2: ");
            String nom_j2 = System.console().readLine();
            jugador j2 = new jugador(nom_j2);
            System.out.println ("Escull si vols jugar amb blanques o amb negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            //Aquest bool el fem servir per inicialitzar el torn
            boolean nrot = true;

            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;
            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);

            int cnot = p.getNum_maquines();
            if (cnot == 0)System.out.println ("Estem jugant jugador contra jugador");
            else System.out.println ("Estem jugant jugador contra maquina");
        }
        else{
            //Persona contra maquina
            maq = 1;
            System.out.println("Introduix nom del jugador 1: ");
            String nom_j1 = System.console().readLine();
            jugador j1 = new jugador (nom_j1);
            //Aqui s'ha de deixar escollir els parametres de la maquina dintre dels possibles
            jugador j2 = new maquina(7,"Minimax","Minimax7");
            System.out.println ("Escull si vols jugar amb blanques o negres");
            System.out.println("W -> Si vols jugar blanques");
            System.out.println("B -> Si vols jugar negres");
            data2 = System.console().readLine();
            if (data2.equals("W")){
                //J1 juga amb blanques
                ordre = true;
            }
            else{
                //J1 juga amb negres
                ordre = false;
            }
            System.out.println("Per ultim escull les opcions de captura per a la partida");

            boolean diagonal;
            boolean horitzontal;
            boolean vertical;
            String data = "res";
            System.out.println("Introdueix 1 per habilitar el mode de captura i 0 per deshabilitar-lo");
            System.out.println("Diagonal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) diagonal = true;
            else diagonal = false;

            System.out.println("Horitzontal -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) horitzontal = true;
            else horitzontal = false;

            System.out.println("Vertical -> 1 o 0");
            data = System.console().readLine();
            if (data.equals("1")) vertical  = true;
            else vertical = false;

            mode_joc m = new mode_joc (diagonal,horitzontal,vertical);

            boolean nrot = true;
            p = new partida (j1,j2,t1,m,nrot,maq,ordre);

            int cnot = p.getNum_maquines();
            if (cnot == 0)System.out.println ("Estem jugant jugador contra jugador");
            else System.out.println ("Estem jugant jugador contra maquina");
        }
    }

    public static void main (String [] args){
        Driverclasspartida d = new Driverclasspartida();
        boolean control = true;
        while(control){
            System.out.println("Selecciona quin metode vols provar inserint el numero corresponent, qualsevol altre caracter per sortir del driver");
            System.out.println("1 -> Test creadora sense parametres");
            System.out.println("2 -> Test creadora amb parametres");
            System.out.println("3 -> Test ini_partida");
            System.out.println("4 -> Test jugar");
            System.out.println("5 -> Test get num maquines");
            String data = System.console().readLine();
            if(data.equals("1")) d.TestCreadoraSP();
            else if (data.equals("2")) d.TestCreadoraAP();
            else if (data.equals("3")) d.Testinipartida();
            else if (data.equals("4")) d.Testjugar();
            else if (data.equals("5")) d.TesGetNumMaquines();
            else control = false;
        }
    }
}