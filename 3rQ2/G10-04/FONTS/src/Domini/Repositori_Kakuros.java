package Domini;

import java.util.SortedMap;
import java.util.TreeMap;

//COMENTARI DE CLASSE:
//Representa el repositori logic que gestiona tot el que va relacionat amb els kakuros. La classe nomes tindra una unica
//instancia.
//El repositori es representa com un SortedMap de strings, la clau del primer map es el id de un kakuro i el contingut
//es la el kakuro en format estandard.
public class Repositori_Kakuros {

    SortedMap<String, String> map;

    //Creadora del repositori de kakuros: assigna al parametre implicit  el mapa que es passa per parametre. Aquest es
    //la lectura del gestor dels kakuros que han estat afegits anteriorment en la aplicacio.
    public Repositori_Kakuros(SortedMap<String, String> m) {
        map = new TreeMap<String, String>(m);
    };

    //Retorna el kakuro en format estandard del map del parametre implicit que te com a key el id del parametre.
    public String get_kakuro(String id) {
        return map.get(id);
    }

    //Retorna la mida del repositori del parametre implicit.
    public int size() {
        return map.size();
    }

    //Retorna un Array de strings que conte totes les id del kakuros que estan en mapa del parametre implicit
    public String[] llistar_kakuros() {
        return (String[])this.map.keySet().toArray(new String[map.size()]);
    }

    //Guarda el kakuro en format estandard del parametre del metode en una nova instancia del mapa del parametre
    //implicit i retorna la nova id que te en aquest.
    public String setkakuro(String kakuro) {
        int aux;
        if (map.isEmpty()) aux = 0;
        else {
            String s = map.lastKey();
            aux = Integer.parseInt(s);
            ++aux;
        }
        map.put(Integer.toString(aux), kakuro);
        return map.lastKey();
    }

    //Elimina totes les instancies (kakuros) del mapa del parametre implicit.
    public void instanciar() {
        map.clear();
    }

    //Elimina la instancia (kakuro) del mapa del parametre implicit amb la key igual al id del parametre del metode.
    public void eliminar_kakuro(String id) {
        map.remove(id);
    }
}
