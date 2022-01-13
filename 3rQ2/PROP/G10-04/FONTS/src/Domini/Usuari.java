package Domini;

//COMENTARI DE CLASSE:
//Representa la classe que fa referencia als usuaris de la aplicacio. Cada usuari conte el seu id (user_id) i la seva
//contrassenya.

public class Usuari {

    String user_id;
    String password;

    //Creadora de Usuari: posa valors als parametres implicits user_id i password amb els parametres corresponents.
    public Usuari(String usuari, String pass){
        this.user_id = usuari;
        this.password = pass;
    }

    //Retorna la contrassenya de l'usuari del parametre implicit.
    public String get_pass() {
        return this.password;
    }

    //Posa valor al parametre implicit password amb el valor del parametre de la funcio
    public void set_pass(String pass) {
        this.password = pass;
    }

    //Retorna el id de usuari del parametre implicit.
    public String get_user_id(){
        return this.user_id;
    }
}
