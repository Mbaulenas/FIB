package Domini;

import java.util.HashMap;
import java.util.Map;

//COMENTARI DE CLASSE:
//Representa la classe que fa referencia als usuaris de la aplicacio. Cada usuari conte el seu id (user_id) i la seva
//contrassenya.
public class Repositori_Usuaris {

    Map<String, String> repositori;

    //Creadora del repositori d'usuaris: assigna al parametre implicit  el mapa que es passa per parametre. Aquest es la
    //lectura del gestor dels usuaris que estan ja registrats anteriorment en cas que hi hagi.
    public Repositori_Usuaris(Map<String, String> m) {
        this.repositori = new HashMap<String, String>(m);
    };

    //Retorna la mida del repositori del parametre implicit.
    public int size() { return repositori.size(); }

    //Retorna un ture en cas que el "user" del parametre estigui en el repositori del parametre implicit. False en cas
    //contrari.
    public boolean check_register(String user, String pass) {
        if (!this.repositori.containsKey(user)) return false;
        return true;
    }

    //Retorna un ture en cas que el "user" del parametre estigui en el repositori i que "pass" coincideixi amb el
    //contingut d'aquest en el parametre implicit. False en cas contrari.
    public boolean check_loggin(String user, String pass) {
        if (this.repositori.containsKey(user)) {
            if (this.repositori.get(user).equals(pass)) return true;
        }
        return false;
    }

    //Afegeix una nova instancia en el repositori del parametre implicit amb una nova key valida i com a contingut
    //usuari.
    public void add_usuari(String user, String pass) {
        repositori.put(user, pass);
    }

}
