import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;
import java.awt.Point;

public class jugador {

    private String username;

    public jugador (String nom){
        this.username = nom;
    }

    public jugador(){}

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String red) {
        if(!this.isMaquina()) this.username = red;
        else System.out.println("No es pot canviar el nom d'una maquina");
    }

    public boolean isMaquina(){
        return false;
    }


    public static void main (String []args){

        jugador m = new jugador();
    }
}

class maquina extends jugador{
    private int profunditat;
    private String algorisme;

    //Quan creem la maqiuna es posa un nom per defecte que es el nom de l'algorisme i la profunditat
    //No s'ha de poder canviar el nom de la maquina ja que si no no quadrara

    public maquina (int profunditat, String algorisme, String nom){
        super(nom);
        this.profunditat = profunditat;
        this.algorisme = algorisme;

    }

    public int getProfunditat() {
        return profunditat;
    }

    public String getAlgorisme() {
        return algorisme;
    }

/*    private void setAlgorisme(String algorisme) {
        this.algorisme = algorisme;
    }*/

/*    private void setProfunditat(int profunditat) {
        //potser que aquesta funcio desapareixi perque no la necessitem
        //Saura de canviar perque canvii el nom en cas de canviar la profunditat
        this.profunditat = profunditat;
    }*/

    public boolean isMaquina() {
        return true;
    }

    public Point pensa_jugada(boolean max, board t){

        System.out.println("Passo per la funcio pensar jugada que crida a minimax");
        algorisme a = new algorisme();
        int depth = this.profunditat;
        Point b = a.minimax (depth, max, t);
        return b;

    }

}

