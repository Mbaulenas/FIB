import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;

/**
 * Classe per contenir el mode de joc
 * @author Genis Nin
 * @version 2.0
 */
public class ranking {
    private mode_joc m;
    private String path;

    /**
     * Constructora de la classe ranquing. Crea una instancia de ranking i segons el mode de joc passat com a parametre
     * calcula l'atribut path que conte el cami al fitxer o s'emmagatzema el ranking
     * @param m mode de joc que identifica el ranking
     */
    //Funcio que ens retorna el path al fitxer que conte el ranking per cada mode de joc.
    public  ranking (mode_joc m){
        this.m = m;
        String aux = "RES";
        if (m.isDiagonal()){
            if (m.isHoritzontal()){
               if(m.isVertical()){
                   aux = ("ranks/111.txt");
               }
               else{
                   aux = ("ranks/110.txt");
               }
            }
            else{
                if(m.isVertical()){
                    aux = ("ranks/101.txt");
                }
                else{
                    aux = ("ranks/100.txt");
                }
            }
        }
        else{
            if (m.isHoritzontal()){
                if(m.isVertical()){
                    aux = ("ranks/011.txt");
                }
                else{
                    aux = ("ranks/010.txt");
                }
            }
            else{
                if(m.isVertical()){
                    aux = ("ranks/001.txt");
                }
                //Aquesta opcio no cal ja que mai es donara aquest cas.
            }
        }

       this.path = aux;
    }

    /**
     * Provoca que s'escrigui per pantalla el ranking del mode de joc que identifica el ranking. L'escriptura es fa
     * des de la classe rankpersistent
     */
    public void get_entrada ()  {
        rankpersistent r = new rankpersistent();
        String data = r.llegir_entrada(this.path);
        String [] help = data.split(":");
        for(int i = 0; i < help.length; i+=4){
            System.out.print(help[i] + " ");
            System.out.print(help[i+1] + " ");
            System.out.print(help[i+2] + " ");
            System.out.println(help[i+3] + " ");
        }
    }


    /**
     * Funcio encarregada d'actualitzar el ranking. S'encarrega d'afegir o modificar una entrada en el ranking
     * corresponent segons el nom del jugador, la puntuacio obtinguda i si ha guanyat o perdut
     * @param nom nom del jugador
     * @param puntuacio nombre de fitxes del jugador en el tauler al final de la partida
     * @param victoria boolea que indica si ha guanyat o perdut la partida
     */
    public void actualitzar_ranking(String nom, int puntuacio, boolean victoria){

        String svic = "RES",sder = "RES",spunt = "RES", snom = "RES";
        int vic = 0,der = 0,punt = 0;
        //String nom = "Alejandro";
        //ranking r = new ranking(m);
        //String path = r.getranking(m);
        boolean existeix = false;
        boolean record = true;
        rankpersistent r = new rankpersistent();
        String ajuda = r.llegir_entrada(this.path);
        String [] help = ajuda.split(":");
        for (int i = 0; i < help.length; i+=4){ //Aqui tindriem un for fins a .length
            String data = help[i]; //Agafariem el que hi ha en el vector cada 4 pos
            if (data.equals(nom)){
                svic = help[i+1];
                sder = help[i+2];
                spunt = help[i+3];
                punt = Integer.parseInt(spunt);
                if (punt >= puntuacio) {
                    puntuacio = punt;
                    record = false;
                }
                if (victoria){
                    vic = Integer.parseInt(svic);
                    vic++;
                    svic = Integer.toString(vic);
                }
                else{
                    der = Integer.parseInt(sder);
                    der++;
                    sder = Integer.toString(der);
                }
                existeix = true;
            }

        }

        if (existeix) conte(nom,svic,sder,puntuacio,this.path,record);
        else if (victoria) no_conte(nom,"1","0",puntuacio,this.path);
        else no_conte(nom,"0","1",puntuacio,this.path);

    }

    //Funcio per introduir una entrada al ranking en cas de que el jugador no tingui cap entrada previa al
    //ranking
    //Ja funciona he fet servir dos vectors per poder guardar les dades noves.
    private void no_conte(String nom, String victories, String derrotes, int puntuacio, String path){
        String n = "res",v = "res",d = "res",p = "res";
        int punt_aux = 0;
        rankpersistent r = new rankpersistent();
        String data = r.llegir_entrada(path);
        String vec[] = data.split(":");
        int tam = vec.length + 4;
        String vec_result[] = new String[tam];
        boolean colocat = false;
        int j = 0;
        for (int i = 0; i < vec.length; i += 4){
            n = vec[i];
            v = vec[i+1];
            d = vec[i+2];
            p = vec[i+3];
            punt_aux = Integer.parseInt(p);
            if (punt_aux < puntuacio && !colocat){
                //S'ha de colocar el nou jugador amb els seus parametres abans que el que acabem de llegir
                //en el vector vec_result. Just despres afegim el jugador que acabem de llegir.
                String pp = Integer.toString(puntuacio);
                vec_result[j] = nom;
                vec_result[j+1] = victories;
                vec_result[j+2] = derrotes;

                vec_result[j+3] = pp;

                j+=4;

                vec_result[j] = n;
                vec_result[j+1] = v;
                vec_result[j+2] = d;
                vec_result[j+3] = p;

                colocat = true;

            }
            else{
                vec_result[j] = n;
                vec_result[j+1] = v;
                vec_result[j+2] = d;
                vec_result[j+3] = p;
            }
            j+=4;

        }
        r.escriure_entrada(path,vec_result);
    }


    //Funcio per insertar una entrada al ranking en cas de que el jugador ja tingui una entrada en el
    //ranking
    private void conte(String nom, String victories, String derrotes, int puntuacio, String path, boolean record){
        String n = "res",v = "res",d = "res",p = "res";
        rankpersistent r = new rankpersistent();
        String data = r.llegir_entrada(path);
        String vec[] = data.split(":");
        String vec_result[] = new String[vec.length];
        if (record){
            boolean colocat = false;

            int j = 0;
            for (int i = 0; i < vec.length; i += 4){
                //ojo on es fa l'augment de la j per no saltarnos cap linia
                if (colocat){
                    n = vec[i];
                    if (!n.equals(nom)){
                        vec_result[j] = vec[i];
                        vec_result[j+1] = vec[i+1];
                        vec_result[j+2] = vec[i+2];
                        vec_result[j+3] = vec[i+3];
                        j+=4;
                    }

                }
                else {
                    n = vec[i];
                    if (!n.equals(nom)){
                        v = vec[i+1];
                        d = vec[i+2];
                        p = vec[i+3];
                        int punt = Integer.parseInt(p);
                        if (puntuacio <= punt){
                            vec_result[j] = n;
                            vec_result[j+1] = v;
                            vec_result[j+2] = d;
                            vec_result[j+3] = p;
                            j+=4;
                        }
                        else {
                            String pp = Integer.toString(puntuacio);
                            vec_result[j] = nom;
                            vec_result[j+1] = victories;
                            vec_result[j+2] = derrotes;
                            vec_result[j+3] = pp;
                            colocat = true;
                            j += 4;
                            vec_result[j] = n;
                            vec_result[j+1] = v;
                            vec_result[j+2] = d;
                            vec_result[j+3] = p;
                            j+=4;
                        }
                    }
                    else {
                        String pp = Integer.toString(puntuacio);
                        vec_result[j] = nom;
                        vec_result[j+1] = victories;
                        vec_result[j+2] = derrotes;
                        vec_result[j+3] = pp;
                        colocat = true;
                        j+=4;
                    }

                }

            }

        }
        else {
            //No ha fet record per tant nomes cal actualitzar el numero de victories/derrotes

            /*String n = "res",v = "res",d = "res",p = "res";
            rankpersistent r = new rankpersistent();
            String data = r.llegir_entrada(path);
            String vec[] = data.split(":");*/
            //String vec_result[] = null;

            for (int i = 0; i < vec.length; i += 4){

                n = vec[i];
                v = vec[i+1];
                d = vec[i+2];
                p = vec[i+3];

                if (n.equals(nom)){
                    String pp = Integer.toString(puntuacio);
                    vec_result[i] = nom;
                    vec_result[i+1] = victories;
                    vec_result[i+2] = derrotes;
                    vec_result[i+3] = pp;
                }
                else {
                    vec_result[i] = n;
                    vec_result[i+1] = v;
                    vec_result[i+2] = d;
                    vec_result[i+3] = p;
                }
            }
        }
        r.escriure_entrada(path,vec_result);
    }


    public static void main (String[] args){
        /*boolean diagonal = true;
        boolean horitzontal = false;
        boolean vertical = false;*/
        mode_joc m = new mode_joc ();
        ranking r = new ranking(m);
        jugador j = new jugador();
        j.setUsername("Minimax7");
        String name = j.getUsername();
        boolean victoria = false;
        int puntuacio = 100;
        r.get_entrada();
        r.actualitzar_ranking(name,puntuacio,victoria);
        System.out.println("-----------------------------------------");
        r.get_entrada();
        /*rankpersistent r = new rankpersistent();
        String data = r.llegir_entrada ("ranks/111.txt");
        String aux [] = data.split(":");
        r.escriure_entrada ("ranks/prova.txt", aux);*/

    }
}


