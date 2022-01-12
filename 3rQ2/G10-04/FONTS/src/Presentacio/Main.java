package Presentacio;
import Controladors.*;

public class Main {

    public static void main (String[] args) {
        Controlador_Domini CD = new Controlador_Domini();
        Controlador_Presentacio CP = new Controlador_Presentacio(CD);
    }

}
