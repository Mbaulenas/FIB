package Controladors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import Persistencia.*;
import javafx.util.Pair;

//COMENTARI DE CLASSE:
//Representa al controlador de la part de persitencia. Controla que les funcionalitats es criden de forma correcta i que
//es retornen els valors esperats. Conte instancies dels 4 gestors que tenim a la part de persistencia.

public class Controlador_Persistencia {

    static Gestor_Partides p;
    static Gestor_Usuaris u;
    static Gestor_Kakuro k;
    static Gestor_Ranking r;

    //Creadora del controlador de persistencia: Crea instancies de tots els gestors.
    public Controlador_Persistencia() {
        this.u = new Gestor_Usuaris();
        this.k = new Gestor_Kakuro();
        this.r = new Gestor_Ranking();
        this.p = new Gestor_Partides();

    }

    /*------------------------------------------------------------------------------*/
                                        /* PARTIDA */
    /*------------------------------------------------------------------------------*/

    //Funcio que enllaça el controlador de Domini amb el gestor de partides que fa referencia a guardar una partida.
    public void CP_guardar_partida(String usuari, String id_partida, String partida) {
        p.guardar_partida(usuari, id_partida, partida);
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de partides que fa referencia a una lectura de les
    //partides disponibles. Retorna el mapa amb la lectura de totes les partides disponibles.
    public Map<String, SortedMap<String, String>> CP_partides_disponibles() {
        return p.partides_disponibles();
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de partides que fa referencia a eliminar una partida amb
    //els parametres de la funcio que envia el controlador de domini.
    public void CP_eliminar_partida(String id, String usuari) {
        p.eliminar_partida(id, usuari);
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de partides que fa referencia a eliminar partiedes amb
    //el parametre de la funcio que envia el controlador de domini.
    public void CP_eliminar_partides (String usuari) {
        p.eliminar_partides(usuari);
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de partides que fa referencia a crear una carpeta ab el
    //string del usuari.
    public void CP_crear_carpeta_partides (String usuari) {
        p.crear_carpeta_partides(usuari);
    }

    /*------------------------------------------------------------------------------*/
                                        /* USUARI */
    /*------------------------------------------------------------------------------*/

    //Funcio que enllaça el controlador de Domini amb el gestor de usuaris que fa referencia a una lectura dels usuaris
    //disponibles.
    //Retorna el mapa amb la lectura de tots els usuaris disponibles.
    public Map<String, String> CP_llegir_usuaris() {
        ArrayList<String> s = new ArrayList<String>(u.llegir_usuaris());
        Map<String, String> aux = new HashMap<>(s.size());
        int i = 0;
        while (i < s.size()) {
            aux.put(s.get(i), s.get(++i));
            ++i;
        }
        return aux;
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de usuaris que fa referencia a el registre d'un nou
    //usuari.
    public void CP_register(String usuari, String pass) {
        u.register(usuari, pass);
    }

    
    /*------------------------------------------------------------------------------*/
                                        /*KAKURO*/
    /*------------------------------------------------------------------------------*/

    //Funcio que enllaça el controlador de Domini amb el gestor de usuaris que fa referencia a guardar un kakuro amb el
    //seu id corresponent.
    public void CP_guardar_kakuro(String id, String kakuro) {
        k.guardar_kakuro(id, kakuro);
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de usuaris i que fa referencia a la lectura de kakuros
    //disponibles en el gestor.
    //Retorna el llistat de kakuros disponibles en forma de Sorted Map.
    public SortedMap<String, String> CP_llegir_kakuros() {
        return k.llegir_kakuros();
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de usuaris i que fa referencia a la eliminacio del
    //repositori de kakuros (de tots els kakuros).
    public void CP_instanciar_repositori_kakuro() {
        k.instanciar();
    }

    //Funcio que enllaça el controlador de Domini amb el gestor de usuaris i que fa referencia a la eliminacio del
    //kakuro de la id correponent del parametre de la funcio.
    public void CP_eliminar_kakuro(String id) {
        k.eliminar_kakuro(id);
    }

    /*------------------------------------------------------------------------------*/
                                        /*RANKING*/
    /*------------------------------------------------------------------------------*/

    //Retorna les dades del Ranking.txt en el format correcte per a carregar-les a la classe Ranking.
    public Map<String, Map<String, Pair<Double, Double>>> CP_load_rank() {
        return r.load();
    }

    //Elimina el Ranking.txt existent i en crea un de nou.
    public void CP_restart_rank() {
        r.restart_rank();
        this.r = new Gestor_Ranking();
    }

    //Envia les dades en format correcte per a guardar-les al Ranking.txt.
    public void CP_save(String s) {
        r.save(s);
    }
}
