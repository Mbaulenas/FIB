package Domini;
import java.util.*;


import javafx.util.Pair;

//COMENTARI DE CLASSE:
//Aquesta classe representa un kakuro i els atributs. Conte totes les funcionalitats relacionades amb un kakuro.

public class Kakuro{

    private String difi;
    private int mida_col;
    private int mida_fila;
    private int caselles_blanques;
    private int caselles_negres;
    private String[][] Kakuro;
    private ArrayList<ArrayList<Quadrat>> M = new ArrayList<>();

    //Des de mat2 fins a mat8 estan totes les combinacions per posibles per omplir files i columnes de qualsevol kakuro.
    static int[][] mat2 = {
        {1,2},
        {1,3},
        {1,4,2,3},
        {1,5,2,4},
        {1,6,2,5,3,4},
        {1,7,2,6,3,5},
        {1,8,2,7,3,6,4,5},
        {1,9,2,8,3,7,4,6},
        {2,9,3,8,4,7,5,6},
        {3,9,4,8,5,7},
        {4,9,5,8,6,7},
        {5,9,6,8},
        {6,9,7,8},
        {7,9},
        {8,9}};
        
    static int[][] mat3 = {
        {1,2,3},
        {1,2,4},
        {1,2,5,1,3,4},
        {1,2,6,1,3,5,2,3,4},
        {1,2,7,1,3,6,1,4,5,2,3,5},
        {1,2,8,1,3,7,1,4,6,2,3,6,2,4,5},
        {1,2,9,1,3,8,1,4,7,1,5,6,2,3,7,2,4,6,3,4,5},
        {1,3,9,1,4,8,1,5,7,2,3,8,2,4,7,2,5,6,3,4,6},
        {1,4,9,1,5,8,1,6,7,2,3,9,2,4,8,2,5,7,3,4,7,3,5,6},
        {1,5,9,1,6,8,2,4,9,2,5,8,2,6,7,3,4,8,3,5,7,4,5,6},
        {1,6,9,1,7,8,2,5,9,2,6,8,3,4,9,3,5,8,3,6,7,4,5,7},
        {1,7,9,2,6,9,2,7,8,3,5,9,3,6,8,4,5,8,4,6,7},
        {1,8,9,2,7,9,3,6,9,3,7,8,4,5,9,4,6,8,5,6,7},
        {2,8,9,3,7,9,4,6,9,4,7,8,5,6,8},
        {3,8,9,4,7,9,5,6,9,5,7,8},
        {4,8,9,5,7,9,6,7,8},
        {5,8,9,6,7,9},
        {6,8,9},
        {7,8,9}};
        
    static int[][] mat4 = {
                {1,2,3,4}, 
                {1,2,3,5},
                {1,2,3,6,1,2,4,5},
                {1,2,3,7,1,2,4,6,1,3,4,5},
                {1,2,3,8,1,2,4,7,1,2,5,6,1,3,4,6,2,3,4,5},
                {1,2,3,9,1,2,4,8,1,2,5,7,1,3,4,7,1,3,5,6,2,3,4,6},
                {1,2,4,9,1,2,5,8,1,2,6,7,1,3,4,8,1,3,5,7,1,4,5,6,2,3,4,7,2,3,5,6},
                {1,2,5,9,1,2,6,8,1,3,4,9,1,3,5,8,1,3,6,7,1,4,5,7,2,3,4,8,2,3,5,7,2,4,5,6},
                {1,2,6,9,1,2,7,8,1,3,5,9,1,3,6,8,1,4,5,8,1,4,6,7,2,3,4,9,2,3,5,8,2,3,6,7,2,4,5,7,3,4,5,6},
                {1,2,7,9,1,3,6,9,1,3,7,8,1,4,5,9,1,4,6,8,1,5,6,7,2,3,5,9,2,3,6,8,2,4,5,8,2,4,6,7,3,4,5,7},
                {1,2,8,9,1,3,7,9,1,4,6,9,1,4,7,8,1,5,6,8,2,3,6,9,2,3,7,8,2,4,5,9,2,4,6,8,2,5,6,7,3,4,5,8,3,4,6,7},
                {1,3,8,9,1,4,7,9,1,5,6,9,1,5,7,8,2,3,7,9,2,4,6,9,2,4,7,8,2,5,6,8,3,4,5,9,3,4,6,8,3,5,6,7},
                {1,4,8,9,1,5,7,9,1,6,7,8,2,3,8,9,2,4,7,9,2,5,6,9,2,5,7,8,3,4,6,9,3,4,7,8,3,5,6,8,4,5,6,7},
                {1,5,8,9,1,6,7,9,2,4,8,9,2,5,7,9,2,6,7,8,3,4,7,9,3,5,6,9,3,5,7,8,4,5,6,8},
                {1,6,8,9,2,5,8,9,2,6,7,9,3,4,8,9,3,5,7,9,3,6,7,8,4,5,6,9,4,5,7,8},
                {1,7,8,9,2,6,8,9,3,5,8,9,3,6,7,9,4,5,7,9,4,6,7,8},
                {2,7,8,9,3,6,8,9,4,5,8,9,4,6,7,9,5,6,7,8},
                {3,7,8,9,4,6,8,9,5,6,7,9},
                {4,7,8,9,5,6,8,9},
                {5,7,8,9},
                {6,7,8,9}};
        
    static int[][] mat5 = {
                {1,2,3,4,5}, 
                {1,2,3,4,6},
                {1,2,3,4,7,1,2,3,5,6},
                {1,2,3,4,8,1,2,3,5,7,1,2,4,5,6},
                {1,2,3,4,9,1,2,3,5,8,1,2,3,6,7,1,2,4,5,7,1,3,4,5,6},
                {1,2,3,5,9,1,2,3,6,8,1,2,4,5,8,1,2,4,6,7,1,3,4,5,7,2,3,4,5,6},
                {1,2,3,6,9,1,2,3,7,8,1,2,4,5,9,1,2,4,6,8,1,2,5,6,7,1,3,4,5,8,1,3,4,6,7,2,3,4,5,7},
                {1,2,3,7,9,1,2,4,6,9,1,2,4,7,8,1,2,5,6,8,1,3,4,5,9,1,3,4,6,8,1,3,5,6,7,2,3,4,5,8,2,3,4,6,7},
                {1,2,3,8,9,1,2,4,7,9,1,2,5,6,9,1,2,5,7,8,1,3,4,6,9,1,3,4,7,8,1,3,5,6,8,1,4,5,6,7,2,3,4,5,9,2,3,4,6,8,2,3,5,6,7},
                {1,2,4,8,9,1,2,5,7,9,1,2,6,7,8,1,3,4,7,9,1,3,5,6,9,1,3,5,7,8,1,4,5,6,8,2,3,4,6,9,2,3,4,7,8,2,3,5,6,8,2,4,5,6,7},
                {1,2,5,8,9,1,2,6,7,9,1,3,4,8,9,1,3,5,7,9,1,3,6,7,8,1,4,5,6,9,1,4,5,7,8,2,3,4,7,9,2,3,5,6,9,2,3,5,7,8,2,4,5,6,8,3,4,5,6,7},
                {1,2,6,8,9,1,3,5,8,9,1,3,6,7,9,1,4,5,7,9,1,4,6,7,8,2,3,4,8,9,2,3,5,7,9,2,3,6,7,8,2,4,5,6,9,2,4,5,7,8,3,4,5,6,8},
                {1,2,7,8,9,1,3,6,8,9,1,4,5,8,9,1,4,6,7,9,1,5,6,7,8,2,3,5,8,9,2,3,6,7,9,2,4,5,7,9,2,4,6,7,8,3,4,5,6,9,3,4,5,7,8},
                {1,3,7,8,9,1,4,6,8,9,1,5,6,7,9,2,3,6,8,9,2,4,5,8,9,2,4,6,7,9,2,5,6,7,8,3,4,5,7,9,3,4,6,7,8},
                {1,4,7,8,9,1,5,6,8,9,2,3,7,8,9,2,4,6,8,9,2,5,6,7,9,3,4,5,8,9,3,4,6,7,9,3,5,6,7,8},
                {1,5,7,8,9,2,4,7,8,9,2,5,6,8,9,3,4,6,8,9,3,5,6,7,9,4,5,6,7,8},
                {1,6,7,8,9,2,5,7,8,9,3,4,7,8,9,3,5,6,8,9,4,5,6,7,9},
                {2,6,7,8,9,3,5,7,8,9,4,5,6,8,9},
                {3,6,7,8,9,4,5,7,8,9},
                {4,6,7,8,9},
                {5,6,7,8,9}};
        
    static int[][] mat6 = {
                {1,2,3,4,5,6},
                {1,2,3,4,5,7},
                {1,2,3,4,5,8,1,2,3,4,6,7},
                {1,2,3,4,5,9,1,2,3,4,6,8,1,2,3,5,6,7},
                {1,2,3,4,6,9,1,2,3,4,7,8,1,2,3,5,6,8,1,2,4,5,6,7},
                {1,2,3,4,7,9,1,2,3,5,6,9,1,2,3,5,7,8,1,2,4,5,6,8,1,3,4,5,6,7},
                {1,2,3,4,8,9,1,2,3,5,7,9,1,2,3,6,7,8,1,2,4,5,6,9,1,2,4,5,7,8,1,3,4,5,6,8,2,3,4,5,6,7},
                {1,2,3,5,8,9,1,2,3,6,7,9,1,2,4,5,7,9,1,2,4,6,7,8,1,3,4,5,6,9,1,3,4,5,7,8,2,3,4,5,6,8},
                {1,2,3,6,8,9,1,2,4,5,8,9,1,2,4,6,7,9,1,2,5,6,7,8,1,3,4,5,7,9,1,3,4,6,7,8,2,3,4,5,6,9,2,3,4,5,7,8},
                {1,2,3,7,8,9,1,2,4,6,8,9,1,2,5,6,7,9,1,3,4,5,8,9,1,3,4,6,7,9,1,3,5,6,7,8,2,3,4,5,7,9,2,3,4,6,7,8},
                {1,2,4,7,8,9,1,2,5,6,8,9,1,3,4,6,8,9,1,3,5,6,7,9,1,4,5,6,7,8,2,3,4,5,8,9,2,3,4,6,7,9,2,3,5,6,7,8},
                {1,2,5,7,8,9,1,3,4,7,8,9,1,3,5,6,8,9,1,4,5,6,7,9,2,3,4,6,8,9,2,3,5,6,7,9,2,4,5,6,7,8},
                {1,2,6,7,8,9,1,3,5,7,8,9,1,4,5,6,8,9,2,3,4,7,8,9,2,3,5,6,8,9,2,4,5,6,7,9,3,4,5,6,7,8},
                {1,3,6,7,8,9,1,4,5,7,8,9,2,3,5,7,8,9,2,4,5,6,8,9,3,4,5,6,7,9},
                {1,4,6,7,8,9,2,3,6,7,8,9,2,4,5,7,8,9,3,4,5,6,8,9},
                {1,5,6,7,8,9,2,4,6,7,8,9,3,4,5,7,8,9},
                {2,5,6,7,8,9,3,4,6,7,8,9},
                {3,5,6,7,8,9},
                {4,5,6,7,8,9}};
        
    static int[][] mat7 = {
                {1,2,3,4,5,6,7}, 
                {1,2,3,4,5,6,8},
                {1,2,3,4,5,6,9,1,2,3,4,5,7,8},
                {1,2,3,4,5,7,9,1,2,3,4,6,7,8},
                {1,2,3,4,5,8,9,1,2,3,4,6,7,9,1,2,3,5,6,7,8},
                {1,2,3,4,6,8,9,1,2,3,5,6,7,9,1,2,4,5,6,7,8},
                {1,2,3,4,7,8,9,1,2,3,5,6,8,9,1,2,4,5,6,7,9,1,3,4,5,6,7,8},
                {1,2,3,5,7,8,9,1,2,4,5,6,8,9,1,3,4,5,6,7,9,2,3,4,5,6,7,8},
                {1,2,3,6,7,8,9,1,2,4,5,7,8,9,1,3,4,5,6,8,9,2,3,4,5,6,7,9},
                {1,2,4,6,7,8,9,1,3,4,5,7,8,9,2,3,4,5,6,8,9},
                {1,2,5,6,7,8,9,1,3,4,6,7,8,9,2,3,4,5,7,8,9},
                {1,3,5,6,7,8,9,2,3,4,6,7,8,9},
                {1,4,5,6,7,8,9,2,3,5,6,7,8,9},
                {2,4,5,6,7,8,9},
                {3,4,5,6,7,8,9}};
        
    static int[][] mat8 = {
                {1,2,3,4,5,6,7,8}, 
                {1,2,3,4,5,6,7,9},
                {1,2,3,4,5,6,8,9},
                {1,2,3,4,5,7,8,9},
                {1,2,3,4,6,7,8,9},
                {1,2,3,5,6,7,8,9},
                {1,2,4,5,6,7,8,9},
                {1,3,4,5,6,7,8,9},
                {2,3,4,5,6,7,8,9}};
        
    static int[][] mat9 = {{1,2,3,4,5,6,7,8,9}};

    //Aquesta funcio que retorna el numero de caselles blanques que hi ha en un kakuro.
    private int numBlanques(){
        int n = 0;
        for(int i = 0; i < Kakuro.length; ++i){
            for(int j = 0; j < Kakuro[i].length; j+=2){
                if(Kakuro[i][j] == "?") ++n;
            }
        }
        return n;
    }

    //Aquesta funcio retorna les coordenades de la casella negra que restringeix una fila de caselles blanques.
    private Pair<Integer,Integer> getNegraFila(int i, int j){
        Pair<Integer,Integer> p = new Pair<Integer,Integer>(-1,-1);
        boolean found = false;
        for(int k = j-2; k >= 0 && !found; k-=2){
            if(Kakuro[i][k].contains("F")){
                Pair<Integer,Integer> aux = new Pair<Integer,Integer>(i,k);
                p = aux;
                found = true;
            }
        }
        return p;
    }

    //Aquesta funcio retorna les coordenades de la casella negra que restringeix una columna de caselles blanques.
    private Pair<Integer,Integer> getNegraCol(int i, int j){
        Pair<Integer,Integer> p = new Pair<Integer,Integer>(-1,-1);
        boolean found = false;
        for(int k = i-1; k >= 0 && !found; --k){
            if(Kakuro[k][j].contains("C")){
                Pair<Integer,Integer> aux = new Pair<Integer,Integer>(k,j);
                p = aux;
                found = true;
            }
        }
        return p;
    }

    //Aquesta funcio retorna la longitud de la fila de caselles blanques on es troba una casella blanca determinada.
    private int getDistF(int i, int j){
        boolean found = false;
        int cont = 0;
        for(int k = j+2; k < Kakuro[i].length && !found; k+=2){
            String s = Kakuro[i][k];
            if(s.charAt(0) != 'C' && s.charAt(0) != 'F' && s.charAt(0) != '*') ++cont;
            else found = true;
        }
        return cont;
    }

    //Aquesta funcio retorna la longitud de la columna de caselles blanques on es troba una casella blanca determinada.
    private int getDistC(int i, int j){
        boolean found = false;
        int cont = 0;
        for(int k = i+1; k < Kakuro.length  && !found; k++){
            String s = Kakuro[k][j];
            if(s.charAt(0) == '?') ++cont;
            else if (s.charAt(0) != 'C' || s.charAt(0) != 'F' || s.charAt(0) != '*') found = true;
        }
        return cont;
    }

    //Aquesta funcio retorna un kakuro en forma d'una matriu d'strings.
    public String[][] getKakuro(){
        return this.Kakuro;
    }

    //Aquesta funcio es la constructora d'un kakuro buit.
    public Kakuro(){
        this.Kakuro = new String[0][0];
    }

    //Aquesta funcio es un constructor que crea un objecte Kakuro a partir d'una matriu d'strings.
    public Kakuro(String[][] s){
        this.Kakuro = s;
    }

    //Aquesta funcio es un constructor que crea un objecte Kakuro a partir d'una altre objecte Kakuro.
    public Kakuro(Kakuro k){
        this.Kakuro = k.getKakuro();
    }

    //Aquesta funcio retorna un array d'objectes Quadrat de totes les caselles blanques d'un kakuro.
    private Quadrat[] getBlanques(){
        Quadrat[] blanques = new Quadrat[numBlanques()];
        int[] aux = {0,0};
        int cont = 0;

        for(int i = 0; i < Kakuro.length; ++i){
            for(int j = 0; j < Kakuro[i].length; j+=2){
                if(Kakuro[i][j] == "?"){
                    Quadrat c = new Quadrat();
                    Pair<Integer,Integer> fil = getNegraFila(i,j);
                    Pair<Integer,Integer> col = getNegraCol(i,j);

                    c.setTipus(0);
                    c.setNumeros(aux);
                    c.setCoordenades(i,j);
                    c.setSumaF(fil.getKey(),fil.getValue());
                    c.setSumaC(col.getKey(),col.getValue());

                    blanques[cont] = c;
                    ++cont;
                }
            }
        }
        return blanques;
    }


    //Aquesta funcio retorna un vector d'objectes Quadrat de totes les caselles negres amb al menys una restriccio.
    private Vector<Quadrat> getNombres(){
        Vector<Quadrat> v = new Vector<Quadrat>(0);
        for(int i = 0; i < Kakuro.length; ++i){
            for(int j = 0; j < Kakuro[i].length; j+=2){
                if(Kakuro[i][j].contains("F") || Kakuro[i][j].contains("C")){
                    Quadrat c = new Quadrat();
                    if(Kakuro[i][j].contains("F") && Kakuro[i][j].contains("C")){
                        c.setTipus(2);

                        String k = Kakuro[i][j];
                        String x = "";
                        String y = "";
                        boolean found = false;
                        for(int t = 1; t < k.length(); t++){
                            if(k.charAt(t) != 'F' && !found) x = x+k.charAt(t);
                            else if(k.charAt(t) != 'F' && found) y = y+k.charAt(t);
                            else found = true;
                        }
                        int a = Integer.parseInt(x);
                        int u = Integer.parseInt(y);
                        int[] aux = {a,u};

                        c.setNumeros(aux);
                        c.setCoordenades(i,j);
                        c.setDistanciaF(getDistF(i,j));
                        c.setDistanciaC(getDistC(i,j));

                        v.add(c);
                    }
                    else if(Kakuro[i][j].contains("C")){
                        c.setTipus(1);

                        String k = Kakuro[i][j];
                        String y = "";
                        for(int t = 1; t < k.length(); t++) y = y+k.charAt(t);

                        int u = Integer.parseInt(y);
                        int[] aux = {u,0};

                        c.setNumeros(aux);
                        c.setCoordenades(i,j);
                        c.setDistanciaC(getDistC(i,j));

                        v.add(c);
                    }
                    else if(Kakuro[i][j].contains("F")){
                        c.setTipus(1);

                        String k = Kakuro[i][j];
                        String y = "";
                        for(int t = 1; t < k.length(); t++) y = y+k.charAt(t);

                        int u = Integer.parseInt(y);
                        int[] aux = {0,u};

                        c.setNumeros(aux);
                        c.setCoordenades(i,j);
                        c.setDistanciaF(getDistF(i,j));

                        v.add(c);
                    }
                }
            }
        }
        return v;
    }

    //Aquesta funcio coloca un numero en una casella blanca d'un kakuro.
    private void setNumero(int n, Pair<Integer,Integer> p){
        String l = Integer.toString(n);
        Kakuro[p.getKey()][p.getValue()] = l;
    }

    //Aquesta funcio retorna el valor d'una casella blanca d'un kakuro en forma d'enter.
    private int getValor(int i, int j){
        int n = 0;
        if(Kakuro[i][j] != "?") n = Integer.parseInt(Kakuro[i][j]);
        return n;
    }

    //Aquesta funcio retorna el valor de la casella negra amb restriccio de la fila d'una casella blanca concreta.
    private int getNombreF(int i, int j){
        int n = 0;
        String k = Kakuro[i][j];
        String x = "";
        boolean found = false;
        for(int t = 0; t < k.length(); t++){ //C7F12
            if(k.charAt(t) != 'F' && found) x = x+k.charAt(t);
            else if(k.charAt(t) == 'F') found = true;
        }
        n = Integer.parseInt(x);
        return n;
    }

    //Aquesta funcio retorna el valor de la casella negra amb restriccio de la columna d'una casella blanca concreta.
    private int getNombreC(int i, int j){

        int n = 0;
        String k = Kakuro[i][j];
        String x = "";
        boolean found = false;
        for(int t = 1; t < k.length() && !found; t++){
            if(k.charAt(t) != 'F') x = x+k.charAt(t);
            else found = true;
        }
        n = Integer.parseInt(x);

        return n;

    }

    //Aquesta funcio comprova si un enter es troba a un array o no; retorna true si hi es, altrament false.
    private static boolean posicio(int x, int[] v){
        boolean trobat = true;
        for(int i = 0; i < v.length; ++i){
            if(x == v[i]) return false;
        }
        return trobat;
    }

    //Aquesta funcio comprova si un numero compleix les restriccions d'una casella blanca que no sigui la ultima per omplir d'una fila o columna.
    private static boolean condicions_kakuro(int sum, int[] v, int valor) {
        if(posicio(valor, v)){
            int aux = valor;
            int cont = 0;
            for (int i = 0; i < v.length; ++i){ 
                if(v[i] == 0) ++cont;
                aux += v[i];
            }        
            if(aux < sum && cont > 1) return true;
        }
        return false;
    }

    //Aquest funcio comprova si un numero compleix les restriccions de la ultima casella blanca que queda per omplir a una fila o columna.
    private static boolean condicions_kakuro_complert(int sum, int[] v, int valor) {
        if(posicio(valor, v)){
            int aux = valor;
            int cont = 0;
            for (int i = 0; i < v.length; ++i){ 
                if(v[i] == 0) ++cont;
                aux += v[i];
            }
            if(cont == 1 && aux == sum) return true;          
        }
        return false;
    }

    //Retorna un vector que representa la fila on es troba una determinada casella blanca.
    private static int[] getRunF(Kakuro k, Pair<Integer,Integer> pos, Vector<Quadrat> nombres) {
        int dist = 0;
        for(Quadrat c: nombres) {
            if(pos.getKey() == c.getCoordenades().getKey() && pos.getValue() == c.getCoordenades().getValue()) {
                dist = c.getDistanciaF();
            }
        }
        int[] v = new int[dist];
        int j = 0;
        for(int i = pos.getValue()+2; i < pos.getValue()+dist*2+2; i+=2) {
            v[j] = k.getValor(pos.getKey(), i);
            ++j;
            
        }
        return v;
    }

    //Retorna un vector que representa la columna on es troba una determinada casella blanca.
    private static int[] getRunC(Kakuro k, Pair<Integer,Integer> pos, Vector<Quadrat> nombres) {
        int dist = 0;
        for(Quadrat c: nombres) {
            if(pos.getKey() == c.getCoordenades().getKey() && pos.getValue() == c.getCoordenades().getValue()) {
                dist = c.getDistanciaC();
            }
        }
        int[] v = new int[dist];
        int j = 0;
        for(int i = pos.getKey()+1; i < pos.getKey()+dist+1; ++i) {
            v[j] = k.getValor(i, pos.getValue());
            ++j;
        }
        return v;
    }

    //Aquesta funcio retorna una matriu on cada fila es una combinacio de numeros de l'1 al 9 per a sumar sum.
    public static int[][] getSac(int sum, int l){
        int[][] ret = new int[0][0];
        if(l == 2){
            int files = (mat2[sum-3].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat2[sum-3][k];
                    ++k;
                }
            }
        }
        else if(l == 3){
            int files = (mat3[sum-6].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat3[sum-6][k];
                    ++k;
                }
            }
        }
        else if(l == 4){

            int files = (mat4[sum-10].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat4[sum-10][k];
                    ++k;
                }
            }
        }
        else if(l == 5){
            int files = (mat5[sum-15].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat5[sum-15][k];
                    ++k;
                }
            }
        }
        else if(l == 6){
            int files = (mat6[sum-21].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat6[sum-21][k];
                    ++k;
                }
            }
        }
        else if(l == 7){
            int files = (mat7[sum-28].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){

                    ret[i][j] = mat7[sum-28][k];
                    ++k;
                }
            }
        }
        else if(l == 8){
            int files = (mat8[sum-36].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat8[sum-36][k];
                    ++k;
                }
            }
        }
        else if(l == 9){
            int files = (mat9[0].length) / l;
            ret = new int[files][l];
            int k = 0;
            for(int i = 0; i < ret.length; ++i){
                for(int j = 0; j < ret[i].length; ++j){
                    ret[i][j] = mat5[sum-15][k];
                    ++k;
                }
            }
        }
        return ret;
    }

    //Aquesta funcio retorna un vector de booleans el qual indica els numeros que poden omplir una casella blanca determinada respectant les seves restriccions.
    //Una posicio concreta que sigui true es el valor del numero (menys 1) que pot anar a la casella.
    private boolean[] getPossibles(int fRes, int colRes, int fSize, int colSize){
        boolean[] ret = {false,false,false,false,false,false,false,false,false};
        int[][] fil = getSac(fRes, fSize);
        int[][] col = getSac(colRes, colSize);
        for(int i = 0; i < fil.length; ++i){
            for(int j = 0; j < fil[0].length; ++j){
                ret[fil[i][j]-1] = true;
            }
        }
        for(int i = 0; i < col.length; ++i){
            for(int j = 0; j < col[0].length; ++j){
                if(!ret[col[i][j]-1]) ret[col[i][j]-1] = false;
            }
        }
        return ret;
    }

    //Aquesta funcio executa l'algorisme per resoldre kakuros; retorna un objecte kakuro resolt.
    public Kakuro res_kakuro() {
        Quadrat[] blanques = getBlanques();
        Vector<Quadrat> nombres = getNombres();
        
        Kakuro current = new Kakuro(this.Kakuro);
        Stack<Kakuro> bueno = new Stack<Kakuro>();
        bueno.push(current);

        int it = 0;
        Quadrat cela = blanques[it];
        int valor = 0;

        while (it < blanques.length) {
            int[] aux = new int[2];
            aux[0] =  valor+1;
            aux[1] = 0;
            cela.setNumeros(aux);

            int[] v;

            int nombreF = -1;
            Pair<Integer,Integer> negraF = cela.getSumaF();
            nombreF = current.getNombreF(negraF.getKey(), negraF.getValue());
            v = getRunF(bueno.peek(), negraF, nombres);
            int distF = v.length;
            boolean cumpleF = condicions_kakuro(nombreF, v, cela.getPrimerNum());
            boolean cumpleFS = condicions_kakuro_complert(nombreF, v, cela.getPrimerNum());

            int nombreC = -1;
            Pair<Integer,Integer> negraC = cela.getSumaC();
            nombreC = current.getNombreC(negraC.getKey(), negraC.getValue());
            v = getRunC(bueno.peek(), negraC, nombres);
            boolean cumpleC = condicions_kakuro(nombreC, v, cela.getPrimerNum());
            int distC = v.length;
            boolean cumpleCS = condicions_kakuro_complert(nombreC, v, cela.getPrimerNum());

            boolean[] prob = getPossibles(nombreF, nombreC, distF, distC);

            if (prob[valor] && ((cumpleC && cumpleF) || (cumpleFS && cumpleC) || (cumpleCS && cumpleF || (cumpleCS && cumpleFS)))) { 
                current.setNumero(valor+1, cela.getCoordenades());
                bueno.push(current);

                if (it < blanques.length) { 
                    ++it;
                    if(it != blanques.length)cela = blanques[it];
                    else if (it < 0) break; 
                }
                valor = 0;
            }
            else if (valor < 8) {
                ++valor;
                while(!prob[valor] && valor < 8) ++valor;
            }
            else {
                current = bueno.pop();
                --it;
                cela = blanques[it];
                Pair <Integer,Integer> p = cela.getCoordenades();
                valor = current.getValor(p.getKey(),p.getValue());
                --valor;
                while (valor == 8) {
                    current.setNumero(0, cela.getCoordenades());
                    current = bueno.pop();
                    --it;
                    cela = blanques[it];
                    p = cela.getCoordenades();
                    valor = current.getValor(p.getKey(),p.getValue());
                    --valor;
                    current.setNumero(0, cela.getCoordenades());
                }
                ++valor;
                current.setNumero(0, cela.getCoordenades());
            }
        }
        return current;
    }

    //Aquesta funcio resol un kakuro i retorna el temps emprat en mil·lisegons.
    public long res_kakuro_temps() {
        long startTime = System.nanoTime();

        Quadrat[] blanques = getBlanques();
        Vector<Quadrat> nombres = getNombres();

        Kakuro current = new Kakuro(this.Kakuro);
        Stack<Kakuro> bueno = new Stack<Kakuro>();
        bueno.push(current);

        int it = 0;
        Quadrat cela = blanques[it];
        int valor = 0;

        while (it < blanques.length) {
            int[] aux = new int[2];
            aux[0] =  valor+1;
            aux[1] = 0;
            cela.setNumeros(aux);

            int[] v;

            int nombreF = -1;
            Pair<Integer,Integer> negraF = cela.getSumaF();
            nombreF = current.getNombreF(negraF.getKey(), negraF.getValue());
            v = getRunF(bueno.peek(), negraF, nombres);
            int distF = v.length;
            boolean cumpleF = condicions_kakuro(nombreF, v, cela.getPrimerNum());
            boolean cumpleFS = condicions_kakuro_complert(nombreF, v, cela.getPrimerNum());

            int nombreC = -1;
            Pair<Integer,Integer> negraC = cela.getSumaC();
            nombreC = current.getNombreC(negraC.getKey(), negraC.getValue());
            v = getRunC(bueno.peek(), negraC, nombres);
            boolean cumpleC = condicions_kakuro(nombreC, v, cela.getPrimerNum());
            int distC = v.length;
            boolean cumpleCS = condicions_kakuro_complert(nombreC, v, cela.getPrimerNum());

            boolean[] prob = getPossibles(nombreF, nombreC, distF, distC);

            if (prob[valor] && ((cumpleC && cumpleF) || (cumpleFS && cumpleC) || (cumpleCS && cumpleF || (cumpleCS && cumpleFS)))) {
                current.setNumero(valor+1, cela.getCoordenades());
                bueno.push(current);

                if (it < blanques.length) {
                    ++it;
                    if(it != blanques.length)cela = blanques[it];
                    else if (it < 0) break;
                }
                valor = 0;
            }
            else if (valor < 8) {
                ++valor;
                while(!prob[valor] && valor < 8) ++valor;
            }
            else {
                current = bueno.pop();
                --it;
                cela = blanques[it];
                Pair <Integer,Integer> p = cela.getCoordenades();
                valor = current.getValor(p.getKey(),p.getValue());
                --valor;
                while (valor == 8) {
                    current.setNumero(0, cela.getCoordenades());
                    current = bueno.pop();
                    --it;
                    cela = blanques[it];
                    p = cela.getCoordenades();
                    valor = current.getValor(p.getKey(),p.getValue());
                    --valor;
                    current.setNumero(0, cela.getCoordenades());
                }
                ++valor;
                current.setNumero(0, cela.getCoordenades());
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        elapsedTime /= 1000000;
        return elapsedTime;
    }

    //Aquesta funcio executa l'algorisme per resoldre kakuros pero recorre el vector de posibles numeros que poden haber-hi en una casella
    //blanca de forma inversa; retorna un objecte kakuro resolt.
    public Kakuro res_kakuro_v2() {
        Quadrat[] blanques = getBlanques();
        Vector<Quadrat> nombres = getNombres();
        
        Kakuro current = new Kakuro(this.Kakuro);
        Stack<Kakuro> bueno = new Stack<Kakuro>();
        bueno.push(current);

        int it = 0;
        Quadrat cela = blanques[it];
        int valor = 8;

        while (it < blanques.length) {
            int[] aux = new int[2];
            aux[0] =  valor+1;
            aux[1] = 0;
            cela.setNumeros(aux);

            int[] v;

            int nombreF = -1;
            Pair<Integer,Integer> negraF = cela.getSumaF();
            nombreF = current.getNombreF(negraF.getKey(), negraF.getValue());
            v = getRunF(bueno.peek(), negraF, nombres);
            int distF = v.length;
            boolean cumpleF = condicions_kakuro(nombreF, v, cela.getPrimerNum());
            boolean cumpleFS = condicions_kakuro_complert(nombreF, v, cela.getPrimerNum());

            int nombreC = -1;
            Pair<Integer,Integer> negraC = cela.getSumaC();
            nombreC = current.getNombreC(negraC.getKey(), negraC.getValue());
            v = getRunC(bueno.peek(), negraC, nombres);
            boolean cumpleC = condicions_kakuro(nombreC, v, cela.getPrimerNum());
            int distC = v.length;
            boolean cumpleCS = condicions_kakuro_complert(nombreC, v, cela.getPrimerNum());

            boolean[] prob = getPossibles(nombreF, nombreC, distF, distC);

            if (prob[valor] && ((cumpleC && cumpleF) || (cumpleFS && cumpleC) || (cumpleCS && cumpleF || (cumpleCS && cumpleFS)))) { 
                current.setNumero(valor+1, cela.getCoordenades());
                bueno.push(current);

                if (it < blanques.length) { 
                    ++it;
                    if(it != blanques.length)cela = blanques[it];
                    else if (it < 0) break; 
                }
                valor = 8;
            }
            else if (valor > 0) {
                --valor;
                while(!prob[valor] && valor > 0) --valor;
            }
            else {
                current = bueno.pop();
                --it;
                cela = blanques[it];
                Pair <Integer,Integer> p = cela.getCoordenades();
                valor = current.getValor(p.getKey(),p.getValue());
                --valor;
                while (valor == 0) {
                    current.setNumero(0, cela.getCoordenades());
                    current = bueno.pop();
                    --it;
                    cela = blanques[it];
                    p = cela.getCoordenades();
                    valor = current.getValor(p.getKey(),p.getValue());
                    --valor;
                    current.setNumero(0, cela.getCoordenades());
                }
                --valor;
                current.setNumero(0, cela.getCoordenades());
            }
        }
        return current;
    }

    //Aquesta funció retorna una matriu de String que conté els elements d'un kakuro sense comes.
    public String[][] getKakuro_sincomas() {
        String[][] aux = new String[this.Kakuro.length][(this.Kakuro[0].length/2) + 1];
        for (int i = 0; i < aux.length; ++i) {
            for (int j = 0; j < aux[0].length; ++j) {
                aux[i][j] = this.Kakuro[i][2*j];  
            }
        }
        return aux;
    }

    //Constructora: genera un objecte kakuro donat numero de files, numero de columnes i la seva dificultat.
    public Kakuro(int filas, int columnas, String diff) {
        this.mida_fila = filas;
        this.mida_col = columnas;
        this.difi = diff;
    }
    
    //Constructora: genera un objecte kakuro donat un kakuro en format estandard en el parametre del metode.
    public Kakuro(String part) {

        int aux = Character.getNumericValue(part.charAt(0));
        int aux2 = Character.getNumericValue(part.charAt(2));
        this.mida_fila = aux;
        this.mida_col = 2*aux2-1;
        this.Kakuro = new String[aux][2*aux2-1];
        int k = 4;
        for (int i = 0; i < aux && k < part.length(); ++i) {
            for (int j = 0; j < 2*aux2-1 && k < part.length(); ++j) {
                if (part.charAt(k) == '\n') ++k;
                if (part.charAt(k) == '*') {
                    this.Kakuro[i][j] = "*";
                    ++k;
                }
                else if (part.charAt(k) == ',' ) {
                    this.Kakuro[i][j] = ",";
                    ++k;
                }
                else if (part.charAt(k) == '?') {
                    this.Kakuro[i][j] = "?";
                    ++k;
                }
                else if (part.charAt(k) == 'C') {
                    this.Kakuro[i][j] = "C";
                    ++k;
                    this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                    ++k;
                    if (part.charAt(k) != 'F' && part.charAt(k) != ',' && part.charAt(k) != '\n') {
                        this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                        if (part.charAt(k) == 'F') {
                            this.Kakuro[i][j] += "F";
                            ++k;
                            this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                            ++k;
                            if (part.charAt(k) != ',') {
                                this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                                ++k;
                            }
                        }
                    } else if (part.charAt(k) == 'F') {
                        this.Kakuro[i][j] += "F";
                        ++k;
                        this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                        if (part.charAt(k) != ',') {
                            this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                            ++k;
                        }
                    }
                }
                else if (part.charAt(k) == 'F') {
                    this.Kakuro[i][j] = "F";
                    ++k;
                    this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                    ++k;
                    if (part.charAt(k) != ',') {
                        this.Kakuro[i][j] += Character.getNumericValue(part.charAt(k));
                        ++k;
                    }
                }
            }
        }
        ++k;
        String s = "";
        while (k < part.length() && part.charAt(k) != '\n') {
            s += part.charAt(k);
            ++k;
        }
        this.difi = s;
    }

    //Metodes que donat un taulell valida si el seu format es correcte.
    public boolean validar_taulell() {
        for (int i = 0; i < this.Kakuro.length; ++i) {
            System.out.println("alo");
            for (int j = 0; j < this.Kakuro[0].length; ++j) {
                if (this.Kakuro[i][j].equals("?")) {
                    if (i + 1 < this.mida_fila && i-1 >= 1) {
                        if (!this.Kakuro[i+1][j].equals("?") && !this.Kakuro[i-1][j].equals("?")) {
                            System.out.println("1");
                            System.out.println(i + " " + j);
                            return false;
                        } 
                    }
                    else {
                        if (i-1 >= 1) {
                            if (!this.Kakuro[i-1][j].equals("?")) {
                                System.out.println("2");
                                
                                return false;
                            }
                        }
                    }
                    if (j + 2 < this.mida_col && j-2 >= 1) {
                        if (!this.Kakuro[i][j-2].equals("?") && !this.Kakuro[i][j+2].equals("?")) {
                            System.out.println("3");
                            System.out.println(i + " " + j);
                            return false;
                        }
                    }
                    else {
                        if (j-2 >= 1) {
                            if (!this.Kakuro[i][j-2].equals("?")) {
                                System.out.println("4");
                                return false;
                            }
                        }
                    }
                    Pair<Integer, Integer> fil = getNegraFila(i, j);
                    Pair<Integer, Integer> col = getNegraCol(i, j);
                    if ((fil.getValue() == -1 && fil.getKey() == -1) || (col.getKey() == -1 && col.getValue() == -1)) {
                        System.out.println("5");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /*--------------------------------------------------------------------------------*/
                                //Generacio taulell buit
    /*--------------------------------------------------------------------------------*/
    
    //Metode que posa el taulell amb tot blanques i a la primera fila i columna negres.
    private void generar_def() {
        for (int i = 0; i < this.mida_fila; ++i) {
            ArrayList<Quadrat> aux = new ArrayList<>();
            this.M.add(aux);
            for (int j = 0; j < this.mida_col; ++j) {
                Quadrat q = new Quadrat();
                if (i == 0 || j == 0) q.setTipus(4); 
                else q.setTipus(0);
                aux.add(q);
            }
        }
    }
    
    //Metode que posa el taulell amb tot blanques i a la primera fila i columna negres de forma exhaustiva (amb tots els seus atributs a 0).
    private void setear_def() {
        for (int i = 0; i < this.mida_fila; ++i) {
            for (int j = 0; j < this.mida_col; ++j) {
                if (i == 0 || j == 0) this.M.get(i).get(j).setTipus(4);
                else this.M.get(i).get(j).setTipus(0);
                this.M.get(i).get(j).setPrimerNum(0);
                this.M.get(i).get(j).setSegonNum(0);
                this.M.get(i).get(j).setDistanciaC(0);
                this.M.get(i).get(j).setDistanciaF(0);
                this.M.get(i).get(j).setRestColumna(new TreeSet<Integer>());
                this.M.get(i).get(j).setRestFila(new TreeSet<Integer>());
            }
        }
    }
    
    //Metode que genera totes les restriccions de fila.
    private boolean generar_rest_files() {
        for (int i = this.mida_fila-1; i >= 0; --i) {
            int lo = 0;
            for (int j = this.mida_col-1; j >= 0; --j) {
                if (this.M.get(i).get(j).getTipus() == 0) ++lo;
                else {
                    if (lo > 1 && lo < 10) {
                        this.M.get(i).get(j).setTipus(1);            
                        this.M.get(i).get(j).setDistanciaF(lo);
                    }
                    else if (lo == 1 || lo > 9) return false;
                    lo = 0;
                }
            } 
        }
        return true;
    }
    
    //Metode que genera totes les restriccions de columna.
    private boolean generar_rest_columnes() {
        for (int i = this.mida_fila-1; i >= 0; --i) {
            int lo = 0;
            for (int j = this.mida_col-1; j >= 0; --j) {
                if (this.M.get(j).get(i).getTipus() == 0) ++lo;
                else {
                    if (lo > 1 && lo < 10) {
                        if (this.M.get(j).get(i).getTipus() == 4) this.M.get(j).get(i).setTipus(2);
                        else this.M.get(j).get(i).setTipus(3);
                        this.M.get(j).get(i).setDistanciaC(lo);
                    } 
                    else if (lo == 1 || lo > 9) return false;
                    lo = 0;
                }
            } 
        }
        return true;
    }

    //Genera un taulell buit de forma aleatoria donada la seva dificultat. Aquest metode no instancia el kakuro.
    private void generar2() {
        boolean c = false;
        int min = (this.mida_fila + this.mida_col - 1);
        if (this.difi == "facil") {
            int aux = (int)(Math.random() * (this.mida_fila * this.mida_col * 0.4 + min) - min + 1) + min;
            this.caselles_negres = aux;
        }
        else if (this.difi == "mitjana") {
            int aux = (int)(Math.random() * (this.mida_fila * this.mida_col * 0.3 + min) - min + 1) + min;
            this.caselles_negres = aux;
        }
        else {
            int aux = (int)(Math.random() * (this.mida_fila * this.mida_col * 0.2 + min) - min + 1) + min;
            this.caselles_negres = aux;
        }
        this.caselles_blanques = this.mida_fila * this.mida_col - this.caselles_negres;
        while (!c) {
            setear_def();
            int i = 0;
            while (i < this.caselles_negres - (this.mida_fila + this.mida_col - 1)) {
                Random r = new Random();
                int aux = r.nextInt(this.mida_fila);
                int aux2 = r.nextInt(this.mida_col);
                while(aux == 0 || aux2 == 0 || this.M.get(aux).get(aux2).getTipus() == 4) { 
                    aux = r.nextInt(this.mida_fila);
                    aux2 = r.nextInt(this.mida_col);
                }
                this.M.get(aux2).get(aux).setTipus(4);
                ++i;
                if (i < this.caselles_negres - (this.mida_fila + this.mida_col - 1) && aux != aux2) {
                    this.M.get(aux).get(aux2).setTipus(4);
                    ++i;
                }
            }
            if (generar_rest_files() && generar_rest_columnes()) c = true;
        }
    }
    
    //Genera un taulell buit de forma aleatoria donada la seva dificultat. Aquest metode si instancia el kakuro.
    public void generar() {
        generar_def();
        boolean c = false;
        int min = (this.mida_fila + this.mida_col - 1);
        if (this.difi == "facil") {
            int aux = (int)(Math.random() * (this.mida_fila * this.mida_col * 0.4 + min) - min + 1) + min;
            this.caselles_negres = aux;
        }
        else if (this.difi == "mitjana") {
            int aux = (int)(Math.random() * (this.mida_fila * this.mida_col * 0.3 + min) - min + 1) + min;
            this.caselles_negres = aux;
        }
        else {
            int aux = (int)(Math.random() * (this.mida_fila * this.mida_col * 0.2 + min) - min + 1) + min;
            this.caselles_negres = aux;
        }
        this.caselles_blanques = this.mida_fila * this.mida_col - this.caselles_negres;
        while (!c) {
            setear_def();
            int i = 0;
            while (i < this.caselles_negres - (this.mida_fila + this.mida_col - 1)) {
                Random r = new Random();
                int aux = r.nextInt(this.mida_fila);
                int aux2 = r.nextInt(this.mida_col);
                while(aux == 0 || aux2 == 0 || this.M.get(aux).get(aux2).getTipus() == 4) { 
                    aux2 = r.nextInt(this.mida_col);
                    aux = r.nextInt(this.mida_fila);
                }
                this.M.get(aux).get(aux2).setTipus(4);
                ++i;
                if (i < this.caselles_negres - (this.mida_fila + this.mida_col - 1) && aux != aux2) {
                    this.M.get(aux2).get(aux).setTipus(4);
                    ++i;
                }
            }
            if (generar_rest_files() && generar_rest_columnes()) c = true;
        }
    }
    
    //Metode que genera totes les restriccions de fila i replica les restriccions a la tira de caselles blanques
    //d'aquella restriccio.
    private void restriccions_fil() {
        SortedSet<Integer> s = new TreeSet<Integer>();
        int sum = 0;
        for (int i = this.mida_fila-1; i >= 0; --i) {
            for (int j = this.mida_col-1; j >= 0; --j) {
                if (this.M.get(i).get(j).getTipus() == 0) {
                    s.add(this.M.get(i).get(j).getPrimerNum());
                    sum += this.M.get(i).get(j).getPrimerNum();
                }
                else if (this.M.get(i).get(j).getTipus() == 1 || this.M.get(i).get(j).getTipus() == 3) {
                    if (this.M.get(i).get(j+1).getTipus() == 0) {
                        this.M.get(i).get(j).setPrimerNum(sum);
                        replicar_fil(i, j, s);
                    }
                    s.clear();
                    sum = 0;
                }
            }
            sum = 0;
        }
    }

    //Metode que recorre la tira de caselles blanques de fila i posa la restriccio de fila a cada una d'aquestes.
    private void replicar_fil(int fila, int columna , SortedSet<Integer> s) {
        for (int i = columna + 1; i < this.M.get(fila).get(columna).getDistanciaF() + columna + 1; ++i) {
            this.M.get(fila).get(i).setRestFila(new TreeSet<Integer>(s));
        }
    }

    //Metode que genera totes les restriccions de columna i replica les restriccions a la tira de caselles blanques
    //d'aquella restriccio.
    private void restriccions_col() {
        SortedSet<Integer> s = new TreeSet<Integer>();
        int sum = 0;
        for (int i = this.mida_fila-1; i >= 0; --i) {
            for (int j = this.mida_col-1; j >= 0; --j) {
                if (this.M.get(j).get(i).getTipus() == 0) {
                    s.add(this.M.get(j).get(i).getPrimerNum());
                    sum += this.M.get(j).get(i).getPrimerNum();
                }
                else if (this.M.get(j).get(i).getTipus() == 2 || this.M.get(j).get(i).getTipus() == 3) {
                    if (this.M.get(j+1).get(i).getTipus() == 0) {
                        this.M.get(j).get(i).setSegonNum(sum);
                        replicar_col(j, i, s);
                    }
                    s.clear();
                    sum = 0;
                }
            }
            sum = 0;
        }
    }

    //Metode que recorre la tira de caselles blanques de columna i posa la restriccio de fila a cada una d'aquestes.
    private void replicar_col(int fila, int columna , SortedSet<Integer> s) {
        for (int i = fila + 1; i < this.M.get(fila).get(columna).getDistanciaC() + fila + 1; ++i) {
            this.M.get(i).get(columna).setRestColumna(new TreeSet<Integer> (s));
        }
    } 
        
    /*--------------------------------------------------------------------------------*/
                                //Algorisme de Generacio 
    /*--------------------------------------------------------------------------------*/

    //Metode que recorre la tira de caselles blanques de fila i posa la restriccio de fila a cada una d'aquestes.
    private void actualitzar_restriccions_fila(int row , int column, SortedSet<Integer> s) {
        for (int j = column + 1; j < this.mida_col &&  j < this.M.get(row).get(column).getDistanciaF() + column + 1; ++j) {
            this.M.get(row).get(j).setRestFila(new TreeSet<Integer> (s));
        }
    }
    
    //Metode que recorre la tira de caselles blanques de columna i posa la restriccio de fila a cada una d'aquestes.
    private void actualitzar_restriccions_col(int row , int column, SortedSet<Integer> s) {
        for (int i = row + 1; i < this.mida_fila && i < this.M.get(row).get(column).getDistanciaC() + row + 1; ++i) {
            this.M.get(i).get(column).setRestColumna(new TreeSet<Integer>(s));
        }
    }
    
    //Metode que donada una posicio de una casella blanca busca la columna de la seva restriccio de fila.
    private int buscar_Rest_Fila(int i, int j) {
        int columna = j;
        while (this.M.get(i).get(columna).getTipus() == 0) --columna;
        return columna;
    }
    
    //Metode que donada una posicio de una casella blanca busca la columna de la seva restriccio de columa.
    private int buscar_Rest_Columna(int i, int j) {
        int fila = i;
        while (this.M.get(fila).get(j).getTipus() == 0) --fila;
        return fila;
    }

    //Metode que donada una posicio del taulell en casella blanca va per totes les caselles que formen part de la seva
    //tira de caselles i treu el valor que se li ha assignat a la casella anterior.
    private void treure_valor(int row, int column, int valor) {
        int i = buscar_Rest_Columna(row, column);
        int j = buscar_Rest_Fila(row, column);
        ++i;++j;
        while (j < this.mida_col && this.M.get(row).get(j).getTipus() == 0) {
            this.M.get(row).get(j).eliminar_de_f(valor);
            this.M.get(row).get(j).eliminar_de_c(valor);
            ++j;
        } 
        while (i < this.mida_fila && this.M.get(i).get(column).getTipus() == 0) {
            this.M.get(i).get(column).eliminar_de_c(valor);
            this.M.get(i).get(column).eliminar_de_f(valor);
            ++i;  
        }
    }

    //Metode que mira si la interseccio entre els dos sacs de la casella blanca
    private int creuament(int row , int column) {
        SortedSet<Integer> aux = new TreeSet<Integer>(this.M.get(row).get(column).getRestColumna());
        aux.retainAll(this.M.get(row).get(column).getRestFila());
        if (aux.size() == 1) {
            this.M.get(row).get(column).setPrimerNum(aux.first());
            return aux.first();
        } 
        return 0;
    }
    
    //Metode que treu per pantalla elkakuro generat acutalment.
    public void visualitzar() {
        for (int i = 0; i < this.mida_fila; ++i) {
            for(int j = 0; j < this.mida_col; ++j){
                if (j != 0)  System.out.print(",");
                if (this.M.get(i).get(j).getTipus() == 4)  System.out.print("*");
                //else if (this.M.get(i).get(j).getTipus() == 0) System.out.print(this.M.get(i).get(j).getPrimerNum());
                else if (this.M.get(i).get(j).getTipus() == 0) System.out.print("?");
                else if (this.M.get(i).get(j).getTipus() == 2) System.out.print("C" + this.M.get(i).get(j).getSegonNum());
                else if (this.M.get(i).get(j).getTipus() == 1) System.out.print("F" + this.M.get(i).get(j).getPrimerNum());
                else if (this.M.get(i).get(j).getTipus ()== 3) System.out.print("C" + this.M.get(i).get(j).getSegonNum() + "F" + this.M.get(i).get(j).getPrimerNum());
            }
            System.out.println();
        }
    }

    //Metode public de generacio de kakuros.
    public void generar_kakuro() {
        generar();
        inicialitzar3();
    }

    //Metode que passa de la matriu de Arraylist a un String[][] el kakuro acutal.
    public String[][] kakuro_presentacio() {
        String[][] result = new String[this.mida_fila][this.mida_col];
        for (int i = 0; i < this.mida_fila; ++i) {
            for (int j = 0; j < this.mida_col; ++j) {
                if (this.M.get(i).get(j).getTipus() == 4)  result[i][j] = "*";
                else if (this.M.get(i).get(j).getTipus() == 0) result[i][j] = "?";
                else if (this.M.get(i).get(j).getTipus() == 2) result[i][j] = ("C" + (Integer.toString(this.M.get(i).get(j).getSegonNum())));
                else if (this.M.get(i).get(j).getTipus() == 1) result[i][j] = ("F" + (Integer.toString(this.M.get(i).get(j).getPrimerNum())));
                else if (this.M.get(i).get(j).getTipus() == 3) result[i][j] = ("C" + (Integer.toString(this.M.get(i).get(j).getSegonNum())) + "F" + (Integer.toString(this.M.get(i).get(j).getPrimerNum())));
            } 
        }
        return result;
    }

    /*--------------------------------------------------------------------------------*/
                                    //NOU ALGORISME
    /*--------------------------------------------------------------------------------*/

    //Metode que donada una posicio del taulell mira les seves caselles anteriors blanques el seu sac de restriccio de fila.
    private void mirar_restriccions_columna(int fila_restrictiva, int fila_act, int columna, SortedSet<Integer> set) {
        for (int i = fila_restrictiva + 1; i < fila_act; ++i) {
            if (M.get(i).get(columna).getPrimerNum() != 0) set.remove(this.M.get(i).get(columna).getPrimerNum());
        }
    }

    //Metode que donada una posicio del taulell mira les seves caselles anteriors blanques el seu sac de restriccio de columna.
    private void mirar_restriccions_fila(int columna_restrictiva, int fila_act, int columna_act, SortedSet<Integer> set) {
        for (int j = columna_restrictiva + 1; j <= columna_act; ++j) {
            if (M.get(fila_act).get(j).getPrimerNum() != 0) set.remove(this.M.get(fila_act).get(j).getPrimerNum());
        }
    }

    //Metode auxiliar que posa els valors del 1 al nou al parametre de la funcio
    private void recarregar(SortedSet<Integer> s) {
        for (int i = 1; i <= 9; ++i) s.add(i);
    }

    //Metode que completa la suma de les files del kakuro actual.
    private void posar_sum_files() {
        int sum = 0;
        for (int i = this.mida_fila-1; i >= 0; --i) {
            for (int j = this.mida_col-1; j >= 0; --j) {
                if (this.M.get(i).get(j).getTipus() == 0) sum += this.M.get(i).get(j).getPrimerNum();
                else if (this.M.get(i).get(j).getTipus() == 1 || this.M.get(i).get(j).getTipus() == 3) {
                    if (this.M.get(i).get(j+1).getTipus() == 0) this.M.get(i).get(j).setPrimerNum(sum);
                    sum = 0;
                }
            }
            sum = 0;
        }
    }

    //Metode que completa la suma de les columnes del kakuro actual.
    private void posar_sum_col() {
        int sum = 0;
        for (int i = this.mida_fila-1; i >= 0; --i) {
            for (int j = this.mida_col-1; j >= 0; --j) {
                if (this.M.get(j).get(i).getTipus() == 0) sum += this.M.get(j).get(i).getPrimerNum();
                else if (this.M.get(j).get(i).getTipus() == 2 || this.M.get(j).get(i).getTipus() == 3) {
                    if (this.M.get(j+1).get(i).getTipus() == 0) this.M.get(j).get(i).setSegonNum(sum);
                    sum = 0;
                }
            }
            sum = 0;
        }
    }

    //Metode que buida els dos sacs de restriccio de totes les caselles blanques del kakuro actual.
    private void natejar() {
        for (int i = 0; i < this.mida_fila; ++i) {
            for (int j = 0; j < this.mida_col; ++j) {
                if (this.M.get(i).get(j).getTipus() == 0) this.M.get(i).get(j).setPrimerNum(0); 
                else if (this.M.get(i).get(j).getTipus() != 4) {
                    this.M.get(i).get(j).setRestColumna(new TreeSet<Integer>());
                    this.M.get(i).get(j).setRestFila(new TreeSet<Integer>());
                }
            }
        }
    }

    //Metodes que deixa el kakuro actual buit de numeros i restriccions deixant només la distribucio de blanques i negres.
    private void formatejar() {
        for (int i = this.mida_fila-1; i >= 0; --i) {
            for (int j = this.mida_col-1; j >= 0; --j) {
                this.M.get(i).get(j).setPrimerNum(0);
                this.M.get(i).get(j).setSegonNum(0);
                this.M.get(i).get(j).setRestColumna(new TreeSet<Integer>());
                this.M.get(i).get(j).setRestFila(new TreeSet<Integer>());
            }
        }
    }

    //Metode de generacio del kakuro
    public void inicialitzar3() {
        Random r = new Random();
        boolean aux = false;
        boolean generat = false;
        int cont_general = 0;
        SortedSet<Integer> fila = new TreeSet<>();
        SortedSet<Integer> poss = new TreeSet<>();
        boolean poss_acabades = false;
        
        while (!generat) {
            while (!generat && (cont_general < this.mida_col * this.mida_fila * 2)) {
                fila.clear();
                poss_acabades = false;
                if (r.nextBoolean()) { 
                    recarregar(poss);                                                                              //accedeixes per files.
                    for (int i = 1; i < this.mida_fila && !poss_acabades; ++i) {
                        for (int j = 1; j < this.mida_col && !poss_acabades; ++j) {
                            if (M.get(i).get(j).getTipus() != 0 || (j == this.mida_col - 1 && M.get(i).get(j).getTipus() == 0)) {
                                if (j == this.mida_col - 1 && M.get(i).get(j).getTipus() == 0) {
                                    mirar_restriccions_columna(buscar_Rest_Columna(i, j), i, j, poss);
                                    if (aux) {                                                                                            //minims
                                        while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.first())) poss.remove(poss.first());
                                        if (!poss.isEmpty()) {
                                            this.M.get(i).get(j).setPrimerNum(poss.first());
                                            fila.add(poss.first());
                                        }
                                    } else if (!aux) {                                                                                                 //maxims
                                        while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.last())) poss.remove(poss.last());
                                        if (!poss.isEmpty()) {
                                            this.M.get(i).get(j).setPrimerNum(poss.last());
                                            fila.add(poss.last());
                                        }
                                    }
                                    if (poss.isEmpty()) poss_acabades = true;
                                    recarregar(poss);
                                }
                                if (this.M.get(i).get(j-1).getTipus() == 0 && !poss_acabades) actualitzar_restriccions_fila(i, buscar_Rest_Fila(i, j-1), fila);
                                fila.clear();
                                aux = r.nextBoolean();
                            }
                            else {
                                
                                mirar_restriccions_columna(buscar_Rest_Columna(i, j), i, j, poss);
                                if (aux) {                                                                                            //minims
                                    while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.first())) poss.remove(poss.first());
                                    if (!poss.isEmpty()) {
                                        this.M.get(i).get(j).setPrimerNum(poss.first());
                                        fila.add(poss.first());
                                    }
                                } 
                                else if (!aux) {                                                                                                 //maxims
                                    while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.last())) poss.remove(poss.last());
                                    if (!poss.isEmpty()) {
                                        this.M.get(i).get(j).setPrimerNum(poss.last());
                                        fila.add(poss.last());
                                    }
                                }
                                if (poss.isEmpty()) poss_acabades = true;
                                recarregar(poss);
                            }
                        }
                    }
                    if (!poss_acabades) {
                        posar_sum_files();
                        restriccions_col();
                    }
                }
                else {
                    recarregar(poss);
                    for (int i = 1; i < this.mida_fila && !poss_acabades; ++i) {
                        for (int j = 1; j < this.mida_col && !poss_acabades; ++j) {
                            if (M.get(j).get(i).getTipus() != 0 || (j == this.mida_col - 1 && M.get(j).get(i).getTipus() == 0)) {
                                if (j == this.mida_col - 1 && M.get(j).get(i).getTipus() == 0) {
                                    mirar_restriccions_fila(buscar_Rest_Fila(j, i), j, i, poss);
                                    if (aux ) {                       
                                        while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.first())) poss.remove(poss.first());      //podriem fer retain???
                                        if (!poss.isEmpty()) {
                                            this.M.get(j).get(i).setPrimerNum(poss.first());
                                            fila.add(poss.first());
                                        }
                                    } else if (!aux){                                                                                            //maxims
                                        while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.last())) poss.remove(poss.last());
                                        if (!poss.isEmpty()) {
                                            this.M.get(j).get(i).setPrimerNum(poss.last());
                                            fila.add(poss.last());
                                        }
                                    }
                                    if (poss.isEmpty()) poss_acabades = true;
                                    recarregar(poss);
                                }
                                if (M.get(j-1).get(i).getTipus() == 0 && !poss_acabades) actualitzar_restriccions_col(buscar_Rest_Columna(j-1, i),  i, fila);
                                fila.clear();
                                aux = r.nextBoolean();
                            }   
                            else {
                                mirar_restriccions_fila(buscar_Rest_Fila(j, i), j, i, poss);
                                if (aux) {                       
                                    while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.first())) poss.remove(poss.first());      //podriem fer retain???
                                    if (!poss.isEmpty()) {
                                        this.M.get(j).get(i).setPrimerNum(poss.first());
                                        fila.add(poss.first());
                                    }
                                } else if (!aux) {                                                                                            //maxims
                                    while (!fila.isEmpty() && !poss.isEmpty() && fila.contains(poss.last())) poss.remove(poss.last());
                                    if (!poss.isEmpty()) {
                                        this.M.get(j).get(i).setPrimerNum(poss.last());
                                        fila.add(poss.last());
                                    }
                                }
                                if (poss.isEmpty()) poss_acabades = true;
                                recarregar(poss);
                            }
                        }
                    }
                    if (!poss_acabades) {
                        posar_sum_col();
                        restriccions_fil();
                    }
                }
                natejar();
                int cont = 0;
                    int i = 1; 
                    boolean tret = false;
                    while (i < this.mida_fila && !poss_acabades) {
                        int j = 1;
                        while (j < this.mida_col) {
                            if (this.M.get(i).get(j).getTipus() == 0 && this.M.get(i).get(j).getPrimerNum() == 0) {
                                int valor = creuament(i, j);
                                if (valor != 0) {
                                    tret = true;
                                    ++cont;
                                    treure_valor(i, j, valor);
                                }
                            }
                            if (!tret) ++j;
                            else {
                                i = 1; j = 1;
                                tret = false;
                            }
                        }
                        ++i;
                    }
                if (cont == this.caselles_blanques) generat = true;
                else  {
                    ++cont_general;
                    formatejar();
                }
            }
            if (generat) break;
            cont_general = 0;
            generar2();
        }
    }
    
    //Metode que passa el kakuro actual a un String[][].
    public String[][] convertir() {
        String[][] kak = new String[this.mida_fila][2*mida_col-1];
        //System.out.println(kak.length + " " + kak[0].length);
        for (int i = 0; i < kak.length; ++i) {
            for (int j = 0; j < kak[i].length; ++j) {
                if (j % 2 != 0) kak[i][j] = ",";
                else {
                    if (this.M.get(i).get(j/2).getTipus() == 4)  kak[i][j] = "*";
                    else if (this.M.get(i).get(j/2).getTipus() == 0) kak[i][j] = "?";
                    else if (this.M.get(i).get(j/2).getTipus() == 2) kak[i][j] = "C" + (Integer.toString(this.M.get(i).get(j/2).getSegonNum()));
                    else if (this.M.get(i).get(j/2).getTipus() == 1) kak[i][j] = "F" + (Integer.toString(this.M.get(i).get(j/2).getPrimerNum()));
                    else if (this.M.get(i).get(j/2).getTipus() == 3) kak[i][j] = "C" + (Integer.toString(this.M.get(i).get(j/2).getSegonNum())) + "F" + (Integer.toString(this.M.get(i).get(j/2).getPrimerNum()));
                }
            }
        }
        return kak;
    }

    /*--------------------------------------------------------------------------------*/
                                //COMUNICACIO AMB DOMINI
    /*--------------------------------------------------------------------------------*/

    //Metode que passa el kakuro actual al format string estandard
    public String get_string() {
        String resultat = this.mida_fila + "," + this.mida_col + "\n";
        for (int i = 0; i < this.mida_fila; ++i) {
            for(int j = 0; j < this.mida_col; ++j){
                if (this.M.get(i).get(j).getTipus() == 4)  resultat += "*";
                else if (this.M.get(i).get(j).getTipus() == 0) resultat += "?";
                else if (this.M.get(i).get(j).getTipus() == 2) resultat += "C" + (Integer.toString(this.M.get(i).get(j).getSegonNum()));
                else if (this.M.get(i).get(j).getTipus() == 1) resultat += "F" + (Integer.toString(this.M.get(i).get(j).getPrimerNum()));
                else if (this.M.get(i).get(j).getTipus() == 3) resultat += "C" + (Integer.toString(this.M.get(i).get(j).getSegonNum())) + "F" + (Integer.toString(this.M.get(i).get(j).getPrimerNum()));
                if (j != this.mida_col - 1) resultat += ",";
            }
            resultat += "\n";
        }
        resultat += this.difi;
        return resultat;
    }

    //Metode que passa el kakuro actual que te format de String[][] al format estandard.
    public String get_string2() {
        int aux = this.mida_col/2+1;
        String resultat = this.mida_fila + "," + aux + "\n";
        for (int i = 0; i < this.mida_fila; ++i) {
            for(int j = 0; j < this.mida_col; ++j){
                resultat += this.Kakuro[i][j];
            }
            resultat += "\n";
        }
        resultat += this.difi;
        return resultat;
    }

    //Metode que retorna la dificultat del kakuro del parametre implicit.
    public String get_dificultat() {
        return this.difi;
    }
}
