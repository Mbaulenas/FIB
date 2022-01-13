package Domini;
import java.util.*;
import javafx.util.*;

public class Quadrat {

    // variable tipus es una variable de estados, sirve para identificar 
    //si la celda es blanca == 0,
    // de restriccion de fila == 1,
    // de restriccion columna = 2
    // de doble restriccion == 3
    // negra == 4.
    private int tipus;

    //distancia tindra valor valid per als cuadrats amb restriccions
    //representen la distancia de la restriccio de fila per els cuadrats amb restriccio de fila i aix� mateix amb la les del columnes
    private int distanciaF;
    private int distanciaC;

    // si blanca: numeros[0] = numero que volem, numeros[1] = null.
    // si restriccio: numeros[0] = restriccio fila, numeros[1] = restriccio columna
    // si negra: tots els valors nulls = 0;
    private int [] numeros = new int[2];

    //Representa el sac de restriccions de la fila de cada Cuadrat blanc.
    private SortedSet<Integer> RestFila = new TreeSet<Integer>();

    //Representa el sac de restriccions de la fila de cada Cuadrat blanc.
    private SortedSet<Integer> RestColumna = new TreeSet<Integer>();

    //coordenades de la cela al Kakuro
    private Pair<Integer,Integer> coordenades = new Pair<Integer,Integer>(-1,-1);

    //pair que amb les coordenades de la anterior casella negra de la fila
    private Pair<Integer,Integer> sumaF = new Pair<Integer,Integer>(-1,-1);

    //pair que amb les coordenades de la anterior casella negra de la columna
    private Pair<Integer,Integer> sumaC = new Pair<Integer,Integer>(-1,-1);

    public Quadrat() {
        this.tipus = 0;
        this.distanciaF = 0;
        this.distanciaC = 0;
    }

    //retorna el tipus de Cuadrat que es tracta la casella impl�cita.
    public int getTipus() { return tipus; }

    //Dona valor tipus a la Cuadrat impl�cit.
    public void setTipus(int tipus) {
        this.tipus = tipus;
    }

    //Retorna la distancia de restriccio de fila de Cuadrat que es tracta la casella impl�cita. Per tenir valor v�lid la casella ha de ser amb restricci� de fila o doble restricci�.
    public int getDistanciaF() { return distanciaF; }

    //Dona valor a la distancia de la restriccio de la casella negra de restriccio de fila o de doble restriccio.
    public void setDistanciaF(int distancia) { this.distanciaF = distancia; }

    //Retorna la distancia de restriccio de columna de Cuadrat que es tracta la casella impl�cita. Per tenir valor v�lid la casella ha de ser amb restricci� de columna o doble restricci�.
    public int getDistanciaC() { return distanciaC; }

    //Dona valor a la distancia de la restriccio de la casella negra de restriccio de columna o de doble restriccio.
    public void setDistanciaC(int distanciaC) { this.distanciaC = distanciaC; }

    //Retorna el primer valor del vector de numeros del parametre implicit.
    public int getPrimerNum() {
        return numeros[0];
    }

    //Retorna el segon valor del vector de numeros del parametre implicit.
    public int getSegonNum() {
        return numeros[1];
    }

    //Dona valor num al primer valor del vector numeros del parametre implicit.
    public void setPrimerNum(int num) {
        this.numeros[0] = num;
    }

    //Dona valor num al segon valor del vector numeros del parametre implicit.
    public void setSegonNum(int num) {
        this.numeros[1] = num;
    }

    //Retorna el sac de restriccions de la fila de cada Cuadrat blanc.
    public SortedSet<Integer> getRestFila() {
        return RestFila;
    }

    //Retorna el sac de restriccions de la columna de cada Cuadrat blanc.
    public SortedSet<Integer> getRestColumna() {
        return RestColumna;
    }

    //Elimina el parametre valor de la restriccio de fila del parametre implicit  en cas de que existeixi.
    public void eliminar_de_f(int valor) {
        RestFila.remove(valor);
    }
    //Elimina el parametre valor de la restriccio de columna del parametre implicit  en cas de que existeixi.
    public void eliminar_de_c(int valor) {
        RestColumna.remove(valor);
    }

    //Retorna True en cas que i sigui present dins de del sac de restriccions de fila del parametre implicit. False en cas contrari.
    public boolean getRestFila(int i) {
        return RestFila.contains(i);
    }

    //Retorna True en cas que i sigui present dins del sac de restriccions de columna del parametre impl�cit. False en cas contrari.
    public boolean getRestColumna(int i) {
        return RestColumna.contains(i);
    }

    // Dona valor s al Sorted Set de columna del parametre implicit.
    public void setRestColumna(SortedSet<Integer> s) {
        this.RestColumna = s;
    }
    
    // Dona valor s al Sorted Set de fila del parametre implicit.
    public void setRestFila(SortedSet<Integer> s) {
        this.RestFila = s;
    }

    //Retorna el array numeros
    public int[] getNumeros() {
        return numeros;
    }

    //Dona els valors de num al array numeros
    public void setNumeros(int[] num) {
        this.numeros = num;
    }

    //Retorna les coordenades del cuadrat
    public Pair<Integer,Integer> getCoordenades() { return coordenades; }

    //Posa a l'atribut coordenades n i m
    public void setCoordenades(int n, int m) {
        Pair<Integer,Integer> aux = new Pair<Integer,Integer>(n,m);
        this.coordenades = aux;
    }

    //Retorna les coordenades de la casella negra amb restriccio de la fila a la que pertany el cuadrat
    public Pair<Integer,Integer> getSumaF() { return sumaF; }

    //Posa l'atribut sumaF igual a n i m
    public void setSumaF(int n, int m) {
        Pair<Integer,Integer> aux = new Pair<Integer,Integer>(n,m);
        this.sumaF = aux;
    }

    //Retorna les coordenades de la casella negra amb restriccio de la columna a la que pertany el cuadrat
    public Pair<Integer,Integer> getSumaC() { return sumaC; }

    //Posa l'atribut sumaC igual a n i m
    public void setSumaC(int n, int m) {
        Pair<Integer,Integer> aux = new Pair<Integer,Integer>(n, m);
        this.sumaC = aux;
    }
}