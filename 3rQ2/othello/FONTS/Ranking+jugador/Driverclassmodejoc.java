public class Driverclassmodejoc {

    //Aquesta funcio nomes la fem servir per escriure com queda el mode de joc
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

    //Test de la constructora sense parametres
    public void testConstructor_sp(){
        System.out.println("La creadora sense parametres posa tots els modes de captura en true");
        mode_joc m = new mode_joc();
        escriu_mj(m);
    }

    //Test de la constructora amb parametres
    public void testConstructor_ap(){
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
        escriu_mj(m);

    }

    //Test de set parametre horitzontal
    public void testSet(){
        System.out.println("Per provar aquesta funcio crearem un mode de joc amb parametres i modificarem");
        System.out.println("els parametres fent servir les funcions set");

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
        escriu_mj(m);
        boolean mode = false;
        boolean control = true;

        while (control == true){
            System.out.println("Quin dels parametres vols modificar?");
            System.out.println("diagonal -> d, horitzontal -> h o vertical -> v");
            System.out.println("Qualsevol altre valor per sortir de la prova");
            data = System.console().readLine();
            System.out.println("Vols habilitar -> 1 o no deshabilitar -> 0 aquest mode de captura?");
            String aux = System.console().readLine();

            if (data.equals("d")){
                if (aux.equals("1")){
                    mode = true;
                    m.setDiagonal(mode);
                }
                else{
                    mode = false;
                    m.setDiagonal(mode);
                }
            }
            else if (data.equals("h")){
                if (aux.equals("1")){
                    mode = true;
                    m.setHoritzontal(mode);
                }
                else{
                    mode = false;
                    m.setHoritzontal(mode);
                }
            }
            else if (data.equals("v")){
                if (aux.equals("1")){
                    mode = true;
                    m.setVertical(mode);
                }
                else{
                    mode = false;
                    m.setVertical(mode);
                }
            }
            else {
                control = false;
            }
            System.out.println("Els parametres de captura queden de la seguent manera");
            escriu_mj(m);

        }

    }


    //Tot el que esta comentat es perque no se si cal provar-ho ja que ho faig servir en la funcio
    //que faig servir per escriure com queda el mode de joc i ja es pot verue que funciona alli.

 /*   //Test de comprobacio de si horitzotal
    public boolean testIsHoritzontal(mode_joc m){
        boolean aux = m.isHoritzontal();
        return aux;
    }

    //Test de comprobacio de si diagonal
    public boolean testIsDiagonal(mode_joc m){
        boolean aux = m.isDiagonal();
        return aux;
    }

    //Test de comprobacio de si vertical
    public boolean testIsVertical(mode_joc m){
        boolean aux = m.isVertical();
        return aux;
    }*/

    public static void main (String []args){
        Driverclassmodejoc D = new Driverclassmodejoc();
        boolean control = true;
        while (control){
            System.out.println("Driver per provar els metodes de la classe mode_joc");
            System.out.println("Selecciona quin metode vols provar:");
            System.out.println("1 -> creadora sense parametres");
            System.out.println("2 -> creadora amb parametres");
            System.out.println("3 -> modificador dels parametres");
            System.out.println("qualsevol altre valor -> sortir del driver");
            String data = System.console().readLine();
            if (data.equals("1")){
                D.testConstructor_sp();
            }
            else if (data.equals("2")){
                D.testConstructor_ap();
            }
            else if (data.equals("3")){
                D.testSet();
            }
            else {
                control = false;
            }
        }
    }
}