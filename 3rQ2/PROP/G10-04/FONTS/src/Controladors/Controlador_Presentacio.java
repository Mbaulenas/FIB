package Controladors;
import Presentacio.*;

import javax.swing.*;

//COMENTARI DE CLASSE:
//Classe que fa de intermediari entre les vistes i el controlador de domini.

public class Controlador_Presentacio {

    private Controlador_Domini CD;
    private String usuari;
    private String contrasenya;
    private String id_partida;
    private String id_kakuro;
    private String[][] solucio;
    private String[][] kakuro;
    private int temps;
    private String dificultat;

    //Uneix les dades de una partida (el kakuro del usuari, la dificultat, el temps emprat i la solucio correcta
    //del kakuro) en un unic String.
    private String concatenar_partida() {
        String resultat = this.kakuro.length + "," + this.kakuro[0].length + "\n";
        for (int i = 0; i < this.kakuro.length; ++i) {
            for (int j = 0; j < this.kakuro[i].length; ++j) {
                if (j != 0) resultat += ",";
                resultat += this.kakuro[i][j];
            }
            resultat += "\n";
        }
        resultat += dificultat + "\n";
        resultat += temps + "\n";
        for (int i = 0; i < this.kakuro.length; ++i) {
            for (int j = 0; j < this.kakuro[i].length; ++j) {
                if (j != 0) resultat += ",";
                    resultat += this.solucio[i][j];
            }
            if (i != this.kakuro.length - 1) resultat += "\n";
        }
        return resultat;
    }

    //Seteja el valor de les variables privades dificultat, kakuro, solucio i temps a partir del
    //controlador de domini
    private void preparar_partida() {
        this.dificultat = CD.CD_get_dificultat();
        this.kakuro = CD.CD_get_kakuro_actual();
        this.solucio = CD.CD_get_kakuro_resolt();
        this.temps = CD.CD_get_time();
    }

    public Vista_Accedir frameAccedir;
    public Vista_Menu frameMenu;
    public Vista_Ranquing frameRanquing;
    public Vista_Partida framePartida;

    //Creadora de Controlador_Presentacio: inicialitza CD i inicialitza la Vista_Accedir.
    public Controlador_Presentacio(Controlador_Domini CD){
        this.CD = CD;
        frameAccedir = new Vista_Accedir(this);

        frameAccedir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAccedir.setVisible(true);
    }

    /*------------------------------------------------------------------------------*/
                                        /* USUARI */
    /*------------------------------------------------------------------------------*/

    //Dona usuari i contrasenya els valors user i pass respectivament.
    //Si CD_loggin(user, pass) retorna true vol dir que sha loguejat correctament i canvia al frameMenu.
    public boolean login(String user, String pass){
        this.usuari = user;
        this.contrasenya = pass;
        boolean logged = CD.CD_loggin(user, pass);
        if (logged) {
            frameMenu = new Vista_Menu(this);
            frameAccedir.setVisible(false);
            frameMenu.setVisible(true);
        }
        return logged;
    }

    //Registra un nou usuari amb usuari = user i contrasenya = pass.
    public boolean register(String user, String pass){
        return CD.CD_register(user,pass);
    }

    //Deixa a usuari i contrasenya sense valor i torna al frameAccedir per tancar sesió.
    public void logout(){
        this.usuari = "";
        this.contrasenya = "";
        frameMenu.setVisible(false);
        frameAccedir = new Vista_Accedir(this);
        frameAccedir.setVisible(true);
    }

    /*------------------------------------------------------------------------------*/
                                        /* MENU */
    /*------------------------------------------------------------------------------*/

    //Canvia del frameRanquing o del framePartida al frameMenu.
    public void tornar_menu(String s) {
        if (s.equals("Ranquing")) {
            frameRanquing.setVisible(false);
        }
        else if (s.equals("Partida")) {
            framePartida.setVisible(false);
        }
        frameMenu.setVisible(true);
    }

    //Crea el framePartida i deixa de mostrar el frameMenu per mostrar framePartida.
    public void jugar() {
        framePartida = new Vista_Partida(this);
        frameMenu.setVisible(false);
        framePartida.setVisible(true);
    }

    //Crea el frameRanquing i deixa de mostrar el frameMenu per mostrar frameRanquing.
    public void visualitzar_ranquing() {
        frameRanquing = new Vista_Ranquing(this);
        frameMenu.setVisible(false);
        frameRanquing.setVisible(true);
    }

    /*------------------------------------------------------------------------------*/
                                        /* PARTIDA */
    /*------------------------------------------------------------------------------*/

    //Genera un nou kakuro amb n files, n columnes i dificultat dif, el guarda i en crea una partida.
    //Actualitza les variables privades de la classe ficant id_partida = "nova" ja que encara no disposa
    //dun identificador de partida.
    public void nou_kakuro(int n, String dif) {
        String aux = CD.CD_generar_kakuro(n, dif);
        this.id_kakuro = CD.CD_guardar_kakuro(aux);
        CD.CD_crear_partida(id_kakuro);
        preparar_partida();
        this.id_partida = "nova";
    }

    //Retorna el kakuro amb les respostes del usuari
    public String[][] carregar_kakuro() {
        return kakuro;
    }

    //Retorna el kakuro amb la solucio correcta
    public String[][] carregar_solucio() {
        return solucio;
    }

    //Guarda la partida del usuari. Actualitza el temps i la informacio del contingut de kakuro.
    //Tindra en compte si es una partida nova o no.
    public String guardar_partida(int t, String[][] kak_user){
        this.kakuro = kak_user;
        this.temps = t;
        if (this.id_partida.equals("nova")) {
            this.id_partida = CD.CD_guardar_partida_nova(usuari, concatenar_partida());
        }
        else {
            CD.CD_guardar_partida_existent(usuari, id_partida, concatenar_partida());
        }
        return id_partida;
    }

    //Retorna un array de String amb els identificadors dels kakuros del repositori.
    public String[] llistar_kakuros(){
        return CD.CD_llistar_kakuros();
    }

    //Canvia id_kakuro per id del nou kakuro, crea una partida amb el kakuro corresponent i la prepara
    //id_partida = "nova" ja que no disposa didentificador.
    public void jugar_kakuro(String id) {
        this.id_kakuro = id;
        CD.CD_crear_partida(id_kakuro);
        preparar_partida();
        this.id_partida = "nova";
    }

    //Elimina el kakuro amb identificador id del repositori de kakuros.
    public void eliminar_kakuro(String id) {
        CD.CD_eliminar_kakuro(id);
    }

    //Elimina tots els kakuros del repositori de kakuros.
    public void reiniciar_repositori() {
        CD.CD_instanciar_repositori_kakuro();
    }

    //Retorna un array deString amb els identificadors de les partides começades per lusuari usuari.
    public String[] llistar_partides(){
         return CD.CD_llistar_partides(usuari);
    }

    //Actualitza el valor de id_partida amb el de la nova partida id, carrega la partida del usuari usuari i
    //prepara la partida
    public void jugar_partida(String id) {
        this.id_partida = id;
        CD.CD_carregar_partida(id, usuari);
        preparar_partida();
    }

    //Elimina la partida amb identificador id del repositori de partides del usauri usuari.
    public void eliminar_partida(String id) {
        CD.CD_eliminar_partida(id, usuari);
    }

    //Elimina totes les partides del repositori de partides del usuari usuari.
    public void eliminar_partides_mitjes() {
        CD.CD_eliminar_partides(usuari);
    }

    //Retorna temps.
    public int getTemps(){
        return this.temps;
    }

    //Retorna true el kakuro proposat per lusuari cont es valid.
    public boolean proposar_kakuro(String cont) {
        return CD.CD_proposta_usuari(cont);
    }

    //Guarda el kakuro kak i retorna el seu identificador.
    public String guardar_kakuro(String kak) {
        this.id_kakuro = CD.CD_guardar_kakuro(kak);
        return id_kakuro;
    }

    //Crea una nova partida a partir del kakuro proposat per lusuari
    public void nova_proposta() {
        CD.CD_crear_partida(id_kakuro);
        preparar_partida();
        this.id_partida = "nova";
    }

    //Retorna el temps que triga el programa en resoldre el kakuro amb identificador id_kakuro
    public long maquina_resol(){
        return CD.CD_maquina_resol(id_kakuro);
    }

    /*------------------------------------------------------------------------------*/
                                    /* RANQUING */
    /*------------------------------------------------------------------------------*/

    //Actualitza els dos tipus de ranquing amb el temps emprat per l'usuari en resoldre el kakuro.
    public void actualizar_ranquing(int t) {
        CD.CD_add_rank(usuari, dificultat, t);
    }

    //Elimina tot el ranquing actual deixantlo buit.
    public void reiniciar_ranquing() {
        CD.CD_reiniciar_ranquing();
    }

    //Retorna un string amb el usuari de la posició pos del ranquing de temps de dificultat dif.
    public String user_ranquing_temps(int pos, String dif){
        return CD.CD_user_ranquing_temps(pos, dif);
    }

    //Retorna un string amb el temps de la posició pos del ranquing de temps de dificultat dif.
    public String temps_ranquing(int pos, String dif){
        return CD.CD_temps_ranquing(pos, dif);
    }

    //Retorna un string amb el usuari de la posició pos del ranquing de nombre de dificultat dif.
    public String user_ranquing_nombre(int pos, String dif){
        return CD.CD_user_ranquing_nombre(pos, dif);
    }

    //Retorna un string amb el nombre de la posició pos del ranquing de temps de dificultat dif.
    public String nombre_ranquing(int pos, String dif){
        return CD.CD_nombre_ranquing(pos, dif);
    }
}