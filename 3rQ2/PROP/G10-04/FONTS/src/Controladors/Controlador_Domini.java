package Controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import Domini.*;
import javafx.util.Pair;

//COMENTARI DE CLASSE:
//Representa al controlador de la part de Domini. Controla que les funcionalitats es criden de forma correcta i que es
//retornen els valors esperats.
//Conte instancies de kakuro partida usuari i dels repositoris de kakuro, usuaris partides i ranking.
public class Controlador_Domini {

    static Controlador_Persistencia p;

    Kakuro kakuro;
    Usuari usuari;
    Partida partida;
    Ranking ranking;

    Repositori_Kakuros repo_k;
    Repositori_Usuaris repo_u;
    Repositori_Partides repo_p;

    //Creadora del controlador de Domini: Crea instancies de tots els paràmetres de la classe.
    public Controlador_Domini() {
        p = new Controlador_Persistencia();
        this.repo_u = new Repositori_Usuaris(new HashMap<String, String>(p.CP_llegir_usuaris()));
        this.repo_k = new Repositori_Kakuros(p.CP_llegir_kakuros());
        this.repo_p = new Repositori_Partides(p.CP_partides_disponibles());
        this.ranking = new Ranking(p.CP_load_rank());
    };

    /*------------------------------------------------------------------------------*/
                                    /* PARTIDA */
    /*------------------------------------------------------------------------------*/

    //Funcio que enllaça el controlador presentacio amb la classe partida i kakuro que fa referencia a la creacio d'una
    //partida.
    public void CD_crear_partida(String id_kak) {
        this.kakuro = new Kakuro(this.repo_k.get_kakuro(id_kak));
        this.partida = new Partida(this.kakuro);
    }

    //Retorna la dificultat de la partida del parametre implicit.
    public String CD_get_dificultat() {
        return this.partida.get_dificultat();
    }

    //Retorna el temps de la partida del parametre implicit.
    public int CD_get_time() {
        return this.partida.get_time();
    }

    //Retorna el kakuro actual a mitges del parametre implicit.
    public String[][] CD_get_kakuro_actual() {
        return this.partida.get_actual();
    }

    //Retorna el kakuro resolt de la partida del parametre implicit.
    public String[][] CD_get_kakuro_resolt() {
        return this.partida.get_resolt();
    }

    //Funcio que enllaça el controlador presentacio amb la creacio de un nou kakuro proposat per un usuari.
    public boolean CD_proposta_usuari(String proposta) {
        Kakuro kak = new Kakuro(proposta);
        Kakuro kuk = new Kakuro(proposta);
        if (kak.validar_taulell()) {
            kak = kak.res_kakuro();
            kak.printKakuro();
            System.out.println(" ");
            kuk = kuk.res_kakuro_v2();
            kuk.printKakuro();
            boolean b = true;
            for(int i = 0; b && i < kak.getKakuro().length; i++){
                for(int j = 0; b && j < kak.getKakuro()[0].length; j++){
                    if(!kak.getKakuro()[i][j].equals(kuk.getKakuro()[i][j])){
                        return false;
                    }
                }
            }
            return true;
        } else return false;
    }

    //Funcio que enllaça el controlador presentacio amb la lectura de una nova partida ja existent dins del repositori
    //de partides.
    public void CD_carregar_partida(String id, String user) {
        this.partida = new Partida(this.repo_p.carregar_partida(id, user));
    }

    //Funcio que enllaça el controlador presentacio amb la lectura de totes les partides ja existent de l'usuari del
    //parametre de la funcio.
    public String[] CD_llistar_partides(String usuari) {
        return this.repo_p.llistar_partides(usuari);
    }

    //Funcio que enllaça els 3 controladors amb la escriptura de una partida nova al repositori de partides i al gestor
    //de partides.
    public String CD_guardar_partida_nova(String id_user, String partida) {
        String id_partida = this.repo_p.guardar_partida_nova(id_user, partida);
        p.CP_guardar_partida(id_user, id_partida, partida);
        return id_partida;
    }

    //Funcio que enllaça els 3 controladors amb la escriptura de una partida existent al repositori de partides i al
    //gestor de partides.
    public void CD_guardar_partida_existent(String id_user, String id_partida, String partida) {
        this.repo_p.guardar_partida_existent(id_user, id_partida, partida);
        p.CP_guardar_partida(id_user, id_partida, partida);
    }

    //Funcio que enllaça els 3 controladors amb la eliminacio de una partida amb identificador id de l'usuari user.
    public void CD_eliminar_partida(String id, String user) {
        repo_p.eliminar_partida(id, user);
        p.CP_eliminar_partida(id, user);
    }

    //Funcio que enllaça els 3 controladors amb la eliminacio de totes les partides de l'usuari user.
    public void CD_eliminar_partides(String usuari) {
        repo_p.eliminar_partides(usuari);
        p.CP_eliminar_partides(usuari);
    }


    /*------------------------------------------------------------------------------*/
                                    /* USUARI */
    /*------------------------------------------------------------------------------*/

    //Funcio que enllaça el controlador presentacio amb la lectura i validacio de la existencia de un usuari en el
    //repositori de usuaris.
    public boolean CD_loggin(String user, String pass) {
        if (repo_u.check_loggin(user, pass)) {
            this.usuari = new Usuari(user, pass);
            return true;
        }
        return false;
    }

    //Funcio que enllaça el controlador presentacio amb la lectura i validacio de la existencia de un usuari en el
    //repositori de usuaris.
    public boolean CD_register(String user, String password) {
        if (!this.repo_u.check_register(user, password)) {
            this.repo_u.add_usuari(user, password);
            this.usuari = new Usuari(user, password);
            p.CP_register(user, password);
            p.CP_crear_carpeta_partides(user);
            this.repo_p.usuari_nou(user);
            return true;
        } else {
            return false;
        }
    }

    /*------------------------------------------------------------------------------*/
                                    /* KAKURO */
    /*------------------------------------------------------------------------------*/

    //Funcio que enllaça els 3 controladors amb la eliminacio del kakuro amb identificador id.
    public void CD_eliminar_kakuro(String id) {
        repo_k.eliminar_kakuro(id);
        p.CP_eliminar_kakuro(id);
    }

    //Funcio que enllaça el controlador de presentacio amb el repositori de kakuros amb la lectura de tots els kakuros
    //disonibles.
    public String[] CD_llistar_kakuros() {
        String[] aux = repo_k.llistar_kakuros();
        return aux;
    }

    //Funcio que enllaça els 3 controladors amb guardar un kakuro nou al repositori de kakuros i al gestor de kakuros.
    public String CD_guardar_kakuro(String kak) {
        String id = repo_k.setkakuro(kak);
        p.CP_guardar_kakuro(id, kak);
        return id;
    }

    //Funcio que enllaça el controlador de presentacio amb la generacio d'un nou kakuro aleatori.
    public String CD_generar_kakuro(int columnas, String difi) {
        this.kakuro = new Kakuro(columnas, columnas, difi);
        this.kakuro.generar_kakuro();
        String aux = this.kakuro.get_string();
        return aux;
    }

    //Funcio que enllaça els 3 controladors amb la eliminacio de tots els kakuros del repositori.
    public void CD_instanciar_repositori_kakuro() {
        this.repo_k.instanciar();
        p.CP_instanciar_repositori_kakuro();
    }

    public long CD_maquina_resol(String id) {
        Kakuro k = new Kakuro(this.repo_k.get_kakuro(id));
        return k.res_kakuro_temps();
    }

    /*------------------------------------------------------------------------------*/
                                    /* RANQUING */
    /*------------------------------------------------------------------------------*/

    //Elimina el Ranking anterior i en crea un de nou buit.
    public void CD_reiniciar_ranquing() {
        p.CP_restart_rank();
        this.ranking = new Ranking(p.CP_load_rank());
    }

    public void CD_add_rank(String us, String ds, int t) {
        Double d = Double.valueOf(t);
        ranking.actualitzar_ranking(us, ds, d);
        CD_save();
    }

    //Retorna l'usuari que esta en la posicio "pos" del ranquing per temps mitja.
    public String CD_user_ranquing_temps(int pos, String dif){
        SortedSet<Pair<String, Double>> rankt = new TreeSet<>(ranking.mostra_ranking_temps(dif));
        Integer i = 0;
        for (Pair<String, Double> p : rankt) {
            if(i == pos) {
                return p.getKey();
            }
            ++i;
        }
        return "";
    }

    //Retorna el temps mitja que esta en la posicio "pos" del ranquing per temps mitja.
    public String CD_temps_ranquing(int pos, String dif){
        SortedSet<Pair<String, Double>> rankt = new TreeSet<>(ranking.mostra_ranking_temps(dif));
        Integer i = 0;
        String ps;
        for (Pair<String, Double> p : rankt) {
            if(i == pos) {
                ps = p.getValue().toString();
                return ps;
            }
            ++i;
        }
        return "";
    }

    //Retorna l'usuari que esta en la posicio "pos" del ranquing per nombre de partides acabades.
    public String CD_user_ranquing_nombre(int pos, String dif){
        SortedSet<Pair<String, Double>> rankt = new TreeSet<>(ranking.mostra_ranking_nombre(dif));
        Integer i = 0;
        for (Pair<String, Double> p : rankt) {
            if(i == pos) return p.getKey();
            ++i;
        }
        return "";
    }

    //Retorna el nombre de partides acabades que esta en la posicio "pos" del ranquing per nombre de partides acabades.
    public String CD_nombre_ranquing(int pos, String dif){
        SortedSet<Pair<String, Double>> rankt = new TreeSet<>(ranking.mostra_ranking_nombre(dif));
        Integer i = 0;
        String ps;
        for (Pair<String, Double> p : rankt) {
            if(i == pos) {
                ps = p.getValue().toString();
                return ps;
            }
            ++i;
        }
        return "";
    }

    //Reinicia el Ranking.txt i envia les dades en el format correcte per a escriure-les al nou Ranking.txt.
    public void CD_save() {
        ArrayList<String> v = new ArrayList<String>(ranking.guardar());
        p.CP_restart_rank();
        for (int i=0; i < v.size(); ++i) {
            p.CP_save(v.get(i));
        }
    }
}